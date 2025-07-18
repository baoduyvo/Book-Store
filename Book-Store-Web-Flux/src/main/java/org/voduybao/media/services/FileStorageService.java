package org.voduybao.media.services;

import org.springframework.http.codec.multipart.FilePart;
import org.voduybao.tools.dao.entities.media.MediaGallery;

public interface FileStorageService {

    MediaGallery saveFile(String fileOriginalName,
                          String mimeType,
                          String fileName,
                          String objectName,
                          FilePart fp) throws Exception;
}
