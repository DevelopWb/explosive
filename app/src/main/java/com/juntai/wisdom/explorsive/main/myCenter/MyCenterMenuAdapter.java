package com.juntai.wisdom.explorsive.main.myCenter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.HomePageMenuBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 15:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 15:39
 */
public class MyCenterMenuAdapter extends BaseQuickAdapter<HomePageMenuBean, BaseViewHolder> {
    public MyCenterMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageMenuBean item) {

        helper.setText(R.id.homepage_menu_title_tv, item.getMenuName());
        helper.setText(R.id.homepage_menu_title_en_tv, item.getMenuEnName());
        helper.setImageResource(R.id.menu_icon_iv,item.getMenuIconId());
        helper.setBackgroundRes(R.id.homepage_menu_ll, item.getMenuBgId());
    }
}
