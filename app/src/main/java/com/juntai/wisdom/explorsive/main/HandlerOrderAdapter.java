package com.juntai.wisdom.explorsive.main;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import com.juntai.wisdom.explorsive.bean.BaseNormalRecyclerviewBean;
import com.juntai.wisdom.explorsive.bean.ExplosiveTypeBean;
import com.juntai.wisdom.explorsive.bean.ImportantTagBean;
import com.juntai.wisdom.explorsive.bean.ItemSignBean;
import com.juntai.wisdom.explorsive.bean.LocationBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.ReceiveOrderDetailBean;
import com.juntai.wisdom.explorsive.bean.TextKeyValueBean;
import com.juntai.wisdom.explorsive.main.mine.DosageAdapter;
import com.juntai.wisdom.explorsive.main.mine.receive.AddReceiveApplyActivity;
import com.juntai.wisdom.explorsive.utils.StringTools;
import com.juntai.wisdom.explorsive.utils.UrlFormatUtil;
import com.juntai.wisdom.explorsive.utils.UserInfoManager;

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

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HandlerOrderAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_TITILE_BIG, R.layout.item_layout_type_title_big);
        addItemType(MultipleItem.ITEM_TITILE_SMALL, R.layout.item_layout_type_title_small);
        addItemType(MultipleItem.ITEM_EDIT, R.layout.item_layout_type_edit);
        addItemType(MultipleItem.ITEM_LOCATION, R.layout.item_layout_location);
        addItemType(MultipleItem.ITEM_SIGN, R.layout.item_layout_type_sign);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);
        addItemType(MultipleItem.ITEM_APPLY_DOSAGE, R.layout.item_layout_apply_dosage);


    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {

            case MultipleItem.ITEM_APPLY_DOSAGE:
                helper.addOnClickListener(R.id.add_dosage_iv);
                List<ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean> explosiveUsageBeans = (List<ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean>) item.getObject();
                DosageAdapter dosageAdapter = new DosageAdapter(R.layout.dosage_item);
                RecyclerView dosageRv = helper.getView(R.id.apply_dosage_rv);
                LinearLayoutManager dosageManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                dosageRv.setLayoutManager(dosageManager);
                dosageRv.setAdapter(dosageAdapter);
                dosageAdapter.setNewData(explosiveUsageBeans);
                dosageAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean   explosiveUsageBean = (ReceiveOrderDetailBean.DataBean.ExplosiveUsageBean) adapter.getData().get(position);
                        AppNetModule
                                .createrRetrofit()
                                .getExplosiveTypes(getBaseBuilder().build())
                                .compose(RxScheduler.ObsIoMain((AddReceiveApplyActivity)mContext))
                                .subscribe(new BaseObserver<ExplosiveTypeBean>((AddReceiveApplyActivity)mContext) {
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
            case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                //recycleview
                BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) item.getObject();
                RecyclerView recyclerView = helper.getView(R.id.item_normal_rv);
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL
                        , false);
                BaseQuickAdapter adapter = baseNormalRecyclerviewBean.getAdapter();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
                switch (baseNormalRecyclerviewBean.getType()) {
                    case MultipleItem.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE:
                        List<TextKeyValueBean> arrays = (List<TextKeyValueBean>) baseNormalRecyclerviewBean.getObject();
                        adapter.setNewData(arrays);
                        break;
                    default:
                        break;
                }
                break;
            case MultipleItem.ITEM_LOCATION:
                LocationBean locationBean = (LocationBean) item.getObject();
                if (!isDetail) {
                    helper.addOnClickListener(R.id.location_ll);
                    helper.setGone(R.id.location_iv, true);
                } else {
                    helper.setGone(R.id.location_iv, false);
                }
                if (!TextUtils.isEmpty(locationBean.getAddress())) {
                    helper.setText(R.id.location_tv, locationBean.getAddress());
                }
                helper.setText(R.id.locate_key_tv, locationBean.getKey());

                break;

            case MultipleItem.ITEM_SIGN:
                ItemSignBean signBean = (ItemSignBean) item.getObject();
                if (signBean.isCanSign()) {
                    helper.addOnClickListener(R.id.sign_ll);
                }
                int gravity = signBean.getLayoutGravity();
                LinearLayout signLl = helper.getView(R.id.item_sign_ll);
                ImageView signIv = helper.getView(R.id.sign_name_iv);
                if (0 == gravity) {
                    helper.setGone(R.id.sign_tag, true);
                    signLl.setGravity(Gravity.LEFT);
                } else {
                    helper.setGone(R.id.sign_tag, false);
                    signLl.setGravity(Gravity.RIGHT);
                }
                helper.setText(R.id.sign_name_tv, signBean.getSignName());
                if (StringTools.isStringValueOk(signBean.getSignPicPath())) {
                    ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(signBean.getSignPicPath()),
                            signIv);
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
