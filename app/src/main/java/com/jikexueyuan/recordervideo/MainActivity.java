package com.jikexueyuan.recordervideo;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

/**
 * 系统录制视频功能
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int VIDEO_RECORDER = 1;
    private Button btn_play;
    private Button btn_recorder;
    private VideoView videoview;
    private Uri outputUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = (Button) findViewById(R.id.btn_play);
        btn_recorder = (Button) findViewById(R.id.btn_recorder);
        videoview = (VideoView) findViewById(R.id.videoview);

        btn_play.setOnClickListener(this);
        btn_recorder.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_recorder:
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent,VIDEO_RECORDER);
                break;
            case R.id.btn_play:
                videoview.setVideoURI(outputUri);   //设置uri
                videoview.start();
                break;
        }

    }

    /**
     * 接受返回的结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            //获取录制完视频的Uri,
            outputUri = data.getData();

        }
    }
}
