package com.person.myy.moleattack_as.collection;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.person.myy.moleattack_as.object.Hole;

import java.util.LinkedList;
import java.util.List;


public class HoleCollection {
	public static final int XSIZE=3,YSIZE=4,HOLECOUNT=XSIZE*YSIZE;
	public static List<Hole> collection;
	private Bitmap hole_bmp;
	private int hole_w,hole_h;
	//确定九方格的宽高
	public int f_w,f_h,h_x,h_y;
	private Rect hole_r;//地洞安排在这个区域内
	
	public HoleCollection(Bitmap hole_bmp,Rect hole_r)
	{
		this.hole_r=hole_r;
		this.hole_bmp=hole_bmp;
		hole_w=hole_bmp.getWidth();
		hole_h=hole_bmp.getHeight();
		collection=new LinkedList<Hole>();
	}
	
	public void draw(Canvas canvas,Paint paint)
	{
//		int num=0;
		for(Hole hole:collection)
		{
			hole.draw(canvas, paint);
//			num++;
//			Log.i("HC", ""+num);
		}
	}
	
	public void initialize()
	{
		f_w=(hole_r.right-hole_r.left)/XSIZE;
		f_h=(hole_r.bottom-hole_r.top)/YSIZE;
		if(collection.size()>0)
		{
			collection.removeAll(collection);
		}
		for(int i=0;i<HOLECOUNT;i++)
		{
			h_x=(i%XSIZE)*f_w+f_w/2-hole_w/2;
			h_y=hole_r.top+(i/XSIZE)*f_h+f_h/2-hole_h/2;
			collection.add(new Hole(h_x,h_y,i+1,hole_bmp,true));
//			Log.i("HC"," hx "+h_x+" hy "+h_y);
		}
	}
}
