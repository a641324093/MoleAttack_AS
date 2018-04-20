package com.person.myy.moleattack_as;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	public static MainActivity instance;//用于得到该Activity实例
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance=this;
		// 设置全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 显示自定义的SurfaceView视图
		setContentView(new GameSurfaceView(this));
//		setVolumeControlStream(AudioManager.STREAM_ALARM);
	}

}
