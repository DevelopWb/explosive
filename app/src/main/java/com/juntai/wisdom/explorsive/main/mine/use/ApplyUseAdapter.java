package com.juntai.wisdom.explorsive.main.mine.use;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.IdNameBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.OrderListBean;
import com.juntai.wisdom.explorsive.main.mine.OrderProgressAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  民爆物矿内使用申请
 *
 * @CreateDate: 2020/7/7 9:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/7 9:53
 */
public class ApplyUseAdapter extends BaseQuickAdapter<OrderListBean.DataBean, BaseViewHolder> {
    public ApplyUseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.DataBean item) {
        helper.setText(R.id.order_no_tv, item.getApplyNumber());
        helper.setText(R.id.order_time_tv, item.getApplyTime());
        helper.setText(R.id.apply_user_tv, item.getApplyUsername());
        int status = item.getStat();
        RecyclerView mRecyclerView = helper.getView(R.id.order_progress_rv);
        OrderProgressAdapter orderProgressAdapter = new OrderProgressAdapter(null);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(orderProgressAdapter);
        orderProgressAdapter.setNewData(getAdapterData(status));
    }

    private List<MultipleItem> getAdapterData(int status) {

        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new IdNameBean(0,status, "申请")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, "--------"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new IdNameBean(1,status, "派出所")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, "--------"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new IdNameBean(2,status, "仓库领用")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, "--------"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new IdNameBean(3,status, "使用")));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, "--------"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_CONTENT, new IdNameBean(4,status, "完成")));
        return arrays;
    }
}
