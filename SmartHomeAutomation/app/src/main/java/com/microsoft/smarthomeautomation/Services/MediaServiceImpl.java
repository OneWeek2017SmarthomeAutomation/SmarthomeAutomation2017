package com.microsoft.smarthomeautomation.Services;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by andlam on 7/25/2017.
 */

public class MediaServiceImpl implements MediaService {

    public static final int MAX_VOL = 100;
    private MediaPlayer player;
    private Context context;

    public MediaServiceImpl(Context context) {
        this.player = null;
        this.context = context;
    }

    @Override
    public boolean StartMediaPlayer(String songFilename) {
        MediaPlayer mp = new MediaPlayer();

        try {
            mp.setDataSource(songFilename);
            mp.prepare();
        } catch (IOException io) {
            mp.release();
            return false;
        }

        this.player = mp;
        mp.start();
        return true;
    }

    @Override
    public boolean SetMediaVolume(int volume) {
        if (volume < 0 || volume > MAX_VOL) {
            throw new IllegalArgumentException("Volume is out of allowable range");
        }
        if (this.player == null) {
            return false;
        }
        float vol = (float) (Math.log(MAX_VOL - volume) / Math.log(MAX_VOL));
        this.player.setVolume(vol, vol);
        return true;
    }

    @Override
    public boolean StopMediaPlayer() {
        if (this.player == null) {
            //throw new IllegalArgumentException("No Media player to stop");
            return false;
        }
        this.player.stop();
        this.player.release();
        this.player = null;
        return true;
    }
}
