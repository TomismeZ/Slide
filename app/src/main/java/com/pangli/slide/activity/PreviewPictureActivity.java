package com.pangli.slide.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.pangli.slide.R;
import com.pangli.slide.view.SlideShowView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreviewPictureActivity extends AppCompatActivity{
    private String[] imageUrls = new String[]{
            "http://d.hiphotos.baidu.com/image/pic/item/9f2f070828381f30b2bd028fac014c086e06f074.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/2934349b033b5bb55e73afd833d3d539b600bc74.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/ac345982b2b7d0a2bdfa8bbbceef76094b369ae1.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/2e2eb9389b504fc213023f23e0dde71190ef6db3.jpg" } ;
    private String[] imageUris = new String[]{
            "http://www.baidu.com",
            "http://www.sina.com.cn",
            "http://www.taobao.com",
            "http://www.tudou.com" };

    private List<Map<String,String>> imageList = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_picture);

        for (int i = 0; i < 4; i++) {
            Map<String,String> image_uri = new HashMap<String,String>();
            image_uri.put("imageUrls", imageUrls[i]);
            image_uri.put("imageUris", imageUris[i]);
            imageList.add(image_uri);
        }

        SlideShowView view = (SlideShowView) findViewById(R.id.slideshowView);
        view.setImageUrls(imageList);
    }
}
