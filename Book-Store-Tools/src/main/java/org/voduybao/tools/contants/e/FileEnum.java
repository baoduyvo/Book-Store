package org.voduybao.tools.contants.e;

public enum FileEnum {
    IMAGE,
    VIDEO,
    AUDIO,
    UNKNOWN;

    public static FileEnum fromMimeType(String mimeType) {
        if (mimeType == null) return UNKNOWN;
        if (mimeType.startsWith("image/")) return IMAGE;
        if (mimeType.startsWith("video/")) return VIDEO;
        if (mimeType.startsWith("audio/")) return AUDIO;
        return UNKNOWN;
    }
}