package com.duyrau.emotion.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duyrau.emotion.R;
import com.duyrau.emotion.model.EmotionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyrau on 3/6/2016.
 */
public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.MyViewHolder> {

    private Context mContext;

    private List<EmotionItem> mData;
    private MediaPlayer mPlayer;

    public SentenceAdapter(Context c, List<EmotionItem> items, MediaPlayer player) {
        mContext = c;
        mData = items;
        mPlayer = player;
    }

    public List<EmotionItem> getmData() {
        return mData;
    }

    public void setmData(List<EmotionItem> mData) {
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);

        // each item width of recycler view has a width of one quarter of parent's width.
        itemView.getLayoutParams().width = parent.getWidth() / 6;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imgView.setImageResource(mData.get(position).getImageId());

        final List<Integer> playlist = new ArrayList<>();
        for (int i = 0, len = mData.size(); i < len; i++) {
            playlist.add(mData.get(i).getAudioId());
        }


        holder.imgView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playListOfAudioSequentially(mContext, playlist);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;

        public MyViewHolder(View view) {
            super(view);
            imgView = (ImageView) view.findViewById(R.id.rv_item_imageview);

        }
    }

    void playListOfAudioSequentially(Context c, List<Integer> playlist) {
        play(c, playlist);
    }

    private void play(final Context c, List<Integer> playlist) {
        stop();
        if (playlist != null && playlist.size() > 0) {
            final List<Integer> pList = new ArrayList<>(playlist);

            int audioId = pList.get(0);
            pList.remove(0);

            mPlayer = MediaPlayer.create(c, audioId);

            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (mp == mPlayer) {
                        mPlayer.start();
                    }
                }
            });

            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stop();
                    // Recursively call the play() method with one less
                    // track in the list.
                    play(c, pList);
                }
            });
        }
    }

    private void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
        }
    }
}
