package com.pangli.slide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.pangli.slide.bean.Image;
import com.pangli.slide.utils.HttpCallbackListener;
import com.pangli.slide.utils.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    private List<Image> images;
    private Button btnStart, btnStop;
    private ViewFlipper viewFlipper;
    private int[] imgs = {R.drawable.aa, R.drawable.bb, R.drawable.cc, R.drawable.dd};//图片源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        images=new ArrayList<>();
        getJSONData(); //获取图片数据

        for (int i = 0; i < imgs.length; i++) { // 动态添加图片源
            ImageView iv = new ImageView(this);
            iv.setImageResource(imgs[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(iv, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout
                    .LayoutParams.MATCH_PARENT));

        }
         viewFlipper.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
        viewFlipper.setFlipInterval(3000);//间隔3秒
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                if (!viewFlipper.isFlipping()) {
                    //如果当前页是最后一页，重新开始播放时从第一页开始播放
                    if (viewFlipper.getDisplayedChild() == viewFlipper.getChildCount() - 1) {
                        viewFlipper.setDisplayedChild(0);
                    }
                    viewFlipper.startFlipping();//开始播放
                    viewFlipper.setInAnimation(this, android.support.v7.appcompat.R.anim.abc_grow_fade_in_from_bottom);
                    viewFlipper.setOutAnimation(this, android.support.v7.appcompat.R.anim
                            .abc_shrink_fade_out_from_bottom);
                    viewFlipper.getInAnimation().setAnimationListener(this);
                }
                break;
            case R.id.btn_stop:
                if (viewFlipper.isFlipping()) {
                    viewFlipper.stopFlipping();//停止播放
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //当播放到最后一页时自动停止
        if (viewFlipper.getDisplayedChild() == viewFlipper.getChildCount() - 1) {
            Toast.makeText(this, "最后一页", Toast.LENGTH_LONG).show();
            if (viewFlipper.isFlipping()) {
                viewFlipper.stopFlipping();//停止播放
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void getJSONData(){
        HttpUtil.sendHttpRequest("http://10.0.0.194:96/api.php?action=getapplist", new HttpCallbackListener() {

            @Override
            public void onFinish(String response) {
                try {

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=new JSONArray(jsonObject.getString("img"));
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        Image image=new Image();
                        image.setId(jsonObject1.getString("id"));
                        image.setTitle(jsonObject1.getString("title"));
                        image.setImgUrl(jsonObject1.getString("img"));
                        images.add(image);
                        Log.d("MainActivity",image.getImgUrl());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                Log.d("MainActivity","响应失败----");
            }
        });
    }

}
