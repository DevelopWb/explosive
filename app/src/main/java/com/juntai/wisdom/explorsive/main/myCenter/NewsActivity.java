package com.juntai.wisdom.explorsive.main.myCenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.base.BaseRecyclerviewActivity;
import com.juntai.wisdom.explorsive.bean.NewsBean;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;
import com.juntai.wisdom.explorsive.main.mine.SearchPresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  消息
 * @date 2022-01-06 15:46
 */
public class NewsActivity extends BaseRecyclerviewActivity<MainPresent> implements MainContactInterface {

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public void initData() {
        setTitleName("消息通知");

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsBean.DataBean dataBean = (NewsBean.DataBean) adapter.getData().get(position);
                // : 2022-01-06 消息点击的事件
                if (1==dataBean.getType()) {
                    //领取
                    new SearchPresent().receiveApplyAdapterItemLogic(mContext, dataBean.getStat(),dataBean.getApplyId(),dataBean.getId());
                }else {
                //使用
                    new SearchPresent().useApplyAdapterItemLogic(mContext,dataBean.getStat(),dataBean.getApplyId(),dataBean.getId());

                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getNewsList(getBaseBuilder().build(), AppHttpPath.NEWS_LIST);
    }

    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new NewsAdapter(R.layout.item_news);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        NewsBean newsBean = (NewsBean) o;
        if (newsBean != null) {
            List<NewsBean.DataBean> arrays = newsBean.getData();
            if (arrays != null) {
                adapter.setNewData(arrays);
            }
        }


    }
}
