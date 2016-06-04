package com.duyrau.emotion.listener;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyrau on 4/6/2016.
 */
public class MediaCompletionListener implements MediaPlayer.OnCompletionListener {

    private Context mContext;
    private MediaPlayer mediaPlayer;

    List<Integer> buffers = new ArrayList<>();

    public MediaCompletionListener(Context c, MediaPlayer m) {
        mContext = c;
//        mp = m;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
//        mediaPlayer.release();
//        mp = null;
//        idx++;
//        mp = MediaPlayer.create(mContext, audios[idx]);
    }
}
