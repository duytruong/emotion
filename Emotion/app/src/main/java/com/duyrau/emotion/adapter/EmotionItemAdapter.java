package com.duyrau.emotion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.duyrau.emotion.R;
import com.duyrau.emotion.model.EmotionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dj81hc on 5/26/2016.
 */
public class EmotionItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<EmotionItem> mData = new ArrayList<>();

    public EmotionItemAdapter(Context c, List<EmotionItem> emotionItems) {
        mContext = c;
        setmData(emotionItems);
    }

    public List<EmotionItem> getmData() {
        return mData;
    }

    public void setmData(List<EmotionItem> mData) {
        if (mData != null) {
            this.mData = mData;
        }
    }

    @Override

    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_emotion, parent, false);
        } else {
            view = convertView;
        }
        ImageView imgView = (ImageView) view.findViewById(R.id.grid_item_emotion_imageview);
        imgView.setImageResource(mData.get(position).getImageId());
        return view;
    }
}
