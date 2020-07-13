package com.njau.utils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FFMpegUtil {
	//视频文件路径：
	//public static String videoPath = "D:/LABPROJECT/sowbirthwarn/sow";

	//存放截取视频某一帧的图片
	//public static String videoFramesPath = "D:/test/img/";
	
	//String videoFileName = "A001001.mp4"
	public String videoPath;
	public String videoFramesPath;
	
	public String videoFileName;
	
	public String sowName;
	
	/**
	 * 将视频文件帧处理并以“jpg”格式进行存储。
	 * 依赖FrameToBufferedImage方法：将frame转换为bufferedImage对象
	 *
	 * @param videoFileName
	 * 
	 * 
	 */
	
	
	public FFMpegUtil(String sowName,String videoPath,String videoFramesPath,String videoFileName){
		this.sowName=sowName;
		this.videoPath=videoPath;
		this.videoFramesPath=videoFramesPath;
		this.videoFileName=videoFileName;
	}
	
	
	public String grabberVideoFramer(String videoFileName){
		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//最后获取到的视频的图片的路径
		String videPicture="";
		//Frame对象
		Frame frame = null;
		//标识
		int flag = 0;
		try {
			 /*
            获取视频文件
            */
			FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath + "/" + videoFileName);
			fFmpegFrameGrabber.start();
			
            //获取视频总帧数
			int ftp = fFmpegFrameGrabber.getLengthInFrames();
			//System.out.println(ftp);
			int time = (int) (ftp / fFmpegFrameGrabber.getFrameRate() / 60);
			int perMinFrame = (int)(ftp/time);
			//System.out.println(perMinFrame);
			int count = 1;
			//System.out.println(time);
			//System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);
			
			while (flag <= ftp) {
				frame = fFmpegFrameGrabber.grabImage();
				/*
				对视频的第五帧进行处理
				 */
				if (frame != null && (flag%perMinFrame)==0) {
					//文件绝对路径+名字
					//String fileName = videoFramesPath + UUID.randomUUID().toString()+"_" + String.valueOf(flag) + ".jpg";
					String fileName = "";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					Date date = new Date();
					fileName = videoFramesPath  +sowName+ "_"+sdf.format(date) + ".jpg";
					
				/*	if(count<10){
						fileName = videoFramesPath  +sowName+ "_00000"+count + ".jpg";
					}else if(count<100){
						fileName = videoFramesPath  +sowName+ "_0000"+count + ".jpg";
					}else{
						fileName = videoFramesPath  +sowName+ "_000"+count + ".jpg";
					}*/
					
					//文件储存对象
					File outPut = new File(fileName);
					ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);
					
					//视频第五帧图的路径
					String savedUrl = videoFramesPath + outPut.getName();
					videPicture=savedUrl;
					count++;
					
				}
				flag++;
				
			}
			fFmpegFrameGrabber.stop();
			fFmpegFrameGrabber.close();
		} catch (Exception E) {
			E.printStackTrace();
		}
		return videPicture;
	}

	public BufferedImage FrameToBufferedImage(Frame frame) {
		//创建BufferedImage对象
		Java2DFrameConverter converter = new Java2DFrameConverter();
		BufferedImage bufferedImage = converter.getBufferedImage(frame);
		return bufferedImage;
	}


	/**
	 * 测试：
	 * 1、在D盘中新建一个test文件夹，test中再分成video和img，在video下存入一个视频，并命名为test
	 * D:/test/video     D:/test/img
	 * @param args
	 */
	/*public static void main(String[] args) {
		String videoFileName = "A001001.mp4";
		grabberVideoFramer(videoFileName);
	}*/
}
