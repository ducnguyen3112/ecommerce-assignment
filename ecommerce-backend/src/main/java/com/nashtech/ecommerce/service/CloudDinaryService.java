package com.nashtech.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CloudDinaryService {

    Map<?, ?> upload(MultipartFile multipartFile) throws IOException;

    File convert(MultipartFile multipartFile);

}
