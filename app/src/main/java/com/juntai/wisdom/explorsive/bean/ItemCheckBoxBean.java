package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  checkbox
 * @CreateDate: 2021/1/29 10:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/29 10:11
 */
public class ItemCheckBoxBean {

    private int id;//序号
    private String name;
    private boolean selecte;

    public ItemCheckBoxBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ItemCheckBoxBean(String key, boolean selecte) {
        this.name = key;
        this.selecte = selecte;
    }

    public ItemCheckBoxBean(int index, String key, boolean selecte) {
        this.id = index;
        this.name = key;
        this.selecte = selecte;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public boolean isSelecte() {
        return selecte;
    }

    public void setSelecte(boolean selecte) {
        this.selecte = selecte;
    }
}
