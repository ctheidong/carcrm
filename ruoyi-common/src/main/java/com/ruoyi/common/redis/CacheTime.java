package com.ruoyi.common.redis;

/**
 * @author hlf
 * 2020/5/18 15:43
 * 文件说明：
 */
public class CacheTime {

    /**
     * 缓存时效 5秒钟
     */
    public static int CACHE_EXP_FIVE_SECONDS = 5;

    /**
     * 缓存时效 1分钟
     */
    public static int CACHE_EXP_MINUTE = 60;

    /**
     * 缓存时效 5分钟
     */
    public static int CACHE_EXP_FIVE_MINUTES = 60 * 5;

    /**
     * 缓存时效 10分钟
     */
    public static int CACHE_EXP_TEN_MINUTES = 60 * 10;

    /**
     * 缓存时效 15分钟
     */
    public static int CACHE_EXP_QUARTER_MINUTES = 60 * 15;

    /**
     * 缓存时效 60分钟
     */
    public static int CACHE_EXP_HOUR = 60 * 60;

    /**
     * 缓存时效 2小时
     */
    public static int CACHE_EXP_TWO_HOUR = 60 * 60 * 2;

    /**
     * 缓存时效 12小时
     */
    public static int CACHE_EXP_HALF_DAY = 12 * 60 * 60;

    /**
     * 缓存时效 1天
     */
    public static int CACHE_EXP_DAY = 3600 * 24;

    /**
     * 缓存时效 1周
     */
    public static int CACHE_EXP_WEEK = 3600 * 24 * 7;

    /**
     * 缓存时效 1月
     */
    public static int CACHE_EXP_MONTH = 3600 * 24 * 30 * 7;

    /**
     * 缓存时效 永久
     */
    public static int CACHE_EXP_FOREVER = 0;

    /**
     * token过期时间 2小时
     */
    public static long TIME_OUT = 3600 * 1000 * 2;
//    public static long TIME_OUT = 60 * 1000 ;
}
