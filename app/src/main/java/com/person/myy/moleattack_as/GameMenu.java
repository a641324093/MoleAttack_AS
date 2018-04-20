package com.person.myy.moleattack_as;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.person.myy.moleattack_as.object.GameButton;

import java.util.HashMap;

class GameMenu {
	
	private Bitmap bg_start_bmg,bg_over_bmg,but1_up_bmg,but1_down_bmg,but2_up_bmg,but2_down_bmg;
	private GameSurfaceView gsv;
	private HashMap <String,Bitmap> map;
	private GameButton start_but,back_but;
	
	
	public GameMenu(HashMap <String,Bitmap> map,GameSurfaceView gsv)
	{
		this.gsv=gsv;
		this.bg_start_bmg=map.get("BS");
		this.bg_over_bmg=map.get("BO");
		this.but1_up_bmg=map.get("BU1");
		this.but1_down_bmg=map.get("BD1");
		this.but2_up_bmg=map.get("BU2");
		this.but2_down_bmg=map.get("BD2");
		
		start_but = new GameButton(GameSurfaceView.SCREEN_W/2-but1_up_bmg.getWidth()/2
				,GameSurfaceView.SCREEN_H/2-but1_up_bmg.getHeight()/2,but1_up_bmg,but1_down_bmg,true);
		back_but = new GameButton(GameSurfaceView.SCREEN_W-but2_up_bmg.getWidth()
				,GameSurfaceView.SCREEN_H-but2_up_bmg.getHeight(),but2_up_bmg,but2_down_bmg,true);
	}
	
	public void draw(Canvas canvas ,Paint paint)
	{
		if(gsv.gameState==GameSurfaceView.GAME_MENU)
		{
			canvas.drawBitmap(bg_start_bmg, 0, 0, paint);
			start_but.draw(canvas, paint);
		}
		else if(gsv.gameState==GameSurfaceView.GAME_OVER)
		{
			canvas.drawBitmap(bg_over_bmg, 0, 0, paint);
			back_but.draw(canvas, paint);
//			Log.i("GameMenu", "”Œœ∑Ω· ¯±≥æ∞");
		}
		
	}
	
	public void onTouchEvent(MotionEvent event)
	{
		if(gsv.gameState==GameSurfaceView.GAME_MENU)
		{
			if(start_but.onTouch(event)==true)
			{
				gsv.reinitializeGame();
				GameSurfaceView.gameState=GameSurfaceView.GAME_ING;
				gsv.gs_collection.play("BUT");
			}
		}
		else if(gsv.gameState==GameSurfaceView.GAME_OVER)
		{
			if(back_but.onTouch(event)==true)
			{
				GameSurfaceView.gameState=GameSurfaceView.GAME_MENU;
				gsv.gs_collection.play("BUT");
			}
		}
	}
	
	

}
