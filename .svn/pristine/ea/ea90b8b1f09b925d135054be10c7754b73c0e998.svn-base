package com.gdcable.epm.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 获取各种地址工具
 *
 */
public class PathUtil {

	public static final String CONFIG_PATH ="config.properties";
	public static  Properties props = new Properties();

	static
	{
		InputStream in = PathUtil.class.getClassLoader().getResourceAsStream(CONFIG_PATH);
		try
		{
			props.load(in);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 获取配置文件信息，根据key值
	 * </pre>
	 * @param sourcekey
	 * @return
	 */
	public static String getResource(String sourcekey)
	{
		return props.getProperty(sourcekey);
	}
   //获取当前文件路径
   public String getWebClassesPath() {
	   try{
		   String path = getClass().getResource("").getPath();
		   //getClass().getProtectionDomain().getCodeSource().getLocation().getPath() //包含类名
		   return path;
	   }catch(Exception e){
		   System.out.println("路径获取错误");
		   e.printStackTrace();
	   }
	   return "";
   }

   //获取当前工程的web-inf路径
   public String getWebInfPath() throws IllegalAccessException{
	   String path = getWebClassesPath();
	   if (path.indexOf("WEB-INF") > 0) {
		   path = path.substring(0, path.indexOf("WEB-INF")+8);
	   } else {
		   throw new IllegalAccessException("路径获取错误");
	   }
	   return path;
   }

  //获取当前工程路径
   public String getWebRoot() throws IllegalAccessException{
	   String path = getWebClassesPath();
	   if (path.indexOf("WEB-INF") > 0) {
		   path = path.substring(0, path.indexOf("WEB-INF/classes"));
	   } else {
    	 throw new IllegalAccessException("路径获取错误");
	   }
	   return path;
   }
   
	 //获取当前工程的Class路径--本地
//	   public String getClassPath() throws IllegalAccessException{
//		   String path = getWebClassesPath();
//		   if (path.indexOf("target") > 0) {
//			   path = path.substring(0, path.indexOf("target/classes")+14);
//		   } else {
//			   throw new IllegalAccessException("路径获取错误");
//		   }
//		   return path;
//	   }
	   
	 //获取当前工程的Class路径--服务器
	   public String getClassPath() throws IllegalAccessException{
		   String path = getWebClassesPath();
		   System.out.println(path);
		   if (path.indexOf("WEB-INF") > 0) {
			   path = path.substring(0, path.indexOf("WEB-INF/classes")+16);
		   } else {
			   throw new IllegalAccessException("路径获取错误");
		   }
		   return path;
	   }
	   public static void main(String[] args){
			URLDecoder coders = new URLDecoder();
			String str = "url=/album/photo/batch-upload&amp;" +
					"uploadDataFieldName=file&amp;" +
					"picDescFieldName=descr&amp;" +
					"ext={'param1':'参数值1'}&amp;" +
					"fileType={'description':'图片', 'extension':'*.gif;*.jpeg;*.png;*.jpg;*.bmp'}&amp;" +
							"maxSize=100&amp;" +
							"width=750&amp;" +
							"height=500&amp;" +
							"gridWidth=121&amp;" +
							"gridHeight=120&amp;" +
							"picWidth=100&amp;" +
							"picHeight=100&amp;" +
							"compressSize=100&amp;" +
							"maxNum=100&amp;" +
							"compressSide=0&amp;" +
							"compressLength=10000&amp;" +
							"mode=0&amp;" +
							"backgroundUrl=&amp;" +
							"listBackgroundUrl=&amp;" +
							"buttonUrl=";
			try {
				System.out.println(coders.decode(str,"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
