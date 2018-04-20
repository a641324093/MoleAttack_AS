package com.person.myy.moleattack_as.object;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.person.myy.moleattack_as.GameSize;

public class GameText {
	private static int COLOR=Color.BLACK,TEXTSIZE=(int) GameSize.getNewY(40);
	private String text;
	private float x,y;
	private Bitmap bmp=null;
	private Rect rect;
	private int text_w,text_h;
	private boolean adaptive;
	
	public GameText(String text,int x,int y,boolean adaptive)
	{
		this.adaptive=adaptive;
		if(adaptive==false)
		{
			this.x=GameSize.getNewX(x);
			this.y=GameSize.getNewY(y);
		}
		else
		{
			this.x=x;
			this.y=y;
		}
		this.text=text;

	}
	
	public GameText(String text,int x,int y,Bitmap bmp,boolean adaptive)
	{
		this.adaptive=adaptive;
		if(adaptive==false)
		{
			this.x=GameSize.getNewX(x);
			this.y=GameSize.getNewY(y);
		}
		else
		{
			this.x=x;
			this.y=y;
		}
		this.bmp=bmp;
		this.text=text;
	}
	
	public GameText(String text,int x,int y,int color,int front_size,boolean adaptive)
	{
		this.adaptive=adaptive;
		if(adaptive==false)
		{
			this.x=GameSize.getNewX(x);
			this.y=GameSize.getNewY(y);
		}
		else
		{
			this.x=x;
			this.y=y;
		}
		this.text=text;
		this.COLOR=color;
		this.TEXTSIZE=front_size;
	}
	
	public void draw(Canvas canvas,Paint paint)
	{
		paint.setColor(COLOR);
		paint.setTextSize(TEXTSIZE);
		if (rect==null) 
		{
			rect = new Rect();
			paint.getTextBounds(text, 0, text.length(), rect);
			text_w = rect.width();
			text_h = rect.height();
		}
		
		if(bmp!=null)
		{
			canvas.drawBitmap(bmp,(x+text_w/2)-bmp.getWidth()*1/2,
					(y-text_h/2)-bmp.getHeight()*1/3, paint);
		}
		canvas.drawText(text, x, y, paint);
//		canvas.drawRect(x, y, x+text_w,y+text_h, paint);
	}
	
	public void logic()
	{
		
	}
	
	public void setText(String text)
	{
		this.text=text;
	}
}
