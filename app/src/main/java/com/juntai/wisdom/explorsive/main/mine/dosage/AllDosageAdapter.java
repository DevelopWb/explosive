package com.juntai.wisdom.explorsive.main.mine.dosage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.AllDosageBean;
import com.juntai.wisdom.explorsive.bean.AllDosageBean.DataBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-23 11:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-23 11:06
 */
public class AllDosageAdapter extends BaseQuickAdapter<AllDosageBean.DataBean, BaseViewHolder> {
    public AllDosageAdapter(int layoutResId) {
        super(layoutResId);
    }



    @Override
    protected void convert(BaseViewHolder helper, AllDosageBean.DataBean item) {
        helper.setText(R.id.explosive_num_name_tv, item.getTypeName());
        helper.setText(R.id.explosive_num_start_et,String.valueOf(item.getQuantity()));
        helper.setText(R.id.explosive_num_end_et, item.getUnit());
        EditText startNumEt = helper.getView(R.id.explosive_num_start_et);
        EditText endNumEt = helper.getView(R.id.explosive_num_end_et);
        startNumEt.setClickable(false);
        startNumEt.setFocusable(false);
        endNumEt.setClickable(false);
        endNumEt.setFocusable(false);
        helper.setBackgroundRes(R.id.explosive_num_start_et, R.drawable.sp_filled_gray_lighter);
        helper.setBackgroundRes(R.id.explosive_num_end_et, R.drawable.sp_filled_gray_lighter);
    }
}
