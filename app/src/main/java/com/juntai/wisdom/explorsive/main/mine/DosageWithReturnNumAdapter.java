package com.juntai.wisdom.explorsive.main.mine;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.utils.MoneyUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-23 11:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-23 11:06
 */
public class DosageWithReturnNumAdapter extends BaseQuickAdapter<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean, BaseViewHolder> {
    private boolean isDetail = false;

    public DosageWithReturnNumAdapter(int layoutResId) {
        super(layoutResId);
    }


    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    @Override
    protected void convert(BaseViewHolder helper, UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean item) {

        helper.addOnClickListener(R.id.add_dosage_num_iv);
        if (0 == helper.getAdapterPosition()) {
            if (!isDetail) {
                helper.setGone(R.id.add_dosage_num_iv, true);
            }else {
                helper.setGone(R.id.add_dosage_num_iv, false);
            }
        } else {
            helper.setGone(R.id.add_dosage_num_iv, false);
        }
        EditText startNumEt = helper.getView(R.id.dosage_return_start_num_et);
        EditText endNumEt = helper.getView(R.id.dosage_return_end_num_et);
        startNumEt.setTag(item);
        if (isDetail) {
            startNumEt.setClickable(false);
            startNumEt.setFocusable(false);
            helper.setBackgroundRes(R.id.dosage_return_start_num_et, R.drawable.sp_filled_gray_lighter);
            helper.setBackgroundRes(R.id.dosage_return_end_num_et, R.drawable.sp_filled_gray_lighter);
            endNumEt.setClickable(false);
            endNumEt.setFocusable(false);
        } else {
            startNumEt.setClickable(true);
            startNumEt.setFocusable(true);
            helper.setBackgroundRes(R.id.dosage_return_start_num_et, R.drawable.stroke_gray_square_bg);
            helper.setBackgroundRes(R.id.dosage_return_end_num_et, R.drawable.stroke_gray_square_bg);
            endNumEt.setClickable(true);
            endNumEt.setFocusable(true);
        }
        startNumEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean startBean = (UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean) startNumEt.getTag();
                startBean.setStartNumber(s.toString().trim());
            }
        });
        endNumEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean startBean = (UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean) startNumEt.getTag();
                startBean.setEndNumber(s.toString().trim());
            }
        });
        UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean bean = (UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean) startNumEt.getTag();
        startNumEt.setText(bean.getStartNumber());
        endNumEt.setText(bean.getEndNumber());

    }

}
