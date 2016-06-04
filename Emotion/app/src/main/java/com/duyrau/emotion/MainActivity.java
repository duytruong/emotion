package com.duyrau.emotion;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    private Button btnDeleteWord, btnAbout;

    private int[] emotionIds = {R.drawable.camxuc_vui, R.drawable.camxuc_buon, R.drawable.camxuc_buonngu, R.drawable.camxuc_dau, R.drawable.camxuc_doi, R.drawable.camxuc_khat, R.drawable.camxuc_khoc,
            R.drawable.camxuc_khongthich, R.drawable.camxuc_muon, R.drawable.camxuc_so, R.drawable.camxuc_thich};
    private String[] emotionItemsName = {"camxuc_vui", "camxuc_buon", "camxuc_buonngu", "camxuc_dau", "camxuc_doi", "camxuc_khat", "camxuc_khoc",
            "camxuc_khongthich", "camxuc_muon", "camxuc_so", "camxuc_thich"};
    private int[] emotionItemsAudio = {R.raw.camxuc_vui, R.raw.camxuc_buon, R.raw.camxuc_buonngu, R.raw.camxuc_dau, R.raw.camxuc_doi, R.raw.camxuc_khat, R.raw.camxuc_khoc,
            R.raw.camxuc_khongthich, R.raw.camxuc_muon, R.raw.camxuc_so, R.raw.camxuc_thich};

    private int[] foodIds = {R.drawable.thucan_banh, R.drawable.thucan_banhmi, R.drawable.thucan_ca, R.drawable.thucan_com,
        R.drawable.thucan_kem, R.drawable.thucan_kfc, R.drawable.thucan_nho, R.drawable.thucan_pho, R.drawable.thucan_pizza,
        R.drawable.thucan_tao, R.drawable.thucan_traicay, R.drawable.thucan_trung};
    private String[] foodItemsName = {"thucan_banh", "thucan_banhmi", "thucan_ca", "thucan_com", "thucan_kem",
        "thucan_kfc", "thucan_nho", "thucan_pho", "thucan_pizza", "thucan_tao","thucan_traicay","thucan_trung"};
    private int[] foodItemsAudio = {R.raw.thucan_banh, R.raw.thucan_banhmi, R.raw.thucan_ca, R.raw.thucan_com,
        R.raw.thucan_kem, R.raw.thucan_kfc, R.raw.thucan_nho, R.raw.thucan_pho, R.raw.thucan_pizza,
        R.raw.thucan_tao, R.raw.thucan_traicay, R.raw.thucan_trung};

    private int[] nhanvatIds = {R.drawable.gapai_con, R.drawable.gapai_ongba, R.drawable.gapai_bacsi, R.drawable.gapai_anhchi, R.drawable.gapai_cogiao, R.drawable.gapai_me, R.drawable.gapai_ba,
            R.drawable.gapai_embe, R.drawable.gapai_thaygiao};
    private String[] nhanvatItemsName = {"gapai_con", "gapai_ongba", "gapai_bacsi", "gapai_anhchi", "gapai_cogiao", "gapai_me", "gapai_ba", "gapai_embe", "gapai_thaygiao"};
    private int[] gapaiAudio = {R.raw.gapai_con, R.raw.gapai_ongba, R.raw.gapai_bacsi, R.raw.gapai_anhchi, R.raw.gapai_cogiao, R.raw.gapai_me, R.raw.gapai_ba,
            R.raw.gapai_embe, R.raw.gapai_thaygiao};

    private int[] hanhdongIds = {R.drawable.hanhdong_an, R.drawable.hanhdong_xedap, R.drawable.hanhdong_boi, R.drawable.hanhdong_dabanh, R.drawable.hanhdong_danhrang,
            R.drawable.hanhdong_dichoi, R.drawable.hanhdong_divesinh, R.drawable.hanhdong_ngu, R.drawable.hanhdong_ruamat, R.drawable.hanhdong_ruatay,
            R.drawable.hanhdong_tam, R.drawable.hanhdong_thayquanao, R.drawable.hanhdong_uong};
    private String[] hanhdongItemsName = {"hanhdong_an", "hanhdong_boi", "hanhdong_dabanh", "hanhdong_danhrang",
            "hanhdong_dichoi", "hanhdong_divesinh", "hanhdong_ngu", "hanhdong_ruamat", "hanhdong_ruatay", "hanhdong_tam",
            "hanhdong_thayquanao", "hanhdong_uong", "hanhdong_xedap"};
    private int[] hanhdongAudio = {R.raw.hanhdong_an, R.raw.hanhdong_xedap, R.raw.hanhdong_boi, R.raw.hanhdong_dabanh, R.raw.hanhdong_danhrang,
            R.raw.hanhdong_dichoi, R.raw.hanhdong_divesinh, R.raw.hanhdong_ngu, R.raw.hanhdong_ruamat, R.raw.hanhdong_ruatay,
            R.raw.hanhdong_tam, R.raw.hanhdong_thayquanao, R.raw.hanhdong_uong};

    private int[] douongIds = {R.drawable.douong_cocacola, R.drawable.douong_dau, R.drawable.douong_nuoccam,
            R.drawable.douong_nuocep, R.drawable.douong_nuockhoang, R.drawable.douong_nuocmia,
            R.drawable.douong_sua, R.drawable.douong_suadaunanh};
    private String[] douongItemsName = {"douong_cocacola", "douong_dau", "douong_nuoccam", "douong_nuocep",
            "douong_nuockhoang", "douong_nuocmia", "douong_sua", "douong_suadaunanh"};

    private int[] douongAudio = {R.raw.douong_cocacola, R.raw.douong_dau, R.raw.douong_nuoccam,
            R.raw.douong_nuocep, R.raw.douong_nuockhoang, R.raw.douong_nuocmia,
            R.raw.douong_sua, R.raw.douong_suadaunanh};

    private List<EmotionItem> sentence = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionGroupListView = (ListView)findViewById(R.id.listView);
        emotionItemGridView = (GridView)findViewById(R.id.gridview_emotions);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_sentence);
        btnDeleteWord = (Button) findViewById(R.id.btn_delete_word);
        btnAbout = (Button) findViewById(R.id.btn_about);

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

        btnDeleteWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EmotionItem> sentenceItems = sentenceAdapter.getmData();
                if (sentenceItems != null && sentenceItems.size() > 0) {
                    sentenceItems.remove(sentenceItems.size() - 1);
                    sentenceAdapter.notifyDataSetChanged();
                }
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);

                // set title
                alertDialogBuilder.setTitle("Về ứng dụng nguồn mở \"I'm\"");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Phiên bản 1.0\n" +
                                "Ngày phát triển: 5/6/2016\n" +
                                "Được phát triển bởi: Nhóm 1 - Tikkun Olam Makers Vietnam 2016\n" +
                                "Nhằm mục đích hỗ trợ trẻ tự kỷ trong việc giao tiếp. \n" +
                                "Nguồn hình ảnh: Thư viện ảnh PAXT tại website: concuame.com\n" +
                                "Email góp ý: ducduytruong2012@gmail.com")
                        .setCancelable(false)
                        .setPositiveButton("Đóng",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
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

        groups.add(createEmotionGroupContainsItems(R.drawable.gapai, createEmotionItems(nhanvatItemsName.length,
                nhanvatItemsName, nhanvatIds, gapaiAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.camxuc, createEmotionItems(emotionItemsName.length,
                emotionItemsName, emotionIds, emotionItemsAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.thucan, createEmotionItems(foodItemsName.length,
                foodItemsName, foodIds, foodItemsAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.douong, createEmotionItems(douongItemsName.length,
                douongItemsName, douongIds, douongAudio)));
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
    }

}