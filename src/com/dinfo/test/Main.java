package com.dinfo.test;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;

import com.sun.jna.Pointer;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TessAPI1;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.ITessAPI.TessBaseAPI;
import net.sourceforge.tess4j.ITessAPI.TessPageSegMode;
import net.sourceforge.tess4j.util.ImageIOHelper;
import net.sourceforge.tess4j.util.LoadLibs;


public class Main {
	 public static void main(String[] args) {
//		 String nativeTempDir = System.getProperty("java.io.tmpdir"); 
//		 System.err.println(nativeTempDir);
		    String datapath = ".";
		    String language = "chi_sim";
		    TessBaseAPI handle=TessAPI1.TessBaseAPICreate();
		    File tiff = new File("D://Im0-3.jpg");
		    BufferedImage image = null;
			try {
				image = ImageIO.read(tiff);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // require jai-imageio lib to read TIFF
	        ByteBuffer buf = ImageIOHelper.convertImageData(image);
	        int bpp = image.getColorModel().getPixelSize();
	        int bytespp = bpp / 8;
	        int bytespl = (int) Math.ceil(image.getWidth() * bpp / 8.0);
	        TessAPI1.TessBaseAPIInit3(handle, datapath, language);
	        TessAPI1.TessBaseAPISetPageSegMode(handle, TessPageSegMode.PSM_AUTO);
	        Pointer utf8Text = TessAPI1.TessBaseAPIRect(handle, buf, bytespp, bytespl, 0, 0, image.getWidth(), image.getHeight());
	        String result = utf8Text.getString(0);
	        System.err.println(result);
//		 ITesseract instance=new Tesseract();
//		 File imageFile = new File("D://爱普股份：实际控制人关于公司股票交易异常波动的问询函的回函15-3-31.pdf");
//		  try {
//			List<IIOImage> imageList = ImageIOHelper.getIIOImageList(imageFile);
//			 String result = instance.doOCR(imageList,null);
//			 System.err.println(result);
//		} catch (TesseractException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	 }
//	 private static void loadDLL(String libFullName) {  
//	        try {  
//	            String nativeTempDir = System.getProperty("java.io.tmpdir");  
//	            InputStream in = null;  
//	            FileOutputStream writer = null;  
//	            BufferedInputStream reader = null;  
//	            File extractedLibFile = new File(nativeTempDir + File.separator + libFullName);  
//	            if (!extractedLibFile.exists()) {  
//	                try {  
//	                    in = Tesseract.class.getResourceAsStream("/" + libFullName);  
//	                    Tesseract.class.getResource(libFullName);  
//	                    reader = new BufferedInputStream(in);  
//	                    writer = new FileOutputStream(extractedLibFile);  
//	                    byte[] buffer = new byte[1024];  
//	                    while (reader.read(buffer) > 0) {  
//	                        writer.write(buffer);  
//	                        buffer = new byte[1024];  
//	                    }  
//	                    in.close();  
//	                    writer.close();  
//	                    System.load(extractedLibFile.toString());  
//	                } catch (IOException e) {  
//	                    e.printStackTrace();  
//	                } finally {  
//	                    if (in != null) {  
//	                        in.close();  
//	                    }  
//	                    if (writer != null) {  
//	                        writer.close();  
//	                    }  
//	                }  
//	            } else {  
//	                System.load(extractedLibFile.toString());  
//	            }  
//	        } catch (IOException e) {  
//	          //  logger.error("初始化 " + libFullName + " DLL错误", e);  
//	        }  
//	 }  
}
