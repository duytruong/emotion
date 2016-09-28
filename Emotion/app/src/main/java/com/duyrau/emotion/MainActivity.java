package com.duyrau.emotion;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by duyrau on 5/6/2016.
 */
public class MainActivity extends AppCompatActivity {

    private ListView emotionGroupListView;
    private GridView emotionItemGridView;
    private EmotionItemAdapter emotionItemAdapter;
    private SentenceAdapter sentenceAdapter;
    private EmotionGroupAdapter emotionGroupAdapter;
    private MediaPlayer mediaPlayer, sentencePlayer;
    private Button btnAbout, btnOpenTrainerMode;

    private int[] emotionIds = {R.drawable.camxuc_vui, R.drawable.camxuc_buon, R.drawable.camxuc_buonngu, R.drawable.camxuc_dau, R.drawable.camxuc_doi, R.drawable.camxuc_khat, R.drawable.camxuc_khoc,
            R.drawable.camxuc_khongthich, R.drawable.camxuc_muon, R.drawable.camxuc_so, R.drawable.camxuc_thich,
            R.drawable.camxuc_thoi,R.drawable.camxuc_lanh,R.drawable.camxuc_on,R.drawable.camxuc_nong,R.drawable.camxuc_ret,R.drawable.camxuc_met,R.drawable.camxuc_them};
    private String[] emotionItemsName = {"camxuc_vui", "camxuc_buon", "camxuc_buonngu", "camxuc_dau", "camxuc_doi", "camxuc_khat", "camxuc_khoc",
            "camxuc_khongthich", "camxuc_muon", "camxuc_so", "camxuc_thich",
            "camxuc_thoi","camxuc_lanh","camxuc_on","camxuc_nong","camxuc_ret","camxuc_met","camxuc_them"};
    private int[] emotionItemsAudio = {R.raw.camxuc_vui, R.raw.camxuc_buon, R.raw.camxuc_buonngu, R.raw.camxuc_dau, R.raw.camxuc_doi, R.raw.camxuc_khat, R.raw.camxuc_khoc,
            R.raw.camxuc_khongthich, R.raw.camxuc_muon, R.raw.camxuc_so, R.raw.camxuc_thich,
            R.raw.camxuc_thoi,R.raw.camxuc_lanh,R.raw.camxuc_on,R.raw.camxuc_nong,R.raw.camxuc_ret,R.raw.camxuc_met,R.raw.camxuc_them};

    private int[] foodIds = {R.drawable.thucan_banh, R.drawable.thucan_banhmi, R.drawable.thucan_ca, R.drawable.thucan_com,
            R.drawable.thucan_kem, R.drawable.thucan_kfc, R.drawable.thucan_nho, R.drawable.thucan_pho, R.drawable.thucan_pizza,
            R.drawable.thucan_tao, R.drawable.thucan_traicay, R.drawable.thucan_trung,
            R.drawable.thucan_chagio,R.drawable.thucan_canh,R.drawable.thucan_xoi,R.drawable.thucan_dualeo,
            R.drawable.thucan_che,R.drawable.thucan_cam,R.drawable.thucan_banhgio,R.drawable.thucan_thitbo,
            R.drawable.thucan_banhsinhnhat,R.drawable.thucan_thitga,R.drawable.thucan_thitheo,R.drawable.thucan_cachua,
            R.drawable.thucan_bap,R.drawable.thucan_dua,R.drawable.thucan_mi,R.drawable.thucan_thithop,
            R.drawable.thucan_keomut,R.drawable.thucan_banhbeo,R.drawable.thucan_thit,R.drawable.thucan_khoaitaychien,
            R.drawable.thucan_rau};
    private String[] foodItemsName = {"thucan_banh", "thucan_banhmi", "thucan_ca", "thucan_com", "thucan_kem",
            "thucan_kfc", "thucan_nho", "thucan_pho", "thucan_pizza", "thucan_tao","thucan_traicay","thucan_trung",
            "thucan_chagio","thucan_canh","thucan_xoi","thucan_dualeo","thucan_che","thucan_cam","thucan_banhgio",
            "thucan_thitbo","thucan_banhsinhnhat","thucan_thitga","thucan_thitheo","thucan_cachua","thucan_bap",
            "thucan_dua","thucan_mi","thucan_thithop","thucan_keomut","thucan_banhbeo","thucan_thit",
            "thucan_khoaitaychien","thucan_rau"};
    private int[] foodItemsAudio = {R.raw.thucan_banh, R.raw.thucan_banhmi, R.raw.thucan_ca, R.raw.thucan_com,
            R.raw.thucan_kem, R.raw.thucan_kfc, R.raw.thucan_nho, R.raw.thucan_pho, R.raw.thucan_pizza,
            R.raw.thucan_tao, R.raw.thucan_traicay, R.raw.thucan_trung,
            R.raw.thucan_chagio,R.raw.thucan_canh,R.raw.thucan_xoi,R.raw.thucan_dualeo,R.raw.thucan_che,
            R.raw.thucan_cam,R.raw.thucan_banhgio,R.raw.thucan_thitbo,R.raw.thucan_banhsinhnhat,R.raw.thucan_thitga,
            R.raw.thucan_thitheo,R.raw.thucan_cachua,R.raw.thucan_bap,R.raw.thucan_dua,R.raw.thucan_mi,
            R.raw.thucan_thithop,R.raw.thucan_keomut,R.raw.thucan_banhbeo,R.raw.thucan_thit,R.raw.thucan_khoaitaychien,
            R.raw.thucan_rau};

    private int[] nhanvatIds = {R.drawable.gapai_con, R.drawable.gapai_ongba, R.drawable.gapai_bacsi, R.drawable.gapai_anhchi, R.drawable.gapai_cogiao, R.drawable.gapai_me, R.drawable.gapai_ba,
            R.drawable.gapai_embe, R.drawable.gapai_thaygiao};
    private String[] nhanvatItemsName = {"gapai_con", "gapai_ongba", "gapai_bacsi", "gapai_anhchi", "gapai_cogiao", "gapai_me", "gapai_ba", "gapai_embe", "gapai_thaygiao"};
    private int[] gapaiAudio = {R.raw.gapai_con, R.raw.gapai_ongba, R.raw.gapai_bacsi, R.raw.gapai_anhchi, R.raw.gapai_cogiao, R.raw.gapai_me, R.raw.gapai_ba,
            R.raw.gapai_embe, R.raw.gapai_thaygiao};

    private int[] hanhdongIds = {R.drawable.hanhdong_an, R.drawable.hanhdong_xedap, R.drawable.hanhdong_boi, R.drawable.hanhdong_dabanh, R.drawable.hanhdong_danhrang,
            R.drawable.hanhdong_dichoi, R.drawable.hanhdong_divesinh, R.drawable.hanhdong_ngu, R.drawable.hanhdong_ruamat, R.drawable.hanhdong_ruatay,
            R.drawable.hanhdong_tam, R.drawable.hanhdong_thayquanao, R.drawable.hanhdong_uong,
            R.drawable.hanhdong_tambon,R.drawable.hanhdong_taptheduc,R.drawable.hanhdong_giatquanao,R.drawable.hanhdong_dongcua,
            R.drawable.hanhdong_lauban,R.drawable.hanhdong_imlang,R.drawable.hanhdong_nammo,R.drawable.hanhdong_nghelai,
            R.drawable.hanhdong_ui,R.drawable.hanhdong_phatdung,R.drawable.hanhdong_di,R.drawable.hanhdong_nhai,
            R.drawable.hanhdong_da,R.drawable.hanhdong_phatngoi,R.drawable.hanhdong_chay,R.drawable.hanhdong_nauan,
            R.drawable.hanhdong_thoathiem,R.drawable.hanhdong_can,R.drawable.hanhdong_digiay,R.drawable.hanhdong_cattoc,
            R.drawable.hanhdong_hoc,R.drawable.hanhdong_xoa,R.drawable.hanhdong_chaidau,R.drawable.hanhdong_macquanao,
            R.drawable.hanhdong_gai,R.drawable.hanhdong_xepquanao,R.drawable.hanhdong_mocua,R.drawable.hanhdong_nhay, R.drawable.hanhdong_om};
    private String[] hanhdongItemsName = {"hanhdong_an", "hanhdong_boi", "hanhdong_dabanh", "hanhdong_danhrang",
            "hanhdong_dichoi", "hanhdong_divesinh", "hanhdong_ngu", "hanhdong_ruamat", "hanhdong_ruatay", "hanhdong_tam",
            "hanhdong_thayquanao", "hanhdong_uong", "hanhdong_xedap","hanhdong_tambon","hanhdong_taptheduc","hanhdong_giatquanao",
            "hanhdong_dongcua","hanhdong_lauban","hanhdong_imlang","hanhdong_nammo","hanhdong_nghelai","hanhdong_ui",
            "hanhdong_phatdung","hanhdong_di","hanhdong_nhai","hanhdong_da","hanhdong_phatngoi","hanhdong_chay",
            "hanhdong_nauan","hanhdong_thoathiem","hanhdong_can","hanhdong_digiay","hanhdong_cattoc","hanhdong_hoc",
            "hanhdong_xoa","hanhdong_chaidau","hanhdong_macquanao","hanhdong_gai","hanhdong_xepquanao","hanhdong_mocua",
            "hanhdong_nhay","hanhdong_om"};
    private int[] hanhdongAudio = {R.raw.hanhdong_an, R.raw.hanhdong_xedap, R.raw.hanhdong_boi, R.raw.hanhdong_dabanh, R.raw.hanhdong_danhrang,
            R.raw.hanhdong_dichoi, R.raw.hanhdong_divesinh, R.raw.hanhdong_ngu, R.raw.hanhdong_ruamat, R.raw.hanhdong_ruatay,
            R.raw.hanhdong_tam, R.raw.hanhdong_thayquanao, R.raw.hanhdong_uong,
            R.raw.hanhdong_tambon,R.raw.hanhdong_taptheduc,R.raw.hanhdong_giatquanao,R.raw.hanhdong_dongcua,R.raw.hanhdong_lauban,
            R.raw.hanhdong_imlang,R.raw.hanhdong_nammo,R.raw.hanhdong_nghelai,R.raw.hanhdong_ui,R.raw.hanhdong_phatdung,
            R.raw.hanhdong_di,R.raw.hanhdong_nhai,R.raw.hanhdong_da,R.raw.hanhdong_phatngoi,R.raw.hanhdong_chay,
            R.raw.hanhdong_nauan,R.raw.hanhdong_thoathiem,R.raw.hanhdong_can,R.raw.hanhdong_digiay,R.raw.hanhdong_cattoc,
            R.raw.hanhdong_hoc,R.raw.hanhdong_xoa,R.raw.hanhdong_chaidau,R.raw.hanhdong_macquanao,R.raw.hanhdong_gai,
            R.raw.hanhdong_xepquanao,R.raw.hanhdong_mocua,R.raw.hanhdong_nhay,R.raw.hanhdong_om};

    private int[] douongIds = {R.drawable.douong_cocacola, R.drawable.douong_dau, R.drawable.douong_nuoccam,
            R.drawable.douong_nuocep, R.drawable.douong_nuockhoang, R.drawable.douong_nuocmia,
            R.drawable.douong_sua, R.drawable.douong_suadaunanh,R.drawable.douong_trada,R.drawable.douong_suasocola,
            R.drawable.douong_bia,R.drawable.douong_nuocsuoi,R.drawable.douong_tranong,R.drawable.douong_suadau,
            R.drawable.douong_capheda,R.drawable.douong_suabot,R.drawable.douong_nuocsam};
    private String[] douongItemsName = {"douong_cocacola", "douong_dau", "douong_nuoccam", "douong_nuocep",
            "douong_nuockhoang", "douong_nuocmia", "douong_sua", "douong_suadaunanh","douong_trada","douong_suasocola","douong_bia",
            "douong_nuocsuoi","douong_tranong","douong_suadau","douong_capheda","douong_suabot","douong_nuocsam"};
    private int[] douongAudio = {R.raw.douong_cocacola, R.raw.douong_dau, R.raw.douong_nuoccam,
            R.raw.douong_nuocep, R.raw.douong_nuockhoang, R.raw.douong_nuocmia,
            R.raw.douong_sua, R.raw.douong_suadaunanh,R.raw.douong_trada,R.raw.douong_suasocola,R.raw.douong_bia,
            R.raw.douong_nuocsuoi,R.raw.douong_tranong,R.raw.douong_suadau,R.raw.douong_capheda,R.raw.douong_suabot,R.raw.douong_nuocsam};

    private int[] cotheIds = {R.drawable.cothe_bung, R.drawable.cothe_cam, R.drawable.cothe_chan, R.drawable.cothe_co, R.drawable.cothe_dau, R.drawable.cothe_daugoi, R.drawable.cothe_lung, R.drawable.cothe_nguc,
            R.drawable.cothe_ngongiua,R.drawable.cothe_ngontay,R.drawable.cothe_rang,R.drawable.cothe_tran,
            R.drawable.cothe_ron,R.drawable.cothe_ngoncai,R.drawable.cothe_moi,R.drawable.cothe_mat,
            R.drawable.cothe_mui,R.drawable.cothe_maucam,R.drawable.cothe_longmi,R.drawable.cothe_ngonchan,
            R.drawable.cothe_tai,R.drawable.cothe_nach,R.drawable.cothe_longmay,R.drawable.cothe_ngonaput,
            R.drawable.cothe_ngonut,R.drawable.cothe_vai,R.drawable.cothe_toc,R.drawable.cothe_mieng,
            R.drawable.cothe_daumat,R.drawable.cothe_ngontro,R.drawable.cothe_ngonchancai,R.drawable.cothe_ma, R.drawable.cothe_tay};
    private String[] cotheItemsName = { "cothe_bung", "cothe_cam", "cothe_chan", "cothe_co", "cothe_dau", "cothe_daugoi", "cothe_lung", "cothe_nguc",
            "cothe_ngongiua","cothe_ngontay","cothe_rang","cothe_tran","cothe_ron","cothe_ngoncai","cothe_moi",
            "cothe_mat","cothe_mui","cothe_maucam","cothe_longmi","cothe_ngonchan","cothe_tai","cothe_nach",
            "cothe_longmay","cothe_ngonaput","cothe_ngonut","cothe_vai","cothe_toc","cothe_mieng","cothe_daumat",
            "cothe_ngontro","cothe_ngonchancai","cothe_ma","cothe_tay"};
    private int[] cotheAudio = { R.raw.cothe_bung, R.raw.cothe_cam, R.raw.cothe_chan, R.raw.cothe_co, R.raw.cothe_dau, R.raw.cothe_daugoi, R.raw.cothe_lung, R.raw.cothe_nguc,
            R.raw.cothe_ngongiua,R.raw.cothe_ngontay,R.raw.cothe_rang,R.raw.cothe_tran,R.raw.cothe_ron,
            R.raw.cothe_ngoncai,R.raw.cothe_moi,R.raw.cothe_mat,R.raw.cothe_mui,R.raw.cothe_maucam,R.raw.cothe_longmi,
            R.raw.cothe_ngonchan,R.raw.cothe_tai,R.raw.cothe_nach,R.raw.cothe_longmay,R.raw.cothe_ngonaput,
            R.raw.cothe_ngonut,R.raw.cothe_vai,R.raw.cothe_toc,R.raw.cothe_mieng,R.raw.cothe_daumat,
            R.raw.cothe_ngontro,R.raw.cothe_ngonchancai,R.raw.cothe_ma,R.raw.cothe_tay};

    private int[] trongnhaIds = { R.drawable.trongnha_tivi, R.drawable.trongnha_ban, R.drawable.trongnha_giuong, R.drawable.trongnha_maylanh, R.drawable.trongnha_tu, R.drawable.trongnha_goi, R.drawable.trongnha_noi, R.drawable.trongnha_dongho};
    private String[] trongnhaItemsName= { "trongnha_tivi", "trongnha_ban", "trongnha_giuong", "trongnha_maylanh", "trongnha_tu", "trongnha_goi", "trongnha_noi", "trongnha_dongho"};
    private int[] trongnhaAudio = { R.raw.trongnha_tivi, R.raw.trongnha_ban, R.raw.trongnha_giuong, R.raw.trongnha_maylanh, R.raw.trongnha_tu, R.raw.trongnha_goi, R.raw.trongnha_noi, R.raw.trongnha_dongho};

    private int[] dongvatIds = {R.drawable.dongvat_ngua,R.drawable.dongvat_ca,R.drawable.dongvat_cavoi,R.drawable.dongvat_tho,
            R.drawable.dongvat_cacke,R.drawable.dongvat_ga,R.drawable.dongvat_chim,R.drawable.dongvat_rong,
            R.drawable.dongvat_cong,R.drawable.dongvat_rua,R.drawable.dongvat_bo,R.drawable.dongvat_gian,
            R.drawable.dongvat_ruoi,R.drawable.dongvat_khunglong,R.drawable.dongvat_nai,R.drawable.dongvat_kien,
            R.drawable.dongvat_meo,R.drawable.dongvat_gau,R.drawable.dongvat_cua,R.drawable.dongvat_khi,
            R.drawable.dongvat_tom,R.drawable.dongvat_ech,R.drawable.dongvat_caocao,R.drawable.dongvat_sutu,
            R.drawable.dongvat_thanlan,R.drawable.dongvat_buom,R.drawable.dongvat_tomhum,R.drawable.dongvat_casau,
            R.drawable.dongvat_vit,R.drawable.dongvat_camap,R.drawable.dongvat_voi,R.drawable.dongvat_gamai};
    private String[] dongvatItemsName = {"dongvat_ngua","dongvat_ca","dongvat_cavoi","dongvat_tho","dongvat_cacke",
            "dongvat_ga","dongvat_chim","dongvat_rong","dongvat_cong","dongvat_rua","dongvat_bo","dongvat_gian",
            "dongvat_ruoi","dongvat_khunglong","dongvat_nai","dongvat_kien","dongvat_meo","dongvat_gau","dongvat_cua",
            "dongvat_khi","dongvat_tom","dongvat_ech","dongvat_caocao","dongvat_sutu","dongvat_thanlan","dongvat_buom",
            "dongvat_tomhum","dongvat_casau","dongvat_vit","dongvat_camap","dongvat_voi","dongvat_gamai"};
    private int[] dongvatAudio = {R.raw.dongvat_ngua,R.raw.dongvat_ca,R.raw.dongvat_cavoi,R.raw.dongvat_tho,
            R.raw.dongvat_cacke,R.raw.dongvat_ga,R.raw.dongvat_chim,R.raw.dongvat_rong,R.raw.dongvat_cong,
            R.raw.dongvat_rua,R.raw.dongvat_bo,R.raw.dongvat_gian,R.raw.dongvat_ruoi,R.raw.dongvat_khunglong,
            R.raw.dongvat_nai,R.raw.dongvat_kien,R.raw.dongvat_meo,R.raw.dongvat_gau,R.raw.dongvat_cua,
            R.raw.dongvat_khi,R.raw.dongvat_tom,R.raw.dongvat_ech,R.raw.dongvat_caocao,R.raw.dongvat_sutu,
            R.raw.dongvat_thanlan,R.raw.dongvat_buom,R.raw.dongvat_tomhum,R.raw.dongvat_casau,R.raw.dongvat_vit,
            R.raw.dongvat_camap,R.raw.dongvat_voi,R.raw.dongvat_gamai};

    private int[] hoaquaIds = {R.drawable.hoaqua_chomchom,R.drawable.hoaqua_buoi,R.drawable.hoaqua_bongcaixanh,
            R.drawable.hoaqua_cudau,R.drawable.hoaqua_oi,R.drawable.hoaqua_carot,R.drawable.hoaqua_xoai,
            R.drawable.hoaqua_chanhday,R.drawable.hoaqua_chanh,R.drawable.hoaqua_dudu,R.drawable.hoaqua_chuoi,R.drawable.hoaqua_caphao};
    private String[] hoaquaItemsName = {"hoaqua_chomchom","hoaqua_buoi","hoaqua_bongcaixanh","hoaqua_cudau","hoaqua_oi",
            "hoaqua_carot","hoaqua_xoai","hoaqua_chanhday","hoaqua_chanh","hoaqua_dudu","hoaqua_chuoi","hoaqua_caphao"};
    private int[] hoaquaAudio = {R.raw.hoaqua_chomchom,R.raw.hoaqua_buoi,R.raw.hoaqua_bongcaixanh,R.raw.hoaqua_cudau,
            R.raw.hoaqua_oi,R.raw.hoaqua_carot,R.raw.hoaqua_xoai,R.raw.hoaqua_chanhday,R.raw.hoaqua_chanh,
            R.raw.hoaqua_dudu,R.raw.hoaqua_chuoi,R.raw.hoaqua_caphao};

    private int[] lophocIds = {R.drawable.lophoc_giongu,R.drawable.lophoc_catgiay,R.drawable.lophoc_xephangngang,R.drawable.lophoc_taptheduc,R.drawable.lophoc_demtien,R.drawable.lophoc_nandatset,R.drawable.lophoc_choicat,R.drawable.lophoc_cap,R.drawable.lophoc_nhac,R.drawable.lophoc_hopan,R.drawable.lophoc_gioimlang,R.drawable.lophoc_ngoixuongomdau,R.drawable.lophoc_giay,R.drawable.lophoc_truong,R.drawable.lophoc_bang,R.drawable.lophoc_butsap,R.drawable.lophoc_kesach,R.drawable.lophoc_den,R.drawable.lophoc_gamban,R.drawable.lophoc_ngoitheohang,R.drawable.lophoc_quatban,R.drawable.lophoc_phan,R.drawable.lophoc_ghe,R.drawable.lophoc_doncautieu,R.drawable.lophoc_thetapdoc,R.drawable.lophoc_vitinh,R.drawable.lophoc_daytheduc,R.drawable.lophoc_choi,R.drawable.lophoc_bangchucai,R.drawable.lophoc_ngankeo,R.drawable.lophoc_giochoi,R.drawable.lophoc_hochat,R.drawable.lophoc_docsach,R.drawable.lophoc_viet,R.drawable.lophoc_duxangang,R.drawable.lophoc_hangdoc,R.drawable.lophoc_sach,R.drawable.lophoc_dorac,R.drawable.lophoc_tap,R.drawable.lophoc_tapdoc,R.drawable.lophoc_duday,R.drawable.lophoc_banchai,R.drawable.lophoc_thuoc,R.drawable.lophoc_tapviet,R.drawable.lophoc_chuiong,R.drawable.lophoc_cautuot,R.drawable.lophoc_lamtoan,R.drawable.lophoc_xepvongtron,R.drawable.lophoc_thoikhoabieu,R.drawable.lophoc_nghenhac};
    private String[] lophocItemsName = {"lophoc_giongu","lophoc_catgiay","lophoc_xephangngang","lophoc_taptheduc","lophoc_demtien","lophoc_nandatset","lophoc_choicat","lophoc_cap","lophoc_nhac","lophoc_hopan","lophoc_gioimlang","lophoc_ngoixuongomdau","lophoc_giay","lophoc_truong","lophoc_bang","lophoc_butsap","lophoc_kesach","lophoc_den","lophoc_gamban","lophoc_ngoitheohang","lophoc_quatban","lophoc_phan","lophoc_ghe","lophoc_doncautieu","lophoc_thetapdoc","lophoc_vitinh","lophoc_daytheduc","lophoc_choi","lophoc_bangchucai","lophoc_ngankeo","lophoc_giochoi","lophoc_hochat","lophoc_docsach","lophoc_viet","lophoc_duxangang","lophoc_hangdoc","lophoc_sach","lophoc_dorac","lophoc_tap","lophoc_tapdoc","lophoc_duday","lophoc_banchai","lophoc_thuoc","lophoc_tapviet","lophoc_chuiong","lophoc_cautuot","lophoc_lamtoan","lophoc_xepvongtron","lophoc_thoikhoabieu","lophoc_nghenhac"};
    private int[] lophocAudio = {R.raw.lophoc_giongu,R.raw.lophoc_catgiay,R.raw.lophoc_xephangngang,R.raw.lophoc_taptheduc,R.raw.lophoc_demtien,R.raw.lophoc_nandatset,R.raw.lophoc_choicat,R.raw.lophoc_cap,R.raw.lophoc_nhac,R.raw.lophoc_hopan,R.raw.lophoc_gioimlang,R.raw.lophoc_ngoixuongomdau,R.raw.lophoc_giay,R.raw.lophoc_truong,R.raw.lophoc_bang,R.raw.lophoc_butsap,R.raw.lophoc_kesach,R.raw.lophoc_den,R.raw.lophoc_gamban,R.raw.lophoc_ngoitheohang,R.raw.lophoc_quatban,R.raw.lophoc_phan,R.raw.lophoc_ghe,R.raw.lophoc_doncautieu,R.raw.lophoc_thetapdoc,R.raw.lophoc_vitinh,R.raw.lophoc_daytheduc,R.raw.lophoc_choi,R.raw.lophoc_bangchucai,R.raw.lophoc_ngankeo,R.raw.lophoc_giochoi,R.raw.lophoc_hochat,R.raw.lophoc_docsach,R.raw.lophoc_viet,R.raw.lophoc_duxangang,R.raw.lophoc_hangdoc,R.raw.lophoc_sach,R.raw.lophoc_dorac,R.raw.lophoc_tap,R.raw.lophoc_tapdoc,R.raw.lophoc_duday,R.raw.lophoc_banchai,R.raw.lophoc_thuoc,R.raw.lophoc_tapviet,R.raw.lophoc_chuiong,R.raw.lophoc_cautuot,R.raw.lophoc_lamtoan,R.raw.lophoc_xepvongtron,R.raw.lophoc_thoikhoabieu,R.raw.lophoc_nghenhac};

    private int[] mausacIds = {R.drawable.mausac_cauvong,R.drawable.mausac_den,R.drawable.mausac_tim,R.drawable.mausac_nau,R.drawable.mausac_hong,R.drawable.mausac_xanhlacay,R.drawable.mausac_do,R.drawable.mausac_vang,R.drawable.mausac_xanhduong,R.drawable.mausac_trang,R.drawable.mausac_xam};
    private String[] mausacItemsName = {"mausac_cauvong","mausac_den","mausac_tim","mausac_nau","mausac_hong","mausac_xanhlacay","mausac_do","mausac_vang","mausac_xanhduong","mausac_trang","mausac_xam"};
    private int[] mausacAudio = {R.raw.mausac_cauvong,R.raw.mausac_den,R.raw.mausac_tim,R.raw.mausac_nau,R.raw.mausac_hong,R.raw.mausac_xanhlacay,R.raw.mausac_do,R.raw.mausac_vang,R.raw.mausac_xanhduong,R.raw.mausac_trang,R.raw.mausac_xam};

    private int[] ngoaitroiIds = {R.drawable.ngoaitroi_chobenthanh,R.drawable.ngoaitroi_dalat,R.drawable.ngoaitroi_nui,R.drawable.ngoaitroi_congvien,R.drawable.ngoaitroi_benxe,R.drawable.ngoaitroi_phat,R.drawable.ngoaitroi_hotay,R.drawable.ngoaitroi_hoboi,R.drawable.ngoaitroi_langbac,R.drawable.ngoaitroi_quancafe,R.drawable.ngoaitroi_cho,R.drawable.ngoaitroi_congviennuoc,R.drawable.ngoaitroi_bungbinh,R.drawable.ngoaitroi_nhatho,R.drawable.ngoaitroi_tet,R.drawable.ngoaitroi_damsen,R.drawable.ngoaitroi_sapa,R.drawable.ngoaitroi_sothu,R.drawable.ngoaitroi_giangsinh,R.drawable.ngoaitroi_buudiensaigon,R.drawable.ngoaitroi_buudienhanoi,R.drawable.ngoaitroi_bien,R.drawable.ngoaitroi_hoguom,R.drawable.ngoaitroi_nhathoducba,R.drawable.ngoaitroi_trungthu,R.drawable.ngoaitroi_khuvuichoi,R.drawable.ngoaitroi_xedap,R.drawable.ngoaitroi_sanbay,R.drawable.ngoaitroi_ducme,R.drawable.ngoaitroi_dibien,R.drawable.ngoaitroi_halong};
    private String[] ngoaitroiItemsName = {"ngoaitroi_chobenthanh","ngoaitroi_dalat","ngoaitroi_nui","ngoaitroi_congvien","ngoaitroi_benxe","ngoaitroi_phat","ngoaitroi_hotay","ngoaitroi_hoboi","ngoaitroi_langbac","ngoaitroi_quancafe","ngoaitroi_cho","ngoaitroi_congviennuoc","ngoaitroi_bungbinh","ngoaitroi_nhatho","ngoaitroi_tet","ngoaitroi_damsen","ngoaitroi_sapa","ngoaitroi_sothu","ngoaitroi_giangsinh","ngoaitroi_buudiensaigon","ngoaitroi_buudienhanoi","ngoaitroi_bien","ngoaitroi_hoguom","ngoaitroi_nhathoducba","ngoaitroi_trungthu","ngoaitroi_khuvuichoi","ngoaitroi_xedap","ngoaitroi_sanbay","ngoaitroi_ducme","ngoaitroi_dibien","ngoaitroi_halong"};
    private int[] ngoaitroiAudio = {R.raw.ngoaitroi_chobenthanh,R.raw.ngoaitroi_dalat,R.raw.ngoaitroi_nui,R.raw.ngoaitroi_congvien,R.raw.ngoaitroi_benxe,R.raw.ngoaitroi_phat,R.raw.ngoaitroi_hotay,R.raw.ngoaitroi_hoboi,R.raw.ngoaitroi_langbac,R.raw.ngoaitroi_quancafe,R.raw.ngoaitroi_cho,R.raw.ngoaitroi_congviennuoc,R.raw.ngoaitroi_bungbinh,R.raw.ngoaitroi_nhatho,R.raw.ngoaitroi_tet,R.raw.ngoaitroi_damsen,R.raw.ngoaitroi_sapa,R.raw.ngoaitroi_sothu,R.raw.ngoaitroi_giangsinh,R.raw.ngoaitroi_buudiensaigon,R.raw.ngoaitroi_buudienhanoi,R.raw.ngoaitroi_bien,R.raw.ngoaitroi_hoguom,R.raw.ngoaitroi_nhathoducba,R.raw.ngoaitroi_trungthu,R.raw.ngoaitroi_khuvuichoi,R.raw.ngoaitroi_xedap,R.raw.ngoaitroi_sanbay,R.raw.ngoaitroi_ducme,R.raw.ngoaitroi_dibien,R.raw.ngoaitroi_halong};

    private int[] quanaoIds = {R.drawable.quanao_dep,R.drawable.quanao_goi,R.drawable.quanao_non,R.drawable.quanao_aothun,R.drawable.quanao_khan,R.drawable.quanao_quan,R.drawable.quanao_mumem,R.drawable.quanao_mubaohiem,R.drawable.quanao_nem,R.drawable.quanao_quanjean,R.drawable.quanao_coao,R.drawable.quanao_daynit,R.drawable.quanao_men,R.drawable.quanao_tuiao,R.drawable.quanao_giay,R.drawable.quanao_somi,R.drawable.quanao_daykeo,R.drawable.quanao_du,R.drawable.quanao_ao,R.drawable.quanao_vo,R.drawable.quanao_khuy};
    private String[] quanaoItemsName = {"quanao_dep","quanao_goi","quanao_non","quanao_aothun","quanao_khan","quanao_quan","quanao_mumem","quanao_mubaohiem","quanao_nem","quanao_quanjean","quanao_coao","quanao_daynit","quanao_men","quanao_tuiao","quanao_giay","quanao_somi","quanao_daykeo","quanao_du","quanao_ao","quanao_vo","quanao_khuy"};
    private int[] quanaoAudio = {R.raw.quanao_dep,R.raw.quanao_goi,R.raw.quanao_non,R.raw.quanao_aothun,R.raw.quanao_khan,R.raw.quanao_quan,R.raw.quanao_mumem,R.raw.quanao_mubaohiem,R.raw.quanao_nem,R.raw.quanao_quanjean,R.raw.quanao_coao,R.raw.quanao_daynit,R.raw.quanao_men,R.raw.quanao_tuiao,R.raw.quanao_giay,R.raw.quanao_somi,R.raw.quanao_daykeo,R.raw.quanao_du,R.raw.quanao_ao,R.raw.quanao_vo,R.raw.quanao_khuy};

    private int[] giaothongIds = {R.drawable.giaothong_banhxe,R.drawable.giaothong_denxanh,R.drawable.giaothong_xichlo,R.drawable.giaothong_dendo,R.drawable.giaothong_denvang,R.drawable.giaothong_xemay,R.drawable.giaothong_maybay,R.drawable.giaothong_oto,R.drawable.giaothong_xebuyt,R.drawable.giaothong_moto};
    private String[] giaothongItemsName = {"giaothong_banhxe","giaothong_denxanh","giaothong_xichlo","giaothong_dendo","giaothong_denvang","giaothong_xemay","giaothong_maybay","giaothong_oto","giaothong_xebuyt","giaothong_moto"};
    private int[] giaothongAudio = {R.raw.giaothong_banhxe,R.raw.giaothong_denxanh,R.raw.giaothong_xichlo,R.raw.giaothong_dendo,R.raw.giaothong_denvang,R.raw.giaothong_xemay,R.raw.giaothong_maybay,R.raw.giaothong_oto,R.raw.giaothong_xebuyt,R.raw.giaothong_moto};

    private List<EmotionItem> sentence = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionGroupListView = (ListView)findViewById(R.id.listView);
        emotionItemGridView = (GridView)findViewById(R.id.gridview_emotions);
        btnAbout = (Button) findViewById(R.id.btn_about);
        btnOpenTrainerMode = (Button) findViewById(R.id.btn_open_trainer_mode);

        sentenceAdapter = new SentenceAdapter(this, new ArrayList<EmotionItem>(), sentencePlayer);

        emotionGroupAdapter = new EmotionGroupAdapter(this, createEmotionGroups());

        emotionGroupListView.setDivider(null);
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
                mediaPlayer = MediaPlayer.create(view.getContext(),
                        ((EmotionItem)emotionItemAdapter.getItem(position)).getAudioId());
                mediaPlayer.start();

                sentence.add((EmotionItem) emotionItemAdapter.getItem(position));
                sentenceAdapter.setmData(sentence);
                sentenceAdapter.notifyDataSetChanged();

            }
        });

        btnOpenTrainerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrainerActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);

                alertDialogBuilder.setTitle("Về ứng dụng nguồn mở \"I'm\"");

                alertDialogBuilder
                        .setMessage("Phiên bản 1.0\n" +
                                "Ngày phát triển: 5/6/2016\n" +
                                "Được phát triển bởi: Nhóm 1 - Tikkun Olam Makers Vietnam 2016\n" +
                                "Nhằm mục đích hỗ trợ trẻ tự kỷ Việt Nam trong việc giao tiếp. \n" +
                                "Nguồn hình ảnh: Thư viện ảnh PAXT tại website: concuame.com và hình ảnh miễn phí tại www.iconfinder.com\n" +
                                "Email góp ý: ducduytruong2012@gmail.com")
                        .setCancelable(false)
                        .setPositiveButton("Đóng",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();

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
        groups.add(createEmotionGroupContainsItems(R.drawable.cothe, createEmotionItems(cotheItemsName.length,
                cotheItemsName, cotheIds, cotheAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.trongnha, createEmotionItems(trongnhaItemsName.length,
                trongnhaItemsName, trongnhaIds, trongnhaAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.dongvat, createEmotionItems(dongvatItemsName.length,
                dongvatItemsName, dongvatIds, dongvatAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.hoaqua, createEmotionItems(hoaquaItemsName.length,
                hoaquaItemsName, hoaquaIds, hoaquaAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.lophoc, createEmotionItems(lophocItemsName.length,
                lophocItemsName, lophocIds, lophocAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.mausac, createEmotionItems(mausacItemsName.length,
                mausacItemsName, mausacIds, mausacAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.ngoaitroi, createEmotionItems(ngoaitroiItemsName.length,
                ngoaitroiItemsName, ngoaitroiIds, ngoaitroiAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.quanao, createEmotionItems(quanaoItemsName.length,
                quanaoItemsName, quanaoIds, quanaoAudio)));
        groups.add(createEmotionGroupContainsItems(R.drawable.giaothong, createEmotionItems(giaothongItemsName.length,
                giaothongItemsName, giaothongIds, giaothongAudio)));

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
