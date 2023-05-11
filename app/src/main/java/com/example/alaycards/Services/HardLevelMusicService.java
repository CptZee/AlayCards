package com.example.alaycards.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.alaycards.R;

public class HardLevelMusicService extends Service {
    private MediaPlayer mediaPlayer;
    private final IBinder binder = new HardLevelMusicService.MusicServiceBinder();

    public class MusicServiceBinder extends Binder {
        HardLevelMusicService getService() {
            return HardLevelMusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.fx_hard);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.start());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void pausePlayback() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void resumePlayback() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}