package org.voduybao.bookstorebackend.dao.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mediaApp",
        url = "http://localhost:8080"
)
public interface MediaClient {
}
