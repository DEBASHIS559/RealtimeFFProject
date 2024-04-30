package com.ff.RealProjectDemoFF.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CommonUtil {
    public static String getCurrentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = formatter.format(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
        return currentTime;
    }
}
