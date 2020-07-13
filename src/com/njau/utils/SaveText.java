package com.njau.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;
import com.njau.entities.Sow;

public class SaveText implements Runnable{
	/*public static final String filePath = "/home/promise/delivery_detect/output/final.txt";
	public static final String filePath1 = "/home/promise/delivery_detect/output/posture_transition.txt";*/
	public static final String filePath = "D:\\sowtest\\piglet_detect.txt";
	//public static void main(String[] args) throws SQLException, IOException {
		// sendMessageToPhone();
		//saveTxtData();

	//}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("开启线程");
			saveTxtData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveTxtData() throws IOException {
		System.out.println("开始读取txt");
		FileReader in = null;
		try {
			in = new FileReader(filePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LineNumberReader reader = new LineNumberReader(in);
		
		String s = reader.readLine();
		/*System.out.println("txt中每行数据为"+s);*/
		int lines = 0;
		while (s != null) {
			lines++;
			s = reader.readLine();
		}
		int fileCount = lines;
		reader.close();
		in.close();
		System.out.println(fileCount);

		String sql1 = "select count(*) from alert1";
		
		JDBC jdbc = new JDBC();
		jdbc.read1(sql1);
		
		int MySqlcount = jdbc.getCount();
		int count = 1;
		List<String[]> list = new ArrayList();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()){
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String lineTxt = br.readLine();
				while ((lineTxt = br.readLine()) != null) {
					if (((MySqlcount+1) < fileCount) && count <= MySqlcount) {
						count++;
						continue;
					} else if ((MySqlcount+1) == fileCount) {
						break;
					} else {
						String a[] = lineTxt.split(",");
						System.out.println(a[0]+" "+a[1]);
						saveAlert(a);
						Data data = new Data();
						System.out.println(data);
					}
					// System.out.println(lineTxt);
					// String a[] = lineTxt.split(",");
					// System.out.println(a.length);
					// saveAlert(a);
				}
				br.close();
			} else {
				System.out.println("文件不存在!");
			}
		} catch (Exception e) {
			System.out.println("文件读取错误!");
		}

	}

	// 从指定行数读取数据
	/*
	 * static void readAppointedLineNumber(File sourceFile, int lineNumber)
	 * throws IOException { String sql1 = "select count(*) from alert1"; JDBC
	 * jdbc = new JDBC(); jdbc.read(sql1); int count1 = jdbc.getCount();
	 * FileReader in = new FileReader(sourceFile); LineNumberReader reader = new
	 * LineNumberReader(in); String s = null; int line = 1;
	 * 
	 * System.out.println("当前行号为:" + reader.getLineNumber());
	 * reader.setLineNumber(count1); System.out.println("更改后行号为:" +
	 * reader.getLineNumber()); long i = reader.getLineNumber(); while
	 * (reader.readLine() != null) {
	 * 
	 * 
	 * }
	 * 
	 * reader.close(); in.close(); }
	 */

	// 文件内容的总行数。
	
	

	public static void saveAlert(String[] arrs) {
		String sql = null;

		sql = "insert into alert1(id,num,smallpig,expectant) values(default,'" + arrs[0] + "','" + arrs[1] + "','"
				+ arrs[2] + "')";

		JDBC jdbc = new JDBC();
		try {
			jdbc.write(sql);
			/* jdbc.read(sql1); */
			/* int count1 = jdbc.getCount(); */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//
	public static void saveFre(String[] arrs) {
		String sql = null;
		sql = "insert into pre_alert(id,num,frequency,expectant) values(default,'" + arrs[0] + "','" + arrs[1] + "','"
				+ arrs[2] + "')";
		JDBC jdbc = new JDBC();
		try {
			jdbc.write(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void preAlert(String[] arrs) { String sql = null; sql =
	 * "select * from pre_alert"; JDBC jdbc = new JDBC(); jdbc.read(sql);
	 * 
	 * }
	 */
	

	

}
