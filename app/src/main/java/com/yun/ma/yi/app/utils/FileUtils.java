package com.yun.ma.yi.app.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class FileUtils {
    /**
     * 随机命名
     */
    private static final String RANDOM_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";
    /**
     * 时间命名
     */
    private static final String TIME_STRING = "yyyyMMddHHmmss";
    /**
     * 限制图片最大宽度进行压缩
     */
    private static final int MAX_WIDTH = 720;
    /**
     * 限制图片最大高度进行压缩
     */
    private static final int MAX_HEIGHT = 1280;
    /**
     * 上传最大图片限制
     */
    private static final int MAX_UPLOAD_PHOTO_SIZE = 300 * 1024;

    private FileUtils() {
    }
    /**
     * SD卡是否存在
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    /**
     * jpg文件名
     *
     * @return
     */
    public static String getUploadPhotoFile(Context context) {
        return FilePath.getImageFileLoadPath(context.getApplicationContext()) + getTimeString() + ".jpg";
    }
    /**
     * 保存拍摄图片
     *
     * @param photoPath
     * @param bitmap
     * @return
     */
    public static boolean savePhoto(String photoPath,Bitmap bitmap) {
        if (bitmap==null){
            bitmap = BitmapFactory.decodeFile(photoPath);
        }
        if (photoPath != null) {
            FileOutputStream fos = null;
            try {
                Bitmap preBitmap = compressBitmap(compressBitmapToBytes(bitmap,MAX_UPLOAD_PHOTO_SIZE), MAX_WIDTH, MAX_HEIGHT);
                Bitmap roBm = rotationBitmap(preBitmap);
                byte[] newDatas = compressBitmapToBytes(roBm, MAX_UPLOAD_PHOTO_SIZE);
                fos = new FileOutputStream(photoPath);
                fos.write(newDatas);
                G.log("compress over ");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                G.log(e);
            } finally {
                closeCloseable(fos);
            }
        }
        return false;
    }
    public static String getChoosePicture(Context context,Uri selectedImage ){
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = context.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        String imagePath = c.getString(columnIndex);
        c.close();
        return imagePath;
    }
    /**
     * @param bitmap   要保存的图片
     * @param filePath 目标路径
     * @return 是否成功
     * @Description 保存图片到指定路径
     */
    public static boolean saveBmpToPath(final Bitmap bitmap, final String filePath) {
        if (bitmap == null || filePath == null) {
            return false;
        }
        boolean result = false; //默认结果
        File file = new File(filePath);
        OutputStream outputStream = null; //文件输出流
        try {
            outputStream = new FileOutputStream(file);
            result = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream); //将图片压缩为JPEG格式写到文件输出流，100是最大的质量程度
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close(); //关闭输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 三星手机适配
     *
     * @param bitmap
     * @return
     */
    private static Bitmap rotationBitmap(Bitmap bitmap) {
        String model = Build.MODEL;
        if (model.startsWith("SM-") || model.startsWith("GT-")) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return resizedBitmap;
        } else {
            return bitmap;
        }
    }

    /**
     * 把字节流按照图片方式大小进行压缩
     * @param datas
     * @param w
     * @param h
     * @return
     */
    public static Bitmap compressBitmap(byte[] datas, int w, int h) {
        if (datas != null) {
           BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds  = true;
            BitmapFactory.decodeByteArray(datas, 0, datas.length, opts);
            if (opts.outWidth != 0 && opts.outHeight != 0) {
                G.log(opts.outWidth + " " + opts.outHeight);
                int scaleX = opts.outWidth / w;
                int scaleY = opts.outHeight / h;
                int scale = 1;
                if (scaleX >= scaleY && scaleX >= 1) {
                    scale = scaleX;
                }
                if (scaleX < scaleY && scaleY >= 1) {
                    scale = scaleY;
                }
                opts.inJustDecodeBounds = false;
                opts.inSampleSize = scale;
                G.log("compressBitmap inSampleSize " + datas.length + " " + scale);
                return BitmapFactory.decodeByteArray(datas, 0, datas.length, opts);
            }
        }
        return null;
    }

    /**
     * 质量压缩图片
     *
     * @param bitmap
     * @param maxSize
     * @return
     */
    public static byte[] compressBitmapToBytes(Bitmap bitmap, int maxSize) {
        if (bitmap == null) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] datas = baos.toByteArray();
        int options = 80;
        int longs = datas.length;
        while (longs > maxSize && options > 0) {
            G.log("compressBitmapToBytes " + longs + "  " + options);
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            datas = baos.toByteArray();
            longs = datas.length;
            options -= 20;
        }
        return datas;
    }

    /**
     * 获取文件路径下所有文件大小, 适当放到子线程中执行
     *
     * @param file
     * @return
     */
    public static long getFileSize(File file) {
        if (file == null || !file.exists()) return 0;
        long totalSize = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                totalSize += getFileSize(f);
            }
            return totalSize;
        } else {
            return file.length();
        }
    }

    /**
     * 删除文件夹下所有文件,适当放到子线程中执行
     *
     * @param file
     */
    public static void delteFiles(File file) {
        if (file == null || !file.exists()) return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (!f.isDirectory()) {
                    f.delete();
                }
            }
        } else {
            file.delete();
        }
    }

    /**
     * 关闭资源
     *
     * @param close
     */
    public static void closeCloseable(Closeable close) {
        if (close != null) {
            try {
                close.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件大小
     *
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) {
        if (fileS <= 0) return "0B";
        if (fileS < 1024) {
            return new DecimalFormat("#.00").format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            return new DecimalFormat("#.00").format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            return new DecimalFormat("#.00").format((double) fileS / 1048576) + "MB";
        } else {
            return new DecimalFormat("#.00").format((double) fileS / 1073741824) + "GB";
        }
    }

    /**
     * sd卡容量
     *
     * @return
     */
    public static long getAvailableStorage() {
        long availableSize = 0;
        if (isSDCardExist()) {
            File sdFile = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(sdFile.getPath());
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2) {
                availableSize = stat.getAvailableBytes();
            } else {
                availableSize = ((long) stat.getAvailableBlocks() * stat.getBlockSize());
            }
        }
        return availableSize;
    }

    /**
     * 获取随机文件名称字符串
     *
     * @param length 生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(RANDOM_STRING.length());
            sb.append(RANDOM_STRING.charAt(number));
        }
        return sb.toString();
    }

    public static String getTimeString() {
        return new SimpleDateFormat(TIME_STRING).format(new Date());
    }

    /**
     * 将字符串写入文件中
     *
     * @param str    需要写入的字符串
     * @param file   写入文件的路径
     * @param append true 追加写入 false 覆盖写入
     * @return
     */
    public static boolean writeFile(String str, File file, boolean append) {
        if (TextUtils.isEmpty(str) || file == null) return false;
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, append);
            fw.write(str);
            fw.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCloseable(fw);
        }
        return false;
    }

    /**
     *  * 读取图片的旋转的角度
     *  * @param path
     *  *  图片绝对路径
     *  * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return degree;
    }

    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     *  * 把Bitmap转Byte      
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    public static void showPicture(String path, ImageView imageView){
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bitmap);
    }
}
