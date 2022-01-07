package com.juntai.wisdom.explorsive.main.mine.dosage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.base.BaseTabViewPageActivity;
import com.juntai.wisdom.explorsive.bean.AllDosageBean;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  用量
 * @date 2022-01-07 10:00
 */
public class AllDosageActivity extends BaseTabViewPageActivity<MainPresent> implements MainContactInterface {

    private RecyclerView mDosageContentRv;
    private AllDosageAdapter dosageAdapter;

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }


    @Override
    protected int getTabMode() {
        return TabLayout.MODE_SCROLLABLE;
    }

    @Override
    protected int getTabHeadLayout() {
        return R.layout.all_dosage_item;
    }

    @Override
    protected int getTabFootLayout() {
        return 0;
    }

    @Override
    protected String gettitleName() {
        return "用量";
    }


    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();

        arrays.append(0, DosageFragment.newInstance(MainContactInterface.USE_DOSAGE,baseId));
        arrays.append(1, DosageFragment.newInstance(MainContactInterface.RECEIVE_DOSAGE,baseId));

        return arrays;
    }

    @Override
    protected String[] getTabTitles() {
        return new String[]{MainContactInterface.USE_DOSAGE,
                MainContactInterface.RECEIVE_DOSAGE};
    }


    @Override
    public void onSuccess(String tag, Object o) {

        AllDosageBean dosageBean = (AllDosageBean) o;
        if (dosageBean != null) {
            List<AllDosageBean.DataBean> dataBeans = dosageBean.getData();
            dosageAdapter.setNewData(dataBeans);

        }


    }

    @Override
    public void initData() {
        mPresenter.getStockOfMine(getBaseBuilder().add("mineId", String.valueOf(baseId)).build(), AppHttpPath.STOCK_OF_MINE);
    }


    public void initView() {
        super.initView();
        mDosageContentRv = (RecyclerView) findViewById(R.id.dosage_content_rv);
        dosageAdapter = new AllDosageAdapter(R.layout.dosage_number_item);
        dosageAdapter.setEmptyView(getAdapterEmptyView("暂无库存量",-1));
        initRecyclerview(mDosageContentRv, dosageAdapter, LinearLayoutManager.VERTICAL);
    }
}
