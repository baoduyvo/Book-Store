package org.voduybao.bookstorebackend.services.shared;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

    /**
     * Upload file lên MinIO
     * @param file Multipart file
     * @return Tên file đã lưu trên MinIO
     * @throws Exception nếu xảy ra lỗi
     */
    String uploadFile(MultipartFile file) throws Exception;

    /**
     * Tải file từ MinIO về
     * @param fileName tên file đã upload
     * @return Dữ liệu dưới dạng byte[]
     * @throws Exception nếu không tìm thấy hoặc lỗi
     */
    byte[] downloadFile(String fileName) throws Exception;

    /**
     * Xóa file khỏi bucket
     * @param fileName tên file cần xóa
     * @throws Exception nếu lỗi
     */
    void deleteFile(String fileName) throws Exception;

    /**
     * Kiểm tra file có tồn tại không
     * @param fileName tên file
     * @return true nếu tồn tại
     * @throws Exception nếu lỗi
     */
    boolean fileExists(String fileName) throws Exception;
}
