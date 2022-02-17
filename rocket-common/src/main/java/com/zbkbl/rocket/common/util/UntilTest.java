package com.zbkbl.rocket.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title: UntilTest
 * @Description:
 * @author: LiuYang
 * @date: 2021/12/13 4:41 下午
 */
@Slf4j
public class UntilTest {
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        Date date = null;
        try{
            dt = df.parse("2021-03-04");
            date = df.parse("2021-12-31");
        }catch (Exception e){
            log.error("parse has error!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);
//        calendar.add(Calendar.DATE, -1559);
        log.info(df.format(calendar.getTime()));
        long day = (calendar.getTimeInMillis() - cl.getTimeInMillis())/(1000L * 24 * 3600);
        log.info("day : {}", day);
    }
}