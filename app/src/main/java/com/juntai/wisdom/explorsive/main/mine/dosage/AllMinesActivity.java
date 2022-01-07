package com.juntai.wisdom.explorsive.main.mine.dosage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.base.BaseRecyclerviewActivity;
import com.juntai.wisdom.explorsive.bean.IdNameBean;
import com.juntai.wisdom.explorsive.bean.IdNameListBean;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  所有的矿场
 * @date 2021-12-29 10:54
 */
public class AllMinesActivity extends BaseRecyclerviewActivity<MainPresent> implements MainContactInterface {

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public void initData() {
        mPresenter.getAllMine(getBaseBuilder().build(), AppHttpPath.GET_ALL_MINE);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IdNameBean idNameBean = (IdNameBean) adapter.getItem(position);
                startActivity(new Intent(mContext,AllDosageActivity.class).putExtra(BASE_ID,idNameBean.getId()));
            }
        });
    }


    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new AllMineAdapter(R.layout.item_mines);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        IdNameListBean idNameBeans = (IdNameListBean) o;
        if (idNameBeans != null) {
            List<IdNameBean> arrays = idNameBeans.getData();
            adapter.setNewData(arrays);
        }


    }
}
