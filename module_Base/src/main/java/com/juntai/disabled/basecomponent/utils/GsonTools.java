package com.juntai.disabled.basecomponent.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @aouther Ma
 * @date 2019/3/23
 */
public class GsonTools {

    public GsonTools() {

    }

    public static String createGsonString(Object object) {
        Gson gson = new Gson();
        String gsonString = gson.toJson(object);
        return gsonString;
    }

    public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
        try {
            Gson gson = new Gson();
            T t = gson.fromJson(gsonString, cls);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    public static <T> List<Map<String, T>> changeGsonToListMaps(
            String gsonString) {
        List<Map<String, T>> list = null;
        Gson gson = new Gson();
        list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
        Map<String, T> map = null;
        Gson gson = new Gson();
        map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }

    /**
     * 将ModelA的属性值赋值给modleB
     *
     * @param modelA
     * @param bClass
     * @param <T>
     * @return
     */
    public static <T> T modelA2B(Object modelA, Class<T> bClass) {
        try {
            Gson gson = new Gson();
            String gsonA = gson.toJson(modelA);
            T instanceB = gson.fromJson(gsonA, bClass);
            return instanceB;
        } catch (Exception e) {
            return null;
        }
    }

    //通过Gson将Bean转化为Json字符串形式
    public static RequestBody getJsonRequest(Object object) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), createGsonString(object));
    }
}