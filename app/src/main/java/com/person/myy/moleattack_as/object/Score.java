package com.person.myy.moleattack_as.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.person.myy.moleattack_as.GameSize;
import com.person.myy.moleattack_as.GameSurfaceView;


public class Score {
	//文本移动的速度
	public final static float TEXT_SPE= GameSize.getNewY(2);
	//CHANGE_FLAG_FORDURATION改变地鼠随机上升线程的间隔的分值标准
	public final static int	SCORE_ADD=10,CHANGE_FLAG_FORDURATION=10;
	//CHANGE_FLAG_FORSPE改变地鼠速度的分数变化数值标准
	public final static int CHANGE_FLAG_FORSPE=30,TEXT_MOVE=0,TEXT_STAND=1;
	private  static int SIZE=(int)GameSize.getNewY(20);
	private int x,y;
	private Rect rect;
	public int color=Color.RED;
	public boolean islive;
	public int state;
	private int text_w,text_h;
	private int score=0,f_score1=0,f_score2=0;
	private String text="当前分数"+score;
	private GameSurfaceView gsv;
	
	public Score(GameSurfaceView gsv)
	{
		this.gsv=gsv;
		x=0;
		y=GameSurfaceView.SCREEN_H*9/10;
		islive=true;
		state = TEXT_MOVE;
	}
	
	public void draw(Canvas canvas,Paint paint)
	{
		if (rect==null) 
		{
			rect = new Rect();
			paint.getTextBounds(text, 0, text.length(), rect);
			text_w = rect.width();
			text_h = rect.height();
		}
		paint.setTextSize(SIZE);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		int c = paint.getColor();
		paint.setColor(color);
		if(islive==true)
		{
			canvas.drawText(text, x, y, paint);
		}
		paint.setColor(c);
//		Log.i("Score"," size "+size);
	}
	
	public void logic()
	{
		if(islive==true)
		{
			if(state==TEXT_MOVE)
			{
				if(x<=GameSurfaceView.SCREEN_W)
				{
					x+=TEXT_SPE;
				}
				else 
				{
					x=0-text_w;
				}
			}
			else if(state==TEXT_STAND)
			{
				//nothing
			}
		}
		onChange();
	}
	/**
	 * 当分数变化时执行的方法
	 */
	public void onChange()
	{
		if(score-f_score1>=CHANGE_FLAG_FORDURATION)
		{
			gsv.up_thread.decreadDuration();
			f_score1+=CHANGE_FLAG_FORDURATION;
		}
		if(score-f_score2>=CHANGE_FLAG_FORSPE)
		{
			gsv.m_collection.increaSpe();
			f_score2+=CHANGE_FLAG_FORSPE;
		}
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void reset()
	{
		x=0;
		score=0;
		text="当前分数"+score;//重新设置text
		gsv.up_thread.resetDuration();//重置线程间隔
		gsv.m_collection.resetSpe();//重置地鼠速度
	}
	
	public void increase()
	{
		score+=SCORE_ADD;
		text="当前分数"+score;//重新设置text
	}
}
