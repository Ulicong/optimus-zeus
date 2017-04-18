package common.util;

import common.google.gson.Gson;
import common.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * Json工具类
 * Created by li.huan.
 * Created on 2017/4/18
 */
public class JsonUtil {

    private static final GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithModifiers(Modifier.PROTECTED);

    /**
     * 将对象转换成 json 字符串
     */
    public static String toJson(Object input) {
        if (input == null || input.equals("null")) {
            return null;
        }
        Gson gson = gsonBuilder.create();
        return gson.toJson(input);
    }


    /**
     * 将json串转换成 对象
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, classOfT);
    }


    /**
     * 将json字符串转换成指定的ArrayList对象 <code>
     * Type typeOfT = new TypeToken<Collection<Foo>>(){}.getType();
     * </code>
     *
     */
    public static <T> T fromJson(String json, Type typeOf) {
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, typeOf);
    }

}
