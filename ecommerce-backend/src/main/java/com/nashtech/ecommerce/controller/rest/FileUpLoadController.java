package com.nashtech.ecommerce.controller.rest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.service.impl.CloudDinaryService;

@Controller
public class FileUpLoadController {
	
	@Autowired
	private CloudDinaryService cloudDinaryService;
	
	@PostMapping("/cloudinary")
	public ResponseEntity<ResponseMessageDto> upLoad(@RequestParam("file") MultipartFile multipartFile) throws IOException{
		Map<?, ?> resultMap=cloudDinaryService.upload(multipartFile);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDto(HttpStatus.OK, resultMap.get("url").toString(),LocalDateTime.now()));
	}
}
