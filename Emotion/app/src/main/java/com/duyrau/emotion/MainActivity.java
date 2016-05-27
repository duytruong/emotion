package com.duyrau.emotion;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.duyrau.emotion.adapter.EmotionGroupAdapter;
import com.duyrau.emotion.adapter.EmotionItemAdapter;
import com.duyrau.emotion.model.EmotionGroup;
import com.duyrau.emotion.model.EmotionItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView emotionGroupsListView;
    private GridView emotionsGridView;
    private EmotionItemAdapter emotionItemAdapter;
    private EmotionGroupAdapter emotionGroupAdapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emotionGroupsListView = (ListView)findViewById(R.id.listView);
        emotionsGridView = (GridView)findViewById(R.id.gridview_emotions);

        emotionGroupAdapter = new EmotionGroupAdapter(this, createEmotionGroups());
        emotionGroupsListView.setAdapter(emotionGroupAdapter);
        emotionGroupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                emotionItemAdapter = new EmotionItemAdapter(getApplicationContext(),
                        ((EmotionGroup)emotionGroupAdapter.getItem(position)).getItems());
                emotionsGridView.setAdapter(emotionItemAdapter);
            }
        });

        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/audio0.mp3");

        Log.d("A", uri.toString());
        mediaPlayer = MediaPlayer.create(this, uri);
        emotionsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaPlayer.start();
            }
        });
    }

    private List<EmotionItem> createEmotionItems() {
        List<EmotionItem> items = new ArrayList<>();
        items.add(new EmotionItem(R.drawable.ic_soccer_black_48dp));
        items.add(new EmotionItem(R.drawable.ic_food_black_48dp));
        items.add(new EmotionItem(R.drawable.e));
        return items;
    }

    private EmotionGroup createEmotionGroupContainsItems(List<EmotionItem> items) {
        EmotionGroup group = new EmotionGroup(R.drawable.food);
        group.setItems(items);
        return group;
    }

    private List<EmotionGroup> createEmotionGroups() {
        List<EmotionGroup> groups = new ArrayList<>();
        groups.add(new EmotionGroup(R.drawable.e));
        groups.add(createEmotionGroupContainsItems(createEmotionItems()));
        groups.add(new EmotionGroup(R.drawable.soccer));
        return groups;
    }
}
