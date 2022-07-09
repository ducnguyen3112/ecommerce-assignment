package com.nashtech.ecommerce.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nashtech.ecommerce.exception.CustomRuntimeException;
import com.nashtech.ecommerce.service.CloudDinaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class CloudDinaryServiceImpl implements CloudDinaryService {

    Cloudinary cloudinary;


    public CloudDinaryServiceImpl(Cloudinary cloudinary) {

        this.cloudinary = cloudinary;
    }

    @Override
    public Map<?, ?> upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map<?, ?> resultMap = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return resultMap;

    }

    @Override
    public File convert(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try {
            FileOutputStream fOutputStream = new FileOutputStream(file);
            fOutputStream.write(multipartFile.getBytes());
            fOutputStream.close();
            return file;
        } catch (IOException e) {
            throw new CustomRuntimeException(e.getMessage(), e);
        }
    }
}
