package com.person.myy.moleattack_as;

import android.graphics.Matrix;
import android.graphics.Point;
import android.view.View;
/**
 * 用于坐标适应性处理
 * @author myy
 *
 */
public class GameSize {
	private static int O_W=320,O_H=480;
	private static int N_W,N_H;
	private static float WSCALE,HSCALE;
	
	public GameSize(View v)
	{
	}
	/**
	 * 初始化参数
	 * @param v
	 */
	public static void setView(View v)
	{
		N_W=v.getWidth();
		N_H=v.getHeight();
		WSCALE=(float)N_W/(float)O_W;
		HSCALE=(float)N_H/(float)O_H;
	}
	/**
	 * 通过比例把点的坐标缩放到合适的点
	 * @param p 在320(O_W)*480(O_H)的屏幕上想放点的的坐标
	 * @return
	 */
	public static Point getNewPiont(Point p)
	{
		if(p.x>O_W||p.y>O_H)
		{
			return null;
		}
		return new Point((int)(p.x*WSCALE),(int)(p.y*HSCALE));
	}
	
	public static float getNewY(float y)
	{
		return y*HSCALE;
	}
	
	public static float getNewX(float x)
	{
		return x*WSCALE;
	}
}
