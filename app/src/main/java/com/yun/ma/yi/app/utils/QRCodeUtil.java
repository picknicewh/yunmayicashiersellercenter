package com.yun.ma.yi.app.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * 作者： wh
 * 时间：  2017/11/28
 * 名称：二维码生成工具类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class QRCodeUtil {

    /**
     * 二维码生成器
     * @param  contents 二维码的内容
     *@param  width 宽度
     * @param  height 高度
     */
    public static Bitmap getEncodeBitmap(Bitmap log,String contents,int width,int height) {
        Bitmap bitmap = null;
        MultiFormatWriter formatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = formatWriter.encode(contents, BarcodeFormat.QR_CODE, width, height);
            bitmap = bitMatrix2Bitmap(bitMatrix);
            bitmap = addLogo(bitmap,log);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public static Bitmap getEncodeBitmap(Bitmap log,String contents) {
        Bitmap bitmap = getEncodeBitmap(log,contents,800,800);
        return bitmap;
    }
    /**
     * 将  BitMatrix转换成Bitmap
     *
     * @param matrix
     */
    private static Bitmap bitMatrix2Bitmap(BitMatrix matrix) {
        matrix = updateBit(matrix, 0);
        int w = matrix.getWidth();
        int h = matrix.getHeight();
        int[] rawData = new int[w * h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int color = Color.WHITE;
                if (matrix.get(i, j)) {
                    // 有内容的部分，颜色设置为黑色，当然这里可以自己修改成喜欢的颜色
                    color = Color.BLACK;
                }
                rawData[i + (j * w)] = color;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        bitmap.setPixels(rawData, 0, w, 0, 0, w, h);
        return bitmap;
    }

    /**
     * 白边边框
     *
     * @param margin 边框大小
     * @param matrix 收缩比例
     */
    private static BitMatrix updateBit(BitMatrix matrix, int margin) {
        int tempM = margin * 2;
        int[] rec = matrix.getEnclosingRectangle(); // 获取二维码图案的属性
        int resWidth = rec[2] + tempM;
        int resHeight = rec[3] + tempM;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
        resMatrix.clear();
        for (int i = margin; i < resWidth - margin; i++) { // 循环，将二维码图案绘制到新的bitMatrix中
            for (int j = margin; j < resHeight - margin; j++) {
                if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }
    /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {

        if (src == null) {
            return null;
        }
        if (logo == null) {
            return src;
        }
        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }
        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }
        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }
}
