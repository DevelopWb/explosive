package com.juntai.wisdom.explorsive.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-19 15:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-19 15:00
 */
public class FaceCheckResponseBean  extends BaseResult {

    /**
     * data : {"Response":{"Score":100,"IsMatch":true,"RequestId":"dd9c1c70-bdd6-475f-b047-baa918a4cd49","FaceModelVersion":"3.0"}}
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
         * Response : {"Score":100,"IsMatch":true,"RequestId":"dd9c1c70-bdd6-475f-b047-baa918a4cd49","FaceModelVersion":"3.0"}
         */

        private ResponseBean Response;

        public ResponseBean getResponse() {
            return Response;
        }

        public void setResponse(ResponseBean Response) {
            this.Response = Response;
        }

        public static class ResponseBean {
            /**
             * Score : 100
             * IsMatch : true
             * RequestId : dd9c1c70-bdd6-475f-b047-baa918a4cd49
             * FaceModelVersion : 3.0
             */

            private int Score;
            private boolean IsMatch;
            private String RequestId;
            private String FaceModelVersion;

            public int getScore() {
                return Score;
            }

            public void setScore(int Score) {
                this.Score = Score;
            }

            public boolean isIsMatch() {
                return IsMatch;
            }

            public void setIsMatch(boolean IsMatch) {
                this.IsMatch = IsMatch;
            }

            public String getRequestId() {
                return RequestId;
            }

            public void setRequestId(String RequestId) {
                this.RequestId = RequestId;
            }

            public String getFaceModelVersion() {
                return FaceModelVersion;
            }

            public void setFaceModelVersion(String FaceModelVersion) {
                this.FaceModelVersion = FaceModelVersion;
            }
        }
    }
}
