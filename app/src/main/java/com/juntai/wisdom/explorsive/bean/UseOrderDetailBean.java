package com.juntai.wisdom.explorsive.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-25 17:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-25 17:04
 */
public class UseOrderDetailBean extends BaseResult {


    /**
     * data : {"id":2,"applyNumber":"202112191154023","applyUserId":7,"applyUsername":"悔创阿里杰克马","applyPhone":"13792975283","applyDepartmentId":5,"applyDepartmentName":"天天矿场","applyDepartmentAddress":"青海省刚察县","applyTime":"2021-12-20 15:03:07","estimateStartUseTime":"2021-12-20 14:48:32","estimateEndUseTime":"2021-12-22 10:48:32","estimateUseAddress":"青海省刚察县","estimateUseLongitude":"118.515156184","estimateUseLatitude":"35.6416515","remarks":"备注","safetyId":1,"safetyName":"铁柱","blasterId":2,"blasterName":"二蛋","safekeepingId":3,"safekeepingName":"狗剩","applySign":"/2021-12-20/7f04024cf8dc4df38d088f5f81378954.jpeg","policeDepartmentId":4,"policeSign":"/2021-12-20/600e963d9d6a401a960a146b4ffd1e6b.jpeg","policeUsername":"王进喜","policeSignTime":"2021-12-25 10:03:58","policeDepartmentSeal":"/2021-12-19/dc02deac6bb2479183b26f3bc05d0ac8.jpg","policeRemarks":"同意申请","grantTime":"2021-12-20 16:59:26","grantUseAddress":"青海省刚察县","grantUseLongitude":"118.515156184","grantUseLatitude":"35.6416515","receiveSign":"/2021-12-20/ddf4cd7af65641f7af16d35f13b1246e.jpeg","safetySign":"/2021-12-20/e1ca62009f6f44208f71b8fa8416136a.jpeg","blasterSign":"/2021-12-20/600e963d9d6a401a960a146b4ffd1e6b.jpeg","safekeepingSign":"/2021-12-20/ddf4cd7af65641f7af16d35f13b1246e.jpeg","useTime":"2021-12-21 11:04:07","useAddress":"青海省刚察县","useLongitude":"118.515156184","useLatitude":"35.6416515","useUserId":2,"useUsername":"王进喜","useSafetyId":10,"useSafetyName":"一无所有王健林","useBlasterId":11,"useBlasterName":"不知妻美刘强东","useSafekeepingId":12,"useSafekeepingName":"普通家庭马化腾","useBillUrl":"/2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg","useRemarks":"备注","isReturn":2,"returnTime":"2021-12-21 11:04:07","stat":4,"isVoid":1,"refuseRemarks":null,"explosiveUsage":[{"mineApplyId":2,"typeName":"煤矿许用瞬发电雷管","applyQuantity":1000,"quantityWords":"一千","typeUnit":"发","explosiveUsageNumber":[{"usageId":3,"startNumber":"10001","endNumber":"10007"},{"usageId":3,"startNumber":"10011","endNumber":"10016"}]},{"mineApplyId":2,"typeName":"煤矿许用毫秒延期电雷管","applyQuantity":2000,"quantityWords":"两千","typeUnit":"个","explosiveUsageNumber":[{"usageId":4,"startNumber":"10001","endNumber":"10007"}]}],"explosiveUsageReturn":[{"mineApplyId":2,"typeName":"煤矿许用瞬发电雷管","applyQuantity":500,"quantityWords":"一千","typeUnit":"发","usageNumberReturn":[{"usageId":5,"startNumber":"10001","endNumber":"10007"},{"usageId":5,"startNumber":"10011","endNumber":"10016"}]},{"mineApplyId":2,"typeName":"煤矿许用毫秒延期电雷管","applyQuantity":300,"quantityWords":"两千","typeUnit":"个","usageNumberReturn":[{"usageId":6,"startNumber":"10001","endNumber":"10007"}]}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * applyNumber : 202112191154023
         * applyUserId : 7
         * applyUsername : 悔创阿里杰克马
         * applyPhone : 13792975283
         * applyDepartmentId : 5
         * applyDepartmentName : 天天矿场
         * applyDepartmentAddress : 青海省刚察县
         * applyTime : 2021-12-20 15:03:07
         * estimateStartUseTime : 2021-12-20 14:48:32
         * estimateEndUseTime : 2021-12-22 10:48:32
         * estimateUseAddress : 青海省刚察县
         * estimateUseLongitude : 118.515156184
         * estimateUseLatitude : 35.6416515
         * remarks : 备注
         * safetyId : 1
         * safetyName : 铁柱
         * blasterId : 2
         * blasterName : 二蛋
         * safekeepingId : 3
         * safekeepingName : 狗剩
         * applySign : /2021-12-20/7f04024cf8dc4df38d088f5f81378954.jpeg
         * policeDepartmentId : 4
         * policeSign : /2021-12-20/600e963d9d6a401a960a146b4ffd1e6b.jpeg
         * policeUsername : 王进喜
         * policeSignTime : 2021-12-25 10:03:58
         * policeDepartmentSeal : /2021-12-19/dc02deac6bb2479183b26f3bc05d0ac8.jpg
         * policeRemarks : 同意申请
         * grantTime : 2021-12-20 16:59:26
         * grantUseAddress : 青海省刚察县
         * grantUseLongitude : 118.515156184
         * grantUseLatitude : 35.6416515
         * receiveSign : /2021-12-20/ddf4cd7af65641f7af16d35f13b1246e.jpeg
         * safetySign : /2021-12-20/e1ca62009f6f44208f71b8fa8416136a.jpeg
         * blasterSign : /2021-12-20/600e963d9d6a401a960a146b4ffd1e6b.jpeg
         * safekeepingSign : /2021-12-20/ddf4cd7af65641f7af16d35f13b1246e.jpeg
         * useTime : 2021-12-21 11:04:07
         * useAddress : 青海省刚察县
         * useLongitude : 118.515156184
         * useLatitude : 35.6416515
         * useUserId : 2
         * useUsername : 王进喜
         * useSafetyId : 10
         * useSafetyName : 一无所有王健林
         * useBlasterId : 11
         * useBlasterName : 不知妻美刘强东
         * useSafekeepingId : 12
         * useSafekeepingName : 普通家庭马化腾
         * useBillUrl : /2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg
         * useRemarks : 备注
         * isReturn : 2
         * returnTime : 2021-12-21 11:04:07
         * stat : 4
         * isVoid : 1
         * refuseRemarks : null
         * explosiveUsage : [{"mineApplyId":2,"typeName":"煤矿许用瞬发电雷管","applyQuantity":1000,"quantityWords":"一千","typeUnit":"发","explosiveUsageNumber":[{"usageId":3,"startNumber":"10001","endNumber":"10007"},{"usageId":3,"startNumber":"10011","endNumber":"10016"}]},{"mineApplyId":2,"typeName":"煤矿许用毫秒延期电雷管","applyQuantity":2000,"quantityWords":"两千","typeUnit":"个","explosiveUsageNumber":[{"usageId":4,"startNumber":"10001","endNumber":"10007"}]}]
         * explosiveUsageReturn : [{"mineApplyId":2,"typeName":"煤矿许用瞬发电雷管","applyQuantity":500,"quantityWords":"一千","typeUnit":"发","usageNumberReturn":[{"usageId":5,"startNumber":"10001","endNumber":"10007"},{"usageId":5,"startNumber":"10011","endNumber":"10016"}]},{"mineApplyId":2,"typeName":"煤矿许用毫秒延期电雷管","applyQuantity":300,"quantityWords":"两千","typeUnit":"个","usageNumberReturn":[{"usageId":6,"startNumber":"10001","endNumber":"10007"}]}]
         */

        private int id;
        private String applyNumber;
        private int applyUserId;
        private String applyUsername;
        private String applyPhone;
        private int applyDepartmentId;
        private String applyDepartmentName;
        private String applyDepartmentAddress;
        private String applyDepartmentSeal;

        private String applyTime;
        private String estimateStartUseTime;
        private String estimateEndUseTime;
        private String estimateUseAddress;
        private String estimateUseLongitude;
        private String estimateUseLatitude;
        private String remarks;
        private int safetyId;
        private String safetyName;
        private int blasterId;
        private int signStatus;

        private String blasterName;
        private int safekeepingId;
        private String safekeepingName;
        private String applySign;
        private int policeDepartmentId;
        private String policeSign;
        private String policeUsername;
        private String policeSignTime;
        private String policeDepartmentSeal;
        private String policeRemarks;
        private int policeVoid;
        private String grantTime;
        private String grantUseAddress;
        private String grantUseLongitude;
        private String grantUseLatitude;
        private String receiveSign;
        private String safetySign;
        private String blasterSign;
        private String safekeepingSign;
        private String useTime;
        private String useAddress;
        private String useLongitude;
        private String useLatitude;
        private int useUserId;
        private String useUsername;
        private int useSafetyId;
        private String useSafetyName;
        private int useBlasterId;
        private String useBlasterName;
        private int useSafekeepingId;
        private String useSafekeepingName;
        private String useBillUrl;
        private String useRemarks;
        private int isReturn;
        private String returnTime;
        private int stat;
        private int isVoid;
        private String mobile;
        private String token;
        private String refuseRemarks;
        private List<ExplosiveUsageBean> explosiveUsage;
        private List<ExplosiveUsageReturnBean> explosiveUsageReturn;

        public int getPoliceVoid() {
            return policeVoid;
        }

        public void setPoliceVoid(int policeVoid) {
            this.policeVoid = policeVoid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getApplyNumber() {
            return applyNumber == null ? "" : applyNumber;
        }

        public void setApplyNumber(String applyNumber) {
            this.applyNumber = applyNumber == null ? "" : applyNumber;
        }

        public int getApplyUserId() {
            return applyUserId;
        }

        public void setApplyUserId(int applyUserId) {
            this.applyUserId = applyUserId;
        }

        public String getApplyUsername() {
            return applyUsername == null ? "" : applyUsername;
        }

        public void setApplyUsername(String applyUsername) {
            this.applyUsername = applyUsername == null ? "" : applyUsername;
        }

        public String getApplyPhone() {
            return applyPhone == null ? "" : applyPhone;
        }

        public void setApplyPhone(String applyPhone) {
            this.applyPhone = applyPhone == null ? "" : applyPhone;
        }

        public int getApplyDepartmentId() {
            return applyDepartmentId;
        }

        public void setApplyDepartmentId(int applyDepartmentId) {
            this.applyDepartmentId = applyDepartmentId;
        }

        public String getApplyDepartmentName() {
            return applyDepartmentName == null ? "" : applyDepartmentName;
        }

        public void setApplyDepartmentName(String applyDepartmentName) {
            this.applyDepartmentName = applyDepartmentName == null ? "" : applyDepartmentName;
        }

        public String getApplyDepartmentAddress() {
            return applyDepartmentAddress == null ? "" : applyDepartmentAddress;
        }

        public void setApplyDepartmentAddress(String applyDepartmentAddress) {
            this.applyDepartmentAddress = applyDepartmentAddress == null ? "" : applyDepartmentAddress;
        }

        public String getApplyDepartmentSeal() {
            return applyDepartmentSeal == null ? "" : applyDepartmentSeal;
        }

        public void setApplyDepartmentSeal(String applyDepartmentSeal) {
            this.applyDepartmentSeal = applyDepartmentSeal == null ? "" : applyDepartmentSeal;
        }

        public String getApplyTime() {
            return applyTime == null ? "" : applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime == null ? "" : applyTime;
        }

        public String getEstimateStartUseTime() {
            return estimateStartUseTime == null ? "" : estimateStartUseTime;
        }

        public void setEstimateStartUseTime(String estimateStartUseTime) {
            this.estimateStartUseTime = estimateStartUseTime == null ? "" : estimateStartUseTime;
        }

        public String getEstimateEndUseTime() {
            return estimateEndUseTime == null ? "" : estimateEndUseTime;
        }

        public void setEstimateEndUseTime(String estimateEndUseTime) {
            this.estimateEndUseTime = estimateEndUseTime == null ? "" : estimateEndUseTime;
        }

        public String getEstimateUseAddress() {
            return estimateUseAddress == null ? "" : estimateUseAddress;
        }

        public void setEstimateUseAddress(String estimateUseAddress) {
            this.estimateUseAddress = estimateUseAddress == null ? "" : estimateUseAddress;
        }

        public String getEstimateUseLongitude() {
            return estimateUseLongitude == null ? "" : estimateUseLongitude;
        }

        public void setEstimateUseLongitude(String estimateUseLongitude) {
            this.estimateUseLongitude = estimateUseLongitude == null ? "" : estimateUseLongitude;
        }

        public String getEstimateUseLatitude() {
            return estimateUseLatitude == null ? "" : estimateUseLatitude;
        }

        public void setEstimateUseLatitude(String estimateUseLatitude) {
            this.estimateUseLatitude = estimateUseLatitude == null ? "" : estimateUseLatitude;
        }

        public String getRemarks() {
            return remarks == null ? "" : remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks == null ? "" : remarks;
        }

        public int getSafetyId() {
            return safetyId;
        }

        public void setSafetyId(int safetyId) {
            this.safetyId = safetyId;
        }

        public String getSafetyName() {
            return safetyName == null ? "" : safetyName;
        }

        public void setSafetyName(String safetyName) {
            this.safetyName = safetyName == null ? "" : safetyName;
        }

        public int getBlasterId() {
            return blasterId;
        }

        public void setBlasterId(int blasterId) {
            this.blasterId = blasterId;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }

        public String getBlasterName() {
            return blasterName == null ? "" : blasterName;
        }

        public void setBlasterName(String blasterName) {
            this.blasterName = blasterName == null ? "" : blasterName;
        }

        public int getSafekeepingId() {
            return safekeepingId;
        }

        public void setSafekeepingId(int safekeepingId) {
            this.safekeepingId = safekeepingId;
        }

        public String getSafekeepingName() {
            return safekeepingName == null ? "" : safekeepingName;
        }

        public void setSafekeepingName(String safekeepingName) {
            this.safekeepingName = safekeepingName == null ? "" : safekeepingName;
        }

        public String getApplySign() {
            return applySign == null ? "" : applySign;
        }

        public void setApplySign(String applySign) {
            this.applySign = applySign == null ? "" : applySign;
        }

        public int getPoliceDepartmentId() {
            return policeDepartmentId;
        }

        public void setPoliceDepartmentId(int policeDepartmentId) {
            this.policeDepartmentId = policeDepartmentId;
        }

        public String getPoliceSign() {
            return policeSign == null ? "" : policeSign;
        }

        public void setPoliceSign(String policeSign) {
            this.policeSign = policeSign == null ? "" : policeSign;
        }

        public String getPoliceUsername() {
            return policeUsername == null ? "" : policeUsername;
        }

        public void setPoliceUsername(String policeUsername) {
            this.policeUsername = policeUsername == null ? "" : policeUsername;
        }

        public String getPoliceSignTime() {
            return policeSignTime == null ? "" : policeSignTime;
        }

        public void setPoliceSignTime(String policeSignTime) {
            this.policeSignTime = policeSignTime == null ? "" : policeSignTime;
        }

        public String getPoliceDepartmentSeal() {
            return policeDepartmentSeal == null ? "" : policeDepartmentSeal;
        }

        public void setPoliceDepartmentSeal(String policeDepartmentSeal) {
            this.policeDepartmentSeal = policeDepartmentSeal == null ? "" : policeDepartmentSeal;
        }

        public String getPoliceRemarks() {
            return policeRemarks == null ? "" : policeRemarks;
        }

        public void setPoliceRemarks(String policeRemarks) {
            this.policeRemarks = policeRemarks == null ? "" : policeRemarks;
        }

        public String getGrantTime() {
            return grantTime == null ? "" : grantTime;
        }

        public void setGrantTime(String grantTime) {
            this.grantTime = grantTime == null ? "" : grantTime;
        }

        public String getGrantUseAddress() {
            return grantUseAddress == null ? "" : grantUseAddress;
        }

        public void setGrantUseAddress(String grantUseAddress) {
            this.grantUseAddress = grantUseAddress == null ? "" : grantUseAddress;
        }

        public String getGrantUseLongitude() {
            return grantUseLongitude == null ? "" : grantUseLongitude;
        }

        public void setGrantUseLongitude(String grantUseLongitude) {
            this.grantUseLongitude = grantUseLongitude == null ? "" : grantUseLongitude;
        }

        public String getGrantUseLatitude() {
            return grantUseLatitude == null ? "" : grantUseLatitude;
        }

        public void setGrantUseLatitude(String grantUseLatitude) {
            this.grantUseLatitude = grantUseLatitude == null ? "" : grantUseLatitude;
        }

        public String getReceiveSign() {
            return receiveSign == null ? "" : receiveSign;
        }

        public void setReceiveSign(String receiveSign) {
            this.receiveSign = receiveSign == null ? "" : receiveSign;
        }

        public String getSafetySign() {
            return safetySign == null ? "" : safetySign;
        }

        public void setSafetySign(String safetySign) {
            this.safetySign = safetySign == null ? "" : safetySign;
        }

        public String getBlasterSign() {
            return blasterSign == null ? "" : blasterSign;
        }

        public void setBlasterSign(String blasterSign) {
            this.blasterSign = blasterSign == null ? "" : blasterSign;
        }

        public String getSafekeepingSign() {
            return safekeepingSign == null ? "" : safekeepingSign;
        }

        public void setSafekeepingSign(String safekeepingSign) {
            this.safekeepingSign = safekeepingSign == null ? "" : safekeepingSign;
        }

        public String getUseTime() {
            return useTime == null ? "" : useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime == null ? "" : useTime;
        }

        public String getUseAddress() {
            return useAddress == null ? "" : useAddress;
        }

        public void setUseAddress(String useAddress) {
            this.useAddress = useAddress == null ? "" : useAddress;
        }

        public String getUseLongitude() {
            return useLongitude == null ? "" : useLongitude;
        }

        public void setUseLongitude(String useLongitude) {
            this.useLongitude = useLongitude == null ? "" : useLongitude;
        }

        public String getUseLatitude() {
            return useLatitude == null ? "" : useLatitude;
        }

        public void setUseLatitude(String useLatitude) {
            this.useLatitude = useLatitude == null ? "" : useLatitude;
        }

        public int getUseUserId() {
            return useUserId;
        }

        public void setUseUserId(int useUserId) {
            this.useUserId = useUserId;
        }

        public String getUseUsername() {
            return useUsername == null ? "" : useUsername;
        }

        public void setUseUsername(String useUsername) {
            this.useUsername = useUsername == null ? "" : useUsername;
        }

        public int getUseSafetyId() {
            return useSafetyId;
        }

        public void setUseSafetyId(int useSafetyId) {
            this.useSafetyId = useSafetyId;
        }

        public String getUseSafetyName() {
            return useSafetyName == null ? "" : useSafetyName;
        }

        public void setUseSafetyName(String useSafetyName) {
            this.useSafetyName = useSafetyName == null ? "" : useSafetyName;
        }

        public int getUseBlasterId() {
            return useBlasterId;
        }

        public void setUseBlasterId(int useBlasterId) {
            this.useBlasterId = useBlasterId;
        }

        public String getUseBlasterName() {
            return useBlasterName == null ? "" : useBlasterName;
        }

        public void setUseBlasterName(String useBlasterName) {
            this.useBlasterName = useBlasterName == null ? "" : useBlasterName;
        }

        public int getUseSafekeepingId() {
            return useSafekeepingId;
        }

        public void setUseSafekeepingId(int useSafekeepingId) {
            this.useSafekeepingId = useSafekeepingId;
        }

        public String getUseSafekeepingName() {
            return useSafekeepingName == null ? "" : useSafekeepingName;
        }

        public void setUseSafekeepingName(String useSafekeepingName) {
            this.useSafekeepingName = useSafekeepingName == null ? "" : useSafekeepingName;
        }

        public String getUseBillUrl() {
            return useBillUrl == null ? "" : useBillUrl;
        }

        public void setUseBillUrl(String useBillUrl) {
            this.useBillUrl = useBillUrl == null ? "" : useBillUrl;
        }

        public String getUseRemarks() {
            return useRemarks == null ? "" : useRemarks;
        }

        public void setUseRemarks(String useRemarks) {
            this.useRemarks = useRemarks == null ? "" : useRemarks;
        }

        public int getIsReturn() {
            return isReturn;
        }

        public void setIsReturn(int isReturn) {
            this.isReturn = isReturn;
        }

        public String getReturnTime() {
            return returnTime == null ? "" : returnTime;
        }

        public void setReturnTime(String returnTime) {
            this.returnTime = returnTime == null ? "" : returnTime;
        }

        public int getStat() {
            return stat;
        }

        public void setStat(int stat) {
            this.stat = stat;
        }

        public int getIsVoid() {
            return isVoid;
        }

        public void setIsVoid(int isVoid) {
            this.isVoid = isVoid;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile == null ? "" : mobile;
        }

        public String getToken() {
            return token == null ? "" : token;
        }

        public void setToken(String token) {
            this.token = token == null ? "" : token;
        }

        public String getRefuseRemarks() {
            return refuseRemarks == null ? "" : refuseRemarks;
        }

        public void setRefuseRemarks(String refuseRemarks) {
            this.refuseRemarks = refuseRemarks == null ? "" : refuseRemarks;
        }

        public List<ExplosiveUsageBean> getExplosiveUsage() {
            if (explosiveUsage == null) {
                return new ArrayList<>();
            }
            return explosiveUsage;
        }

        public void setExplosiveUsage(List<ExplosiveUsageBean> explosiveUsage) {
            this.explosiveUsage = explosiveUsage;
        }

        public List<ExplosiveUsageReturnBean> getExplosiveUsageReturn() {
            if (explosiveUsageReturn == null) {
                return new ArrayList<>();
            }
            return explosiveUsageReturn;
        }

        public void setExplosiveUsageReturn(List<ExplosiveUsageReturnBean> explosiveUsageReturn) {
            this.explosiveUsageReturn = explosiveUsageReturn;
        }

        public static class ExplosiveUsageReturnBean {
            /**
             * mineApplyId : 2
             * typeName : 煤矿许用瞬发电雷管
             * applyQuantity : 500
             * quantityWords : 一千
             * typeUnit : 发
             * usageNumberReturn : [{"usageId":5,"startNumber":"10001","endNumber":"10007"},{"usageId":5,"startNumber":"10011","endNumber":"10016"}]
             */

            private int mineApplyId;
            private String typeName;
            private int applyQuantity;
            private String quantityWords;
            private String typeUnit;
            private List<UsageNumberReturnBean> usageNumberReturn;

            public int getMineApplyId() {
                return mineApplyId;
            }

            public void setMineApplyId(int mineApplyId) {
                this.mineApplyId = mineApplyId;
            }

            public String getTypeName() {
                return typeName == null ? "" : typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName == null ? "" : typeName;
            }

            public int getApplyQuantity() {
                return applyQuantity;
            }

            public void setApplyQuantity(int applyQuantity) {
                this.applyQuantity = applyQuantity;
            }

            public String getQuantityWords() {
                return quantityWords == null ? "" : quantityWords;
            }

            public void setQuantityWords(String quantityWords) {
                this.quantityWords = quantityWords == null ? "" : quantityWords;
            }

            public String getTypeUnit() {
                return typeUnit == null ? "" : typeUnit;
            }

            public void setTypeUnit(String typeUnit) {
                this.typeUnit = typeUnit == null ? "" : typeUnit;
            }

            public List<UsageNumberReturnBean> getUsageNumberReturn() {
                if (usageNumberReturn == null) {
                    return new ArrayList<>();
                }
                return usageNumberReturn;
            }

            public void setUsageNumberReturn(List<UsageNumberReturnBean> usageNumberReturn) {
                this.usageNumberReturn = usageNumberReturn;
            }

            public static class UsageNumberReturnBean {
                /**
                 * usageId : 5
                 * startNumber : 10001
                 * endNumber : 10007
                 */

                private int usageId;
                private String startNumber;
                private String endNumber;

                public int getUsageId() {
                    return usageId;
                }

                public void setUsageId(int usageId) {
                    this.usageId = usageId;
                }

                public String getStartNumber() {
                    return startNumber == null ? "" : startNumber;
                }

                public void setStartNumber(String startNumber) {
                    this.startNumber = startNumber == null ? "" : startNumber;
                }

                public String getEndNumber() {
                    return endNumber == null ? "" : endNumber;
                }

                public void setEndNumber(String endNumber) {
                    this.endNumber = endNumber == null ? "" : endNumber;
                }
            }
        }
    }
}
