package com.yun.ma.yi.app.utils;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串处理工具.
 * @create 2013-7-22 上午10:32:12
 */
public class StringUtils {
	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	/**
	 * 将字符串转位日期类型
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 判断字符串是否为空.
	 * 
	 * @param str
	 *            字符串.
	 * @return 是否为空.
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	
	/**
	 * 把空数据转成null.
	 * 
	 * @param source
	 *            原始值.
	 * @return 转换后的值.
	 */
	public static String emptyToNull(String source) {
		return isEmpty(source) ? null : source;
	}
	
	/**
	 * 把null转换称空串.
	 * 
	 * @param source
	 *            原始串.
	 * @return
	 */
	public static String nullToEmpty(String source) {
		return isEmpty(source) ? "" : source;
	}
	
	/**
	 * 字符串是否相等.
	 * 
	 * @param source
	 *            原始.
	 * @param target
	 *            目标.
	 * @return 是否相等.
	 */
	public static boolean isEquals(String source, String target) {
		if (isEmpty(source)) {
			return isEmpty(target);
		} else {
			return source.equals(target);
		}
	}
	/**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

	/**
	 * 比较版本号
	 * @param version1
	 * @param version2
	 * @return 0 版本不变 1 低于当前版本 -1 高于当前版本
	 */
	public static int compareVersion(String version1, String version2) {
		if (version1.equals(version2)) {
			return 0;
		}
		String[] version1Array = version1.split("\\.");
		String[] version2Array = version2.split("\\.");

		int index = 0;
		int minLen = Math.min(version1Array.length, version2Array.length);
		int diff = 0;

		while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
			index ++;
		}

		if (diff == 0) {
			for (int i = index; i < version1Array.length; i ++) {
				if (Integer.parseInt(version1Array[i]) > 0) {
					return 1;
				}
			}

			for (int i = index; i < version2Array.length; i ++) {
				if (Integer.parseInt(version2Array[i]) > 0) {
					return -1;
				}
			}

			return 0;
		} else {
			return diff > 0 ? 1 : -1;
		}
	}
//	/**
//	 * 取得简拼
//	 * 
//	 * @param name
//	 */
//	public static final String getSpell(String name) {
//		if (!isEmpty(name)) {
//			String pyStr = PYUtil.getPYString(name);
//			if (!isEmpty(pyStr)) {
//				return pyStr.toUpperCase();
//			}
//		}
//		return null;
//	}
}
