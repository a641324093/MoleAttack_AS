package com.person.myy.moleattack_as.object;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.person.myy.moleattack_as.GameSize;
import com.person.myy.moleattack_as.GameUtils;

public class GameButton {

	private float x,y,w,h;
	private Bitmap up,down;
	private boolean isup,adaptive;//坐标是否已采用适应性定义
	
	public GameButton(int x,int y ,Bitmap up,Bitmap down,boolean adaptive)
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
		this.up=up;
		this.down=down;
		
		w=up.getWidth();
		h=up.getHeight();
		
		isup=true;
	}
	
	public void draw(Canvas canvas, Paint paint)
	{
		if(isup)
		{
			canvas.drawBitmap(up, x, y, paint);
		}
		else
		{
			canvas.drawBitmap(down, x,y, paint);
		}
	}
	
	public boolean onTouch(MotionEvent event)
	{
		//设置用于检验是否点击到按钮的reagion
		int t_x=(int) event.getX();
		int t_y=(int) event.getY();
		//设置用于检验是否点击到按钮的reagion
		if(event.getAction()==MotionEvent.ACTION_MOVE||event.getAction()==MotionEvent.ACTION_DOWN)
		{
			if(GameUtils.contains(x,y,x+w,y+h,t_x,t_y))
			{
				isup=false;
			}
			else
			{
				isup=true;
			}
			
		}
		else if(event.getAction()==MotionEvent.ACTION_UP)
		{
			if(GameUtils.contains(x,y,x+w,y+h,t_x,t_y))
			{
				isup=true;
				return true;
			}
		}
		return false;
	}
}
