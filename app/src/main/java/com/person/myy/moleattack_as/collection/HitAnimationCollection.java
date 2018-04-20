package com.person.myy.moleattack_as.collection;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.person.myy.moleattack_as.object.HitAnimation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class HitAnimationCollection {

	public static List<HitAnimation> collection;
	
	public HitAnimationCollection()
	{
		collection = new LinkedList<HitAnimation>();
	}
	
	public void draw(Canvas canvas,Paint paint)
	{
		for(HitAnimation ha:collection)
		{
			ha.draw(canvas, paint);
		}
	}
	
	public void clear()
	{
		if(collection.size()>0)
		{
			collection.clear();
		}
	}
	public void logic()
	{
		Iterator<HitAnimation> i = collection.iterator();
		List<HitAnimation> dle_list = new LinkedList<HitAnimation>();
		while(i.hasNext())
		{
			HitAnimation ha= i.next();
			if(ha.finish==true)
			{
				dle_list.add(ha);
			}
		}
		collection.removeAll(dle_list);
	}
	
	public void add(HitAnimation ha)
	{
		collection.add(ha);
		ha.play();
	}
}
