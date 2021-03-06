package com.juntai.wisdom.explorsive.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-22 9:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-22 9:37
 */
public class ReceiveOrderDetailBean  extends BaseResult {


    /**
     * data : {"id":2,"applyNumber":"202112191154023","applyUserId":7,"applyUsername":"悔创阿里杰克马","applyPhone":"13792975283","applyDepartmentId":5,"applyDepartmentName":"tomato153","applyDepartmentAddress":"青海省刚察县","applyTime":"2021-12-19 13:52:11","useAddress":"青海省刚察县","useLongitude":"118.515156184","useLatitude":"35.6416515","remarks":"备注","applySign":"/2fc39f48adf24c66915239c0918af2de/2021-11-24/d33afd53e5564f45b18efb356766698f.png","policeDepartmentId":4,"policeSign":"/2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg","policeUsername":"铁人王进喜","brigadeDepartmentId":2,"brigadeSign":"/2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg","brigadeUsername":"铁人王进喜","leaderDepartmentId":1,"leaderSign":"/2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg","leaderUsername":"铁人王进喜","deliveryTime":"2021-12-19 17:06:42","deliveryAddress":"青海省刚察县","deliveryLongitude":"118.515156184","deliveryLatitude":"35.6416515","arriveTime":"2021-12-20 09:12:30","arriveAddress":"青海省刚察县","arriveLongitude":"118.515156184","arriveLatitude":"35.6416515","arrivePicture":"/2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg","arriveSign":"/2fc39f48adf24c66915239c0918af2de/2021-11-24/d33afd53e5564f45b18efb356766698f.png","stat":6,"isVoid":1,"refuseRemarks":null,"explosiveUsage":[{"typeName":"煤矿许用瞬发电雷管","applyQuantity":5000,"quantityWords":"五千","typeUnit":"发","explosiveUsageNumber":[{"startNumber":"10001","endNumber":"10007"},{"startNumber":"10011","endNumber":"10016"}]},{"typeName":"煤矿许用毫秒延期电雷管","applyQuantity":6000,"quantityWords":"六千","typeUnit":"个","explosiveUsageNumber":[{"startNumber":"20011","endNumber":"20016"}]}],"deliveryUser":[{"username":"王进喜"},{"username":"杰克马"}]}
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
         * applyDepartmentName : tomato153
         * applyDepartmentAddress : 青海省刚察县
         * applyTime : 2021-12-19 13:52:11
         * useAddress : 青海省刚察县
         * useLongitude : 118.515156184
         * useLatitude : 35.6416515
         * remarks : 备注
         * applySign : /2fc39f48adf24c66915239c0918af2de/2021-11-24/d33afd53e5564f45b18efb356766698f.png
         * policeDepartmentId : 4
         * policeSign : /2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg
         * policeUsername : 铁人王进喜
         * brigadeDepartmentId : 2
         * brigadeSign : /2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg
         * brigadeUsername : 铁人王进喜
         * leaderDepartmentId : 1
         * leaderSign : /2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg
         * leaderUsername : 铁人王进喜
         * deliveryTime : 2021-12-19 17:06:42
         * deliveryAddress : 青海省刚察县
         * deliveryLongitude : 118.515156184
         * deliveryLatitude : 35.6416515
         * arriveTime : 2021-12-20 09:12:30
         * arriveAddress : 青海省刚察县
         * arriveLongitude : 118.515156184
         * arriveLatitude : 35.6416515
         * arrivePicture : /2021-12-19/6cc2005b6fc14b91beb6929941bb6e87.jpg
         * arriveSign : /2fc39f48adf24c66915239c0918af2de/2021-11-24/d33afd53e5564f45b18efb356766698f.png
         * stat : 6
         * isVoid : 1
         * refuseRemarks : null
         * explosiveUsage : [{"typeName":"煤矿许用瞬发电雷管","applyQuantity":5000,"quantityWords":"五千","typeUnit":"发","explosiveUsageNumber":[{"startNumber":"10001","endNumber":"10007"},{"startNumber":"10011","endNumber":"10016"}]},{"typeName":"煤矿许用毫秒延期电雷管","applyQuantity":6000,"quantityWords":"六千","typeUnit":"个","explosiveUsageNumber":[{"startNumber":"20011","endNumber":"20016"}]}]
         * deliveryUser : [{"username":"王进喜"},{"username":"杰克马"}]
         */

        private int id;
        private String applyNumber;
        private String mobile;
        private String token;
        private int applyUserId;
        private String applyUsername;
        private String applyPhone;
        private int applyDepartmentId;
        private String applyDepartmentName;
        private String applyDepartmentAddress;
        private String applyDepartmentSeal;
        private String applyTime;
        private String useAddress;
        private String useLongitude;
        private String useLatitude;
        private String remarks;
        private String applySign;
        private int policeVoid;
        private int brigadeVoid;
        private int leaderVoid;
        private String policeRemarks;
        private String brigadeRemarks;
        private String leaderRemarks;
        private int policeDepartmentId;
        private String policeSign;
        private String policeDepartmentSeal;
        private String policeUsername;
        private int brigadeDepartmentId;
        private String brigadeSign;
        private String brigadeUsername;
        private String brigadeDepartmentSeal;
        private int leaderDepartmentId;
        private String leaderSign;
        private String leaderUsername;
        private String leaderDepartmentSeal;
        private String deliveryTime;
        private String deliveryAddress;
        private String deliveryLongitude;
        private String deliveryLatitude;
        private String arriveTime;
        private String arriveAddress;
        private String arriveLongitude;
        private String arriveLatitude;
        private String arrivePicture;
        private String arriveSign;
        private int stat;
        private int signStatus;
        private int isVoid;
        private String refuseRemarks;
        private List<ExplosiveUsageBean> explosiveUsage;
        private List<ExplosiveUsageNumberBean> explosiveUsageNumber;
        private List<DeliveryListBean.DataBean> deliveryUser;

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

        public String getRemarks() {
            return remarks == null ? "" : remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks == null ? "" : remarks;
        }

        public String getApplySign() {
            return applySign == null ? "" : applySign;
        }

        public void setApplySign(String applySign) {
            this.applySign = applySign == null ? "" : applySign;
        }

        public int getPoliceVoid() {
            return policeVoid;
        }

        public void setPoliceVoid(int policeVoid) {
            this.policeVoid = policeVoid;
        }

        public int getBrigadeVoid() {
            return brigadeVoid;
        }

        public void setBrigadeVoid(int brigadeVoid) {
            this.brigadeVoid = brigadeVoid;
        }

        public int getLeaderVoid() {
            return leaderVoid;
        }

        public void setLeaderVoid(int leaderVoid) {
            this.leaderVoid = leaderVoid;
        }

        public String getPoliceRemarks() {
            return policeRemarks == null ? "" : policeRemarks;
        }

        public void setPoliceRemarks(String policeRemarks) {
            this.policeRemarks = policeRemarks == null ? "" : policeRemarks;
        }

        public String getBrigadeRemarks() {
            return brigadeRemarks == null ? "" : brigadeRemarks;
        }

        public void setBrigadeRemarks(String brigadeRemarks) {
            this.brigadeRemarks = brigadeRemarks == null ? "" : brigadeRemarks;
        }

        public String getLeaderRemarks() {
            return leaderRemarks == null ? "" : leaderRemarks;
        }

        public void setLeaderRemarks(String leaderRemarks) {
            this.leaderRemarks = leaderRemarks == null ? "" : leaderRemarks;
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

        public int getBrigadeDepartmentId() {
            return brigadeDepartmentId;
        }

        public void setBrigadeDepartmentId(int brigadeDepartmentId) {
            this.brigadeDepartmentId = brigadeDepartmentId;
        }

        public String getBrigadeSign() {
            return brigadeSign == null ? "" : brigadeSign;
        }

        public void setBrigadeSign(String brigadeSign) {
            this.brigadeSign = brigadeSign == null ? "" : brigadeSign;
        }

        public String getBrigadeUsername() {
            return brigadeUsername == null ? "" : brigadeUsername;
        }

        public void setBrigadeUsername(String brigadeUsername) {
            this.brigadeUsername = brigadeUsername == null ? "" : brigadeUsername;
        }

        public void setBrigadeDepartmentSeal(String brigadeDepartmentSeal) {
            this.brigadeDepartmentSeal = brigadeDepartmentSeal == null ? "" : brigadeDepartmentSeal;
        }

        public int getLeaderDepartmentId() {
            return leaderDepartmentId;
        }

        public void setLeaderDepartmentId(int leaderDepartmentId) {
            this.leaderDepartmentId = leaderDepartmentId;
        }

        public String getLeaderSign() {
            return leaderSign == null ? "" : leaderSign;
        }

        public void setLeaderSign(String leaderSign) {
            this.leaderSign = leaderSign == null ? "" : leaderSign;
        }

        public String getLeaderUsername() {
            return leaderUsername == null ? "" : leaderUsername;
        }

        public void setLeaderUsername(String leaderUsername) {
            this.leaderUsername = leaderUsername == null ? "" : leaderUsername;
        }

        public String getLeaderDepartmentSeal() {
            return leaderDepartmentSeal == null ? "" : leaderDepartmentSeal;
        }

        public void setLeaderDepartmentSeal(String leaderDepartmentSeal) {
            this.leaderDepartmentSeal = leaderDepartmentSeal == null ? "" : leaderDepartmentSeal;
        }

        public String getDeliveryTime() {
            return deliveryTime == null ? "" : deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime == null ? "" : deliveryTime;
        }

        public String getDeliveryAddress() {
            return deliveryAddress == null ? "" : deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress == null ? "" : deliveryAddress;
        }

        public String getDeliveryLongitude() {
            return deliveryLongitude == null ? "" : deliveryLongitude;
        }

        public void setDeliveryLongitude(String deliveryLongitude) {
            this.deliveryLongitude = deliveryLongitude == null ? "" : deliveryLongitude;
        }

        public String getDeliveryLatitude() {
            return deliveryLatitude == null ? "" : deliveryLatitude;
        }

        public void setDeliveryLatitude(String deliveryLatitude) {
            this.deliveryLatitude = deliveryLatitude == null ? "" : deliveryLatitude;
        }

        public String getArriveTime() {
            return arriveTime == null ? "" : arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime == null ? "" : arriveTime;
        }

        public String getArriveAddress() {
            return arriveAddress == null ? "" : arriveAddress;
        }

        public void setArriveAddress(String arriveAddress) {
            this.arriveAddress = arriveAddress == null ? "" : arriveAddress;
        }

        public String getArriveLongitude() {
            return arriveLongitude == null ? "" : arriveLongitude;
        }

        public void setArriveLongitude(String arriveLongitude) {
            this.arriveLongitude = arriveLongitude == null ? "" : arriveLongitude;
        }

        public String getArriveLatitude() {
            return arriveLatitude == null ? "" : arriveLatitude;
        }

        public void setArriveLatitude(String arriveLatitude) {
            this.arriveLatitude = arriveLatitude == null ? "" : arriveLatitude;
        }

        public String getArrivePicture() {
            return arrivePicture == null ? "-1" : arrivePicture;
        }

        public void setArrivePicture(String arrivePicture) {
            this.arrivePicture = arrivePicture == null ? "" : arrivePicture;
        }

        public String getArriveSign() {
            return arriveSign == null ? "" : arriveSign;
        }

        public void setArriveSign(String arriveSign) {
            this.arriveSign = arriveSign == null ? "" : arriveSign;
        }

        public int getStat() {
            return stat;
        }

        public void setStat(int stat) {
            this.stat = stat;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }

        public int getIsVoid() {
            return isVoid;
        }

        public void setIsVoid(int isVoid) {
            this.isVoid = isVoid;
        }

        public String getPoliceDepartmentSeal() {
            return policeDepartmentSeal == null ? "" : policeDepartmentSeal;
        }

        public void setPoliceDepartmentSeal(String policeDepartmentSeal) {
            this.policeDepartmentSeal = policeDepartmentSeal == null ? "" : policeDepartmentSeal;
        }

        public String getBrigadeDepartmentSeal() {
            return brigadeDepartmentSeal == null ? "" : brigadeDepartmentSeal;
        }


        public String getRefuseRemarks() {
            return refuseRemarks == null ? "" : refuseRemarks;
        }

        public void setRefuseRemarks(String refuseRemarks) {
            this.refuseRemarks = refuseRemarks == null ? "" : refuseRemarks;
        }

        public List<ExplosiveUsageBean> getExplosiveUsage() {
            return explosiveUsage;
        }

        public void setExplosiveUsage(List<ExplosiveUsageBean> explosiveUsage) {
            this.explosiveUsage = explosiveUsage;
        }

        public List<DeliveryListBean.DataBean> getDeliveryUser() {
            return deliveryUser;
        }

        public void setDeliveryUser(List<DeliveryListBean.DataBean> deliveryUser) {
            this.deliveryUser = deliveryUser;
        }

        public List<ExplosiveUsageNumberBean> getExplosiveUsageNumber() {
            if (explosiveUsageNumber == null) {
                return new ArrayList<>();
            }
            return explosiveUsageNumber;
        }

        public void setExplosiveUsageNumber(List<ExplosiveUsageNumberBean> explosiveUsageNumber) {
            this.explosiveUsageNumber = explosiveUsageNumber;
        }
    }
}
