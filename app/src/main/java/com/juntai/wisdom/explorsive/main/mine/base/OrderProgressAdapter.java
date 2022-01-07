package com.juntai.wisdom.explorsive.main.mine.base;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.BaseOrderBean;
import com.juntai.wisdom.explorsive.bean.IdNameBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-21 11:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-21 11:05
 */
public class OrderProgressAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OrderProgressAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_CONTENT, R.layout.single_text_layout2);
        addItemType(MultipleItem.ITEM_DIVIDER, R.layout.single_text_layout2);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.ITEM_CONTENT:
                BaseOrderBean baseOrderBean = (BaseOrderBean) item.getObject();
                IdNameBean idNameBean = baseOrderBean.getIdNameBean();
                int id = idNameBean.getId();
                int currentStatus = idNameBean.getCurrentStatus();
                helper.setText(R.id.single_text_tv, idNameBean.getName());
                if (id == currentStatus) {
                    helper.setBackgroundRes(R.id.single_text_tv,R.drawable.sp_filled_accent);
                    helper.setTextColor(R.id.single_text_tv, ContextCompat.getColor(mContext,R.color.white));
                }else {
                    helper.setBackgroundRes(R.id.single_text_tv,0);
                    helper.setTextColor(R.id.single_text_tv, ContextCompat.getColor(mContext,R.color.gray_deeper));
                }

                break;
            case MultipleItem.ITEM_DIVIDER:
                BaseOrderBean baseOrderBean2 = (BaseOrderBean) item.getObject();
                TextView divTv = helper.getView(R.id.single_text_tv);
                String divStr = baseOrderBean2.getDivStr();
//                divTv.setPadding(DisplayUtil.dp2px(mContext,2), DisplayUtil.dp2px(mContext,2),DisplayUtil.dp2px(mContext,2), DisplayUtil.dp2px(mContext,2));
                divTv.setPadding(0, DisplayUtil.dp2px(mContext,3),0, DisplayUtil.dp2px(mContext,3));
                helper.setText(R.id.single_text_tv, divStr);

                break;
            default:
                break;
        }
    }
}
