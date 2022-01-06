package com.juntai.wisdom.explorsive.main.myCenter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.NewsBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022-01-06 15:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-01-06 15:54
 */
public class NewsAdapter extends BaseQuickAdapter<NewsBean.DataBean, BaseViewHolder> {
    public NewsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean item) {
        helper.setText(R.id.news_content_tv, item.getContent());
        if (2==item.getIsRead()) {
            //已读
            helper.setGone(R.id.unread_tag_tv,false);
        }else {
            helper.setGone(R.id.unread_tag_tv,true);
        }
    }
}
