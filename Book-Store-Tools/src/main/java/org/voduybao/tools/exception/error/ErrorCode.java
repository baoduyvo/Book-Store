package org.voduybao.tools.exception.error;

import java.util.HashMap;
import java.util.Map;

/**
 * 3 số đầu là mã Http status số sau là định danh
 */
public enum ErrorCode {
    // 400
    BAD_REQUEST(4000),
    USER_ID_IS_EMPTY(4001),
    PLATFORM_ID_IS_EMPTY_OR_0(4002),
    INVALID_EMAIL_FORMAT(4003),
    INVALID_PHONE_NUMBER_FORMAT(4004),
    INVALID_USERNAME_FORMAT(4005),
    INVALID_PASSWORD_FORMAT(4006),
    INVALID_REGISTRATION(4007),
    UNSUPPORTED_MEDIA_TYPE(40010),
    PASSWORD_SAME(40011),
    PASSWORD_INCORRECT(40012),
    EMAIL_VERIFIED(40013),
    PHONE_VERIFIED(40014),
    NOT_SUPPORT_AUTH_TYPE(40015),
    WEAK_PASSWORD(40016),
    INVALID_FORMAT_DATE(40017),
    CANNOT_DELETE_AUTHOR_HAVE_BOOK(40018),
    DUPLICATE_NAME_REQUEST(40019),
    UPDATE_ROOT_REQUEST(40020),
    VALID_NAME_REQUEST(40021),
    // 401
    AUTH_INVALID(4010),
    REFRESH_TOKEN_INVALID(4011),
    TOKEN_INVALID(4012),
    TOKEN_REVOKED(4013),
    TOKEN_EXPIRED(4014),
    VERIFICATION_CODE_NOT_FOUND(40110),
    VERIFICATION_CODE_EXPIRED(40111),
    INVALID_VERIFICATION_CODE(40112),
    // 403
    ACCESS_DENIED(4030),
    USER_IS_DEACTIVATED(4031),
    NOT_AUTHORIZED_ACTION(4032),
    // 404
    USER_NOT_FOUND(4040),
    MEDIA_GALLERY_NOT_FOUND(4041),
    CONVERSATION_NOT_FOUND(4042),
    CATEGORY_ID_NOT_FOUND(4043),
    AUTHOR_ID_NOT_FOUND(4044),
    MEDIA_ID_NOT_FOUND(4045),
    PUBLISHER_ID_NOT_FOUND(4046),
    QUOTE_ID_NOT_FOUND(4047),
    BOOK_ID_NOT_FOUND(4048),
    NOT_FOUND_ID_SHIPPING_ADDRESS(4049),
    BUNDLE_ID_NOT_FOUND(40410),
    STATIONERY_ID_NOT_FOUND(40411),
    PRODUCT_ID_NOT_FOUND(40412),
    MADE_IN_ID_NOT_FOUND(40413),
    NOT_FOUND_ID_TRADEMARK(40414),
    NOT_FOUND_ID_REVIEW_AREA(40415),
    NOT_FOUND_ID_REVIEW(40416),
    OPTION_GROUP_ID_NOT_FOUND(40417),
    OPTION_ID_NOT_FOUND(40418),
    TYPE_PRODUCT_NOT_FOUND(40419),
    ID_LANGUAGE_NOT_FOUND(40420),
    ID_NOT_FOUND(40421),
    // 409
    ACCOUNT_EXISTS(4090),
    ACCOUNT_EXISTS_WAIT_FOR_VERIFYING(4091),
    SEND_MANY_OTP(4092),
    // 500
    INTERNAL_SERVER_ERROR(5000);
    public static Map<Integer, ErrorCode> map = new HashMap<>();

    static {
        for (ErrorCode value : ErrorCode.values()) {
            map.put(value.value, value);
        }
    }

    private final Integer value;

    ErrorCode(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    public static ErrorCode valueOf(int status) {
        return map.get(status);
    }
}
