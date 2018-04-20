package com.person.myy.moleattack_as.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.person.myy.moleattack_as.GameSize;
import com.person.myy.moleattack_as.GameSurfaceView;


public class Score {
	//�ı��ƶ����ٶ�
	public final static float TEXT_SPE= GameSize.getNewY(2);
	//CHANGE_FLAG_FORDURATION�ı������������̵߳ļ���ķ�ֵ��׼
	public final static int	SCORE_ADD=10,CHANGE_FLAG_FORDURATION=10;
	//CHANGE_FLAG_FORSPE�ı�����ٶȵķ����仯��ֵ��׼
	public final static int CHANGE_FLAG_FORSPE=30,TEXT_MOVE=0,TEXT_STAND=1;
	private  static int SIZE=(int)GameSize.getNewY(20);
	private int x,y;
	private Rect rect;
	public int color=Color.RED;
	public boolean islive;
	public int state;
	private int text_w,text_h;
	private int score=0,f_score1=0,f_score2=0;
	private String text="��ǰ����"+score;
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
	 * �������仯ʱִ�еķ���
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
		text="��ǰ����"+score;//��������text
		gsv.up_thread.resetDuration();//�����̼߳��
		gsv.m_collection.resetSpe();//���õ����ٶ�
	}
	
	public void increase()
	{
		score+=SCORE_ADD;
		text="��ǰ����"+score;//��������text
	}
}
