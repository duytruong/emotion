package com.duyrau.emotion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.duyrau.emotion.R;
import com.duyrau.emotion.model.EmotionGroup;

import java.util.List;

/**
 * Created by duyrau on 22/5/2016.
 */
public class EmotionGroupAdapter extends BaseAdapter {

    private Context mContext;
    private List<EmotionGroup> mData;

    public EmotionGroupAdapter(Context c, List<EmotionGroup> emotionGroups) {
        mContext = c;
        mData = emotionGroups;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
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
            view = inflater.inflate(R.layout.list_item_emotion_group, parent, false);
        } else {
            view = convertView;
        }
//        TextView txtName = (TextView) view.findViewById(R.id.txt_champion_name);
//        txtName.setText(mObjects.get(position).getName());
        ImageView imgView = (ImageView) view.findViewById(R.id.list_item_emotion_group_imageview);
        imgView.setImageResource(mData.get(position).getImageId());
        return view;
    }
}
