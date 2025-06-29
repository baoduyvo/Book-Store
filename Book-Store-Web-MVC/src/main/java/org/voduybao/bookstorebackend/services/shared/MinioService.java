package org.voduybao.bookstorebackend.services.shared;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

    String uploadFile(MultipartFile file) throws Exception;

    byte[] downloadFile(String fileName) throws Exception;

    void deleteFile(String fileName) throws Exception;

    boolean fileExists(String fileName) throws Exception;
}
