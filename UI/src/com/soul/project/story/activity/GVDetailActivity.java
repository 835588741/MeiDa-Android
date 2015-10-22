package com.soul.project.story.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.soul.project.application.bean.GridViewBean;
import com.soul.project.application.util.BitmapCache;
import com.soul.project.application.view.Constants;
import com.soul.project.application.view.ImageCycleView;
import com.soul.project.application.view.ImageCycleView.ImageCycleViewListener;

public class GVDetailActivity extends Activity{

	private GridViewBean bean;
	private ImageCycleView scrollView;
	private List<String> imgUrls = new ArrayList<String>();
	private List<String> adTexts = new ArrayList<String>();
	private int width;
	private int maxWidth;
	private int maxHeight;
	private ImageLoader mImageLoader;
	private int additional = 5; //轮播控件的额外高度

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gv_dateil_layout);
		mImageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
		mImageLoader.init(ImageLoaderConfiguration.createDefault(GVDetailActivity.this));
		getIntentData();
		createData();
		initViews();
	}


	private void createData() {
		// TODO Auto-generated method stub
		imgUrls.add("http://f.hiphotos.baidu.com/image/pic/item/bd315c6034a85edf405207cf4d540923dc547504.jpg");
		imgUrls.add("http://f.hiphotos.baidu.com/image/pic/item/8644ebf81a4c510fa42d1bf66459252dd52aa575.jpg");
		imgUrls.add("http://b.hiphotos.baidu.com/image/pic/item/d043ad4bd11373f08b02bb65a00f4bfbfbed040e.jpg");
		imgUrls.add("http://a.hiphotos.baidu.com/image/pic/item/f7246b600c338744cbb13271530fd9f9d72aa042.jpg");
		imgUrls.add("http://d.hiphotos.baidu.com/image/pic/item/a686c9177f3e6709fae9f8f639c79f3df9dc55c9.jpg");
		
		adTexts.add("清晨倾城之容马可尼马可尼");
		adTexts.add("初秋沉鱼之貌马可尼马可尼");
		adTexts.add("冬末孤傲之冷艳马可尼马可尼");
		adTexts.add("夏日夏荷之奔放马可尼马可尼");
		adTexts.add("春日融融之暖心马可尼马可尼");
	}

	private void initViews() {
		// TODO Auto-generated method stub
		scrollView = (ImageCycleView)this.findViewById(R.id.activity_index_autoscrollview);

		int height = (int) (maxWidth / 2.0);
		//也可以在代码中设置宽高 
		scrollView.setLayoutParams(new LinearLayout.LayoutParams(maxWidth, height+additional));
		scrollView.setImageResources(imgUrls, adTexts, mAdCycleViewListener);
	}
	

	/**
	 * 轮播图控件接口处理
	 */
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener()
	{
		@Override
		public void onImageClick(int position, View imageView)
		{
			//ToastUtil.show(Shouye.this, "正在执行跳转", ToastUtil.INFO);
		}


		@Override
		public void displayImage(String imageURL, ImageView imageView)
		{
		    // imageView是一个ImageView实例  
		    // ImageLoader.getImageListener的第二个参数是默认的图片resource id  
		    // 第三个参数是请求失败时候的资源id，可以指定为0  
//		    ImageListener listener = ImageLoader.getImageListener(imageView, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);  
//		    mImageLoader.get(bean.getImageUrl(), listener, maxWidth, maxHeight);
			mImageLoader.displayImage(imageURL, imageView, Constants.IM_IMAGE_OPTIONS);
		}
	};
	
	
	@Override
	protected void onResume()
	{
		super.onResume();
		scrollView.startImageCycle();
	};


	@Override
	protected void onPause()
	{
		super.onPause();
		scrollView.pushImageCycle();
	}


	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		scrollView.pushImageCycle();
	}

	private void getIntentData() {
		
		Intent intent = getIntent();
		
		bean = new GridViewBean();
		bean.setAuthor(intent.getStringExtra("author"));
		bean.setBallot(intent.getIntExtra("ballot", 0));//.getIntExtra("ballto"));
		bean.setDescription(intent.getStringExtra("desc"));
		bean.setGrade(intent.getIntExtra("grade",0));
		bean.setImageUrl(intent.getStringExtra("imageUrl"));
		bean.setNumber(intent.getStringExtra("number"));
		bean.setType(intent.getIntExtra("type",0));
		bean.setTitle(intent.getStringExtra("title"));
		maxWidth = Integer.valueOf(intent.getIntExtra("width",0));
		maxHeight= Integer.valueOf(intent.getIntExtra("height", 0));
		
		Log.i("XU", "w="+maxWidth+"  h="+maxHeight);
	}
}
