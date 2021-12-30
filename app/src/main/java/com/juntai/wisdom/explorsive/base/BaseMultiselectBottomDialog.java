package com.juntai.wisdom.explorsive.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.bean.BaseNormalRecyclerviewBean;
import com.juntai.wisdom.explorsive.bean.MultipleItem;
import com.juntai.wisdom.explorsive.bean.RecycleCheckBoxBean;
import com.juntai.wisdom.explorsive.bean.TextKeyValueBean;


import java.lang.reflect.Field;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  仿微信底部弹框
 * @date 2020/4/16 13:59
 */
public class BaseMultiselectBottomDialog extends DialogFragment implements View.OnClickListener {
    LinearLayoutManager manager = null;
    private BaseNormalRecyclerviewBean baseNormalRecyclerviewBean;
    BaseQuickAdapter adapter = null;
    private RecyclerView mBaseBottomDialogRv;
    private OnDialogItemClick onItemClick;

    public BaseMultiselectBottomDialog setOnBottomDialogCallBack(OnDialogItemClick onItemClick) {
        this.onItemClick = onItemClick;
        return this;
    }

    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (getShowsDialog()) {
            setShowsDialog(false);
        }
        super.onActivityCreated(savedInstanceState);
        setShowsDialog(true);

        View view = getView();
        if (view != null) {
            if (view.getParent() != null) {
                throw new IllegalStateException(
                        "DialogFragment can not be attached to a container view");
            }
            getDialog().setContentView(view);
        }
        final Activity activity = getActivity();
        if (activity != null) {
            getDialog().setOwnerActivity(activity);
        }
        if (savedInstanceState != null) {
            Bundle dialogState = savedInstanceState.getBundle(SAVED_DIALOG_STATE_TAG);
            if (dialogState != null) {
                getDialog().onRestoreInstanceState(dialogState);
            }
        }
        // 设置宽度为屏宽、位置靠近屏幕底部
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.base_bottom_multi_select_dialog, null);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            //在每个add事务前增加一个remove事务，防止连续的add
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            //同一实例使用不同的tag会异常,这里捕获一下
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        try {//利用反射获取listenersHandler
            Field field = Dialog.class.getDeclaredField("mListenersHandler");
            field.setAccessible(true);
            Handler mListenersHandler = (Handler) field.get(getDialog());
            if (mListenersHandler != null) {
                mListenersHandler.removeCallbacksAndMessages(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(View view) {
        mBaseBottomDialogRv = (RecyclerView) view.findViewById(R.id.base_bottom_dialog_rv);
         view.findViewById(R.id.base_bottom_dialog_cancel_tv).setOnClickListener(this);
        view.findViewById(R.id.base_bottom_dialog_confirm_tv).setOnClickListener(this);
        //recycleview
        int layoutType = baseNormalRecyclerviewBean.getLayoutManagerType();

        switch (layoutType) {
            case 0:
                manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL
                        , false);
                break;
            case 1:
                manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL
                        , false);
                break;
            case 2:
                manager = new GridLayoutManager(getContext(), baseNormalRecyclerviewBean.getSpanCount());
                break;
            default:
                break;
        }
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
                adapter.setNewData(recycleBean.getData());
                break;
            default:
                break;
        }
        mBaseBottomDialogRv.setAdapter(adapter);
        mBaseBottomDialogRv.setLayoutManager(manager);
        setCancelable(false);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(adapter, view, position);
                }
            }
        });
    }

    public void initAdapterData(BaseNormalRecyclerviewBean baseNormalRecyclerviewBean) {
        this.baseNormalRecyclerviewBean = baseNormalRecyclerviewBean;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.base_bottom_dialog_cancel_tv) {
            dismiss();
        } else if (v.getId() == R.id.base_bottom_dialog_confirm_tv) {
            if (onItemClick != null) {
                onItemClick.onConfirmClick(adapter);
            }
        }
    }

    public interface OnDialogItemClick {
        void onItemClick(BaseQuickAdapter adapter, View view, int position);

        void onConfirmClick(BaseQuickAdapter adapter);
    }
}
