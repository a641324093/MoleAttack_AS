package com.person.myy.moleattack_as.collection;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.HashMap;

public class MediaPlayerCollection {
	
	private HashMap<String,MediaPlayer> map;
	private Intent intent;
	
	public MediaPlayerCollection()
	{
		map = new HashMap<String,MediaPlayer>();
	}
	
	public void load(Context context,String fileNames[],String keys[]) throws IOException {
		AssetManager assetManager = context.getAssets();
		MediaPlayer player;
		for(int i = 0;i<fileNames.length;i++){
			player= new MediaPlayer();
			player.setDataSource(assetManager.openFd(fileNames[i]).getFileDescriptor());
			player.setLooping(true);
			map.put(keys[i], player);
			try {
				player.prepare();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//不要close,系统还会使用
//		assetManager.close();
	}

	public void play(String key)
	{
		MediaPlayer player;
		player = map.get(key);
		player.setLooping(true);//设置为循环
		player.start();
	//	Log.i("MediaPlayerCollection","当前播放时间："+player.getCurrentPosition()+" 是否循环 "+player.isLooping());

		//		MyMusicService mms;
//		intent = new Intent(MainActivity.instance,MyMusicService.class);
//		mms=map.get(key);
//		mms.startService(intent);
	}
	public void pause(String key)
	{
		MediaPlayer player;
		player = map.get(key);
		if(player.getCurrentPosition()>0)
		{
			player.pause();
		}
		
	}
	
	public void stop(String key)
	{
		MediaPlayer player;
		player = map.get(key);
		if(player.getCurrentPosition()>0)
		{
			player.stop();
		}
		
	}


}
