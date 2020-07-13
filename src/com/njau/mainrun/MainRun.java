package com.njau.mainrun;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.njau.commen.SendPreAlert;
import com.njau.utils.GetFrameTread;
import com.njau.utils.SaveText;
import com.njau.utils.SendMassage;
//public static String videoPath = "D:/LABPROJECT/sowbirthwarn/sow";

	//存放截取视频某一帧的图片
	//public static String videoFramesPath = "D:/test/img/";
	
	//String videoFileName = "A001001.mp4"

public class MainRun {
	public  static ExecutorService es = Executors.newCachedThreadPool();
	public static ScheduledExecutorService es1 = Executors.newScheduledThreadPool(10);
	public static void main(String[] args) {
		/*es.execute(new GetFrameTread("A001001", "/home/promise/delivery_detect/video", "/home/promise/delivery_detect/piglet_detect/", "A001001.mp4"));
		es.execute(new GetFrameTread("A002001", "/home/promise/delivery_detect/video", "/home/promise/delivery_detect/piglet_detect/", "A002001.mp4"));
		es.execute(new GetFrameTread("B001001", "/home/promise/delivery_detect/video", "/home/promise/delivery_detect/piglet_detect/", "B001001.mp4"));
		es.execute(new GetFrameTread("C001001", "/home/promise/delivery_detect/video", "/home/promise/delivery_detect/piglet_detect/", "C001001.mp4"));*/
		es1.scheduleAtFixedRate(new GetFrameTread("A001001", "D:/sowtest/video", "D:/picture/001/", "A001001.mp4"), 0, 8000,TimeUnit.SECONDS);
		//es1.scheduleAtFixedRate(new SaveText(), 0, 30, TimeUnit.SECONDS);
	   // es1.scheduleAtFixedRate(new SendMassage(), 0, 60, TimeUnit.SECONDS);
		//es1.scheduleAtFixedRate(new SendPreAlert(), 0, 60, TimeUnit.SECONDS);
		
		}
}
