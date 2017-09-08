package com.third.service.media.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.third.service.media.MediaService;
import com.third.service.media.MediaStorageStrategy;

public class DefaultMediaService implements MediaService {
	private MediaStorageStrategy mediaStorageStrategy;

	@Override
	public String createMedia(String mediaId, String folder,
			MultipartFile media)
	{
		final String basePath = System.getProperty("system.root");

		String[] mediaName = media.getOriginalFilename().split("\\.");
		String mediaIdWithSuffix = mediaId;

		if (mediaName.length > 1)
			mediaIdWithSuffix = mediaId + "." + mediaName[1];

		return mediaStorageStrategy.createMedia(mediaIdWithSuffix, folder,
				media, basePath);
	}

	public void setMediaStorageStrategy(
			MediaStorageStrategy mediaStorageStrategy)
	{
		this.mediaStorageStrategy = mediaStorageStrategy;
	}

}
