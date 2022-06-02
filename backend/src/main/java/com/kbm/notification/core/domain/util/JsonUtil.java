package com.kbm.notification.core.domain.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonUtil {

    private static Gson gson;

    static {
         gson = new GsonBuilder().setPrettyPrinting().create();

    };

    public static String toJson(Object object){
        return gson.toJson(object);
    }

    public static <T> T fromJson(String object, Class<T> objectClass){
        return gson.fromJson(object, objectClass);
    }
}
