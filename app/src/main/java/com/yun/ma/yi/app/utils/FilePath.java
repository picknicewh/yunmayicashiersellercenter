package com.yun.ma.yi.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;

/**
 * Created by ys on 2017/5/25.
 * 文件储存地方
 */

public class FilePath {

    /**
     * 统一路径
     */
    public static String getFilePath() {
        return Environment.getExternalStorageDirectory() + "/YMY/manage/";
    }

    /**
     * 统一apk下载路径
     */
    public static String getApkFileLoadPath(Context context) {
        return createFile(getCachePath(context, "/apk"));
    }

    /**
     * 统一下载图片路径
     */
    public static String getDownLoadImagePath(Context context) {
        return createFile(getDownLadPath(context, "/DCIM"));
    }

    /**
     * 统一下载路径
     */
    public static String getDownLadPath(Context context, String path) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = Environment.getExternalStorageDirectory() + path;
        } else {
            //外部存储不可用
            cachePath = context.getCacheDir().getPath() + path;
        }
        return cachePath;
    }

    /**
     * 统一照相机拍照路径
     */
    public static String getImageFileLoadPath(Context context) {
        return createFile(getCachePath(context, "/image/"));
    }

    /**
     * 统一本地数据库路径
     */
    public static String getDbFileLoadPath() {
        return createFile(getFilePath() + "db/");
    }

    /**
     * 判断文件夹是否存在
     */
    public static String createFile(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * 从URL中得到文件名
     *
     * @param url
     * @return
     */
    public static String convertUrlToFileName(String url) {
        //设置分隔符
        final String[] strs = url.split("/");
        //设置temp
        String fileName = strs[strs.length - 1];
        //得到文件名
        if (fileName.contains("@")) {
            String[] mystr = url.split("@");
            fileName = mystr[0];
        }
        return fileName;
    }

    /**
     * 获取app缓存路径
     *
     * @param context
     * @return
     */
    public static String getCachePath(Context context, String path) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = context.getExternalCacheDir().getPath() + path;
        } else {
            //外部存储不可用
            cachePath = context.getCacheDir().getPath() + path;
        }
        return cachePath;
    }

    /**
     * 更新文件时间
     *
     * @param path
     */
    public void updateFileTime(String path) {
        //获得文件类
        final File file = new File(path);
        //获得系统时间
        final long newModifiedTime = System.currentTimeMillis();
        //设置文件更新时间
        file.setLastModified(newModifiedTime);
    }

    /**
     * 根据文件更新时间排序
     */
    public static class FileLastModifSort implements Comparator<File> {
        //比较两个文件
        public int compare(File arg0, File arg1) {
            //如果第一个新返回1
            if (arg0.lastModified() > arg1.lastModified()) {
                return 1;
                //相等返回0
            } else if (arg0.lastModified() == arg1.lastModified()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * 保存相机照片
     */
    public static String saveImage(Context context, Bitmap bitmap, String picName) {
        picName = picName + ".png";
        String picPath = getDownLoadImagePath(context) + File.separator + picName;
        File file = new File(picPath);
        FileOutputStream fout = null;
        try {
            if (!file.exists() && file.isFile()) file.createNewFile();
            fout = new FileOutputStream(picPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fout.flush();
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return picPath;
    }

    // 删除文件
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
