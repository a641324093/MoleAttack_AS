package com.person.myy.moleattack_as.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.person.myy.moleattack_as.GameSurfaceView;

import java.util.HashMap;

public class HitAnimation {

	private HashMap <String,Bitmap> map;
	public int draw_step;
	public float x, y;
	private float bmg_w,bmg_h,draw_x,draw_y;;
	public boolean finish;
	private GameSurfaceView gsv;
	
	public HitAnimation (int x ,int y,GameSurfaceView gsv,HashMap<String,Bitmap> map)
	{
//		this.x=GameSize.getNewX(x);
//		this.y=GameSize.getNewY(y);
		this.x=x;
		this.y=y;
		this.gsv=gsv;
		this.map=map;
		bmg_w=map.get("H3").getWidth();
		bmg_h=map.get("H3").getHeight();
		
		draw_x=x-bmg_w/2;
		draw_y=y-bmg_h/2;
		draw_step=1;
		finish=false;
	}
	
	public void draw(Canvas canvas,Paint paint)
	{
		if(finish==false)
		{
			if(draw_step<=1)
			{
				canvas.drawBitmap(map.get("H3"),draw_x,draw_y, paint);
				draw_step++;
				
			}
			else if(draw_step<=2)
			{
				canvas.drawBitmap(map.get("H3"),draw_x,draw_y, paint);
				draw_step++;
			}
			else if(draw_step<=3)
			{
				canvas.drawBitmap(map.get("H3"),draw_x,draw_y, paint);
				draw_step++;
			}
			else if(draw_step<=4)
			{
				canvas.drawBitmap(map.get("H3"),draw_x,draw_y, paint);
				draw_step++;
			}
			else if(draw_step<=5)
			{
				canvas.drawBitmap(map.get("H3"),draw_x,draw_y, paint);
				draw_step=1;
				finish=true;
			}
		}
	}
	
	public void play()
	{
//		gsv.gs_collection.play("HIT");
	}
	
}
