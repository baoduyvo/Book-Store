package org.voduybao.media.services;


import org.springframework.http.codec.multipart.FilePart;

public interface MinioService {
    String uploadFile(String fileName, FilePart fp) throws Exception;

    byte[] downloadFile(String fileName) throws Exception;

    void deleteFile(String fileName) throws Exception;

    boolean fileExists(String fileName) throws Exception;

    void autoCreateBucket() throws Exception;

}
