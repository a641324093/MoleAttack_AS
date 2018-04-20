package com.person.myy.moleattack_as.object;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.person.myy.moleattack_as.GameSize;
import com.person.myy.moleattack_as.GameSurfaceView;

public class Turnip {

	private static GameSurfaceView gsv;
	private Bitmap tn_bmp;
	private float x,y;
	boolean islive;
	private boolean adaptive;
	
	public Turnip(int x,int y,Bitmap hp_bmp,GameSurfaceView gsv,boolean adaptive)
	{
		
		this.adaptive=adaptive;
		if(adaptive==false)
		{
			this.x= GameSize.getNewX(x);
			this.y=GameSize.getNewX(y);
		}
		else
		{
			this.x=x;
			this.y=y;
		}
		this.tn_bmp=hp_bmp;
		this.gsv=gsv;
		islive=true;
	}
	
	public void draw(Canvas canvas,Paint paint)
	{
		if(islive==true)
		{
			canvas.drawBitmap(tn_bmp, x, y, paint);
		}
	}
	
	public void logic()
	{
		
	}
	
	public static void play()
	{
		gsv.gs_collection.play("EAT");
	}
}
