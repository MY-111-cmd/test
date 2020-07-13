package com.njau.utils;

public class SendMassage implements Runnable{
	public static void sendMessageToPhone() {
		String sql = null;
		sql = "select * from alert";
		JDBC jdbc = new JDBC();
		jdbc.read(sql);
	}

	@Override
	public void run() {
		sendMessageToPhone();
		
	}

}
