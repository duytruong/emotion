package com.duyrau.emotion;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.duyrau.emotion.adapter.EmotionGroupAdapter;
import com.duyrau.emotion.adapter.EmotionItemAdapter;
import com.duyrau.emotion.adapter.SentenceAdapter;
import com.duyrau.emotion.model.EmotionGroup;
import com.duyrau.emotion.model.EmotionItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView emotionGroupListView;
    private GridView emotionItemGridView;
    private RecyclerView recyclerView;
    private EmotionItemAdapter emotionItemAdapter;
    private SentenceAdapter sentenceAdapter;
    private EmotionGroupAdapter emotionGroupAdapter;
    private MediaPlayer mediaPlayer, sentencePlayer;

    private int[] emotionIds = {R.drawable.camgiac_vui, R.drawable.camgiac_buon};
    private int[] foodIds = {R.drawable.thucan_banh, R.drawable.thucan_banhmi, R.drawable.thucan_ca, R.drawable.thucan_com,
        R.drawable.thucan_kem, R.drawable.thucan_kfc, R.drawable.thucan_nho, R.drawable.thucan_pho, R.drawable.thucan_pizza,
        R.drawable.thucan_tao, R.drawable.thucan_traicay, R.drawable.thucan_trung};
    private String[] emotionItemsName = {"camgiac_vui", "camgiac_buon"};
    private String[] foodItemsName = {"thucan_banh", "thucan_banhmi", "thucan_ca", "thucan_com", "thucan_kem",
        "thucan_kfc", "thucan_nho", "thucan_pho", "thucan_pizza", "thucan_tao","thucan_traicay","thucan_trung"};
    private int[] emotionItemsAudio = {R.raw.camxuc_vui, R.raw.camxuc_buon};
    private int[] foodItemsAudio = {R.raw.thucan_banh, R.raw.thucan_banhmi, R.raw.thucan_ca, R.raw.thucan_com,
        R.raw.thucan_kem, R.raw.thucan_kfc, R.raw.thucan_nho, R.raw.thucan_pho, R.raw.thucan_pizza,
        R.raw.thucan_tao, R.raw.thucan_traicay, R.raw.thucan_trung};

    private int[] nhanvatIds = {R.drawable.gapai_ongba, R.drawable.gapai_ba,
            R.drawable.gapai_me, R.drawable.gapai_thaygiao};
    private String[] nhanvatItemsName = {"gapai_ongba", "gapai_ba", "gapai_me", "gapai_thaygiao"};
    private int[] gapaiAudio = {R.raw.gapai_ongba, R.raw.gapai_ba, R.raw.gapai_me, R.raw.gapai_thaygiao};

    private int[] hanhdongIds = {R.drawable.hanhdong_an, R.drawable.hanhdong_xedap, R.drawable.hanhdong_boi, R.drawable.hanhdong_dabanh, R.drawable.hanhdong_danhrang,
            R.drawable.hanhdong_dichoi, R.drawable.hanhdong_divesinh, R.drawable.hanhdong_ngu, R.drawable.hanhdong_ruamat, R.drawable.hanhdong_ruatay,
            R.drawable.hanhdong_tam, R.drawable.hanhdong_thayquanao, R.drawable.hanhdong_uong};
    private String[] hanhdongItemsName = {"hanhdong_an", "hanhdong_boi", "hanhdong_dabanh", "hanhdong_danhrang",
            "hanhdong_dichoi", "hanhdong_divesinh", "hanhdong_ngu", "hanhdong_ruamat", "hanhdong_ruatay", "hanhdong_tam",
            "hanhdong_thayquanao", "hanhdong_uong", "hanhdong_xedap"};
    private int[] hanhdongAudio = {R.raw.hanhdong_an, R.raw.hanhdong_xedap, R.raw.hanhdong_boi, R.raw.hanhdong_dabanh, R.raw.hanhdong_danhrang,
            R.raw.hanhdong_dichoi, R.raw.hanhdong_divesinh, R.raw.hanhdong_ngu, R.raw.hanhdong_ruamat, R.raw.hanhdong_ruatay,
            R.raw.hanhdong_tam, R.raw.hanhdong_thayquanao, R.raw.hanhdong_uong};

    private List<EmotionItem> sentence = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionGroupListView = (ListView)findViewById(R.id.listView);
        emotionItemGridView = (GridView)findViewById(R.id.gridview_emotions);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_sentence);
        LinearLayoutManager sentenceLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(sentenceLayoutManager);

        sentenceAdapter = new SentenceAdapter(this, new ArrayList<EmotionItem>(), sentencePlayer);
        recyclerView.setAdapter(sentenceAdapter);

        emotionGroupAdapter = new EmotionGroupAdapter(this, createEmotionGroups());
        emotionGroupListView.setAdapter(emotionGroupAdapter);
        emotionGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                emotionItemAdapter = new EmotionItemAdapter(getApplicationContext(),
                        ((EmotionGroup)emotionGroupAdapter.getItem(position)).getItems());
                emotionItemGridView.setAdapter(emotionItemAdapter);
            }
        });

        emotionItemGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int resid = getResources().getIdentifier("e" + groupIdx + "_" + itemIdx,
//                        "raw", view.getContext().getPackageName());
                mediaPlayer = MediaPlayer.create(view.getContext(),
                        ((EmotionItem)emotionItemAdapter.getItem(position)).getAudioId());
                mediaPlayer.start();

                sentence.add((EmotionItem) emotionItemAdapter.getItem(position));
                sentenceAdapter.setmData(sentence);
                sentenceAdapter.notifyDataSetChanged();

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
        groups.add(createEmotionGroupContainsItems(R.drawable.camxuc, createEmotionItems(emotionItemsName.length,
                emotionItemsName, emotionIds, emotionItemsAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.thucan, createEmotionItems(foodItemsName.length,
                foodItemsName, foodIds, foodItemsAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.gapai, createEmotionItems(nhanvatItemsName.length,
                nhanvatItemsName, nhanvatIds, gapaiAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.hanhdong, createEmotionItems(hanhdongItemsName.length,
                hanhdongItemsName, hanhdongIds, hanhdongAudio)));
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

//        if (sentencePlayer != null) {
//            sentencePlayer.release();
//            sentencePlayer = null;
//        }
    }



}
