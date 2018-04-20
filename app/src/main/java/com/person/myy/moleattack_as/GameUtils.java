package com.person.myy.moleattack_as;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class GameUtils {
	
	public static boolean contains(float x,float y,float f,float g,int p_x,int p_y)
	{
		if(p_x>=x&&p_x<=f)
		{
			if(p_y>=y&&p_y<=g)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean contains(Rect rect,int p_x,int p_y)
	{
		int x1,y1,x2,y2;
		x1=rect.left;
		y1=rect.top;
		x2=rect.right;
		y2=rect.bottom;
		return contains(x1,y1,x2,y2,p_x,p_y);
	}
	
	/**
	 * 用于调整大图的宽高
	 * @param bitmap 一般为用作背景的大图，或宽高想与屏幕宽高匹配的
	 * @return 新图
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap ,float f,float g)
	{
		Bitmap n_bitmap = Bitmap.createScaledBitmap(bitmap, (int)f, (int)g, true);
		return n_bitmap;
	}
/**
 * 以GmaeSize设定的屏幕宽高对图片进行适应性伸缩
 * @return
 */
	public static Bitmap resizeBitmap(Bitmap bitmap) {
		return resizeBitmap(bitmap,GameSize.getNewX(bitmap.getWidth())
				,GameSize.getNewY(bitmap.getHeight()));
	}
}
