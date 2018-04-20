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
	 * ���ڵ�����ͼ�Ŀ��
	 * @param bitmap һ��Ϊ���������Ĵ�ͼ������������Ļ���ƥ���
	 * @return ��ͼ
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap ,float f,float g)
	{
		Bitmap n_bitmap = Bitmap.createScaledBitmap(bitmap, (int)f, (int)g, true);
		return n_bitmap;
	}
/**
 * ��GmaeSize�趨����Ļ��߶�ͼƬ������Ӧ������
 * @return
 */
	public static Bitmap resizeBitmap(Bitmap bitmap) {
		return resizeBitmap(bitmap,GameSize.getNewX(bitmap.getWidth())
				,GameSize.getNewY(bitmap.getHeight()));
	}
}
