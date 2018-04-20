package com.person.myy.moleattack_as.collection;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

public class GameSoundCollection {
	private static final int MAXSIZE=10;
	public SoundPool collection;
	private HashMap<String,Integer> map;
	
	public GameSoundCollection()
	{
		collection = new SoundPool(MAXSIZE,AudioManager.STREAM_MUSIC,0);
		map=new HashMap<String,Integer>();
	}
	
	public void load(Context context,String[] fileNames,String keys[]) throws IOException {
		int id;
		AssetManager assetManager = context.getAssets();
		for (int i =0;i<fileNames.length;i++){
			id=collection.load(assetManager.openFd(fileNames[i]),1);
			map.put(keys[i], id);
		}
	}
	
	public void play(String key)
	{
		Integer id= map.get(key);
		if(id==null){
			Log.i("test","id is null");
		}
		collection.play(id, 1, 1,0,0,1);
	}
}
