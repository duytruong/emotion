package com.duyrau.emotion.adapter;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duyrau.emotion.R;
import com.duyrau.emotion.model.EmotionItem;

import java.io.IOException;
import java.util.List;

/**
 * Created by duyrau on 3/6/2016.
 */
public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.MyViewHolder> {

    private List<EmotionItem> mData;
    private MediaPlayer mPlayer;

    public SentenceAdapter(List<EmotionItem> items, MediaPlayer player) {
        mData = items;
        mPlayer = player;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imgView.setImageResource(mData.get(position).getImageId());

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0, len = mData.size(); i < len; i++) {
                    try {

                        mPlayer.setDataSource(Environment.getExternalStorageDirectory() +
                                "/" + mData.get(i).getName() + ".mp3");
                        mPlayer.prepare();
                    } catch (IOException e) {
                    }
                }

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
