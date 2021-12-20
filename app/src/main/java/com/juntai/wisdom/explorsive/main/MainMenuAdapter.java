package com.juntai.wisdom.explorsive.main;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.explorsive.bean.MyMenuBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-15 16:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-15 16:17
 */
public class MainMenuAdapter extends BaseQuickAdapter<MyMenuBean, BaseViewHolder> {
    public MainMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMenuBean item) {
        ImageLoadUtil.loadImage(mContext, item.getImageId(), helper.getView(R.id.menu_iv));
        helper.setText(R.id.menu_title_tv, item.getName());
    }
}
