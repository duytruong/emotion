package com.duyrau.emotion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.duyrau.emotion.adapter.EmotionAdapter;
import com.duyrau.emotion.model.Emotion;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView emotionGroupsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Integer[] data = {R.mipmap.ic_launcher,
//                R.mipmap.ic_launcher,
//                R.mipmap.ic_launcher};

        List<Emotion> emotions = new ArrayList<>();
        emotions.add(new Emotion(R.drawable.e));
        emotions.add(new Emotion(R.drawable.e));
        emotions.add(new Emotion(R.drawable.e));

        emotionGroupsListView = (ListView)findViewById(R.id.listView);
        emotionGroupsListView.setAdapter(new EmotionAdapter(this, emotions));
    }
}
