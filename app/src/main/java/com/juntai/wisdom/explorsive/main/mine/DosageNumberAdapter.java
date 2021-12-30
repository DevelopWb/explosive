package com.juntai.wisdom.explorsive.main.mine;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageNumberBean;
import com.juntai.wisdom.explorsive.utils.MoneyUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-23 11:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-23 11:06
 */
public class DosageNumberAdapter extends BaseQuickAdapter<ExplosiveUsageNumberBean, BaseViewHolder> {
    private boolean isDetail =false;
    public DosageNumberAdapter(int layoutResId) {
        super(layoutResId);
    }


    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    @Override
    protected void convert(BaseViewHolder helper, ExplosiveUsageNumberBean item) {
        if (!isDetail) {
            helper.addOnClickListener(R.id.explosive_num_name_tv);
        }
        helper.setText(R.id.explosive_num_name_tv, item.getTypeName());
        helper.setText(R.id.explosive_num_start_et,item.getStartNumber());
        helper.setText(R.id.explosive_num_end_et, item.getEndNumber());
//        TextView numNameTv = helper.getView(R.id.apply_amount_cap_tv);
        EditText startNumEt = helper.getView(R.id.explosive_num_start_et);
        EditText endNumEt = helper.getView(R.id.explosive_num_end_et);
        initEditText(helper, item, startNumEt);
        initEditText(helper, item, endNumEt);
    }

    private void initEditText(BaseViewHolder helper, ExplosiveUsageNumberBean item, EditText numEt) {
        if (isDetail) {
            numEt.setClickable(false);
            numEt.setFocusable(false);
            helper.setBackgroundRes(R.id.explosive_num_start_et, R.drawable.sp_filled_gray_lighter);
        } else {
            numEt.setClickable(true);
            numEt.setFocusable(true);
            helper.setBackgroundRes(R.id.explosive_num_start_et, R.drawable.stroke_gray_square_bg);
        }

        numEt.setTag(item);
        numEt.addTextChangedListener(new TextWatcher() {
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
                ExplosiveUsageNumberBean bean = (ExplosiveUsageNumberBean) numEt.getTag();
                switch (numEt.getId()) {
                    case R.id.explosive_num_start_et:
                        bean.setStartNumber(s.toString().trim());
                        break;
                    case R.id.explosive_num_end_et:
                        bean.setEndNumber(s.toString().trim());
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
