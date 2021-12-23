package com.juntai.wisdom.explorsive.base;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.CalendarUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.main.mine.SearchPresent;
import com.juntai.wisdom.explorsive.utils.StringTools;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述   搜索控件
 * @CreateDate: 2020/3/15 9:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/15 9:18
 */
public abstract class BaseSearchFragment extends BaseAppFragment<SearchPresent> implements View.OnClickListener,
        IView {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int selectedStatusId = 0;
    private SearchView mSearchContentSv;
    /**
     * 搜索
     */
    private TextView mSearchTv;

    protected ImageView mFilterIv;
    /**
     * 请选择地区
     */
    private TextView mSelectStatusTv;
    /**
     * 请选择年度
     */
    private TextView mStartTimeTv, mEndTimeTv;
    /**
     * 开始搜索
     */
    private TextView mStartSearchByConditionTv;
    private ConstraintLayout mSearchByConditionCl;

    private boolean showFilterView = false;//展示筛选控件
    private RecyclerView mResultRv;
    protected ConstraintLayout mSearchTopCl;

    protected SmartRefreshLayout mSmartrefreshlayout;


    /**
     * 获取搜索结果的适配器
     *
     * @return
     */
    protected abstract BaseQuickAdapter getSearchResultAdapter();


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    protected SearchPresent createPresenter() {
        return new SearchPresent();
    }

    @Override
    protected void initView() {
        mSearchTopCl = getView(R.id.search_top_cl);
        mSearchContentSv = (SearchView) getView(R.id.search_content_sv);
        mSearchTv = (TextView) getView(R.id.search_tv);
        mSearchTv.setOnClickListener(this);
        mFilterIv = (ImageView) getView(R.id.filter_iv);
        mFilterIv.setOnClickListener(this);
        mSelectStatusTv = (TextView) getView(R.id.select_order_status_tv);
        mSelectStatusTv.setOnClickListener(this);
        mStartTimeTv = (TextView) getView(R.id.select_start_time_tv);
        mEndTimeTv = (TextView) getView(R.id.select_end_time_tv);
        mResultRv = getView(R.id.recyclerview);
        mSmartrefreshlayout = getView(R.id.smartrefreshlayout);
        getBaseActivity().initRecyclerview(mResultRv, getSearchResultAdapter(), LinearLayoutManager.VERTICAL);
        mStartTimeTv.setOnClickListener(this);
        mEndTimeTv.setOnClickListener(this);
        mStartSearchByConditionTv = (TextView) getView(R.id.start_search_by_condition_tv);
        mStartSearchByConditionTv.setOnClickListener(this);
        mSearchByConditionCl = (ConstraintLayout) getView(R.id.search_by_condition_cl);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_tv:
                clearData();
                showFilterView = true;
                mStartSearchByConditionTv.performClick();


                break;
            case R.id.filter_iv:
                showFilterView = !showFilterView;
                mSearchByConditionCl.setVisibility(showFilterView ? View.VISIBLE : View.GONE);

                mSearchTv.setVisibility(showFilterView ? View.GONE : View.VISIBLE);
                break;
            case R.id.select_order_status_tv:

                PickerManager.getInstance().showOptionPicker(mContext, getStatusList(),
                        new PickerManager.OnOptionPickerSelectedListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                String status = getStatusList().get(options1);
                                selectedStatusId = options1 + 1;
                                mSelectStatusTv.setText(status);
                            }
                        });
                break;
            case R.id.select_start_time_tv:
                // : 2021-12-20 选择开始时间
                PickerManager.getInstance().showTimePickerView(mContext, new boolean[]{true, true, true, true, true, false}, "选择开始时间", new PickerManager.OnTimePickerTimeSelectedListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String startTime = sdf.format(date);
                        mStartTimeTv.setText(startTime);
                    }
                });
                break;
            case R.id.select_end_time_tv:
                // : 2021-12-20 选择结束时间
                if (TextUtils.isEmpty(mStartTimeTv.getText().toString().trim())) {
                    ToastUtils.toast(mContext, "请先选择开始时间");
                    return;
                }
                PickerManager.getInstance().showTimePickerView(mContext, new boolean[]{true, true, true, true, true, false}, "选择结束时间", new PickerManager.OnTimePickerTimeSelectedListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String endTime = sdf.format(date);
                        // : 2021-12-20 和开始时间比较
                        if (!CalendarUtil.compareTimes(mStartTimeTv.getText().toString().trim(), endTime, "yyyy-MM-dd HH:mm:ss")) {
                            ToastUtils.toast(mContext, "结束时间不能早于开始时间");
                            return;
                        }
                        mEndTimeTv.setText(endTime);
                    }
                });
                break;
            case R.id.start_search_by_condition_tv:
                mFilterIv.performClick();
                requestData();
                clearData();
                break;
        }
    }

    protected abstract List<String> getStatusList();

    /**
     * 初始化数据
     */
    protected void clearData() {
        selectedStatusId = 0;
        mSelectStatusTv.setText("");
        mStartTimeTv.setText("");
        mEndTimeTv.setText("");
    }

    /**
     * 请求数据
     */
    protected abstract void requestData();


    @Override
    protected void lazyLoad() {
        clearData();
        requestData();
    }


    /**
     * 搜索的回调
     */
    public interface OnSearchCallBack {
        void onSearch(String textContent);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        getBaseActivity().getViewFocus(mSmartrefreshlayout);
        mSmartrefreshlayout.finishRefresh();
        mSmartrefreshlayout.finishLoadMore();
    }

    @Override
    public void onError(String tag, Object o) {
        getBaseActivity().getViewFocus(mSmartrefreshlayout);
        mSmartrefreshlayout.finishRefresh();
        mSmartrefreshlayout.finishLoadMore();
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    protected RequestBody getRequestBody() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("mobile", UserInfoManager.getMobile())
                .add("departmentId", String.valueOf(UserInfoManager.getDepartmentId()))
                .add("token", UserInfoManager.getUserToken());
        String content = mSearchContentSv.getQuery().toString();
        if (StringTools.isStringValueOk(content)) {
            builder.add("keyword", content);
        }
        if (selectedStatusId > 0) {
            builder.add("stat", String.valueOf(selectedStatusId));
        }
        if (StringTools.isStringValueOk(mStartTimeTv.getText().toString().trim())) {
            builder.add("startTime", mStartTimeTv.getText().toString().trim());
        }
        if (StringTools.isStringValueOk(mEndTimeTv.getText().toString().trim())) {
            builder.add("endTime", mEndTimeTv.getText().toString().trim());
        }

        return builder.build();
    }

}
