package com.ruoyi.system.utils;

import java.io.InputStream;

public  class DowlonFileUtils {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }
}