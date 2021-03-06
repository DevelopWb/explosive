package com.juntai.wisdom.explorsive.main.mine.use;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseSearchFragment;
import com.juntai.wisdom.explorsive.bean.OrderListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述   搜索控件
 * @CreateDate: 2020/3/15 9:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/15 9:18
 */
public class ApplyUseFragment extends BaseSearchFragment implements View.OnClickListener {

    public static final String GET_ORDERS = "getorders";
    private ApplyUseAdapter mApplyUseAdapter;
    private TextView applyUser;

    @Override
    protected void initData() {
//        mSearchedAdapter.setEmptyView(getBaseActivity().getAdapterEmptyView(getString(R.string.none_record),
//                R.mipmap.none_record));
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setOnRefreshListener(refreshLayout -> {
            requestData();
        });

        mApplyUseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //  订单详情  1派出所；2仓库领取；3使用；4完成
                OrderListBean.DataBean dataBean = (OrderListBean.DataBean) adapter.getData().get(position);
                mPresenter.useApplyAdapterItemLogic(mContext, dataBean.getStat(),dataBean.getId(),0);
            }
        });
        requestData();
    }


    @Override
    protected BaseQuickAdapter getSearchResultAdapter() {
        mApplyUseAdapter = new ApplyUseAdapter(R.layout.order_item_layout);
        mApplyUseAdapter.addHeaderView(getHeadView());
        return mApplyUseAdapter;
    }

    private View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_head_layout, null);
        applyUser = view.findViewById(R.id.apply_user_tv);
        return view;
    }

    /**
     * 订单的状态  1派出所审核；2仓库领用；3使用；4完成 5作废
     *
     * @return
     */
    @Override
    protected List<String> getStatusList() {
        List<String> arrays = new ArrayList<>();
        arrays.add("派出所审核");
        arrays.add("仓库领用");
        arrays.add("使用");
        arrays.add("完成");
        arrays.add("作废");
        return arrays;
    }

    @Override
    protected void requestData() {
        // : 2021-12-20 调用获取订单的接口
        mPresenter.getUseOrderList(getRequestBody(), GET_ORDERS);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case GET_ORDERS:
                OrderListBean orderListBean = (OrderListBean) o;
                if (orderListBean != null) {
                    List<OrderListBean.DataBean> arrays = orderListBean.getData();
                    mApplyUseAdapter.setNewData(arrays);
                }

                break;
            default:
                break;
        }

        super.onSuccess(tag, o);
    }
}
