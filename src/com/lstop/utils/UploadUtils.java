package com.lstop.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadUtils {

	
	public static  Map<String, String> getRequestData(HttpServletRequest request){
		 Map<String, String> map =new HashMap();
		 if (!ServletFileUpload.isMultipartContent(request)) {
			 return null;
		 }
		 //1.配置DiskFileItemFactory
		 DiskFileItemFactory factory =new DiskFileItemFactory();
		 factory.setRepository(new File("E:\\LF\\eclipseworkspace_lf"));
		 //2.创建上传核心类
		 ServletFileUpload servletFileUpload= new ServletFileUpload(factory);
		 //3.解析请求
		 try {
			List<FileItem> fileItems = servletFileUpload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				//判断表单类型
				if (fileItem.isFormField()) {
					//普通表单
					String name = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					map.put(name, value);
				}else {
					//上传项
					if(fileItem.getString().length()!=0) {
						File file = FileUtils.test4(fileItem);
						String netPath= FileUtils.getNetPath(file.getAbsolutePath());
						map.put("imgurl", netPath);
						fileItem.write(file);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return map;
	}
}
