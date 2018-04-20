package com.person.myy.moleattack_as.object;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyMusicService extends Service{

	private MediaPlayer player;
	
	public MyMusicService(Context context,int id) {
		super();
		player= MediaPlayer.create(context, id); 
		player.setLooping(true);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		try {
//			player.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
        boolean playing = intent.getBooleanExtra("playing", false);
        if (playing) {
            player.start();
        } else {
            player.pause();
        }
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
        player.release();
        stopSelf();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
