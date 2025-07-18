package org.voduybao.media.services;

import org.springframework.http.codec.multipart.FilePart;
import org.voduybao.tools.response.http.Result;
import reactor.core.publisher.Mono;

public interface MediaGalleryService {
    Result upload(FilePart file, String type);

    String createBucket(String bucketName);
}
