package com.person.myy.moleattack_as.collection;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.person.myy.moleattack_as.GameSurfaceView;
import com.person.myy.moleattack_as.object.Turnip;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class TurnipCollection {
	public static final int TN_MAX=5;
	public static List<Turnip> collection;
	private Bitmap turnip_bmp;
	
	private int t_w,t_h;
	private GameSurfaceView gsv;
	private Random random = new Random();
	
	public TurnipCollection(Bitmap turnip_bmp,GameSurfaceView gsv)
	{
		this.turnip_bmp=turnip_bmp;
		this.gsv=gsv;
		
		t_w=turnip_bmp.getWidth();
		t_h=turnip_bmp.getHeight();
		
		collection=new LinkedList<Turnip>();
	}
	
	public void initialize()
	{
		int i = this.TN_MAX;
		int x=0,y=0;
		
		while(i>0)
		{
			collection.add(new Turnip(x, y, turnip_bmp,gsv,true));
			if(x+t_w>=GameSurfaceView.SCREEN_W)
			{
				y+=t_h;
			}
			else 
			{
				x+=t_w;
			}
			i--;
		}
	}
	
	public void reinitialize()
	{
		int i = this.TN_MAX;
		int x=0,y=0;
		if(collection.size()>0)
		{
			collection.clear();
		}
		while(i>0)
		{
			
			if(x+t_w>=GameSurfaceView.SCREEN_W)
			{
				y+=t_h;
				x=0;
			}
			collection.add(new Turnip(x, y, turnip_bmp,gsv,true));
			x+=t_w;
			i--;
		}
	}
	
	public void draw(Canvas canvas,Paint paint)
	{
		for(Turnip t:collection)
		{
			t.draw(canvas, paint);
		}
	}
	
	public void logic()
	{
		for(Turnip t:collection)
		{
			t.logic();
		}
		if(collection.size()<=0)
		{
			gsv.gameState=GameSurfaceView.GAME_OVER;
			Log.i("TurnipCollection","ÂÜ²·±»³ÔÍê");
		}
	}
	
	public static void reduce()
	{
		collection.remove(collection.size()-1);
		Turnip.play();
	}
}
