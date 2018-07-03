package com.yun.ma.yi.app.module.report;

import com.yun.ma.yi.app.utils.StringUtils;
import com.yunmayi.app.manage.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.yun.ma.yi.app.utils.CommonUtil.getString;

/**
 * Created by ys on 2017/6/10.
 */

public class ReportSearchCheckText {
    /**
     * 检查输入
     * @return
     */
    public static String check(String startTime, String endTime) {
        String error = null;
        if (StringUtils.isEmpty(startTime)) {
            error = getString(R.string.select_start_time);
        }else if (StringUtils.isEmpty(endTime)) {
            error = getString(R.string.select_end_time);
        }else if (Long.valueOf(startTime.replaceAll("-", "")) > Long.valueOf(endTime.replaceAll("-", ""))) {
            error = getString(R.string.start_below_end);
        }else{
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(format.parse(endTime));
                c.add(Calendar.MONTH, -1);
                Date m = c.getTime();
                String mon = format.format(m);
                if (Long.valueOf(mon.replaceAll("-", "")) > Long.valueOf(startTime.replaceAll("-", ""))){
                    error = getString(R.string.select_time_section);
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return  error;
    }

}
