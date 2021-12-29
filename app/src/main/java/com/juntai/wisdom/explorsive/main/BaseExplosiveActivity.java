package com.juntai.wisdom.explorsive.main;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.base.customview.GestureSignatureView;
import com.juntai.wisdom.explorsive.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.BaseNormalRecyclerviewBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageBean;
import com.juntai.wisdom.explorsive.bean.ItemSignBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MineReceiverBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.TextKeyValueBean;
import com.juntai.wisdom.explorsive.bean.TimeBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.utils.StringTools;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述  巡检的基类
 * @CreateDate: 2021/4/22 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:08
 */
public abstract class BaseExplosiveActivity extends BaseAppActivity<MainPresent> implements MainContactInterface, View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {
    public static int SELECT_ADDR = 998;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
    protected int baseId;

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
        if (getIntent() != null) {
            baseId = getIntent().getIntExtra(BASE_ID, 0);
        }
        setTitleName(getTitleName());
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        adapter = new HandlerOrderAdapter(null);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
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
    protected void commitLogic(BaseAdapterDataBean baseAdapterDataBean) {
    }

    private void setAdapterClick() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                currentPosition = position;
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
                switch (multipleItem.getItemType()) {
                    case MultipleItem.ITEM_SELECT_TIME:
                        TimeBean timeBean = (TimeBean) multipleItem.getObject();
                        switch (timeBean.getTimeKey()) {
                            case MainContactInterface.PLAN_USE_START_TIME:
                                PickerManager.getInstance().showTimePickerView(mContext, null, "预计使用开始时间", new PickerManager.OnTimePickerTimeSelectedListener() {
                                    @Override
                                    public void onTimeSelect(Date date, View v) {
                                        timeBean.setTimeValue(sdf.format(date));
                                        adapter.notifyItemChanged(position);
                                    }
                                });
                                break;
                            case MainContactInterface.PLAN_USE_END_TIME:
                                PickerManager.getInstance().showTimePickerView(mContext, null, "预计使用结束时间", new PickerManager.OnTimePickerTimeSelectedListener() {
                                    @Override
                                    public void onTimeSelect(Date date, View v) {
                                        timeBean.setTimeValue(sdf.format(date));
                                        adapter.notifyItemChanged(position);
                                    }
                                });
                                break;
                            default:
                                break;
                        }

                        break;
                    case MultipleItem.ITEM_LOCATION:
                        // : 2021-12-22  跳转到选择位置类
                        startActivityForResult(new Intent(mContext, LocateSelectionActivity.class),
                                SELECT_ADDR);
                        break;
                    case MultipleItem.ITEM_SELECT:
                        selectBean = (TextKeyValueBean) multipleItem.getObject();
                        switch (selectBean.getKey()) {
                            case MainContactInterface.SAFER:
                                mPresenter.getReceiverOfMine(getBaseBuilder().build(), MainContactInterface.SAFER);
                                break;
                            case MainContactInterface.BLASTER:
                                mPresenter.getReceiverOfMine(getBaseBuilder().build(), MainContactInterface.BLASTER);
                                break;
                            case MainContactInterface.MANAGER:
                                mPresenter.getReceiverOfMine(getBaseBuilder().build(), MainContactInterface.MANAGER);
                                break;
                            default:
                                break;
                        }
                        break;
                    case MultipleItem.ITEM_SIGN:
                        ItemSignBean signBean = (ItemSignBean) multipleItem.getObject();
                        switch (view.getId()) {
                            case R.id.start_sign_tv:
                                itemSignBean = (ItemSignBean) multipleItem.getObject();
                                //签名
                                mSignIv = (ImageView) view.findViewById(R.id.user_sign_iv);
                                if (MainContactInterface.SIGN_TITLE_UNIT.equals(itemSignBean.getSignTitle())) {
                                    showSignatureView();
                                }else {
                                    if (0==itemSignBean.getIsAgree()) {
                                        ToastUtils.toast(mContext,"请选择是否同意申请");
                                        return;
                                    }
                                    if (2==itemSignBean.getIsAgree()&&TextUtils.isEmpty(itemSignBean.getReason())) {
                                        ToastUtils.toast(mContext,"请输入拒绝申请的原因");
                                        return;
                                    }
                                    showSignatureView();
                                }



                                break;

                            case R.id.agree_apply_tv:
                                signBean.setIsAgree(1);
                                break;
                            case R.id.reject_apply_tv:
                                signBean.setIsAgree(2);
                                break;
                            default:
                                break;
                        }
                        adapter.notifyItemChanged(position);
                        break;
                    default:
                        switch (view.getId()) {
                            case R.id.add_dosage_iv:
                                //添加用量
                                List<ExplosiveUsageBean> explosiveUsageBeans = (List<ExplosiveUsageBean>) multipleItem.getObject();
                                explosiveUsageBeans.add(new ExplosiveUsageBean("请选择爆炸物种类", 0, "零", "个"));
                                adapter.notifyItemChanged(position);
                                break;


                            default:
                                break;
                        }
                        break;
                }


            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                currentPosition = position;
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);

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
                        mPresenter.uploadFile(MainContactInterface.UPLOAD_SIGN, signPath);


                        //                        SINGE_STATE = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bottomSheetDialog.dismiss();
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
                baseAdapterDataBean = getBaseAdapterData(false);

                if (baseAdapterDataBean == null) {
                    return;
                }
                commitLogic(baseAdapterDataBean);
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
        UseOrderDetailBean.DataBean useOrderBean = new UseOrderDetailBean.DataBean();
        MultipartBody.Builder builder = mPresenter.getPublishMultipartBody();
        List<MultipleItem> arrays = adapter.getData();
        for (MultipleItem item : arrays) {
            switch (item.getItemType()) {
                case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                    BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) item.getObject();

                    switch (baseNormalRecyclerviewBean.getType()) {
                        case MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE:
                            List<TextKeyValueBean> textKeyValueBeans = (List<TextKeyValueBean>) baseNormalRecyclerviewBean.getObject();
                            for (TextKeyValueBean textKeyValueBean : textKeyValueBeans) {
                                String applyValue = textKeyValueBean.getValue();
                                switch (textKeyValueBean.getKey()) {
                                    case MainContactInterface.APPLY_NO:
                                        receiveOrderBean.setApplyNumber(applyValue);
                                        useOrderBean.setApplyNumber(applyValue);
                                        break;
                                    case MainContactInterface.APPLY_USER:
                                        receiveOrderBean.setApplyUsername(applyValue);
                                        useOrderBean.setApplyUsername(applyValue);
                                        break;
                                    case MainContactInterface.APPLY_USER_MOBILE:
                                        receiveOrderBean.setApplyPhone(applyValue);
                                        useOrderBean.setApplyPhone(applyValue);
                                        break;
                                    case MainContactInterface.APPLY_USER_UNIT:
                                        receiveOrderBean.setApplyDepartmentName(applyValue);
                                        useOrderBean.setApplyDepartmentName(applyValue);
                                        break;
                                    case MainContactInterface.APPLY_USER_UNIT_ADDR:
                                        receiveOrderBean.setApplyDepartmentAddress(applyValue);
                                        useOrderBean.setApplyDepartmentAddress(applyValue);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_SELECT_TIME:
                    TimeBean timeBean = (TimeBean) item.getObject();
                    String timeValue = timeBean.getTimeValue();
                    switch (timeBean.getTimeKey()) {
                        case MainContactInterface.PLAN_USE_START_TIME:
                            useOrderBean.setEstimateStartUseTime(timeValue);
                            break;
                        case MainContactInterface.PLAN_USE_END_TIME:
                            useOrderBean.setEstimateEndUseTime(timeValue);
                            break;
                        default:
                            break;
                    }


                    break;
                case MultipleItem.ITEM_SIGN:
                    //签名
                    ItemSignBean signBean = (ItemSignBean) item.getObject();
                    if (!skipFilter) {
                        if (!StringTools.isStringValueOk(signBean.getSignPicPath())) {
                            switch (UserInfoManager.getDepartmentType()) {
                                //（1矿场；2派出所；3治安大队；4县公安局；5民爆仓库）
                                case 1:
                                    if (MainContactInterface.SIGN_TITLE_UNIT.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "请签名");
                                        return null;
                                    }
                                    break;
                                case 2:
                                    if (MainContactInterface.SIGN_TITLE_POLICE.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "请签名");
                                        return null;
                                    }
                                    break;
                                case 3:
                                    if (MainContactInterface.SIGN_TITLE_BRIGADE.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "请签名");
                                        return null;
                                    }
                                    break;
                                case 4:
                                    if (MainContactInterface.SIGN_TITLE_LEADER.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "请签名");
                                        return null;
                                    }
                                    break;
                                default:
                                    break;
                            }


                        }
                    }
                    //民爆领取申请

                    receiveOrderBean.setSignStatus(signBean.getSignStatus());
                    useOrderBean.setSignStatus(signBean.getSignStatus());
                    switch (signBean.getSignTitle()) {
                        case MainContactInterface.SIGN_TITLE_UNIT:
                            receiveOrderBean.setApplySign(signBean.getSignPicPath());
                            receiveOrderBean.setApplyDepartmentSeal(signBean.getDepartmentSignPath());
                            useOrderBean.setApplySign(signBean.getSignPicPath());
                            useOrderBean.setApplyDepartmentSeal(signBean.getDepartmentSignPath());
                            break;
                        case MainContactInterface.SIGN_TITLE_POLICE:
                            receiveOrderBean.setPoliceVoid(signBean.getIsAgree());
                            receiveOrderBean.setPoliceRemarks(signBean.getReason());
                            receiveOrderBean.setPoliceSign(signBean.getSignPicPath());
                            receiveOrderBean.setPoliceDepartmentSeal(signBean.getDepartmentSignPath());
                            useOrderBean.setPoliceVoid(signBean.getIsAgree());
                            useOrderBean.setPoliceRemarks(signBean.getReason());
                            useOrderBean.setPoliceSign(signBean.getSignPicPath());
                            useOrderBean.setPoliceDepartmentSeal(signBean.getDepartmentSignPath());
                            break;
                        case MainContactInterface.SIGN_TITLE_BRIGADE:
                            receiveOrderBean.setBrigadeVoid(signBean.getIsAgree());
                            receiveOrderBean.setBrigadeRemarks(signBean.getReason());
                            receiveOrderBean.setBrigadeSign(signBean.getSignPicPath());
                            receiveOrderBean.setBrigadeDepartmentSeal(signBean.getDepartmentSignPath());
                            break;
                        case MainContactInterface.SIGN_TITLE_LEADER:
                            receiveOrderBean.setLeaderVoid(signBean.getIsAgree());
                            receiveOrderBean.setLeaderRemarks(signBean.getReason());
                            receiveOrderBean.setLeaderSign(signBean.getSignPicPath());
                            receiveOrderBean.setLeaderDepartmentSeal(signBean.getDepartmentSignPath());
                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_SELECT:
                    TextKeyValueBean selectBean = (TextKeyValueBean) item.getObject();
                    String selectValue = selectBean.getValue();
                    String selectKey = selectBean.getKey();
                    int id = selectBean.getId();
                    switch (selectKey) {
                        case MainContactInterface.SAFER:
                            useOrderBean.setSafetyId(id);
                            useOrderBean.setSafetyName(selectValue);
                            break;
                        case MainContactInterface.BLASTER:
                            useOrderBean.setBlasterId(id);
                            useOrderBean.setBlasterName(selectValue);
                            break;
                        case MainContactInterface.MANAGER:
                            useOrderBean.setSafekeepingId(id);
                            useOrderBean.setSafekeepingName(selectValue);
                            break;
                        default:
                            break;
                    }

                    break;
                case MultipleItem.ITEM_EDIT:
                    TextKeyValueBean textValueEditBean = (TextKeyValueBean) item
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
                            receiveOrderBean.setRemarks(value);
                            useOrderBean.setRemarks(value);
                            break;
                        default:
                            break;
                    }
                    if (StringTools.isStringValueOk(value) && formKey != null) {
                        builder.addFormDataPart(formKey, value);
                    }

                    break;
                case MultipleItem.ITEM_LOCATION:
                    LocationBean locationBean = (LocationBean) item.getObject();
                    receiveOrderBean.setUseAddress(locationBean.getAddress());
                    receiveOrderBean.setUseLatitude(locationBean.getLatitude());
                    receiveOrderBean.setUseLongitude(locationBean.getLongitude());
                    useOrderBean.setEstimateUseAddress(locationBean.getAddress());
                    useOrderBean.setEstimateUseLatitude(locationBean.getLatitude());
                    useOrderBean.setEstimateUseLongitude(locationBean.getLongitude());
                    break;

                case MultipleItem.ITEM_APPLY_DOSAGE:
                    List<ExplosiveUsageBean> explosiveUsageBeans = (List<ExplosiveUsageBean>) item.getObject();
                    receiveOrderBean.setExplosiveUsage(explosiveUsageBeans);
                    useOrderBean.setExplosiveUsage(explosiveUsageBeans);
                    break;
                default:
                    break;
            }
        }
        bean.setBuilder(builder);
        bean.setReceiveOrderBean(receiveOrderBean);
        bean.setUseOrderBean(useOrderBean);
        return bean;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case MainContactInterface.UPLOAD_SIGN:
                List<String> pics = (List<String>) o;
                String path = pics == null ? "" : pics.get(0);
                if (itemSignBean != null) {
                    itemSignBean.setSignPicPath(path);
                    itemSignBean.setSignStatus(2);
                    itemSignBean.setSignTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    if (!MainContactInterface.SIGN_TITLE_UNIT.equals(itemSignBean.getSignTitle())) {
                        itemSignBean.setReason(1==itemSignBean.getIsAgree()?"同意申请":itemSignBean.getReason());
                    }
                    adapter.notifyItemChanged(currentPosition);
                }
                break;

            case MainContactInterface.SAFER:
                //获取场内领取人信息
                initMineOfReceiver(o, 1);
                break;
            case MainContactInterface.BLASTER:
                //获取场内领取人信息
                initMineOfReceiver(o, 2);
                break;
            case MainContactInterface.MANAGER:
                //获取场内领取人信息
                initMineOfReceiver(o, 3);
                break;
            default:
                break;
        }
    }

    /**
     * @param o
     * @param type 人员类型（1安全员；2爆破员；3保管员）
     */
    private void initMineOfReceiver(Object o, int type) {
        List<MineReceiverBean.DataBean> list = new ArrayList<>();
        MineReceiverBean mineReceiverBean = (MineReceiverBean) o;
        if (mineReceiverBean != null) {
            List<MineReceiverBean.DataBean> arrays = mineReceiverBean.getData();
            if (arrays != null) {
                for (MineReceiverBean.DataBean array : arrays) {
                    if (type == array.getType()) {
                        list.add(array);
                    }
                }
            }
        }
        PickerManager.getInstance().showOptionPicker(mContext, list, new PickerManager.OnOptionPickerSelectedListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                MineReceiverBean.DataBean dataBean = list.get(options1);
                selectBean.setId(dataBean.getId());
                selectBean.setValue(dataBean.getName());
                adapter.notifyItemChanged(currentPosition);
            }
        });
    }


    public interface OnSignedCallBack {
        void signed(String picPath);
    }
}
