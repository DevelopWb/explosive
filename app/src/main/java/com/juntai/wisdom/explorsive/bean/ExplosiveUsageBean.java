package com.juntai.wisdom.explorsive.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-25 17:13
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-25 17:13
 */
public class ExplosiveUsageBean implements IPickerViewData {
    /**
     * typeName : 煤矿许用瞬发电雷管
     * applyQuantity : 5000
     * quantityWords : 五千
     * typeUnit : 发
     * explosiveUsageNumber : [{"startNumber":"10001","endNumber":"10007"},{"startNumber":"10011","endNumber":"10016"}]
     */

    private String typeName;
    private int applyQuantity;
    private String quantityWords;
    private String typeUnit;
    private Integer usageId;

    public ExplosiveUsageBean(String typeName, int applyQuantity, String quantityWords, String typeUnit) {
        this.typeName = typeName;
        this.applyQuantity = applyQuantity;
        this.quantityWords = quantityWords;
        this.typeUnit = typeUnit;
    }

    public Integer getUsageId() {
        return usageId;
    }

    public void setUsageId(Integer usageId) {
        this.usageId = usageId;
    }

    private List<ExplosiveUsageNumberBean> explosiveUsageNumber;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getApplyQuantity() {
        return applyQuantity;
    }

    public void setApplyQuantity(int applyQuantity) {
        this.applyQuantity = applyQuantity;
    }

    public String getQuantityWords() {
        return quantityWords;
    }

    public void setQuantityWords(String quantityWords) {
        this.quantityWords = quantityWords;
    }

    public String getTypeUnit() {
        return typeUnit;
    }

    public void setTypeUnit(String typeUnit) {
        this.typeUnit = typeUnit;
    }

    public List<ExplosiveUsageNumberBean> getExplosiveUsageNumber() {
        return explosiveUsageNumber;
    }

    public void setExplosiveUsageNumber(List<ExplosiveUsageNumberBean> explosiveUsageNumber) {
        this.explosiveUsageNumber = explosiveUsageNumber;
    }

    @Override
    public String getPickerViewText() {
        return typeName;
    }

    public static class ExplosiveUsageNumberBean {
        /**
         * startNumber : 10001
         * endNumber : 10007
         */

        private String startNumber;
        private String endNumber;

        public String getStartNumber() {
            return startNumber;
        }

        public void setStartNumber(String startNumber) {
            this.startNumber = startNumber;
        }

        public String getEndNumber() {
            return endNumber;
        }

        public void setEndNumber(String endNumber) {
            this.endNumber = endNumber;
        }
    }
}
