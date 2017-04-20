package com.collection;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hash转list
 *
 * Created by li.huan
 * Create Date 2017-04-20 11:25
 */
public class MapToList {

    public static void main(String[] args){

        HashMap<String, Object> map = new HashMap<>();
        map.put("1","xx");map.put("2","ddd");
        ArrayList<Object> list = new ArrayList<>(map.values());
        for (Object obj:list){
            System.out.print(obj.toString());
        }
    }

}
