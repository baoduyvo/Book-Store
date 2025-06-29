package org.voduybao.bookstorebackend.services.shared;

import java.util.Map;

public interface RedisService {

    void addToHash(String key, String field, Object value);

    boolean hasKey(String key);

    Map<String, Object> getAllFromHash(String key);

}
