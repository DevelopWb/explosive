package com.juntai.wisdom.explorsive.main.mine.dosage;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.IdNameBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022-01-06 17:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-01-06 17:22
 */
public class AllMineAdapter extends BaseQuickAdapter<IdNameBean, BaseViewHolder> {
    public AllMineAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, IdNameBean item) {
        helper.setText(R.id.mine_name_tv, item.getName());
    }
}
