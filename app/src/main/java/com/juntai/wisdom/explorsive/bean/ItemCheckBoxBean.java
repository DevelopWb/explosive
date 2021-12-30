package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  checkbox
 * @CreateDate: 2021/1/29 10:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/29 10:11
 */
public class ItemCheckBoxBean {

    private int itemId;//序号
    private String itemName;
    private boolean selecte;

    public ItemCheckBoxBean(int id, String name) {
        this.itemId = id;
        this.itemName = name;
    }

    public ItemCheckBoxBean(String key, boolean selecte) {
        this.itemName = key;
        this.selecte = selecte;
    }

    public ItemCheckBoxBean(int index, String key, boolean selecte) {
        this.itemId = index;
        this.itemName = key;
        this.selecte = selecte;
    }

    public int getItemId() {
        return itemId;
    }


    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName == null ? "" : itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? "" : itemName;
    }

    public boolean isSelecte() {
        return selecte;
    }

    public void setSelecte(boolean selecte) {
        this.selecte = selecte;
    }
}
