package com.juntai.wisdom.explorsive.main;


import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppNetModule;
import com.juntai.wisdom.explorsive.base.CheckBoxAdapter;
import com.juntai.wisdom.explorsive.base.TextKeyValueAdapter;
import com.juntai.wisdom.explorsive.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.explorsive.bean.BaseNormalRecyclerviewBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveTypeBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveUsageNumberBean;
import com.juntai.wisdom.explorsive.bean.FaceCheckBean;
import com.juntai.wisdom.explorsive.bean.FragmentPicBean;
import com.juntai.wisdom.explorsive.bean.ImportantTagBean;
import com.juntai.wisdom.explorsive.bean.ItemSignBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.RecycleCheckBoxBean;
import com.juntai.wisdom.explorsive.bean.TextKeyValueBean;
import com.juntai.wisdom.explorsive.bean.TimeBean;
import com.juntai.wisdom.explorsive.main.mine.DosageAdapter;
import com.juntai.wisdom.explorsive.main.mine.DosageNumberAdapter;
import com.juntai.wisdom.explorsive.utils.HawkProperty;
import com.juntai.wisdom.explorsive.utils.StringTools;
import com.juntai.wisdom.explorsive.utils.UrlFormatUtil;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import okhttp3.FormBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-22 10:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-22 10:42
 */
public class HandlerOrderAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private boolean isDetail = false;//是否是详情模式
    private boolean canSelect = false;//是否可操作模式
    private boolean canAddIssue = false;//是否可操作模式
    private boolean canDeletePic = false;//是否可操作模式
    private FragmentManager mFragmentManager;


    public void setCanDeletePic(boolean canDeletePic) {
        this.canDeletePic = canDeletePic;
    }

    public void setCanAddIssue(boolean canAddIssue) {
        this.canAddIssue = canAddIssue;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HandlerOrderAdapter(List<MultipleItem> data, FragmentManager mFragmentManager) {
        super(data);
        addItemType(MultipleItem.ITEM_TITILE_BIG, R.layout.item_layout_type_title_big);
        addItemType(MultipleItem.ITEM_TITILE_SMALL, R.layout.item_layout_type_title_small);
        addItemType(MultipleItem.ITEM_EDIT, R.layout.item_layout_type_edit);
        addItemType(MultipleItem.ITEM_TEXT, R.layout.item_layout_type_text);
        addItemType(MultipleItem.ITEM_LOCATION, R.layout.item_layout_location);
        addItemType(MultipleItem.ITEM_SELECT_TIME, R.layout.item_layout_location);
        addItemType(MultipleItem.ITEM_SELECT, R.layout.item_text_select);
        addItemType(MultipleItem.ITEM_SIGN, R.layout.item_layout_type_sign);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);
        addItemType(MultipleItem.ITEM_APPLY_DOSAGE, R.layout.item_layout_apply_dosage);
        addItemType(MultipleItem.ITEM_ISSUE_NO, R.layout.item_layout_issue_no);
        addItemType(MultipleItem.ITEM_FRAGMENT, R.layout.item_layout_fragment);
        addItemType(MultipleItem.ITEM_FACE_CHECK, R.layout.item_face_check);
        this.mFragmentManager = mFragmentManager;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }


    public void setCanSelect(boolean canSelect) {
        this.canSelect = canSelect;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {

            case MultipleItem.ITEM_FACE_CHECK:
                FaceCheckBean  faceCheckBean = (FaceCheckBean) item.getObject();
                helper.setText(R.id.face_title_tv,faceCheckBean.getPersonName());
                if (!isDetail) {
                    helper.addOnClickListener(R.id.user_face_iv);
                }
                if (faceCheckBean.isCheckSuccess()) {
                    helper.setVisible(R.id.face_status_tv,true);
                    ImageLoadUtil.loadCirImgNoCrash(mContext,UrlFormatUtil.getImageOriginalUrl(faceCheckBean.getPersonHeadPic()),helper.getView(R.id.user_face_iv));
                }else {
                    helper.setVisible(R.id.face_status_tv,false);
                    helper.setImageResource(R.id.user_face_iv,R.mipmap.face_check_icon);
                }
                if (TextUtils.isEmpty(faceCheckBean.getPersonSignPic())) {
                    helper.setVisible(R.id.user_sign_iv,false);
                    helper.setGone(R.id.face_start_sign_tv,true);
                    if (!isDetail) {
                        helper.addOnClickListener(R.id.face_start_sign_tv);
                    }
                }else {
                    helper.setVisible(R.id.user_sign_iv,true);
                    helper.setGone(R.id.face_start_sign_tv,false);
                    ImageLoadUtil.loadImage(mContext,UrlFormatUtil.getImageOriginalUrl(faceCheckBean.getPersonSignPic()),helper.getView(R.id.user_sign_iv));

                }
                break;
            case MultipleItem.ITEM_FRAGMENT:
                //
                FragmentPicBean picBean = (FragmentPicBean) item.getObject();
                SelectPhotosFragment fragment = (SelectPhotosFragment) mFragmentManager.findFragmentById(R.id.photo_fg);
                fragment.setObject(picBean);

                if (canDeletePic||!isDetail) {
                    fragment.setPhotoDelateable(true).setMaxCount(1);
                } else {
                    fragment.setPhotoDelateable(false).setMaxCount(picBean.getFragmentPics().size());
                    if (!picBean.getFragmentPics().isEmpty()) {
                        fragment.setIcons(picBean.getFragmentPics());
                    }
                }

                fragment.setSpanCount(1).setOnPicLoadSuccessCallBack(new SelectPhotosFragment.OnPicLoadSuccessCallBack() {
                    @Override
                    public void loadSuccess(List<String> icons) {
                        FragmentPicBean picBean = (FragmentPicBean) fragment.getObject();
                        picBean.setFragmentPics(icons);
                    }
                });


                break;
            case MultipleItem.ITEM_ISSUE_NO:
                if (canAddIssue || !isDetail) {
                    helper.setGone(R.id.add_issue_iv, true);
                } else {
                    helper.setGone(R.id.add_issue_iv, false);
                }
                helper.addOnClickListener(R.id.add_issue_iv);
                List<ExplosiveUsageNumberBean> numberBeans = (List<ExplosiveUsageNumberBean>) item.getObject();
                DosageNumberAdapter numberAdapter = new DosageNumberAdapter(R.layout.dosage_number_item);
                numberAdapter.setDetail(!canAddIssue);
                RecyclerView dosageNumRv = helper.getView(R.id.apply_issue_rv);
                LinearLayoutManager dosageNumManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                dosageNumRv.setLayoutManager(dosageNumManager);
                dosageNumRv.setAdapter(numberAdapter);
                numberAdapter.setNewData(numberBeans);
                numberAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        ExplosiveUsageNumberBean numberBean = (ExplosiveUsageNumberBean) adapter.getData().get(position);
                        List<ExplosiveUsageBean> explosiveUsageBeans = Hawk.get(HawkProperty.CURRENT_SELECTED_EXPLOSIVE_TYPES);
                        PickerManager.getInstance().showOptionPicker(mContext, explosiveUsageBeans, new PickerManager.OnOptionPickerSelectedListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                ExplosiveUsageBean dataBean = explosiveUsageBeans.get(options1);
                                numberBean.setUsageId(dataBean.getUsageId());
                                numberBean.setTypeName(dataBean.getTypeName());
                                adapter.notifyItemChanged(position);
                            }
                        });
                    }
                });
                break;
            case MultipleItem.ITEM_APPLY_DOSAGE:
                if (isDetail) {
                    helper.setGone(R.id.add_dosage_iv, false);
                } else {
                    helper.setGone(R.id.add_dosage_iv, true);
                }
                helper.addOnClickListener(R.id.add_dosage_iv);
                List<ExplosiveUsageBean> explosiveUsageBeans = (List<ExplosiveUsageBean>) item.getObject();
                DosageAdapter dosageAdapter = new DosageAdapter(R.layout.dosage_item);
                dosageAdapter.setDetail(isDetail);
                RecyclerView dosageRv = helper.getView(R.id.apply_dosage_rv);
                LinearLayoutManager dosageManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                dosageRv.setLayoutManager(dosageManager);
                dosageRv.setAdapter(dosageAdapter);
                dosageAdapter.setNewData(explosiveUsageBeans);
                dosageAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        ExplosiveUsageBean explosiveUsageBean = (ExplosiveUsageBean) adapter.getData().get(position);
                        AppNetModule
                                .createrRetrofit()
                                .getExplosiveTypes(getBaseBuilder().build())
                                .compose(RxScheduler.ObsIoMain((BaseExplosiveActivity) mContext))
                                .subscribe(new BaseObserver<ExplosiveTypeBean>((BaseExplosiveActivity) mContext) {
                                    @Override
                                    public void onSuccess(ExplosiveTypeBean o) {
                                        PickerManager.getInstance().showOptionPicker(mContext, o.getData(), new PickerManager.OnOptionPickerSelectedListener() {
                                            @Override
                                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                                ExplosiveTypeBean.DataBean dataBean = o.getData().get(options1);
                                                explosiveUsageBean.setTypeName(dataBean.getName());
                                                explosiveUsageBean.setTypeUnit(dataBean.getUnit());
                                                adapter.notifyItemChanged(position);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onError(String msg) {
                                    }
                                });
                    }
                });
                break;


            case MultipleItem.ITEM_TITILE_BIG:
                helper.setText(R.id.item_big_title_tv, (String) item.getObject());
                break;
            case MultipleItem.ITEM_TITILE_SMALL:
                ImportantTagBean importantTagBean = (ImportantTagBean) item.getObject();
                helper.setGone(R.id.important_tag_tv, importantTagBean.isImportant());
                helper.setText(R.id.item_small_title_tv, importantTagBean.getTitleName());
                break;
            case MultipleItem.ITEM_TEXT:
                TextKeyValueBean textBean = (TextKeyValueBean) item.getObject();
                helper.setText(R.id.item_text_key,textBean.getKey());
                helper.setText(R.id.item_text_value,textBean.getValue());
                break;
            case MultipleItem.ITEM_EDIT:
                TextKeyValueBean textValueEditBean = (TextKeyValueBean) item.getObject();
                EditText editText = helper.getView(R.id.edit_value_et);
                if (isDetail) {
                    if (TextUtils.isEmpty(textValueEditBean.getValue())) {
                        textValueEditBean.setValue("暂无");
                    }
                    editText.setClickable(false);
                    editText.setFocusable(false);
                    helper.setBackgroundRes(R.id.edit_value_et, R.drawable.sp_filled_gray_lighter);
                } else {
                    editText.setClickable(true);
                    editText.setFocusable(true);
                    helper.setBackgroundRes(R.id.edit_value_et, R.drawable.stroke_gray_square_bg);

                }
                int editType = textValueEditBean.getType();
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) editText.getLayoutParams();
                if (0 == editType) {
                    lp.height = DisplayUtil.dp2px(mContext, 32);
                    editText.setGravity(Gravity.CENTER_VERTICAL);
                    editText.setSingleLine(true);
                } else {
                    lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    editText.setMinimumHeight(DisplayUtil.dp2px(mContext, 100));
                    editText.setGravity(Gravity.TOP);
                    editText.setSingleLine(false);
                }
                editText.setLayoutParams(lp);
                editText.setTag(textValueEditBean);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                        String str = s.toString().trim();
                        editBean.setValue(str);
                    }
                });
//                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
//                        if (!hasFocus) {
//                            if (checkEdittextValueFormatCallBack != null) {
//                                checkEdittextValueFormatCallBack.checkEdittextValueFormat(editBean);
//                            }
//                        }
//                    }
//                });
                editText.setHint(textValueEditBean.getHint());
                editText.setText(textValueEditBean.getValue());
                break;

            case MultipleItem.ITEM_SELECT:
                TextKeyValueBean textValueSelectBean = (TextKeyValueBean) item.getObject();
                helper.setText(R.id.item_small_title_tv, textValueSelectBean.getKey());
                TextView textViewTv = helper.getView(R.id.select_value_tv);
                String selectTextValue = textValueSelectBean.getValue();
                if (!isDetail || canSelect) {
                    helper.addOnClickListener(R.id.select_value_tv);
                    helper.addOnClickListener(R.id.tool_pic_iv);
                    helper.setBackgroundRes(R.id.select_value_tv, R.drawable.stroke_gray_square_bg);
                    helper.setGone(R.id.select_arrow_right_iv, true);
                } else {
                    helper.setGone(R.id.select_arrow_right_iv, false);
                    helper.setBackgroundRes(R.id.select_value_tv, R.drawable.sp_filled_gray_lighter);
                }
                textViewTv.setTag(textValueSelectBean);
                textViewTv.setHint(textValueSelectBean.getHint());
                if (selectTextValue.contains("\\n")) {
                    selectTextValue = selectTextValue.replace("\\n", "\n");
                }
                textViewTv.setText(selectTextValue);

                break;
            case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                //recycleview
                BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) item.getObject();
                int layoutType = baseNormalRecyclerviewBean.getLayoutManagerType();
                LinearLayoutManager manager = null;
                BaseQuickAdapter adapter = null;
                switch (layoutType) {
                    case 0:
                        manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL
                                , false);
                        break;
                    case 1:
                        manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL
                                , false);
                        break;
                    case 2:
                        manager = new GridLayoutManager(mContext, baseNormalRecyclerviewBean.getSpanCount());
                        break;
                    default:
                        break;
                }
                RecyclerView recyclerView = helper.getView(R.id.item_normal_rv);
                switch (baseNormalRecyclerviewBean.getRecyclerType()) {
                    case MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE:
                        adapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
                        List<TextKeyValueBean> arrays = (List<TextKeyValueBean>) baseNormalRecyclerviewBean.getObject();
                        adapter.setNewData(arrays);
                        break;
                    case MultipleItem.BASE_RECYCLERVIEW_TYPE_CHECKBOX:
                        RecycleCheckBoxBean recycleBean = (RecycleCheckBoxBean) baseNormalRecyclerviewBean.getObject();
                        adapter = new CheckBoxAdapter(R.layout.item_checkboxes,
                                recycleBean.getData(), recycleBean.isSigleSelect());
                        break;
                    default:
                        break;
                }
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
                break;
            case MultipleItem.ITEM_SELECT_TIME:
                TimeBean timeBean = (TimeBean) item.getObject();
                if (isDetail) {
                    helper.setGone(R.id.location_iv, false);
                } else {
                    helper.addOnClickListener(R.id.location_ll);
                    helper.setGone(R.id.location_iv, true);
                }
                TextView timeValueTv = helper.getView(R.id.location_tv);
                timeValueTv.setText(timeBean.getTimeValue());
                timeValueTv.setHint(timeBean.getHint());
                helper.setText(R.id.locate_key_tv, timeBean.getTimeKey());

                break;
            case MultipleItem.ITEM_LOCATION:
                LocationBean locationBean = (LocationBean) item.getObject();
                if (isDetail) {
                    helper.setGone(R.id.location_iv, false);
                } else {
                    helper.addOnClickListener(R.id.location_ll);
                    helper.setGone(R.id.location_iv, true);
                }
                if (!TextUtils.isEmpty(locationBean.getAddress())) {
                    helper.setText(R.id.location_tv, locationBean.getAddress());
                }
                helper.setText(R.id.locate_key_tv, locationBean.getKey());

                break;

            case MultipleItem.ITEM_SIGN:
                ItemSignBean signBean = (ItemSignBean) item.getObject();
                int signStatus = signBean.getSignStatus();

                helper.setGone(R.id.reason_detail_tv, false);
                helper.addOnClickListener(R.id.start_sign_tv);
                helper.addOnClickListener(R.id.reject_apply_tv);
                helper.addOnClickListener(R.id.agree_apply_tv);

                helper.setText(R.id.sign_title_tv, signBean.getSignTitle());
                //原因
                EditText reasonEt = helper.getView(R.id.reason_et);
                reasonEt.setText(signBean.getReason());
                reasonEt.setTag(signBean);
                reasonEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ItemSignBean bean = (ItemSignBean) reasonEt.getTag();
                        String str = s.toString().trim();
                        bean.setReason(str);
                    }
                });
                //签名图片
                if (StringTools.isStringValueOk(signBean.getSignPicPath())) {
                    ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(signBean.getSignPicPath()),
                            helper.getView(R.id.user_sign_iv));
                }
                if (StringTools.isStringValueOk(signBean.getDepartmentSignPath())) {
                    ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(signBean.getDepartmentSignPath()),
                            helper.getView(R.id.department_sign_iv));
                }else {
                    ImageLoadUtil.loadImage(mContext, R.color.white,
                            helper.getView(R.id.department_sign_iv));
                }
                //同意和拒绝相关的逻辑
                helper.setTextColor(R.id.reject_apply_tv, ContextCompat.getColor(mContext, R.color.black));
                helper.setTextColor(R.id.agree_apply_tv, ContextCompat.getColor(mContext, R.color.black));

                if (0 == signBean.getIsAgree()) {
                    //还未选择
                    helper.setBackgroundRes(R.id.reject_apply_tv, R.drawable.stroke_red_square_bg);
                    helper.setBackgroundRes(R.id.agree_apply_tv, R.drawable.stroke_accent_square_bg);
                    helper.setTextColor(R.id.agree_apply_tv, ContextCompat.getColor(mContext, R.color.colorAccent));
                    helper.setTextColor(R.id.reject_apply_tv, ContextCompat.getColor(mContext, R.color.red));
                    helper.setGone(R.id.reason_et, false);
                } else if (1 == signBean.getIsAgree()) {
                    //同意
                    helper.setBackgroundRes(R.id.reject_apply_tv, R.drawable.stroke_red_square_bg);
                    helper.setBackgroundRes(R.id.agree_apply_tv, R.drawable.sp_filled_accent);
                    helper.setTextColor(R.id.agree_apply_tv, ContextCompat.getColor(mContext, R.color.white));
                    helper.setGone(R.id.reason_et, false);
                } else {
                    //不同意
                    helper.setBackgroundRes(R.id.reject_apply_tv, R.drawable.sp_filled_red);
                    helper.setTextColor(R.id.reject_apply_tv, ContextCompat.getColor(mContext, R.color.white));
                    helper.setBackgroundRes(R.id.agree_apply_tv, R.drawable.stroke_accent_square_bg);
                    helper.setGone(R.id.reason_et, true);
                }
                if (0 == signStatus) {
                    //新建的申请
                    helper.setGone(R.id.start_sign_tv, true);
                    helper.setGone(R.id.status_check_g, false);
                    helper.setGone(R.id.status_detail_g, false);
                } else if (1 == signStatus) {
                    //审核
                    helper.setGone(R.id.start_sign_tv, true);
                    helper.setGone(R.id.status_check_g, true);
                    helper.setGone(R.id.status_detail_g, false);
                } else if (2 == signStatus) {
                    //详情
                    helper.setGone(R.id.start_sign_tv, false);
                    helper.setGone(R.id.status_check_g, false);
                    helper.setGone(R.id.status_detail_g, true);
                    helper.setGone(R.id.reason_et, false);
                    helper.setText(R.id.sign_time_tv, signBean.getSignTime());
                    if (!TextUtils.isEmpty(signBean.getReason())) {
                        helper.setGone(R.id.reason_detail_tv, true);
                        helper.setText(R.id.reason_detail_tv, signBean.getReason());
                    }

                } else {
                    //还未审批
                    helper.setGone(R.id.start_sign_tv, false);
                    helper.setGone(R.id.status_check_g, false);
                    helper.setVisible(R.id.status_detail_g, false);
                }
                break;

            default:
                break;
        }
    }

    /**
     * 获取builder
     *
     * @return
     */
    public FormBody.Builder getBaseBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("token", UserInfoManager.getUserToken());
        builder.add("mobile", UserInfoManager.getMobile());
        return builder;
    }

}
