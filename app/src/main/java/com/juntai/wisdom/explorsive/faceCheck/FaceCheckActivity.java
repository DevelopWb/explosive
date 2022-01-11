package com.juntai.wisdom.explorsive.faceCheck;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.ScreenUtils;
import com.juntai.wisdom.R;
import com.juntai.wisdom.explorsive.AppHttpPath;
import com.juntai.wisdom.explorsive.base.BaseAppActivity;
import com.juntai.wisdom.explorsive.bean.FaceCheckResponseBean;
import com.juntai.wisdom.explorsive.main.MainContactInterface;
import com.juntai.wisdom.explorsive.main.MainPresent;
import com.juntai.wisdom.explorsive.utils.NV21ToBitmap;
import com.tu.tcircleprogresslibrary.TCircleProgressView;

import java.util.List;

/**
 * 模仿支付宝人脸识别
 *
 * @version 1.0
 * @date 2018/8/7  9:57
 */
public class FaceCheckActivity extends BaseAppActivity<MainPresent> implements SurfaceHolder.Callback, MainContactInterface {

    public static String PERSION_ID = "persionId";
    public static String FACE_HEAD = "facehead";
    public static int FACE_CODE = 10089;
    private int persionId;

    private float currentProgress;
    // ===========================================================
    // Constants
    // ===========================================================
    public final static int REQUEST_CODE_CAMERA_OK = 1;
    public final static String TAG = "FaceCheckActivity";

    // ===========================================================
    // Fields
    // ===========================================================
    private TCircleProgressView mTcpv;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;

    private boolean startFouse = true;
    //人脸不在圈内
    private boolean faceNotInRound = true;
    private int mCameraIndex;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mTcpv.setText("没有检测到人脸");
                    break;
                case 2:
                    mTcpv.setText("把脸移入框内");
                    mTcpv.setProgressByAnimation(0, 500);

                    break;
                case 3:
                    mTcpv.setText("保持好姿势");
                    mTcpv.setProgressByAnimation(currentProgress, 1000);
                    mCamera.autoFocus(autoFocusCallback);
                    break;
                case 6:
                    mTcpv.setText("正在验证");
                    mTcpv.setProgressByAnimation(currentProgress, 1000);
                    break;
                case 4:
                    mTcpv.setText("验证通过");
                    break;
                case 5:
                    mTcpv.setText("验证失败");
                    mTcpv.setProgressByAnimation(currentProgress, 0);
                    mReCheckTv.setVisibility(View.VISIBLE);

                    break;
                default:
                    break;
            }
        }
    };
    private TextView mReCheckTv;
    private String facePath;

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.face_check;
    }

    @Override
    public void initView() {
        setTitleName("人脸验证");
        if (getIntent() != null) {
            persionId = getIntent().getIntExtra(PERSION_ID, 0);
        }
        SurfaceView surfaceView = findViewById(R.id.sv_camera);
        mReCheckTv = findViewById(R.id.recheck_tv);
        mReCheckTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTcpv.setProgress(0);
                mReCheckTv.setVisibility(View.GONE);
                startFouse = true;
                //人脸不在圈内
                faceNotInRound = true;
            }
        });
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) surfaceView.getLayoutParams();
        params.width = ScreenUtils.getInstance(mContext).getScreenWidth() / 3 * 2;
        params.height = ScreenUtils.getInstance(mContext).getScreenHeight() / 3 * 2;
        surfaceView.setLayoutParams(params);
        mSurfaceHolder = surfaceView.getHolder();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA_OK);
        } else {
            showCamera();
        }

        mTcpv = findViewById(R.id.tcpv_dam_board);
        mTcpv.setTotalProgress(1000);
        mTcpv.setAnimationDuration(4000);
        mTcpv.setHintTextSize(12);
        mTcpv.setIsShowHint(true);
        //mTcpv.setTextPadding(8);
        mTcpv.setOnProgressListener(new TCircleProgressView.OnProgressListener() {
            @Override
            public void onProgressChanged(float progress) {
                currentProgress = progress;
            }
        });
    }

    @Override
    public void initData() {
        mTcpv.setProgress(0);
        mHandler.sendEmptyMessage(0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA_OK:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showCamera();
                    openCamera();
                } else {
                    Toast.makeText(this, "请手动打开相机权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        openCamera();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    // ===========================================================
    // Methods
    // ===========================================================
    private void showCamera() {
        int cameraCount = Camera.getNumberOfCameras();
        for (int i = 0; i < cameraCount; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {//前置摄像头
                mCameraIndex = i;
                mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                mSurfaceHolder.addCallback(this);
            }
        }
    }

    private void openCamera() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        try {
            mCameraIndex = mCameraIndex == 0 ? Camera.getNumberOfCameras() : mCameraIndex;
            mCamera = Camera.open(mCameraIndex);
            mCamera.setPreviewDisplay(mSurfaceHolder);

            Camera.Parameters parameters = mCamera.getParameters();
            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                parameters.set("orientation", "portrait");
                mCamera.setDisplayOrientation(90);
                parameters.setRotation(90);
            } else {
                parameters.set("orientation", "landscape");
                mCamera.setDisplayOrientation(0);
                parameters.setRotation(0);
            }
            List modes = parameters.getSupportedFocusModes();
            if (modes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }
            // 查看支持的预览尺寸
            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            for (int i = 0; i < supportedPreviewSizes.size(); i++) {
                if (0 == i) {
                    Camera.Size size = supportedPreviewSizes.get(i);
                    parameters.setPreviewSize(size.width, size.height);// 设置预览尺寸
                }
            }
            parameters.setJpegQuality(100);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
            //开启人脸检查
            mCamera.startFaceDetection();
            mCamera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
                @Override
                public void onFaceDetection(Camera.Face[] faces, Camera camera) {
                    if (faces.length > 0) {
//                        Log.d(TAG, "onFaceDetection====left" + faces[0].rect.left);
//                        Log.d(TAG, "onFaceDetection====top" + faces[0].rect.top);
//                        Log.d(TAG, "onFaceDetection====right" + faces[0].rect.right);
//                        Log.d(TAG, "onFaceDetection====bottom" + faces[0].rect.bottom);

                        if (faces[0].rect.left > -200 && faces[0].rect.top > -1000 && faces[0].rect.right > 450 && faces[0].rect.bottom < 900) {
//                            Log.d(TAG, "onFaceDetection==-----------------------------==success");


                            if (startFouse) {
                                startFouse = false;
                                mHandler.sendEmptyMessage(3);
                            }

                        } else {
                            if (faceNotInRound) {
                                faceNotInRound = false;
                                mHandler.sendEmptyMessage(2);
                            }
                        }
                    }


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (mCamera != null) {
                mCamera.release();
            }
            finish();
        }
    }

    Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {
                mCamera.setOneShotPreviewCallback(new Camera.PreviewCallback() {
                    @Override
                    public void onPreviewFrame(byte[] bytes, Camera camera) {
                        NV21ToBitmap nv21ToBitmap = new NV21ToBitmap(mContext);
                        String path = getHeadPicPath();
                        FileCacheUtils.saveBitmapInFaceCheck(nv21ToBitmap.nv21ToBitmap(bytes, mCamera.getParameters().getPreviewSize().width, mCamera.getParameters().getPreviewSize().height),path);
                        //上传头像
                        mPresenter.uploadFile(AppHttpPath.UPLOAD_FILES, path);
                    }
                });
            }
        }
    };
    private String getHeadPicPath() {
        return String.format("%s%s%s", FileCacheUtils.getAppImagePath(), persionId, ".jpeg");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopFaceDetection();
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.FACE_CHECK:
                FaceCheckResponseBean responseBean = (FaceCheckResponseBean) o;
                if (responseBean != null) {
                    FaceCheckResponseBean.DataBean dataBean = responseBean.getData();
                    if (dataBean != null) {
                        if (dataBean.getResponse().isIsMatch()) {
                            mHandler.sendEmptyMessage(4);
                            setResult(FACE_CODE, new Intent().putExtra(FACE_HEAD, facePath));
                            finish();
                        } else {
                            mHandler.sendEmptyMessage(5);
                        }
                    }
                }
                break;

            case AppHttpPath.UPLOAD_FILES:
                List<String> pics = (List<String>) o;
                facePath = pics == null ? "" : pics.get(0);
                mPresenter.startFaceCheck(getBaseBuilder().add("id", String.valueOf(persionId)).add("image", facePath).build(), AppHttpPath.FACE_CHECK);
                break;
            default:
                break;
        }


    }

    @Override
    public void onError(String tag, Object o) {
        super.onError(tag, o);
        mHandler.sendEmptyMessage(5);
    }
}
