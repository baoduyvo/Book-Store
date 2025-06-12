package org.voduybao.bookstorebackend.services.shared.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.services.shared.RedisService;

import java.util.Map;

@Component
@Slf4j(topic = "REDIS")
public class RedisServiceImpl implements RedisService {


    @Override
    public void addToHash(String key, String field, Object value) {

    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

    @Override
    public Map<String, Object> getAllFromHash(String key) {
        return Map.of();
    }
}