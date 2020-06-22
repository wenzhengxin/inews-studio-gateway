package com.trust.inews.studiogate.session;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地全局变量
 */
public class NativeCache {

    public static ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    /**
     * 保存
     */
    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * 获取
     */
    public static Object get(String key) {
        return cache.get(key);
    }

    /**
     * 删除
     */
    public static void remove(String key) {
        cache.remove(key);
    }

}
