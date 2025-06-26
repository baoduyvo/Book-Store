package org.voduybao.tools.contants.c;

import java.util.regex.Pattern;

public interface Constant {
    String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    Integer ACTIVE = 1;
    Integer DISABLE = 0;
    Integer NO_DELETED = 0;

    Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
    Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
    Pattern CHAR_SPECIAL = Pattern.compile("^[a-zA-Z0-9\\s]+$");

    int MAX_PAGE_SIZE = 50;

    int THUMB_WIDTH = 500;
    float THUMB_QUALITY = 0.85f;
}
