package com.vnpt.hddtcustomer.models.menu;


import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataLeftMenu {
    public static LinkedHashMap<String, List<String>> getData(){
        List<String> listDataHeader = new ArrayList<String>();
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();


        listDataHeader.add("Trang chủ");
        listDataHeader.add("Hóa đơn");
        listDataHeader.add("Tra cứu");
        listDataHeader.add("Liên hệ");
        listDataHeader.add("Cấu hình");
        listDataHeader.add("Hướng dẫn sử dụng");
        listDataHeader.add("Đăng xuất");

        List<String> bill = new ArrayList<String>();
        bill.add("Hóa đơn tiền điện");
        bill.add("Hóa đơn tiền nước");
        bill.add("Hóa đơn viễn thông");

        expandableListDetail.put(listDataHeader.get(0), null);
        expandableListDetail.put(listDataHeader.get(1), bill);
        expandableListDetail.put(listDataHeader.get(2), null);
        expandableListDetail.put(listDataHeader.get(3), null);
        expandableListDetail.put(listDataHeader.get(4), null);
        expandableListDetail.put(listDataHeader.get(5), null);
        expandableListDetail.put(listDataHeader.get(6), null);


        return expandableListDetail;
    }
}
