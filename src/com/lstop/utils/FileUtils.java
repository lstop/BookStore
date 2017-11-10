package com.lstop.utils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;


public class FileUtils {

	/**
	 * 
	 * @param diskPath磁盘路径
	 * @return  网络访问路径
	 */
	
	public  static String getNetPath(String diskPath) {
		int index1 = diskPath.indexOf("img");
		String basePath= "http://localhost:8080/img/"+diskPath.substring(index1+4).replace("\\", "/") ;
		return basePath;
	}
	// 获取上传的文件名称
	public static  String getFileName(FileItem fileItem) {
		// 在不同浏览器取值结果不一样(name有的是只有名称，有的是连带的路径的名称)
		String name = fileItem.getName();
		String filename = name.substring(name.lastIndexOf("\\") + 1);
		return filename;
	}

	//保证文件名称唯一性
	public static String getFileNameByHashCode(FileItem fileItem) {
		String filename = getFileName(fileItem);
		// 方式一：使用时间戳区分：
		// filename =System.currentTimeMillis()+filename;
		// 方式二： 使用UUID区分：
		filename = UUID.randomUUID() + "_" + filename;
		return filename;
	}
	// 方式一：默认
	/**

	 * @param fileItem
	 *            上传项
	 * @return 返回的是上传要写入的文件目录
	 */
	public static  File test1(FileItem fileItem) {
		File file = new File("C:\\img");
		if (!file.exists()) {
			// mkdirs:创建多级目录，即使父目录不存在也能创建成功
			// mkdir:只能单级目录，而且要求是必须父目录存在
			file.mkdirs();
		}
		// 创建文件
		File file2 = new File(file, getFileNameByHashCode(fileItem));
		return file2;
	}

	// 方式二：保证名称的唯一性
	public static  File test2(FileItem fileItem) {
		File file = new File("C:\\img");
		if (!file.exists()) {
			// mkdirs:创建多级目录，即使父目录不存在也能创建成功
			// mkdir:只能单级目录，而且要求是必须父目录存在
			file.mkdirs();
		}
		// 创建文件
		File file2 = new File(file, getFileNameByHashCode(fileItem));
		return file2;
	}

	// 方式三：文件目录进行变化
	// 1.按照日期存储(按照日期打散文件)
	public  static  File test3(FileItem fileItem) {
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date(time));

		// 目录
		File file = new File("C:\\img", date);
		if (!file.exists()) {
			// mkdirs:创建多级目录，即使父目录不存在也能创建成功
			// mkdir:只能单级目录，而且要求是必须父目录存在
			file.mkdirs();
		}
		// 创建文件
		File file2 = new File(file, getFileNameByHashCode(fileItem));
		return file2;
	}

	// 2.按照hashcode随机值存储(按照hashcode打散文件)
	public  static   File test4(FileItem fileItem) {

		String  filename =getFileNameByHashCode(fileItem);
		// 获取文件名称hashcode值
		int code = filename.hashCode();
		// 把int类型的值转化成16进制的字符串
		String code1 = Integer.toHexString(code);
		String dir = code1.charAt(0) + "\\" + code1.charAt(1);
		// 目录
		File file = new File("E:\\LF\\eclipseworkspace_lf\\img", dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 创建文件
		File file2 = new File(file, filename);
		return file2;
	}

}
