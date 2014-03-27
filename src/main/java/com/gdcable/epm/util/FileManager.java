package com.gdcable.epm.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <pre>
 * 文件管理类
 * </pre>
 */
public class FileManager
{
	private static final Log log = LogFactory.getLog(FileManager.class);

	public static final int SCALE_WIDTH = 500;

	public static final int SCALE_HEIGHT = 400;

	/**
	 * <pre>
	 * 保存附件
	 * </pre>
	 * @param fbytes 附件字节数组
	 * @param newFileName 新文件名
	 * @param dirName 保存目录
	 * @return
	 */
	public static String saveFile(byte[] fileBytes, String newFileName, String dirName)
	{
		File file = new File(dirName);
		if (!file.exists())
		{
			file.mkdirs();
		}
		String filePath = dirName + File.separator + newFileName;
		// 转换字节数组为输入流，并且调用图片压缩方法进行压缩保存返回图片路径
		ByteArrayInputStream is = new ByteArrayInputStream(fileBytes);
		return Tosmallerpic(filePath, is, (float) 0.7);
	}

	/**
	 * 利用字节数组保存文件
	 * @param fileBytes 字节数组
	 * @param newFileName 新文件名
	 * @param dirName 文件保存目录
	 * @return
	 * @throws IOException
	 */
	public static String saveFileByte(byte[] fileBytes, String newFileName, String dirName) throws IOException
	{
		String filePath = null;
		FileOutputStream fos = null;
		try
		{
			File file = new File(dirName);
			if (!file.exists())
			{
				file.mkdirs();
			}
			filePath =File.separator +"upload"  +File.separator + newFileName;
			fos = new FileOutputStream(new File(dirName + filePath));
			fos.write(fileBytes);
			fos.flush();
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			filePath = null;
			e.printStackTrace();
		}
		catch (IOException e)
		{
			filePath = null;
			e.printStackTrace();
		}
		finally
		{
			if (fos != null)
			{
				fos.close();
				fos = null;
			}
		}
		return filePath;
	}
	
	/**
	 * @param f 图片保存路径
	 * @param inputStream 图片流
	 * @param per 百分比
	 */
	public static String Tosmallerpic(String f, InputStream inputStream, float per)
	{
		Image src;
		try
		{
			src = javax.imageio.ImageIO.read(inputStream); // 构造Image对象
			// int w=800;
			// int h=600;
			String img_midname = f;
			int old_w = src.getWidth(null); // 得到源图宽
			int old_h = src.getHeight(null);
			int new_w = 0;
			int new_h = 0; // 得到源图长

			// double w2=(old_w*1.00)/(w*1.00);
			// double h2=(old_h*1.00)/(h*1.00);

			// 图片跟据长宽留白，成一个正方形图。
			BufferedImage oldpic;
			if (old_w > old_h)
			{
				oldpic = new BufferedImage(old_w, old_w, BufferedImage.TYPE_INT_RGB);
			}
			else
			{
				if (old_w < old_h)
				{
					oldpic = new BufferedImage(old_h, old_h, BufferedImage.TYPE_INT_RGB);
				}
				else
				{
					oldpic = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
				}
			}
			Graphics2D g = oldpic.createGraphics();
			g.setColor(Color.white);
			if (old_w > old_h)
			{
				g.fillRect(0, 0, old_w, old_w);
				g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h, Color.white, null);
			}
			else
			{
				if (old_w < old_h)
				{
					g.fillRect(0, 0, old_h, old_h);
					g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h, Color.white, null);
				}
				else
				{
					// g.fillRect(0,0,old_h,old_h);
					g.drawImage(src.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0, 0, null);
				}
			}
			g.dispose();
			src = oldpic;
			// 图片调整为方形结束
			// if(old_w>w)
			// new_w=(int)Math.round(old_w/w2);
			// else
			new_w = old_w;
			// if(old_h>h)
			// new_h=(int)Math.round(old_h/h2);//计算新图长宽
			// else
			new_h = old_h;
			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			// tag.getGraphics().drawImage(src,0,0,new_w,new_h,null);
			// //绘制缩小后的图
			tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream newimage = new FileOutputStream(img_midname); // 输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			/* 压缩质量 */
			jep.setQuality(per, true);
			encoder.encode(tag, jep);
			// encoder.encode(tag); //近JPEG编码
			newimage.close();
		}
		catch (IOException ex)
		{
			System.out.println("error");
			f = "";
		}
		return f;
	}

	/**
	 * <pre>
	 * 输入流保存文件
	 * </pre>
	 * @param inputStream 输入流
	 * @param newFileName 保存文件名
	 * @param dirName 保存文件路径
	 * @return
	 * @throws IOException
	 */
	public static String saveFileInputStream(InputStream inputStream, String newFileName, String dirName)
			throws IOException
	{
		File file = new File(dirName);
		FileOutputStream fos = null;
		if (!file.exists())
		{
			file.mkdirs();
		}
		String filePath = dirName + File.separator + newFileName;
		try
		{
			File f = new File(filePath);
			fos = new FileOutputStream(f);
			byte[] buffer = new byte[1024 * 1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = inputStream.read(buffer)) != -1)
			{
				bytesum += byteread;
				fos.write(buffer, 0, byteread);
				fos.flush();
			}
			fos.close();
			inputStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fos != null)
			{
				fos.close();
				fos = null;
			}
			if (inputStream != null)
			{
				inputStream.close();
				inputStream = null;
			}
		}
		return filePath;
	}

	/**
	 * <pre>
	 * 获取文件
	 * </pre>
	 * @param filePath
	 * @return
	 */
	public static File getFile(String filePath)
	{
		File file = null;
		file = new File(filePath);
		if (!file.exists())
		{
			System.out.println("not exists this file!");
			return null;
		}
		return file;
	}

	/**
	 * 获取文件的字节数组
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBytes(File file) throws IOException
	{
		byte[] bytes = null;
		InputStream is = null;
		if (file != null)
		{
			try
			{
				is = new FileInputStream(file);
				int length = (int) file.length();
				if (length > Integer.MAX_VALUE) // 当文件的长度超过了int的最大值
				{
					System.out.println("this file is max ");
					return null;
				}
				bytes = new byte[length];
				int offset = 0;
				int numRead = 0;
				while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
				{
					offset += numRead;
				}
				// 如果得到的字节长度和file实际的长度不一致就可能出错了
				if (offset < bytes.length)
				{
					System.out.println("file length is error");
					return null;
				}
				is.close();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if (is != null)
				{
					is.close();
					is = null;
				}
			}
		}

		return bytes;
	}

	/**
	 * <pre>
	 * 复制文件（小文件复制)
	 * </pre>
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void fileCopy(File in, File out) throws IOException
	{
		FileChannel inChannel = new FileInputStream(in).getChannel();
		FileChannel outChannel = new FileOutputStream(out).getChannel();
		try
		{
			inChannel.transferTo(0, inChannel.size(), outChannel);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();
		}
	}

	
   /** 
	* 删除某个文件夹下的所有文件夹和文件 
	* @param delpath String 
	* @throws FileNotFoundException 
	* @throws IOException 
	* @return boolean 
	*/ 
	public static boolean deleteDirAndFiles(String delpath) throws FileNotFoundException,IOException { 
		try { 
			File file = new File(delpath); 
			if (!file.isDirectory()) { 
				file.delete(); 
			}else if (file.isDirectory()) { 
				String[] filelist = file.list(); 
				for (int i = 0; i < filelist.length; i++) { 
					File delfile = new File(delpath + "\\" + filelist[i]); 
					if (!delfile.isDirectory()) { 
						delfile.delete(); 
					}else if (delfile.isDirectory()) { 
						deleteDirAndFiles(delpath + "\\" + filelist[i]); 
					} 
				} 
				file.delete(); 
			} 
			return true;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
	} 
	
}
