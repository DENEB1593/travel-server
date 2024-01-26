package io.everyone.travel.api.web.travel.dto;

import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Value
public class Thumbnail {

    String thumbnailName;

    String contentType;

    byte[] bytes;


    /**
     * 썸네일 형식 검증
     */
    private static boolean verify(MultipartFile file) {
        if (file != null && !file.isEmpty() && StringUtils.isNotEmpty(file.getOriginalFilename())) {
            return StringUtils.isNotEmpty(file.getContentType()) && file.getContentType().startsWith("image");
        }
        return false;
    }

    public static Optional<Thumbnail> toThumbnail(MultipartFile file) {
        if (verify(file)) {
            try {
                return Optional.of(
                    new Thumbnail(file.getOriginalFilename(), file.getContentType(), file.getBytes()));
            } catch (IOException ignored) {
                // 무시
            }
        }
        return Optional.empty();
    }

}
