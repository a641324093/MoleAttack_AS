package com.person.myy.moleattack_as;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.person.myy.moleattack_as.collection.GameSoundCollection;
import com.person.myy.moleattack_as.collection.HitAnimationCollection;
import com.person.myy.moleattack_as.collection.HoleCollection;
import com.person.myy.moleattack_as.collection.MediaPlayerCollection;
import com.person.myy.moleattack_as.collection.MoleCollection;
import com.person.myy.moleattack_as.collection.TurnipCollection;
import com.person.myy.moleattack_as.object.GameText;
import com.person.myy.moleattack_as.object.HitAnimation;
import com.person.myy.moleattack_as.object.Mole;
import com.person.myy.moleattack_as.object.Score;
import com.person.myy.moleattack_as.thread.MoleUpThread;

import java.io.IOException;
import java.util.HashMap;

public class GameSurfaceView extends SurfaceView implements Callback, Runnable {

	public static final int GAME_MENU = 0;
	public static final int GAME_ING = 1;
	public static final int GAME_OVER = 2;
	public static final int GAME_PAUSE = 3;

	private Rect mole_rect;//������ֵ�����
	private static Context context;
	public static int gameState = GAME_MENU;
	public static int SCREEN_W, SCREEN_H;
	private static int thread_time = 50;

	public Bitmap menu_bg_start_bmp = null;
	public Bitmap menu_bg_win_bmp = null;
	public Bitmap menu_bg_over_bmp = null;
	public Bitmap but1_up_bmp = null;
	public Bitmap but1_down_bmp = null;
	public Bitmap but2_up_bmp = null;
	public Bitmap but2_down_bmp = null;
	public Bitmap mole_live_bmp = null;
	public Bitmap mole_die_bmp = null;
	public Bitmap hole_bmp = null;
	public Bitmap game_bg_bmp = null;
	public Bitmap hit1_bmg = null;
	public Bitmap hit2_bmg = null;
	public Bitmap hit3_bmg = null;
	public Bitmap hit4_bmg = null;
	public Bitmap hit5_bmg = null;
	public Bitmap turnip_bmg = null;
	public Bitmap overtext_bg_bmg = null;

	private Score score;
	
	private HashMap<String,Bitmap> hit_bmg_map = new HashMap<String,Bitmap>();
	private HashMap<String,Bitmap> menu_bmg_map = new HashMap<String,Bitmap>(); 
	private GameMenu menu;
	private GameText over_text;
	private Resources res = this.getResources();
	private Canvas canvas;
	private Paint paint;
	private SurfaceHolder holder;
	private Thread main_thread;
	public MoleUpThread up_thread;
	private boolean th_on;
	private HoleCollection h_collection;
	public  MoleCollection m_collection;
	private HitAnimationCollection hit_collection;
	private TurnipCollection t_collection;
	public GameSoundCollection gs_collection;
	public MediaPlayerCollection mp_collection;
	private int media_flag;//�������ֲ��ű�־
	// ����Ϸ��
	public GameSurfaceView(Context context) {
		super(context);
		this.context=context;
		holder = this.getHolder();
		holder.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		// ʹ���ʿ����
		paint.setAntiAlias(true);
		//�������������������Բ��ܽ��ܵ�keydown�¼�
		setFocusableInTouchMode(true);//ȷ���ܽ��յ������¼�
		setFocusable(true);// ȷ�����ǵ�View�ܻ�����뽹��  
		// ������Ϸ��Ļ����
		this.setKeepScreenOn(true);
	}

	public void surfaceCreated(SurfaceHolder holder) {
//		Display display = getWindowManager().getDefaultDisplay(); 
//		int width = display.getWidth(); 
//		int height = display.getHeight();
//		DisplayMetrics dm = new DisplayMetrics(); 
//		dm = getResources().getDisplayMetrics(); 
//		int screenWidth = dm.widthPixels; 
//		int screenHeight = dm.heightPixels; 
//		float density = dm.density; 
//		float xdpi = dm.xdpi; 
//		float ydpi = dm.ydpi;
		
		GameSize.setView(this);
		SCREEN_W = this.getWidth();
		SCREEN_H = this.getHeight();
//		Toast.makeText(this.context,SCREEN_W+"X"+SCREEN_H,1).show();
		initializeGame();
		// ʵ�����̲߳�����
		main_thread = new Thread(this);
		main_thread.start();
		// �����߳��������
		th_on = true;
		
		//���õ�ǰ�������������ý����������������������
		MainActivity.instance.setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		//�÷�������back����ʱʹ��
	}

	protected void initializeGame() {
		// ���ڲ˵������½�����Ϸʱ����
		// ��ʼ����Ϸ��Դͼ
		//��÷�����Ļ��ߵı���ͼ
		menu_bg_start_bmp = BitmapFactory.decodeResource(res,R.drawable.menu_bg_start);
		menu_bg_start_bmp=GameUtils.resizeBitmap(menu_bg_start_bmp,SCREEN_W,SCREEN_H);
		menu_bg_over_bmp = BitmapFactory.decodeResource(res,R.drawable.game_end_bg);
		menu_bg_over_bmp=GameUtils.resizeBitmap(menu_bg_over_bmp,SCREEN_W,SCREEN_H);
		but1_up_bmp = BitmapFactory.decodeResource(res, R.drawable.but1_up);
//		but1_up_bmp=GameUtils.resizeBitmap(but1_up_bmp);
		but1_down_bmp = BitmapFactory.decodeResource(res, R.drawable.but1_down);
//		but1_down_bmp=GameUtils.resizeBitmap(but1_down_bmp);
		but2_up_bmp = BitmapFactory.decodeResource(res, R.drawable.but2_up);
//		but2_up_bmp=GameUtils.resizeBitmap(but2_up_bmp);
		but2_down_bmp = BitmapFactory.decodeResource(res, R.drawable.but2_down);
//		but2_down_bmp=GameUtils.resizeBitmap(but2_down_bmp);
		
		menu_bmg_map = new HashMap<String, Bitmap>();
		menu_bmg_map.put("BS", menu_bg_start_bmp);
		menu_bmg_map.put("BO", menu_bg_over_bmp);
		menu_bmg_map.put("BU1", but1_up_bmp);
		menu_bmg_map.put("BD1", but1_down_bmp);
		menu_bmg_map.put("BU2", but2_up_bmp);
		menu_bmg_map.put("BD2", but2_down_bmp);
		
		game_bg_bmp = BitmapFactory.decodeResource(res, R.drawable.game_bg);
		game_bg_bmp=GameUtils.resizeBitmap(game_bg_bmp,SCREEN_W,SCREEN_H);
		hole_bmp = BitmapFactory.decodeResource(res, R.drawable.hole_1);
//		hole_bmp=GameUtils.resizeBitmap(hole_bmp);
		mole_live_bmp = BitmapFactory.decodeResource(res, R.drawable.mole1);
//		mole_live_bmp=GameUtils.resizeBitmap(mole_live_bmp);
		mole_die_bmp = BitmapFactory.decodeResource(res, R.drawable.mole2);
//		mole_die_bmp=GameUtils.resizeBitmap(mole_die_bmp);
		turnip_bmg = BitmapFactory.decodeResource(res, R.drawable.turnip);
//		turnip_bmg=GameUtils.resizeBitmap(turnip_bmg);
		
		hit3_bmg = BitmapFactory.decodeResource(res, R.drawable.hit_3);
//		hit3_bmg=GameUtils.resizeBitmap(hit3_bmg);
		hit_bmg_map.put("H3",hit3_bmg);
		
		overtext_bg_bmg =BitmapFactory.decodeResource(res, R.drawable.text_bg_bmp);
//		overtext_bg_bmg=GameUtils.resizeBitmap(overtext_bg_bmg);
		
		menu = new GameMenu(menu_bmg_map,this);
		mole_rect = new Rect(0,SCREEN_H*2/7,SCREEN_W,SCREEN_H*5/6);
		h_collection = new HoleCollection(hole_bmp,mole_rect);
		h_collection.initialize();
		m_collection = new MoleCollection(mole_live_bmp,mole_die_bmp,h_collection,this);
		m_collection.initialize();
		t_collection = new TurnipCollection(turnip_bmg,this);
		t_collection.initialize();
		//��ʼ�����������
		hit_collection = new HitAnimationCollection();
		//��ʼ����Ч���ϣ��̣�
		gs_collection = new GameSoundCollection();
		String wavNames[] = {"eaten_wav.wav","button_wav.wav","hit_wav.wav","behit_wav.wav","over_mp3.mp3"};
		String wavKeys[] = {"EAT","BUT","HIT","BEHIT","OVER"};
		try {
			gs_collection.load(this.getContext(),wavNames,wavKeys);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//��ʼ�����ּ��ϣ�����
		mp_collection = new MediaPlayerCollection();
		String bgFileNames [] = {"menu_mid.mid","gamingbg_mp3.mp3"};
		String bgKeys [] = {"MENU","GAME"};
		try {
			mp_collection.load(this.getContext(),bgFileNames, bgKeys);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//ʵ�������¶������
		up_thread = new MoleUpThread(this, m_collection);
		//���������߳�
		up_thread.start();
		
		score = new Score(this);
		over_text= new GameText("�ܷ֣�"+score.getScore(),SCREEN_W*1/4,
				SCREEN_H*2/3,overtext_bg_bmg,true);
		
		media_flag=-1;
		Log.i("GameSurfaceView","��ʼ�����");
	}

	public void reinitializeGame() 
	{
		m_collection.reinitialize();
		t_collection.reinitialize();
		score.reset();
		Log.i("GameSurfaceView","���³�ʼ�����");
	}
	public boolean onTouchEvent(MotionEvent event) {
		switch (gameState) {
		case GAME_MENU:
			menu.onTouchEvent(event);
			break;
		case GAME_ING:
			this.gameingOntouch(event, m_collection);
			return false;//����Ϊfalse��ֻ��ȷ��һ�δ������������
		case GAME_OVER:
			menu.onTouchEvent(event);
			break;
		case GAME_PAUSE:
			break;
		}
		return true;//����Ϊtrue����һֱ�ܵ������¼�
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK)//���³�����ʱ��Ӧ
		{
			if(gameState==GAME_ING||gameState==GAME_OVER)
			{
				gameState=GAME_MENU;
			}
			else if(gameState==GAME_MENU)
			{
				up_thread.flag=false;
				Log.i("GameSurfaceView","��������߳��ѹر�");
				MainActivity.instance.finish();
				System.exit(0);
			}
			else if(gameState==GAME_PAUSE)
			{
				gameState=GAME_MENU;
			}
			return true;
		}
		Log.i("GameSurfaceView","�����¼���Ӧ");
		return true;
	}

	public void myDraw() {

		try {
			canvas = holder.lockCanvas();
			if (canvas != null) {
				switch (gameState) {
				case GAME_MENU:
					menu.draw(canvas, paint);
					break;
				case GAME_ING:
					canvas.drawBitmap(game_bg_bmp, 0, 0, paint);
					h_collection.draw(canvas, paint);
					m_collection.draw(canvas, paint);
					hit_collection.draw(canvas, paint);
					t_collection.draw(canvas, paint);
					score.draw(canvas, paint);
					break;
				case GAME_OVER:
					menu.draw(canvas, paint);
					over_text.draw(canvas, paint);
					break;
				case GAME_PAUSE:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	private void logic() {
		switch (gameState) {
		case GAME_MENU:
			break;
		case GAME_ING:
			m_collection.logic();
			hit_collection.logic();
			t_collection.logic();
			score.logic();
			break;
		case GAME_OVER:
			over_text.setText("�ܷ�:"+score.getScore());
			break;
		case GAME_PAUSE:
			break;
		}
	}

	public void play()
	{
		
		switch (gameState) {
		case GAME_MENU:
				mp_collection.pause("GAME");
				mp_collection.play("MENU");
			break;
		case GAME_ING:
			if(media_flag!=GAME_ING)
			{
				mp_collection.pause("MENU");
				mp_collection.play("GAME");
				media_flag=GAME_ING;
			}
			break;
		case GAME_OVER:
			if(media_flag!=GAME_OVER)
			{
				mp_collection.pause("GAME");
				mp_collection.pause("MENU");
				gs_collection.play("OVER");
				media_flag=GAME_OVER;
			}
			break;
		case GAME_PAUSE:
			break;
		}
	}
	private void gameingOntouch(MotionEvent event,MoleCollection mc)
	{
		int x,y;
		x=(int) event.getX();
		y=(int) event.getY();
		for(Mole m :mc.collection)
		{
			Rect rect = m.getTouchRect();
			if(GameUtils.contains(rect,x,y)&&m.getState()!=Mole.ACT.DIE)
			{
				hit_collection.add(new HitAnimation(x, y,this, hit_bmg_map));
				m.die();
				score.increase();
			}
		}
	}
	@Override
	public void run() {
		while (th_on) {
			long starttime = System.currentTimeMillis();
			//�����ܵĻ滭���߼�����
			myDraw();
			logic();
			play();
			long endtime = System.currentTimeMillis();
			if (endtime - starttime < thread_time) {
				try {
					Thread.sleep(endtime - starttime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
