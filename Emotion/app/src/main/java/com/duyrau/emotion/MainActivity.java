package com.duyrau.emotion;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.duyrau.emotion.adapter.EmotionGroupAdapter;
import com.duyrau.emotion.adapter.EmotionItemAdapter;
import com.duyrau.emotion.model.EmotionGroup;
import com.duyrau.emotion.model.EmotionItem;

import org.lucasr.twowayview.TwoWayView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView emotionGroupListView;
    private GridView emotionItemGridView;
    private TwoWayView sentenceListView;

    private EmotionItemAdapter emotionItemAdapter, sentenceAdapter;
    private EmotionGroupAdapter emotionGroupAdapter;
    private MediaPlayer mediaPlayer;

    private int[] emotionIds = {R.drawable.happy, R.drawable.sad};
    private int[] foodIds = {R.drawable.chicken, R.drawable.noodle};
    private String[] emotionItemsName = {"emotion_happy", "emotion_sad"};
    private String[] foodItemsName = {"food_chicken", "food_noodle"};
    private int[] emotionItemsAudio = {R.raw.emotion_happy, R.raw.emotion_sad};
    private int[] foodItemsAudio = {R.raw.food_chicken, R.raw.food_noodle};

    private long groupIdx = 0;
    private long itemIdx = 0;

    private List<EmotionItem> sentence = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionGroupListView = (ListView)findViewById(R.id.listView);
        emotionItemGridView = (GridView)findViewById(R.id.gridview_emotions);
        sentenceListView  = (TwoWayView)findViewById(R.id.listview_sentence);

        emotionGroupAdapter = new EmotionGroupAdapter(this, createEmotionGroups());
        emotionGroupListView.setAdapter(emotionGroupAdapter);
        emotionGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                groupIdx = id;
                emotionItemAdapter = new EmotionItemAdapter(getApplicationContext(),
                        ((EmotionGroup)emotionGroupAdapter.getItem(position)).getItems());
                emotionItemGridView.setAdapter(emotionItemAdapter);
            }
        });

        emotionItemGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemIdx = id;
                int resid = getResources().getIdentifier("e" + groupIdx + "_" + itemIdx,
                        "raw", view.getContext().getPackageName());
                if (resid != 0) {
                    mediaPlayer = MediaPlayer.create(view.getContext(), resid);
                    mediaPlayer.start();
                }
                sentence.add((EmotionItem) emotionItemAdapter.getItem(position));
                sentenceAdapter = new EmotionItemAdapter(getApplicationContext(), sentence);
                sentenceListView.setAdapter(sentenceAdapter);
            }
        });

        sentenceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0, len = sentence.size(); i < len; i++) {
                    try {
                        mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() +
                                "/" + sentence.get(i).getName() + ".mp3");
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    private List<EmotionItem> createEmotionItems(int size, String[] names, int[] imgIds, int[] audioIds) {
        List<EmotionItem> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(new EmotionItem(names[i], imgIds[i], audioIds[i]));
        }
        return items;
    }

    private EmotionGroup createEmotionGroupContainsItems(int resId, List<EmotionItem> items) {
        EmotionGroup group = new EmotionGroup(resId);
        group.setItems(items);
        return group;
    }

    private List<EmotionGroup> createEmotionGroups() {
        List<EmotionGroup> groups = new ArrayList<>();
        groups.add(createEmotionGroupContainsItems(R.drawable.emotion, createEmotionItems(2,
                emotionItemsName, emotionIds, emotionItemsAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.food, createEmotionItems(2,
                foodItemsName, foodIds, emotionItemsAudio)));
        return groups;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        emotionItemGridView.setAdapter(emotionItemAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }



}
