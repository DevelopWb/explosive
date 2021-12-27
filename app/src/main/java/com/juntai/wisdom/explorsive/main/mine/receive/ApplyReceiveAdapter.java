package com.juntai.wisdom.explorsive.main.mine.receive;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.BaseOrderBean;
import com.juntai.wisdom.explorsive.bean.IdNameBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.OrderListBean;
import com.juntai.wisdom.explorsive.main.BaseExplosiveActivity;
import com.juntai.wisdom.explorsive.main.mine.OrderProgressAdapter;
import com.juntai.wisdom.explorsive.main.mine.SearchPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  民爆领取申请
 * @CreateDate: 2020/7/7 9:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/7 9:53
 */
public class ApplyReceiveAdapter extends BaseQuickAdapter<OrderListBean.DataBean, BaseViewHolder> {
    public ApplyReceiveAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.DataBean item) {
        helper.setText(R.id.order_no_tv, item.getApplyNumber());
        helper.setText(R.id.order_time_tv, item.getApplyTime());
        helper.setText(R.id.apply_user_tv, item.getApplyUsername());
        helper.addOnClickListener(R.id.order_title_ll);
        RecyclerView mRecyclerView = helper.getView(R.id.order_progress_rv);
        OrderProgressAdapter orderProgressAdapter = new OrderProgressAdapter(null);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(orderProgressAdapter);
        orderProgressAdapter.setNewData(getAdapterData(item));
        orderProgressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
                //  订单详情  1派出所审核；2治安大队审核；3局领导审核；4出库；5配送；6完成；7作废
                BaseOrderBean baseOrderBean = (BaseOrderBean) multipleItem.getObject();
                OrderListBean.DataBean dataBean = baseOrderBean.getOrderBean();
                new SearchPresent().receiveApplyAdapterItemLogic(mContext, dataBean);
            }
        });
    }

    private List<MultipleItem> getAdapterData(OrderListBean.DataBean item) {
        int status = item.getStat();
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new BaseOrderBean(item, new IdNameBean(0, status, "申请"))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, new BaseOrderBean(item, "--")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new BaseOrderBean(item, new IdNameBean(1, status, "派出所"))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, new BaseOrderBean(item, "--")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new BaseOrderBean(item, new IdNameBean(2, status, "治安大队"))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, new BaseOrderBean(item, "--")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new BaseOrderBean(item, new IdNameBean(3, status, "领导审批"))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, new BaseOrderBean(item, "--")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new BaseOrderBean(item, new IdNameBean(4, status, "出库"))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, new BaseOrderBean(item, "--")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new BaseOrderBean(item, new IdNameBean(5, status, "配送"))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, new BaseOrderBean(item, "--")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new BaseOrderBean(item, new IdNameBean(6, status, "完成"))));
        return arrays;
    }
}
