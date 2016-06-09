package com.rest.upload.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@RequestMapping("/upload")
	public String regist(Model model, Gallery gallery, HttpServletRequest request){
		
		System.out.println("title is "+gallery.getTitle());
		
		//저장할 경로 지정!!
		ServletContext context=request.getServletContext();
		String path=context.getRealPath("/data/");
		System.out.println(path);
		
		MultipartFile multipartFile=gallery.getMyFile();
		String filename=multipartFile.getOriginalFilename();
		
		try {
			multipartFile.transferTo(new File(path+filename));
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "upload/result";
	}
}






