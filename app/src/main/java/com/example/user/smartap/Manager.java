package com.example.user.smartap;

import java.util.ArrayList;

/**
 * Created by user on 2017-10-07.
 */

// ReserveActivity의 예약 아이템들 관리
class Manager {
    private static final Manager ourInstance = new Manager();

    static Manager getInstance() {
        return ourInstance;
    }

   // public static ArrayList<Reserve_Item> reserve_items = new ArrayList<>();
    public static ArrayList<InfoClass> info_list = new ArrayList<>();

}
