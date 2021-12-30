package com.juntai.wisdom.explorsive.bean;


/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/7/3 11:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/7/3 11:03
 */
public class BaseNormalRecyclerviewBean {


    private  int recyclerType ;
    private  int layoutManagerType;
    //当是GridLayoutManager的时候 列数
    private  int spanCount;
    private Object  object ;

    public BaseNormalRecyclerviewBean(int recyclerType, int layoutManagerType, Object object) {
        this.recyclerType = recyclerType;
        this.layoutManagerType = layoutManagerType;
        this.object = object;
    }

    public BaseNormalRecyclerviewBean(int recyclerType, int layoutManagerType, int spanCount, Object object) {
        this.recyclerType = recyclerType;
        this.layoutManagerType = layoutManagerType;
        this.spanCount = spanCount;
        this.object = object;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public void setRecyclerType(int recyclerType) {
        this.recyclerType = recyclerType;
    }

    public int getLayoutManagerType() {
        return layoutManagerType;
    }

    public void setLayoutManagerType(int layoutManagerType) {
        this.layoutManagerType = layoutManagerType;
    }

    public int getRecyclerType() {
        return recyclerType;
    }


    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
