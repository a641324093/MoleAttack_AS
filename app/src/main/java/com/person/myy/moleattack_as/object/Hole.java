package com.person.myy.moleattack_as.object;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.person.myy.moleattack_as.GameSize;

public class Hole 
{
	private Bitmap hole_bmp;
	private float x,y,locat,h_w,h_h;
	private boolean islive;
	private boolean adaptive;//坐标是否已采用适应性定义
	
	public Hole(int x,int y,int locat,Bitmap hole_bmp,boolean adaptive)
	{
		this.adaptive=adaptive;
		if(adaptive==false)
		{
			this.x= GameSize.getNewX(x);
			this.y=GameSize.getNewY(y);
		}
		else
		{
			this.x=x;
			this.y=y;
		}
		this.locat=locat;
		this.hole_bmp=hole_bmp;
		h_w=hole_bmp.getWidth();
		h_h=hole_bmp.getHeight();
		islive=true;
	}
	
	public void draw(Canvas canvas,Paint paint)
	{
		if(islive==true)
		{
			canvas.drawBitmap(hole_bmp, x, y, paint);
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getH_W() {
		return h_w;
	}

	public float getH_H() {
		return h_h;
	}

	
}
