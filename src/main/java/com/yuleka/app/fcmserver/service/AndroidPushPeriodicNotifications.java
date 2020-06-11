package com.yuleka.app.fcmserver.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson() throws JSONException {
        LocalDate localDate = LocalDate.now();

        String sampleData[] = {"dwyHBsPKwnM:APA91bHMKn6V5Lgah4MoXhb8toM6fNjD3UO91zsppa_JqDRxUhgAk80qiNQsP_j_24wzviT3tSI14Y92KLitU1qlzyIO9CzkV24B5oxUQ7IZuMvuaRY_4UaVeydaYSKLPZNiOA7_JECe"
        ,"fkg-HpePMsA:APA91bFYYbCqhzRa9NthUcBe1KoVOyJp6WMGJyzeGXW2N0AKYY8vVZ1aSVaLwhDlu_rfdR4hYY-xDb8B1RlQrZEctUXVsqY_C5wu6nV_1BTp9GA5riJwkmAS2MmLlf4TSI82b0oPL95U"};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title","안녕하세요");
        notification.put("body","Today is "+localDate.getDayOfWeek().name()+"!");
        notification.put("icon", "http://ec2-3-34-10-176.ap-northeast-2.compute.amazonaws.com/favicon.ico");

        body.put("notification", notification);

        System.out.println(body.toString());

        return body.toString();
    }
}
