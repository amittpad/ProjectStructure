package com.india.projectstructure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constant {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String currDate = sdf.format(new Date());

    public static final String CURRENT_DATE = currDate;
    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_PASSWORD = "password";

}
