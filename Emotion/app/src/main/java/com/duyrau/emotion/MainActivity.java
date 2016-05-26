package com.duyrau.emotion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emotionGroupsListView = (ListView)findViewById(R.id.listView);
        emotionsGridView = (GridView)findViewById(R.id.gridview_emotions);

//        Integer[] data = {R.mipmap.ic_launcher,
//                R.mipmap.ic_launcher,
//                R.mipmap.ic_launcher};

        List<EmotionGroup> emotionGroups = new ArrayList<>();
        EmotionGroup group1 = new EmotionGroup(R.drawable.e);

        List<EmotionItem> items = new ArrayList<>();
        items.add(new EmotionItem(R.drawable.ic_soccer_black_48dp));
        items.add(new EmotionItem(R.drawable.ic_food_black_48dp));
        items.add(new EmotionItem(R.drawable.e));

        group1.setItems(items);

        emotionGroups.add(group1);
        emotionGroups.add(new EmotionGroup(R.drawable.food));
        emotionGroups.add(new EmotionGroup(R.drawable.soccer));

        emotionGroupAdapter = new EmotionGroupAdapter(this, emotionGroups);
        emotionGroupsListView.setAdapter(emotionGroupAdapter);
        emotionGroupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                emotionItemAdapter = new EmotionItemAdapter(getApplicationContext(),
                        ((EmotionGroup)emotionGroupAdapter.getItem(position)).getItems());
                emotionsGridView.setAdapter(emotionItemAdapter);
            }
        });
    }
}
