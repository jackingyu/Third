package com.third.service.media;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
	String createMedia(String mediaId, String folder, MultipartFile media);

}
