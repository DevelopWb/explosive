package com.juntai.wisdom.explorsive.main.myCenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ActivityManagerTool;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseRecyclerviewActivity;
import com.juntai.wisdom.explorsive.bean.HomePageMenuBean;
import com.juntai.wisdom.explorsive.entrance.LoginActivity;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;
import com.juntai.wisdom.explorsive.utils.UrlFormatUtil;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述 个人中心
 * @date 2021-12-20 10:07
 */
public class MyCenterActivity extends BaseRecyclerviewActivity<MainPresent> implements MainContactInterface {

    private ImageView mUserHeadIv;
    /**
     * unitName
     */
    private TextView mUserNameTv;
    /**
     * unitName
     */
    private TextView mUnitNameTv;

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.mycenter_layout;
    }

    @Override
    public void initView() {
        super.initView();
        mUserHeadIv = (ImageView) findViewById(R.id.user_head_iv);
        mUserNameTv = (TextView) findViewById(R.id.user_name_tv);
        mUnitNameTv = (TextView) findViewById(R.id.unit_name_tv);
        ImageLoadUtil.loadCirImgWithCrash(mContext, UrlFormatUtil.getImageOriginalUrl(UserInfoManager.getHeadImage()), mUserHeadIv, R.mipmap.default_user_head_icon);
        mUnitNameTv.setText(UserInfoManager.getDepartmentName());
        mUserNameTv.setText(UserInfoManager.getUserName());
        initViewRightDrawable(getTitleRightTv(), R.mipmap.quit_current_account, 20, 20);
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     new AlertDialog.Builder(mContext)
                                                             .setMessage("是否退出当前账号？")
                                                             .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(DialogInterface dialog, int which) {
                                                                     dialog.dismiss();
                                                                     ActivityManagerTool.getInstance().finishApp();
                                                                     UserInfoManager.clearUserBaseInfo();
                                                                     startActivity(new Intent(mContext, LoginActivity.class));
                                                                 }
                                                             })
                                                             .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(DialogInterface dialog, int which) {
                                                                     dialog.dismiss();
                                                                 }
                                                             }).show();
                                                 }
                                             }
        );

    }

    @Override
    public void initData() {
// : 2021-12-20  个人中心所有的逻辑
        mSmartrefreshlayout.setEnableRefresh(false);
        mSmartrefreshlayout.setEnableLoadMore(false);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        mRecyclerview.setLayoutManager(manager);
        adapter.setNewData(mPresenter.getMyCenterMenus());

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomePageMenuBean menuBean = (HomePageMenuBean) adapter.getData().get(position);

                switch (menuBean.getMenuName()) {
                    case HomePageMenuBean.MODIFY_MOBILE:
                        // : 2022-01-06  修改手机号
                        new AlertDialog.Builder(mContext)
                                .setMessage("请联系管理员")
                                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                        break;
                    case HomePageMenuBean.MODIFY_PWD:
                        // : 2022-01-06  修改密码
                        startActivity(new Intent(mContext, ModifyPwdActivity.class));
                        break;
                    case HomePageMenuBean.UPDATE:
                        update(true);
                        break;
                    case HomePageMenuBean.ABOUT_US:
                        // : 2022-01-06 关于我们
                        startActivity(new Intent(mContext, AboutUsActivity.class).putExtra(AboutUsActivity.TITLE_NAME, "关于我们"));
                        break;
                    default:
                        break;
                }
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
        return new MyCenterMenuAdapter(R.layout.mycenter_menu_item);
    }

}
