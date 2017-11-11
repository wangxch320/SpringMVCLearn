package com.MySpringMVC.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class Hello {

    /**
     * 1. ʹ�� @RequestMapping ע����ӳ������� URL
     * 2. ����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ, ���� InternalResourceViewResolver ��ͼ������, �������µĽ���:
     * ͨ�� prefix + returnVal + ��׺ �����ķ�ʽ�õ�ʵ�ʵ�������ͼ, Ȼ����ת������
     * 
     * /WEB-INF/views/success.jsp
     * 
     * @return
     */
    @RequestMapping("/helloworld")
    public String hello(){
        System.out.println("hello world!");
        return "success";
    }
    
    @RequestMapping("/FileUploadPage")
    public String SpringFileUploadPage() {
    	return "springFileUpload";
    }
    
    @RequestMapping("/FileUpload")
    public String SpringFileUpload(HttpServletRequest request) throws IllegalStateException, IOException{
        //����ǰ�����ĳ�ʼ����  CommonsMutipartResolver ���ಿ�ֽ�������
    	CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
    			request.getSession().getServletContext());
    	//���form���Ƿ���enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
        	//��request��ɶಿ��request
        	MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
        	//��ȡmultiRequest �����е��ļ���
        	Iterator iter=multiRequest.getFileNames();
        	
        	while(iter.hasNext())
        	{
        		//һ�α��������ļ�
        		MultipartFile file=multiRequest.getFile(iter.next().toString());
        		if(file!=null)
        		{
        			//���屣��·��
        			String path="C:/Users/qhdssc/Desktop/"+file.getOriginalFilename();
        			//�ϴ�
        			file.transferTo(new File(path));
        		}
        	}
        }
    	return "springFileUpload";
    }
}