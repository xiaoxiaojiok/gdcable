package com.gdcable.epm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 下载工具类
 * 
 */
@Controller
@RequestMapping(value = "/template")
public class DownloadUtil {

	@RequestMapping(value = "/download")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response,String filepath,String filename) throws IOException{
		String baseDir = request.getSession().getServletContext().getRealPath("/");
		download(response, baseDir + File.separator +filepath, filename);
	}
	
	/**
	 * 下载
	 * @param response
	 * @param filePath 文件物理路径
	 * @param fileName 下载的文件名
	 * @throws IOException
	 */
	public void download(HttpServletResponse response, String filePath, String fileName) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			long fileLength = new File(filePath).length();

			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GBK"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

}
