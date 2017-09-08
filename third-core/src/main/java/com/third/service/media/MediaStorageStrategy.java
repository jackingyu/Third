package com.third.service.media;

import org.springframework.web.multipart.MultipartFile;

public interface MediaStorageStrategy {
	String createMedia(String mediaId, String folder, MultipartFile file,
			String basePath);
}
