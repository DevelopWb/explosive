package com.juntai.wisdom.explorsive.utils;


import com.juntai.wisdom.explorsive.AppHttpPath;

/**
 * author:wong
 * Date: 2019/3/27
 * Description:
 */
public class UrlFormatUtil {


    public static String getImageOriginalUrl(String path) {
        return String.format("%s%s", AppHttpPath.BASE_IMAGE, path);
    }

    /**
     * 缩略图路径
     *
     * @param path
     * @return
     */
    public static String getImageThumUrl(String path) {
        return String.format("%s%s", AppHttpPath.BASE_IMAGE, path);
    }




}
