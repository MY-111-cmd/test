package com.njau.utils;

public class GetFrameTread implements Runnable{
	private String sowName;
	private String videoPath;
	private String videoFramesPath;
	private String videoFileName;
	public GetFrameTread(String sowName,String videoPath,String videoFramesPath,String videoFileName){
		this.sowName=sowName;
		this.videoPath=videoPath;
		this.videoFramesPath=videoFramesPath;
		this.videoFileName=videoFileName;
	}

	@Override
	public void run() {
		System.out.println(sowName+"开始截帧");
		FFMpegUtil ffMpegUtil = new FFMpegUtil(sowName,videoPath, videoFramesPath, videoFileName);
		ffMpegUtil.grabberVideoFramer(videoFileName);
		// TODO Auto-generated method stub
		System.out.println(sowName+"截帧结束");
	}
	

}
