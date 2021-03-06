package com.juntai.wisdom.explorsive.main.mine.receive;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseSearchFragment;
import com.juntai.wisdom.explorsive.bean.OrderListBean;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述   搜索控件
 * @CreateDate: 2020/3/15 9:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/15 9:18
 */
public class ApplyReceiveFragment extends BaseSearchFragment implements View.OnClickListener {

    public static final String GET_ORDERS = "getorders";
    private ApplyReceiveAdapter mSearchedAdapter;
    private TextView applyUser;

    @Override
    protected void initData() {
//        mSearchedAdapter.setEmptyView(getBaseActivity().getAdapterEmptyView(getString(R.string.none_record),
//                R.mipmap.none_record));
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setOnRefreshListener(refreshLayout -> {
            requestData();
        });

        requestData();
        mSearchedAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderListBean.DataBean dataBean = (OrderListBean.DataBean) adapter.getData().get(position);
                mPresenter.receiveApplyAdapterItemLogic(mContext,dataBean.getStat(),dataBean.getId(),0);
            }
        });
    }



    @Override
    protected BaseQuickAdapter getSearchResultAdapter() {
        mSearchedAdapter = new ApplyReceiveAdapter(R.layout.order_item_layout);
        mSearchedAdapter.addHeaderView(getHeadView());
        return mSearchedAdapter;
    }

    private View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_head_layout, null);
        applyUser = view.findViewById(R.id.apply_user_tv);
        return view;
    }

    /**
     * 订单的状态  1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废
     *
     * @return
     */
    @Override
    protected List<String> getStatusList() {
        List<String> arrays = new ArrayList<>();
        arrays.add("派出所审核");
        arrays.add("治安大队审核");
        arrays.add("局领导审核");
        arrays.add("出库");
        arrays.add("配送");
        arrays.add("完成");
        arrays.add("作废");
        return arrays;
    }


    @Override
    protected void requestData() {
        // : 2021-12-20 调用获取订单的接口
        mPresenter.getReceiveOrderList(getRequestBody(), GET_ORDERS);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case GET_ORDERS:
                OrderListBean orderListBean = (OrderListBean) o;
                if (orderListBean != null) {
                    List<OrderListBean.DataBean> arrays = orderListBean.getData();
                    mSearchedAdapter.setNewData(arrays);
                }

                break;
            default:
                break;
        }

        super.onSuccess(tag, o);
    }
}
