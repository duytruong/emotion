package com.duyrau.emotion.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duyrau.emotion.R;
import com.duyrau.emotion.listener.MediaCompletionListener;
import com.duyrau.emotion.model.EmotionItem;

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

    public void setmData(List<EmotionItem> mData) {
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);

        // each item width of recycler view has a width of one quarter of parent's width.
        itemView.getLayoutParams().width = parent.getWidth() / 4;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imgView.setImageResource(mData.get(position).getImageId());

        final int[] audios = new int[mData.size()];
        for (int i = 0, len = mData.size(); i < len; i++) {
            audios[i] = mData.get(i).getAudioId();
        }


        holder.imgView.setOnClickListener(new View.OnClickListener() {

            int idx = 0;

            @Override
            public void onClick(View v) {

                if (mPlayer != null) {
                    mPlayer.release();
                    mPlayer = null;
                }
//                        mPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/" + mData.get(i).getName() + ".mp3");

                mPlayer = MediaPlayer.create(v.getContext(), audios[idx]);
                mPlayer.start();
                mPlayer.setOnCompletionListener(new MediaCompletionListener(mContext, mPlayer));

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
}
