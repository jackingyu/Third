package com.third.service.media.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.third.service.media.MediaStorageStrategy;

public class DefaultMediaStorageStrategy implements MediaStorageStrategy
{
   private final String mediaBasePath = "/medias";
   
	@Override
	public String createMedia(String mediaId, String folder,MultipartFile file,final String basePath)
	{  
		StringBuilder sb = new StringBuilder();
		sb.append(mediaBasePath).append(File.separator).append(folder);
		String subPath = generateFilePath(mediaId);
		sb.append(subPath);
		
		File media = new File(basePath+sb.toString(),mediaId);
		
		String relativePath = sb.append(File.separator).append(mediaId).toString();
		
		if(!media.exists())
			media.mkdirs();
		
		try
		{
			file.transferTo(media);
		}
		catch (IllegalStateException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return relativePath;
	}
	
	protected String generateFilePath(final String fileName)
	{
       int hashcode = fileName.hashCode();
       int mask = 255;
       int firstDir = hashcode & mask;
       int secondDir = (hashcode >> 8) & mask;

       StringBuilder sb = new StringBuilder(File.separator);
       sb.append(String.format("%02x", firstDir));
       sb.append(File.separator);
       sb.append(String.format("%02x", secondDir));

       String path = sb.toString();
       
       return path;
	}
}
