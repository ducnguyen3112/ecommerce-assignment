package com.nashtech.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudDinaryService {

	Map<?, ?> upload(MultipartFile multipartFile) throws IOException;

	File convert(MultipartFile multipartFile);

}
