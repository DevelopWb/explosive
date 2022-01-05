package com.juntai.wisdom.explorsive.main.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.utils.MoneyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-23 11:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-23 11:06
 */
public class DosageWithReturnAdapter extends BaseQuickAdapter<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean, BaseViewHolder> {
    private boolean isDetail = false;

    public DosageWithReturnAdapter(int layoutResId) {
        super(layoutResId);
    }


    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    @Override
    protected void convert(BaseViewHolder helper, UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean item) {
        if (!isDetail) {
            helper.addOnClickListener(R.id.explosive_name_tv);
        }
        helper.setText(R.id.explosive_name_tv, item.getTypeName());
        helper.setText(R.id.unit_tv, item.getTypeUnit());
        helper.setText(R.id.apply_amount_et, String.valueOf(item.getApplyQuantity()));
        helper.setText(R.id.apply_amount_cap_tv, item.getQuantityWords());
        EditText amountEt = helper.getView(R.id.apply_amount_et);
        if (isDetail) {
            amountEt.setClickable(false);
            amountEt.setFocusable(false);
            helper.setBackgroundRes(R.id.apply_amount_et, R.drawable.sp_filled_gray_lighter);
        } else {
            amountEt.setClickable(true);
            amountEt.setFocusable(true);
            helper.setBackgroundRes(R.id.apply_amount_et, R.drawable.stroke_gray_square_bg);
        }
        TextView amountCapTv = helper.getView(R.id.apply_amount_cap_tv);
        amountEt.setTag(item);
        amountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    return;
                }
                UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean bean = (UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean) amountEt.getTag();
                bean.setApplyQuantity(Integer.parseInt(s.toString().trim()));
                bean.setQuantityWords(MoneyUtils.change(Double.parseDouble(s.toString().trim())));
                amountCapTv.setText(MoneyUtils.change(Double.parseDouble(s.toString().trim())));
            }
        });


        RecyclerView dosageNumRv = helper.getView(R.id.dosage_num_rv);
        DosageWithReturnNumAdapter numAdapter = new DosageWithReturnNumAdapter(R.layout.item_dosage_return_num);
        numAdapter.setDetail(isDetail);
        dosageNumRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        dosageNumRv.setAdapter(numAdapter);
        List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean>  data = item.getUsageNumberReturn().isEmpty() ? getReturnNums() : item.getUsageNumberReturn();
        numAdapter.setNewData(data);
        item.setUsageNumberReturn(numAdapter.getData());
        numAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean bean = (UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean) amountEt.getTag();
                adapter.addData(new UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean());
                bean.setUsageNumberReturn(adapter.getData());
            }
        });
    }

    private List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean> getReturnNums() {
        List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean> arrays = new ArrayList<>();
        arrays.add(new UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean());
        return arrays;
    }


}
