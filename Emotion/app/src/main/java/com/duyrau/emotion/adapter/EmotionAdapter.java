package com.duyrau.emotion.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duyrau.emotion.R;
import com.duyrau.emotion.model.Emotion;

import java.util.List;

/**
 * Created by duyrau on 22/5/2016.
 */
public class EmotionAdapter extends BaseAdapter {
    private Context mContext;
    private List<Emotion> mData;

    public EmotionAdapter(Context c, List<Emotion> emotions) {
        mContext = c;
        mData = emotions;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_champion, parent, false);
        } else {
            view = convertView;
        }
        ImageView imgView = (ImageView) view.findViewById(R.id.image_champion_avatar);
        TextView txtName = (TextView) view.findViewById(R.id.txt_champion_name);
//        imgView.setImageResource(mObjects.get(position).getImageId());
        Bitmap bmp = loadBitmapPiece(position);
        imgView.setImageBitmap(bmp);
        txtName.setText(mObjects.get(position).getName());
        return view;
    }
}
