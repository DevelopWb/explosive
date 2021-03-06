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

import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.video.img.ImageZoomActivity;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.base.BaseMultiselectBottomDialog;
import com.juntai.wisdom.explorsive.base.customview.GestureSignatureView;
import com.juntai.wisdom.explorsive.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.explorsive.bean.BaseAdapterDataBean;
import com.juntai.wisdom.explorsive.bean.BaseNormalRecyclerviewBean;
import com.juntai.wisdom.explorsive.bean.BaseUsageBean;
import com.juntai.wisdom.explorsive.bean.DeliveryListBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageNumberBean;
import com.juntai.wisdom.explorsive.bean.FaceCheckBean;
import com.juntai.wisdom.explorsive.bean.FragmentPicBean;
import com.juntai.wisdom.explorsive.bean.ItemCheckBoxBean;
import com.juntai.wisdom.explorsive.bean.ItemSignBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MineReceiverBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.OutInMineRequest;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.RecycleCheckBoxBean;
import com.juntai.wisdom.explorsive.bean.TextKeyValueBean;
import com.juntai.wisdom.explorsive.bean.TimeBean;
import com.juntai.wisdom.explorsive.bean.UseOrderDetailBean;
import com.juntai.wisdom.explorsive.faceCheck.FaceCheckActivity;
import com.juntai.wisdom.explorsive.utils.StringTools;
import com.juntai.wisdom.explorsive.utils.UrlFormatUtil;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: ????????????  ???????????????
 * @CreateDate: 2021/4/22 11:08
 * @UpdateUser: ?????????
 * @UpdateDate: 2021/4/22 11:08
 */
public abstract class BaseExplosiveActivity extends BaseAppActivity<MainPresent> implements MainContactInterface, View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected HandlerOrderAdapter adapter;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    private int currentPosition;
    private BottomSheetDialog bottomSheetDialog;
    private GestureSignatureView gsv_signature;
    private ImageView mSignIv;
    private ItemSignBean itemSignBean;
    public TextView mCommitTv;
    public static String SDCARD_TAG = "/storage/emulated";
    private BaseMultiselectBottomDialog multiselectBottomDialog;
    private FaceCheckBean faceCheckBean;

    protected abstract String getTitleName();


    private TextKeyValueBean selectBean;

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
        adapter = new HandlerOrderAdapter(null, getSupportFragmentManager());
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        setAdapterClick();


    }

    @Override
    public void initData() {

    }

    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_commit, null);
        mCommitTv = view.findViewById(R.id.commit_form_tv);
        mCommitTv.setText("??????");
        mCommitTv.setOnClickListener(this);
        return view;
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
                                PickerManager.getInstance().showTimePickerView(mContext, new boolean[]{true, true, true, true, true, false}, "????????????????????????", new PickerManager.OnTimePickerTimeSelectedListener() {
                                    @Override
                                    public void onTimeSelect(Date date, View v) {
                                        timeBean.setTimeValue(sdf.format(date));
                                        adapter.notifyItemChanged(position);
                                    }
                                });
                                break;
                            case MainContactInterface.PLAN_USE_END_TIME:
                                PickerManager.getInstance().showTimePickerView(mContext, new boolean[]{true, true, true, true, true, false}, "????????????????????????", new PickerManager.OnTimePickerTimeSelectedListener() {
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
                        // : 2021-12-22  ????????????????????????
                        startActivityForResult(new Intent(mContext, LocateSelectionActivity.class),
                                LocateSelectionActivity.SELECT_ADDR);
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
                            case MainContactInterface.DELIVERY:
                                mPresenter.getDeliveryList(getBaseBuilder().build(), AppHttpPath.GET_DELIVERY_LIST);
                                break;
                            default:
                                break;
                        }
                        break;
                    case MultipleItem.ITEM_FACE_CHECK:
                        faceCheckBean = (FaceCheckBean) multipleItem.getObject();
                        switch (view.getId()) {
                            case R.id.face_start_sign_tv:
                                //????????????????????????
                                showSignatureView();
                                break;
                            case R.id.user_face_iv:
                                //??????????????????
                                startActivityForResult(new Intent(mContext, FaceCheckActivity.class).putExtra(FaceCheckActivity.PERSION_ID, faceCheckBean.getPersonId()), FaceCheckActivity.FACE_CODE);

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
                                //??????
                                mSignIv = (ImageView) view.findViewById(R.id.user_sign_iv);

                                switch (itemSignBean.getSignTitle()) {
                                    case MainContactInterface.SIGN_TITLE_UNIT:
                                    case MainContactInterface.ARRIVERE_SIGN:
                                        showSignatureView();
                                        break;
                                    default:
                                        if (0 == itemSignBean.getIsAgree()) {
                                            ToastUtils.toast(mContext, "???????????????????????????");
                                            return;
                                        }
                                        if (2 == itemSignBean.getIsAgree() && TextUtils.isEmpty(itemSignBean.getReason())) {
                                            ToastUtils.toast(mContext, "??????????????????????????????");
                                            return;
                                        }
                                        showSignatureView();
                                        break;
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
                    case MultipleItem.ITEM_APPLY_DOSAGE:
                        switch (view.getId()) {
                            case R.id.add_dosage_iv:
                                //????????????
                                BaseUsageBean usageBean = (BaseUsageBean) multipleItem.getObject();
                                List<ExplosiveUsageBean> explosiveUsageBeans = usageBean.getUsageBeanList();
                                explosiveUsageBeans.add(new ExplosiveUsageBean("????????????????????????", 0, "???", "???"));
                                adapter.notifyItemChanged(position);
                                break;
                            default:
                                break;
                        }
                        break;
                    case MultipleItem.ITEM_RETURN_DOSAGE:
                        switch (view.getId()) {
                            case R.id.add_dosage_iv:
                                //????????????
                                BaseUsageBean returnUsaeBean = (BaseUsageBean) multipleItem.getObject();
                                List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean> explosiveUsageRetrunBeans = returnUsaeBean.getUsageReturnBeans();
                                explosiveUsageRetrunBeans.add(new UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean(0, "????????????????????????", 0, "???", "???", null));
                                adapter.notifyItemChanged(position);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        switch (view.getId()) {

                            case R.id.add_issue_iv:
                                //????????????
                                BaseUsageBean usageNumBean = (BaseUsageBean) multipleItem.getObject();
                                List<ExplosiveUsageNumberBean> explosiveUsageNumBeans = usageNumBean.getUsageNumberBeanList();
                                explosiveUsageNumBeans.add(new ExplosiveUsageNumberBean(0, "????????????????????????", "0", "0"));
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

    /**
     * ???????????????
     */
    protected void commitLogic(BaseAdapterDataBean baseAdapterDataBean) {
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
////                //??????????????????????????????
////                startActivityForResult(new Intent(this, HeadCropActivity.class).putExtra(HeadCropActivity.HEAD_PIC,
////                        path), BASE_REQUEST_RESULT);
////            }
//
//        }
    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {
        super.onLocationReceived(bdLocation);
        if (bdLocation != null) {
            lat = bdLocation.getLatitude();
            lng = bdLocation.getLongitude();
            address = bdLocation.getAddrStr();
            notifyLocationItem();
        }

    }

    /**
     * ????????????item
     */
    private void notifyLocationItem() {
        List<MultipleItem> arrays = adapter.getData();
        for (int i = 0; i < arrays.size(); i++) {
            MultipleItem array = arrays.get(i);
            if (MultipleItem.ITEM_LOCATION == array.getItemType()) {
                //??????
                LocationBean locationBean = (LocationBean) array.getObject();
                if (LOCATE_KEY.equals(locationBean.getKey())) {
                    locationBean.setAddress(address);
                    locationBean.setLatitude(String.valueOf(lat));
                    locationBean.setLongitude(String.valueOf(lng));
                    adapter.notifyItemChanged(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (FaceCheckActivity.FACE_CODE == resultCode) {
            if (data != null) {
                String headPath = data.getStringExtra(FaceCheckActivity.FACE_HEAD);
                if (faceCheckBean != null) {
                    faceCheckBean.setCheckSuccess(true);
                    faceCheckBean.setPersonHeadPic(headPath);
                    adapter.notifyItemChanged(currentPosition);
                }
            }
        }
        if (resultCode == LocateSelectionActivity.SELECT_ADDR) {
            //????????????
            lat = data.getDoubleExtra(LocateSelectionActivity.LAT, 0.0);
            lng = data.getDoubleExtra(LocateSelectionActivity.LNG, 0.0);
            address = data.getStringExtra(LocateSelectionActivity.ADDRNAME);
            notifyLocationItem();
        }
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {
        ArrayList<String> photos = new ArrayList<>();
        List<String> arrays = adapter.getData();
        for (String array : arrays) {
            if (!array.contains(BaseExplosiveActivity.SDCARD_TAG)) {
                array = UrlFormatUtil.getImageOriginalUrl(array);
            }
            photos.add(array);
        }
        //????????????
        startActivity(new Intent(mContext, ImageZoomActivity.class)
                .putExtra("paths", photos)
                .putExtra("item", position));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signature_view_save:
                if (gsv_signature.getTouched()) {
                    try {
                        String signPath = FileCacheUtils.getAppImagePath() + FileCacheUtils.SIGN_PIC_NAME;
                        //???????????????
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
                    ToastUtils.toast(mContext, "????????????");
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
                //??????
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
     * ?????????????????????
     */
    protected void showSignatureView() {

        bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.signature_view_layout, null);
        view.findViewById(R.id.signature_view_save).setOnClickListener(this);
        view.findViewById(R.id.signature_view_rewrite).setOnClickListener(this);
        view.findViewById(R.id.signature_view_cancel).setOnClickListener(this);
        //????????????
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
        releaseDialog();
        //??????????????????
        FileCacheUtils.clearImage(getSignPath(FileCacheUtils.SIGN_PIC_NAME));
    }

    /**
     * ??????adapter????????????
     * skipFilter  ????????????
     *
     * @return
     */
    protected BaseAdapterDataBean getBaseAdapterData(boolean skipFilter) {

        BaseAdapterDataBean bean = new BaseAdapterDataBean();
        ReceiveOrderDetailBean.DataBean receiveOrderBean = new ReceiveOrderDetailBean.DataBean();
        OutInMineRequest outInMineRequest = new OutInMineRequest();
        UseOrderDetailBean.DataBean useOrderBean = new UseOrderDetailBean.DataBean();
        MultipartBody.Builder builder = mPresenter.getPublishMultipartBody();
        List<MultipleItem> arrays = adapter.getData();
        for (MultipleItem item : arrays) {
            switch (item.getItemType()) {
                case MultipleItem.ITEM_FRAGMENT:
                    //????????????
                    FragmentPicBean fragmentPicBean = (FragmentPicBean) item.getObject();
                    List<String> photos = fragmentPicBean.getFragmentPics();
                    String name = fragmentPicBean.getPicName();
                    String msg = null;
                    switch (name) {
                        case MainContactInterface.ARRIVERE_PHOTO:
                        case MainContactInterface.USE_RECORD_PHOTO:
                            msg = "?????????????????????";
                            break;
                        default:
                            break;
                    }
                    if (!skipFilter) {
                        for (String photo : photos) {
                            if ("-1".equals(photo)) {
                                ToastUtils.toast(mContext, msg);
                                return null;
                            }
                        }
                    }

                    for (int i = 0; i < photos.size(); i++) {
                        String picPah = photos.get(i);
                        if (0 == i) {
                            if (MainContactInterface.ARRIVERE_PHOTO.equals(name)) {
                                builder.addFormDataPart("arrivePhoto", "arrivePhoto.jpeg",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                            } else if (MainContactInterface.USE_RECORD_PHOTO.equals(name)) {
                                useOrderBean.setUseBillUrl(picPah);
                            }


                        }
                    }

                    break;
                case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                    BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) item.getObject();

                    switch (baseNormalRecyclerviewBean.getRecyclerType()) {
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
                                    case MainContactInterface.APPLY_TIME:
                                        receiveOrderBean.setApplyTime(applyValue);
                                        useOrderBean.setApplyTime(applyValue);
                                        break;
                                    case MainContactInterface.PLAN_USE_START_TIME:
                                        useOrderBean.setEstimateStartUseTime(applyValue);
                                        break;
                                    case MainContactInterface.PLAN_USE_END_TIME:
                                        useOrderBean.setEstimateEndUseTime(applyValue);
                                        break;
                                    case MainContactInterface.APPLY_USER_UNIT_ADDR:
                                        receiveOrderBean.setApplyDepartmentAddress(applyValue);
                                        useOrderBean.setApplyDepartmentAddress(applyValue);
                                        break;
                                    case MainContactInterface.APPLICATION:
                                        useOrderBean.setRemarks(applyValue);
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
                    if (!skipFilter) {
                        if (TextUtils.isEmpty(timeValue)) {
                            ToastUtils.toast(mContext,"?????????"+timeBean.getTimeKey());
                            return null;
                        }
                    }

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
                    //??????
                    ItemSignBean signBean = (ItemSignBean) item.getObject();
                    if (!skipFilter) {
                        if (!StringTools.isStringValueOk(signBean.getSignPicPath())) {
                            switch (UserInfoManager.getDepartmentType()) {
                                //???1?????????2????????????3???????????????4???????????????5???????????????
                                case 1:
                                    if (MainContactInterface.SIGN_TITLE_UNIT.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "?????????");
                                        return null;
                                    }
                                    break;
                                case 2:
                                    if (MainContactInterface.SIGN_TITLE_POLICE.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "?????????");
                                        return null;
                                    }
                                    break;
                                case 3:
                                    if (MainContactInterface.SIGN_TITLE_BRIGADE.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "?????????");
                                        return null;
                                    }
                                    break;
                                case 4:
                                    if (MainContactInterface.SIGN_TITLE_LEADER.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "?????????");
                                        return null;
                                    }
                                    break;
                                case 5:
                                    if (MainContactInterface.ARRIVERE_SIGN.equals(signBean.getSignTitle())) {
                                        ToastUtils.toast(mContext, "?????????");
                                        return null;
                                    }
                                    break;
                                default:
                                    break;
                            }


                        }
                    }
                    //??????????????????

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
                        case MainContactInterface.ARRIVERE_SIGN:
                            receiveOrderBean.setArriveSign(signBean.getSignPicPath());
                            break;
                        default:
                            break;
                    }
                    break;

                case MultipleItem.ITEM_FACE_CHECK:
                    FaceCheckBean faceCheckBean = (FaceCheckBean) item.getObject();
                    String personName = faceCheckBean.getPersonName();
                    if (!skipFilter) {
                        if (!faceCheckBean.isCheckSuccess()) {
                            ToastUtils.toast(mContext,"?????????"+personName+"?????????");
                            return null;
                        }
                        if (TextUtils.isEmpty(faceCheckBean.getPersonSignPic())) {
                            ToastUtils.toast(mContext,personName+"?????????");
                            return null;
                        }
                    }

                    if (personName.contains(MainContactInterface.RECEIVER)) {
                        //?????????
                        outInMineRequest.setReceivePhoto(faceCheckBean.getPersonHeadPic());
                        outInMineRequest.setReceiveSign(faceCheckBean.getPersonSignPic());
                        useOrderBean.setReceivePhoto(faceCheckBean.getPersonHeadPic());
                        useOrderBean.setReceiveSign(faceCheckBean.getPersonSignPic());
                        useOrderBean.setReceiveId(faceCheckBean.getPersonId());
                    } else if (personName.contains(MainContactInterface.SAFER)) {
                        //?????????
                        outInMineRequest.setSafetyPhoto(faceCheckBean.getPersonHeadPic());
                        outInMineRequest.setSafetySign(faceCheckBean.getPersonSignPic());
                        useOrderBean.setSafetyPhoto(faceCheckBean.getPersonHeadPic());
                        useOrderBean.setSafetySign(faceCheckBean.getPersonSignPic());
                        useOrderBean.setSafetyId(faceCheckBean.getPersonId());

                    }
                    if (personName.contains(MainContactInterface.BLASTER)) {
                        //?????????
                        outInMineRequest.setBlasterPhoto(faceCheckBean.getPersonHeadPic());
                        outInMineRequest.setBlasterSign(faceCheckBean.getPersonSignPic());
                        useOrderBean.setBlasterPhoto(faceCheckBean.getPersonHeadPic());
                        useOrderBean.setBlasterSign(faceCheckBean.getPersonSignPic());
                        useOrderBean.setBlasterId(faceCheckBean.getPersonId());

                    } else {
                        //?????????
                        outInMineRequest.setSafekeepingPhoto(faceCheckBean.getPersonHeadPic());
                        outInMineRequest.setSafekeepingSign(faceCheckBean.getPersonSignPic());
                        useOrderBean.setSafekeepingPhoto(faceCheckBean.getPersonHeadPic());
                        useOrderBean.setSafekeepingSign(faceCheckBean.getPersonSignPic());
                        useOrderBean.setSafekeepingId(faceCheckBean.getPersonId());

                    }
                    break;
                case MultipleItem.ITEM_SELECT:
                    TextKeyValueBean selectBean = (TextKeyValueBean) item.getObject();
                    String selectValue = selectBean.getValue();
                    String selectKey = selectBean.getKey();
                    if (!skipFilter) {
                        if (TextUtils.isEmpty(selectValue)) {
                            ToastUtils.toast(mContext,"?????????"+selectKey);
                            return null;
                        }
                    }

                    int id = selectBean.getId();
                    switch (selectKey) {
                        case MainContactInterface.SAFER:
                            useOrderBean.setSafetyId(id);
                            useOrderBean.setSafetyName(selectValue);
                            useOrderBean.setUseSafetyId(id);
                            useOrderBean.setUseSafetyName(selectValue);
                            break;
                        case MainContactInterface.BLASTER:
                            useOrderBean.setBlasterId(id);
                            useOrderBean.setBlasterName(selectValue);
                            useOrderBean.setUseBlasterId(id);
                            useOrderBean.setUseBlasterName(selectValue);
                            break;
                        case MainContactInterface.MANAGER:
                            useOrderBean.setSafekeepingId(id);
                            useOrderBean.setSafekeepingName(selectValue);
                            useOrderBean.setUseSafekeepingId(id);
                            useOrderBean.setUseSafekeepingName(selectValue);
                            break;
                        case MainContactInterface.DELIVERY:
                            receiveOrderBean.setDeliveryUser(selectBean.getDeliveryBean());
                            break;
                        default:
                            break;
                    }

                    break;
                case MultipleItem.ITEM_TEXT:
                    TextKeyValueBean textValueBean = (TextKeyValueBean) item
                            .getObject();
                    switch (textValueBean.getKey()) {
                        case MainContactInterface.OUT_IN_MINE_TIME:
                            //????????????
                            useOrderBean.setGrantTime(textValueBean.getValue());
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
                            ToastUtils.toast(mContext, "?????????" + key);
                            return null;
                        }
                    }

                    String formKey = null;
                    switch (textValueEditBean.getKey()) {
                        case MainContactInterface.APPLICATION:
                            //??????
                            receiveOrderBean.setRemarks(value);
                            useOrderBean.setRemarks(value);
                            break;
                        case MainContactInterface.OUT_IN_MINE_TIME:
                            //????????????
                            useOrderBean.setGrantTime(value);
                            break;
                        case MainContactInterface.REMARK:
                            //??????
                            useOrderBean.setUseRemarks(value);
                            break;
                        default:
                            break;
                    }

                    break;
                case MultipleItem.ITEM_LOCATION:
                    LocationBean locationBean = (LocationBean) item.getObject();
                    switch (locationBean.getKey()) {
                        case MainContactInterface.DELIVERY_ADDR:
                            receiveOrderBean.setArriveAddress(locationBean.getAddress());
                            receiveOrderBean.setArriveLatitude(locationBean.getLatitude());
                            receiveOrderBean.setArriveLongitude(locationBean.getLongitude());
                            break;
                        case MainContactInterface.OUT_IN_MINE_ADDR:
                            //????????????
                            outInMineRequest.setGrantUseAddress(locationBean.getAddress());
                            outInMineRequest.setGrantUseLatitude(locationBean.getLatitude());
                            outInMineRequest.setGrantUseLongitude(locationBean.getLongitude());
                            useOrderBean.setGrantUseAddress(locationBean.getAddress());
                            useOrderBean.setGrantUseLatitude(locationBean.getLatitude());
                            useOrderBean.setGrantUseLongitude(locationBean.getLongitude());
                            break;
                        default:
                            receiveOrderBean.setUseAddress(locationBean.getAddress());
                            receiveOrderBean.setUseLatitude(locationBean.getLatitude());
                            receiveOrderBean.setUseLongitude(locationBean.getLongitude());
                            useOrderBean.setEstimateUseAddress(locationBean.getAddress());
                            useOrderBean.setEstimateUseLatitude(locationBean.getLatitude());
                            useOrderBean.setEstimateUseLongitude(locationBean.getLongitude());
                            break;
                    }


                    break;

                case MultipleItem.ITEM_APPLY_DOSAGE:
                    BaseUsageBean usageBean = (BaseUsageBean) item.getObject();

                    List<ExplosiveUsageBean> explosiveUsageBeans = usageBean.getUsageBeanList();
                    if (!skipFilter) {
                        for (ExplosiveUsageBean explosiveUsageBean : explosiveUsageBeans) {

                            String typeName = explosiveUsageBean.getTypeName();
                            int amount = explosiveUsageBean.getApplyQuantity();
                            if ("????????????????????????".equals(typeName)) {
                                ToastUtils.toast(mContext,"????????????????????????");
                                return null;
                            }
                            if (amount<1) {
                                ToastUtils.toast(mContext,"?????????"+typeName+"???????????????");
                                return null;
                            }

                        }
                    }

                    receiveOrderBean.setExplosiveUsage(explosiveUsageBeans);
                    useOrderBean.setExplosiveUsage(explosiveUsageBeans);
                    break;
                case MultipleItem.ITEM_RETURN_DOSAGE:
                    BaseUsageBean returnDosage = (BaseUsageBean) item.getObject();
                    List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean> returnBeans = returnDosage.getUsageReturnBeans();
                    if (2==returnDosage.getIsReturn()&&!skipFilter) {
                        for (UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean returnBean : returnBeans) {
                            String typeName = returnBean.getTypeName();
                            int amount = returnBean.getApplyQuantity();
                            List<UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean> returnNums = returnBean.getUsageNumberReturn();
                            if ("????????????????????????".equals(typeName)) {
                                ToastUtils.toast(mContext,"????????????????????????");
                                return null;
                            }
                            if (amount<1) {
                                ToastUtils.toast(mContext,"?????????????????????");
                                return null;
                            }
                            for (UseOrderDetailBean.DataBean.ExplosiveUsageReturnBean.UsageNumberReturnBean returnNum : returnNums) {
                                String  startNum = returnNum.getStartNumber();
                                String  endNum = returnNum.getEndNumber();
                                if (TextUtils.isEmpty(startNum)) {
                                    ToastUtils.toast(mContext,"?????????????????????");
                                    return null;
                                }
                                if (TextUtils.isEmpty(endNum)) {
                                    ToastUtils.toast(mContext,"?????????????????????");
                                    return null;
                                }
                            }
                        }
                    }


                    useOrderBean.setExplosiveUsageReturn(returnBeans);
                    useOrderBean.setIsReturn(returnDosage.getIsReturn());
                    break;
                case MultipleItem.ITEM_ISSUE_NO:


                    BaseUsageBean usageNumBean = (BaseUsageBean) item.getObject();
                    List<ExplosiveUsageNumberBean> explosiveUsageNumberBeans = usageNumBean.getUsageNumberBeanList();
                    if (!skipFilter) {
                        for (ExplosiveUsageNumberBean numberBean : explosiveUsageNumberBeans) {

                            String typeName = numberBean.getTypeName();
                            String startNumber = numberBean.getStartNumber();
                            String endNum = numberBean.getEndNumber();
                            if ("????????????????????????".equals(typeName)) {
                                ToastUtils.toast(mContext,"????????????????????????");
                                return null;
                            }
                            if (TextUtils.isEmpty(startNumber)) {
                                ToastUtils.toast(mContext,"?????????????????????");
                                return null;
                            }
                            if (TextUtils.isEmpty(endNum)) {
                                ToastUtils.toast(mContext,"?????????????????????");
                                return null;
                            }

                        }
                    }
                    receiveOrderBean.setExplosiveUsageNumber(explosiveUsageNumberBeans);
                    useOrderBean.setExplosiveUsageNumber(explosiveUsageNumberBeans);
                    outInMineRequest.setNumber(explosiveUsageNumberBeans);
                    break;
                default:
                    break;
            }
        }
        bean.setBuilder(builder);
        bean.setOutInMineRequest(outInMineRequest);
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
                        itemSignBean.setReason(1 == itemSignBean.getIsAgree() ? "????????????" : itemSignBean.getReason());
                    }
                }
                if (faceCheckBean != null) {
                    faceCheckBean.setPersonSignPic(path);
                }
                adapter.notifyItemChanged(currentPosition);
                break;

            case MainContactInterface.SAFER:
                //???????????????????????????
                initMineOfReceiver(o, 1);
                break;
            case MainContactInterface.BLASTER:
                //???????????????????????????
                initMineOfReceiver(o, 2);
                break;
            case MainContactInterface.MANAGER:
                //???????????????????????????
                initMineOfReceiver(o, 3);
                break;
            case AppHttpPath.GET_DELIVERY_LIST:
                DeliveryListBean deliveryListBean = (DeliveryListBean) o;
                if (deliveryListBean != null) {
                    List<DeliveryListBean.DataBean> arrays = deliveryListBean.getData();
                    if (arrays != null) {
                        if (multiselectBottomDialog == null) {
                            multiselectBottomDialog = new BaseMultiselectBottomDialog();
                        }
                        List<ItemCheckBoxBean> checkBoxBeans = new ArrayList<>();
                        for (DeliveryListBean.DataBean array : arrays) {
                            checkBoxBeans.add(new ItemCheckBoxBean(array.getUserId(), array.getUsername()));
                        }
                        multiselectBottomDialog.initAdapterData(new BaseNormalRecyclerviewBean(MultipleItem.BASE_RECYCLERVIEW_TYPE_CHECKBOX, 1, new RecycleCheckBoxBean(checkBoxBeans, "", false)));
                        multiselectBottomDialog.show(getSupportFragmentManager(), "array");
                        multiselectBottomDialog.setOnBottomDialogCallBack(new BaseMultiselectBottomDialog.OnDialogItemClick() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                            }

                            @Override
                            public void onConfirmClick(BaseQuickAdapter checkAdapter) {
                                List<ItemCheckBoxBean> arrays = checkAdapter.getData();
                                List<DeliveryListBean.DataBean> selectedUsers = new ArrayList<>();
                                StringBuilder sb = new StringBuilder(arrays.size());
                                for (ItemCheckBoxBean array : arrays) {
                                    if (array.isSelecte()) {
                                        sb.append(array.getItemName() + "\u3000");
                                        selectedUsers.add(new DeliveryListBean.DataBean(array.getItemId(), array.getItemName()));
                                    }

                                }
                                if (sb.toString().trim().length() == 0) {
                                    ToastUtils.toast(mContext, "?????????????????????");
                                    return;
                                }
                                multiselectBottomDialog.dismiss();
                                selectBean.setValue(sb.toString());
                                selectBean.setDeliveryBean(selectedUsers);
                                adapter.notifyItemChanged(currentPosition);
                            }
                        });
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * @param o
     * @param type ???????????????1????????????2????????????3????????????
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

    /**
     * ??????dialog
     */
    private void releaseDialog() {
        if (multiselectBottomDialog != null) {
            if (multiselectBottomDialog.isAdded()) {
                multiselectBottomDialog.setOnBottomDialogCallBack(null);
                if (multiselectBottomDialog.getDialog().isShowing()) {
                    multiselectBottomDialog.dismiss();
                }
            }
        }
        multiselectBottomDialog = null;
    }
}
