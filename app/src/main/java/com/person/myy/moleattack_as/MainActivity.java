package com.person.myy.moleattack_as;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	public static MainActivity instance;//���ڵõ���Activityʵ��
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance=this;
		// ����ȫ��
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ��ʾ�Զ����SurfaceView��ͼ
		setContentView(new GameSurfaceView(this));
//		setVolumeControlStream(AudioManager.STREAM_ALARM);
	}

}
