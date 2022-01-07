package com.juntai.wisdom.explorsive.main.mine.base;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.utils.MoneyUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-23 11:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-23 11:06
 */
public class DosageAdapter extends BaseQuickAdapter<ExplosiveUsageBean, BaseViewHolder> {
    private boolean isDetail =false;
    public DosageAdapter(int layoutResId) {
        super(layoutResId);
    }


    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    @Override
    protected void convert(BaseViewHolder helper, ExplosiveUsageBean item) {
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
                if (TextUtils.isEmpty( s.toString().trim())) {
                    return;
                }
                ExplosiveUsageBean bean = (ExplosiveUsageBean) amountEt.getTag();
                bean.setApplyQuantity(Integer.parseInt(s.toString().trim()));
                bean.setQuantityWords(MoneyUtils.change(Double.parseDouble(s.toString().trim())));
                amountCapTv.setText(MoneyUtils.change(Double.parseDouble(s.toString().trim())));
            }
        });
    }
}
