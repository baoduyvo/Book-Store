package org.voduybao.tools.utils;


public class FileUtil {
    public static String createFileName(String fileName, String extension) {
        return fileName + "." + extension;
    }

    public static String getExtensionName(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    public static String generateCurrentFileName() {
        String millisPart = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        return Utils.generateRandomNumber() + millisPart;
    }
}
