package com.juntai.wisdom.explorsive.main;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.RuleTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.video.img.ImageZoomActivity;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.base.customview.GestureSignatureView;
import com.juntai.wisdom.explorsive.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.IdNameBean;
import com.juntai.wisdom.explorsive.bean.ItemSignBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderListBean;
import com.juntai.wisdom.explorsive.bean.TextKeyValueBean;
import com.juntai.wisdom.explorsive.utils.StringTools;
import com.juntai.wisdom.explorsive.utils.UrlFormatUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述  巡检的基类
 * @CreateDate: 2021/4/22 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:08
 */
public abstract class BaseExplosiveActivity extends BaseAppActivity<MainPresent> implements MainContactInterface, View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {
    public static int SELECT_ADDR = 998;

    protected HandlerOrderAdapter adapter;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    public static String PARCELABLE_KEY = "parcelable";
    public static String BASE_ID = "baseid";
    public static String BASE_ID2 = "baseid2";
    public static String BASE_STRING = "basestring";
    public static String SDCARD_TAG = "/storage/emulated";
    public static String BASE_STRING2 = "basestring2";
    public final static String ADD_UNIT = "添加单位";
    public final static String ADD_INSPECTION_SITE = "添加治安巡检点";
    public final static String ADD_IMPORTANTOR = "添加重点人员";
    private int currentPosition;
    private BottomSheetDialog bottomSheetDialog;
    private GestureSignatureView gsv_signature;
    private ImageView mSignIv;
    private ItemSignBean itemSignBean;
    public TextView mCommitTv;

    protected abstract String getTitleName();


    private TextKeyValueBean selectBean;
    private TextView mSelectTv;

    public boolean idDetail = false;
    private OnSignedCallBack onSignedCallBack;


    public void setOnSignedCallBack(OnSignedCallBack onSignedCallBack) {
        this.onSignedCallBack = onSignedCallBack;
    }

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.recycleview_layout;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        adapter = new HandlerOrderAdapter(null);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        if (getFootView() != null) {
            adapter.setFooterView(getFootView());
        }
        setAdapterClick();


    }

    @Override
    public void initData() {

    }

    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_commit, null);
        mCommitTv = view.findViewById(R.id.commit_form_tv);
        mCommitTv.setText("提交");
        mCommitTv.setOnClickListener(this);
        return view;
    }

    /**
     * 提交的逻辑
     */
    protected void commitLogic(MultipartBody.Builder builder) {
    }

    private void setAdapterClick() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                currentPosition = position;
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
                switch (multipleItem.getItemType()) {
                    default:
                        switch (view.getId()) {
                            case R.id.sign_ll:
                                itemSignBean = (ItemSignBean) multipleItem.getObject();
                                //签名
                                mSignIv = (ImageView) view.findViewById(R.id.sign_name_iv);
                                showSignatureView();
                                break;


                            case R.id.location_ll:
                                // : 2021-12-22  跳转到选择位置类
                                startActivityForResult(new Intent(mContext, LocateSelectionActivity.class),
                                        SELECT_ADDR);
                                break;

                            case R.id.add_dosage_iv:
                                //添加用量
                                List<ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean> explosiveUsageBeans = (List<ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean>) multipleItem.getObject();
                                explosiveUsageBeans.add(new ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean("请选择爆炸物种类", 0, "零", "个"));
                                adapter.notifyItemChanged(position);
                                break;
                            default:
                                break;
                        }
                        break;
                }


            }
        });


    }


    @Override
    protected void selectedPicsAndEmpressed(List<String> icons) {
//        if (icons.size() > 0) {
//            String path = icons.get(0);
//            headPicBean = (HeadPicBean) ((MultipleItem) adapter.getData().get(currentPosition)).getObject();
//            if (headPicBean != null && adapter != null) {
//                headPicBean.setPicPath(path);
//                adapter.notifyItemChanged(currentPosition);
//            }
////            if (BaseInspectContract.INSPECTION_IMPORTANTOR_PHOTO.equals(headPicBean.getPicName())) {
////                //跳转到裁剪头像的界面
////                startActivityForResult(new Intent(this, HeadCropActivity.class).putExtra(HeadCropActivity.HEAD_PIC,
////                        path), BASE_REQUEST_RESULT);
////            }
//
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {
//        ArrayList<String> photos = new ArrayList<>();
//        List<String> arrays = adapter.getData();
//        for (String array : arrays) {
//            if (array.contains(AppHttpPath.BASE_IMAGE_THUM)) {
//                array = array.replace(AppHttpPath.BASE_IMAGE_THUM, AppHttpPath.BASE_IMAGE);
//            }
//            photos.add(array);
//        }
//        //查看图片
//        startActivity(new Intent(mContext, ImageZoomActivity.class)
//                .putExtra("paths", photos)
//                .putExtra("item", position));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signature_view_save:
                if (gsv_signature.getTouched()) {
                    try {
                        String signPath = FileCacheUtils.getAppImagePath() + FileCacheUtils.SIGN_PIC_NAME;
                        //保存到本地
                        gsv_signature.save(signPath);
                        if (mSignIv != null) {
                            ImageLoadUtil.loadImageNoCache(mContext, signPath, mSignIv);
                        }
                        if (onSignedCallBack != null) {
                            onSignedCallBack.signed(signPath);
                        }
                        if (itemSignBean != null) {
                            itemSignBean.setSignPicPath(signPath);
                        }
                        //                        SINGE_STATE = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //                    mSignNameTagIv.setVisibility(View.GONE);
                    //                    mSignNameNoticeTv.setVisibility(View.GONE);
                    //                    mSignRedactImg.setVisibility(View.VISIBLE);
                    bottomSheetDialog.dismiss();
                    //                    mSignResign.getRightTextView().setVisibility(View.VISIBLE);
                } else {
                    ToastUtils.toast(mContext, "请签名！");
                }

                break;

            case R.id.signature_view_rewrite:
                gsv_signature.clear();
                break;
            case R.id.signature_view_cancel:
                gsv_signature.clear();
                bottomSheetDialog.dismiss();
                break;
            case R.id.commit_form_tv:
                //提交
                BaseAdapterDataBean baseAdapterDataBean = null;
                if (mCommitTv != null) {
                    if ("申请修改".equals(getTextViewValue(mCommitTv))) {
                        baseAdapterDataBean = getBaseAdapterData(true);
                    } else {
                        baseAdapterDataBean = getBaseAdapterData(false);
                    }
                } else {
                    baseAdapterDataBean = getBaseAdapterData(false);
                }

                if (baseAdapterDataBean == null) {
                    return;
                }
                commitLogic(baseAdapterDataBean.getBuilder());
                break;
            default:
                break;
        }
    }


    /**
     * 展示签名的画板
     */
    protected void showSignatureView() {

        bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.signature_view_layout, null);
        view.findViewById(R.id.signature_view_save).setOnClickListener(this);
        view.findViewById(R.id.signature_view_rewrite).setOnClickListener(this);
        view.findViewById(R.id.signature_view_cancel).setOnClickListener(this);
        //签名画板
        gsv_signature = view.findViewById(R.id.gsv_signature);

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.removeAllFooterView();
            adapter.removeAllHeaderView();
        }
        if (bottomSheetDialog != null) {
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            }
            bottomSheetDialog = null;
        }
        //清除签名文件
        FileCacheUtils.clearImage(getSignPath(FileCacheUtils.SIGN_PIC_NAME));
    }

    /**
     * 获取adapter中的数据
     * skipFilter  跳过过滤
     *
     * @return
     */
    protected BaseAdapterDataBean getBaseAdapterData(boolean skipFilter) {

        BaseAdapterDataBean bean = new BaseAdapterDataBean();
        ReceiveOrderDetailBean.DataBean receiveOrderBean = new ReceiveOrderDetailBean.DataBean();
        MultipartBody.Builder builder = mPresenter.getPublishMultipartBody();
        List<MultipleItem> arrays = adapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
                case MultipleItem.ITEM_SIGN:
                    //签名
                    ItemSignBean signBean = (ItemSignBean) array.getObject();
                    if (!StringTools.isStringValueOk(signBean.getSignPicPath())) {
                        ToastUtils.toast(mContext, "请签名");
                        return null;
                    }
                    builder.addFormDataPart("pictureSign", "pictureSign",
                            RequestBody.create(MediaType.parse(
                                    "file"), new File(getSignPath(FileCacheUtils.SIGN_PIC_NAME))));
                    break;
                case MultipleItem.ITEM_EDIT:
                    TextKeyValueBean textValueEditBean = (TextKeyValueBean) array
                            .getObject();
                    String value = textValueEditBean.getValue();
                    if (!skipFilter) {
                        if (textValueEditBean.isImportant() && TextUtils.isEmpty(textValueEditBean
                                .getValue())) {
                            String key = textValueEditBean.getKey();
                            ToastUtils.toast(mContext, "请输入" + key);
                            return null;
                        }
                    }

                    String formKey = null;
                    switch (textValueEditBean.getKey()) {
//                        case BaseInspectContract.INSPECTION_TEL:
//                            //联系电话
//                            if (!skipFilter) {
//                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
//                                    ToastUtils.toast(mContext, "联系电话格式不正确");
//                                    return null;
//                                }
//                            }
//                            formKey = "phone";
//                            importantorBean.setPhone(value);
//                            workerBean.setPhone(value);
//                            break;
                        case MainContactInterface.APPLICATION:
                            //用途
                            formKey = "remarks";
                            receiveOrderBean.setRemarks(value);
                            break;
                        default:
                            break;
                    }
                    if (StringTools.isStringValueOk(value) && formKey != null) {
                        builder.addFormDataPart(formKey, value);
                    }

                    break;
                case MultipleItem.ITEM_LOCATION:
                    LocationBean locationBean = (LocationBean) array.getObject();
                    builder.addFormDataPart("gpsAddress", locationBean.getAddress());
                    builder.addFormDataPart("longitude", locationBean.getLongitude());
                    builder.addFormDataPart("latitude", locationBean.getLatitude());
//                    receiveOrderBean.setlo
                    break;

                case MultipleItem.ITEM_APPLY_DOSAGE:
                    List<ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean> explosiveUsageBeans = (List<ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean>) array.getObject();
                    builder.addFormDataPart("explosiveUsage", GsonTools.createGsonString(explosiveUsageBeans));
                    receiveOrderBean.setExplosiveUsage(explosiveUsageBeans);
                    break;
                default:
                    break;
            }
        }
        bean.setBuilder(builder);
        bean.setReceiveOrderBean(receiveOrderBean);
        return bean;
    }

    @Override
    public void onSuccess(String tag, Object o) {
    }


    public interface OnSignedCallBack {
        void signed(String picPath);
    }
}