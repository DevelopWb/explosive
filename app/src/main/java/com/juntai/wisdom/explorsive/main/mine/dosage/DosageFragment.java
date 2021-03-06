package com.juntai.wisdom.explorsive.main.mine.dosage;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.CalendarUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.base.BaseAppFragment;
import com.juntai.wisdom.explorsive.bean.AllDosageBean;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;
import com.juntai.wisdom.explorsive.utils.StringTools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述   工作记录
 * @date 2021/6/1 15:58
 */
public class DosageFragment extends BaseAppFragment<MainPresent> implements MainContactInterface, View.OnClickListener {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String fragmentTag;
    private int mineId;
    /**
     * 请选择开始时间
     */
    private TextView mDosageStartTimeTv;
    /**
     * 请选择结束时间
     */
    private TextView mDosageEndTimeTv;
    /**
     * 库存量
     */
    private TextView mDosageTitleTv;
    private RecyclerView mDosageContentRv;
    private AllDosageAdapter dosageAdapter;


    @Override
    protected void lazyLoad() {
        fragmentTag = getArguments().getString(MainContactInterface.TAB_TITLES);
        mineId = getArguments().getInt(MainContactInterface.TAB_ID);

        FormBody.Builder builder = getBaseBuilder().add("mineId",String.valueOf(mineId));
        if (StringTools.isStringValueOk(mDosageStartTimeTv.getText().toString().trim())) {
            builder.add("startTime", mDosageStartTimeTv.getText().toString().trim());
        }
        if (StringTools.isStringValueOk(mDosageEndTimeTv.getText().toString().trim())) {
            builder.add("endTime", mDosageEndTimeTv.getText().toString().trim());
        }

        switch (fragmentTag) {
            case MainContactInterface.USE_DOSAGE:
                // : 2022-01-07 使用量统计
                mPresenter.getUseStatistics(builder.build(), AppHttpPath.USE_STATISTICS);
                break;
            case MainContactInterface.RECEIVE_DOSAGE:
                // : 2022-01-07 使用量统计
                mPresenter.getReceiveStatistics(builder.build(), AppHttpPath.RECEIVE_STATISTICS);
                break;
            default:
                break;
        }
    }

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    public static DosageFragment newInstance(String type, int mineId) {
        Bundle args = new Bundle();
        args.putString(MainContactInterface.TAB_TITLES, type);
        args.putInt(MainContactInterface.TAB_ID, mineId);
        DosageFragment fragment = new DosageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.dosage_fg;
    }

    @Override
    protected void initView() {

        mDosageStartTimeTv = (TextView) getView(R.id.dosage_start_time_tv);
        mDosageStartTimeTv.setOnClickListener(this);
        mDosageEndTimeTv = (TextView) getView(R.id.dosage_end_time_tv);
        mDosageEndTimeTv.setOnClickListener(this);
        mDosageTitleTv = (TextView) getView(R.id.dosage_title_tv);
        mDosageTitleTv.setText("用量");
        mDosageContentRv = (RecyclerView) getView(R.id.dosage_content_rv);
        dosageAdapter = new AllDosageAdapter(R.layout.dosage_number_item);
        dosageAdapter.setEmptyView(getBaseActivity().getAdapterEmptyView("暂无统计量",-1));
        getBaseActivity().initRecyclerview(mDosageContentRv, dosageAdapter, LinearLayoutManager.VERTICAL);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {

        AllDosageBean dosageBean = (AllDosageBean) o;
        if (dosageBean != null) {
            List<AllDosageBean.DataBean> dataBeans = dosageBean.getData();
            dosageAdapter.setNewData(dataBeans);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.dosage_start_time_tv:
                // : 2021-12-20 选择开始时间
                PickerManager.getInstance().showTimePickerView(mContext, new boolean[]{true, true, true, true, true, false}, "选择开始时间", new PickerManager.OnTimePickerTimeSelectedListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String startTime = sdf.format(date);
                        mDosageStartTimeTv.setText(startTime);
                    }
                });
                break;
            case R.id.dosage_end_time_tv:
                // : 2021-12-20 选择结束时间
                if (TextUtils.isEmpty(mDosageStartTimeTv.getText().toString().trim())) {
                    ToastUtils.toast(mContext, "请先选择开始时间");
                    return;
                }
                PickerManager.getInstance().showTimePickerView(mContext, new boolean[]{true, true, true, true, true, false}, "选择结束时间", new PickerManager.OnTimePickerTimeSelectedListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String endTime = sdf.format(date);
                        // : 2021-12-20 和开始时间比较
                        if (!CalendarUtil.compareTimes(mDosageStartTimeTv.getText().toString().trim(), endTime, "yyyy-MM-dd HH:mm")) {
                            ToastUtils.toast(mContext, "结束时间不能早于开始时间");
                            return;
                        }
                        mDosageEndTimeTv.setText(endTime);
                        lazyLoad();
                    }
                });
                break;
        }
    }
}
