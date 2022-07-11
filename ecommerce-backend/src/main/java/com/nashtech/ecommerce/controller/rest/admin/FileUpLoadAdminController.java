package com.nashtech.ecommerce.controller.rest.admin;

import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.service.CloudDinaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class FileUpLoadAdminController {

    @Autowired
    private CloudDinaryService cloudDinaryService;

    @PostMapping("/cloudinary")
    @Operation(summary = "Upload photos to Cloudinary",tags = {"Administrator","User"})
    public ResponseEntity<ResponseMessageDto> upLoad(
            @NotEmpty @RequestParam("file") MultipartFile multipartFile)
            throws IOException {
        Map<?, ?> resultMap = cloudDinaryService.upload(multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDto(
                HttpStatus.OK, resultMap.get("url").toString(), LocalDateTime.now()));
    }
}
