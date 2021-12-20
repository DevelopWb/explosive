package com.juntai.wisdom.explorsive.bean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-12-19 15:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-12-19 15:00
 */
public class FaceCheckResponseBean {
    /**
     * Response : {"Score":100,"IsMatch":true,"FaceModelVersion":"3.0","RequestId":"05bad4a8-389a-4b69-bf24-908292666991"}
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
         * FaceModelVersion : 3.0
         * RequestId : 05bad4a8-389a-4b69-bf24-908292666991
         */

        private float Score;
        private boolean IsMatch;
        private String FaceModelVersion;
        private String RequestId;

        public float getScore() {
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

        public String getFaceModelVersion() {
            return FaceModelVersion;
        }

        public void setFaceModelVersion(String FaceModelVersion) {
            this.FaceModelVersion = FaceModelVersion;
        }

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String RequestId) {
            this.RequestId = RequestId;
        }
    }
}
