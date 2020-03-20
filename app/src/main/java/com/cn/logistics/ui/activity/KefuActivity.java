package com.cn.logistics.ui.activity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cn.logistics.R;
import com.cn.logistics.common.MyActivity;
import com.qiyukf.nimlib.sdk.msg.model.IMMessage;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.helper.UnicornPickImageHelper;
import com.qiyukf.unicorn.api.helper.UnicornVideoPickHelper;
import com.qiyukf.unicorn.api.msg.MessageService;
import com.qiyukf.unicorn.api.msg.UnicornMessageBuilder;
import com.qiyukf.unicorn.ui.fragment.ServiceMessageFragment;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : kefu列表
 */
public final class KefuActivity extends MyActivity {
    public static final int CAPTURE_VIDEO_REQUEST_CODE = 0X123;
    public static final int LOCAL_VIDEO_REQUEST_CODE = 0X12;
    public static final int CAPTURE_IMAGE_REQUEST_CODE = 0X111;
    public static final int ALBUM_IMAGE_REQUEST_CODE = 0X1222;
    public static final int CAPTURE_IMAGE_PROCESS_REQUEST_CODE = 0X1221;

    private UnicornPickImageHelper unicornPickImageHelper;
    private UnicornVideoPickHelper unicornVideoPickHelper;

    @BindView(R.id.ll_kefu)
    LinearLayout llKefu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_kefu;
    }

    @Override
    protected void initView() {

//        addPhoneMenu();
        addServiceFragment();


    }

    @Override
    protected void initData() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    private void addServiceFragment() {
        LinearLayout sdkIconContainer = new LinearLayout(this);


        String title = (String) getTitle(); // 标题
        ConsultSource source = new ConsultSource("https://qiyukf.com", "客服", "custom information string"); // 访客来源信息
        ServiceMessageFragment fragment = Unicorn.newServiceFragment(title, source, sdkIconContainer);

        // 七鱼未初始化或初始化失败
        if (fragment == null) {
            Toast.makeText(this, "暂时无法联系客服", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.ll_kefu, fragment); // 将 fragment 放到对应的 containerId 中。containerId 为 ViewGroup 的 resId
        try {
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }

        unicornVideoPickHelper = new UnicornVideoPickHelper(this, new UnicornVideoPickHelper.UincornVideoSelectListener() {

            @Override
            public void onVideoPicked(File file, String md5) {
                MediaPlayer mediaPlayer = getVideoMediaPlayer(file);
                long duration = mediaPlayer == null ? 0 : mediaPlayer.getDuration();
                int height = mediaPlayer == null ? 0 : mediaPlayer.getVideoHeight();
                int width = mediaPlayer == null ? 0 : mediaPlayer.getVideoWidth();
                IMMessage message = UnicornMessageBuilder.buildVideoMessage(file, duration, width, height, file.getName());
                MessageService.sendMessage(message);
            }
        });

        unicornPickImageHelper = new UnicornPickImageHelper(this, new UnicornPickImageHelper.SendImageCallback() {
            @Override
            public void sendImage(File file, String origName, boolean isOrig) {
                IMMessage message = UnicornMessageBuilder.buildImageMessage(UnicornMessageBuilder.getSessionId(), file, file.getName());
                MessageService.sendMessage(message);
            }
        });

    }

    /**
     * 获取视频mediaPlayer
     *
     * @param file 视频文件
     * @return mediaPlayer
     */
    private MediaPlayer getVideoMediaPlayer(File file) {
        try {
            return MediaPlayer.create(this, Uri.fromFile(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAPTURE_VIDEO_REQUEST_CODE:
                unicornVideoPickHelper.onCaptureVideoResult(data);
                break;
            case LOCAL_VIDEO_REQUEST_CODE:
                unicornVideoPickHelper.onSelectLocalVideoResult(data);
                break;
            case ALBUM_IMAGE_REQUEST_CODE:
                unicornPickImageHelper.onAlbumResult(data);
                break;
            case CAPTURE_IMAGE_REQUEST_CODE:
                unicornPickImageHelper.onCapturePhotoResult(data, CAPTURE_IMAGE_PROCESS_REQUEST_CODE);
                break;
            case CAPTURE_IMAGE_PROCESS_REQUEST_CODE:
//                unicornPickImageHelper.onCapturePhotoPorcessResult(data, CAPTURE_IMAGE_REQUEST_CODE);
                break;
        }
    }
}