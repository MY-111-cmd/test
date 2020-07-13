package com.njau.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.attribute.IntegerSyntax;



public class JDBC {
	// JDBC 驱动名及数据库 URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://localhost:3306/birth?useSSL=false";
    // 数据库的用户名与密码，需要根据自己的设置
    public static final String USER = "root";
    public static final String PASS = "520520";
    private int count;
 
    public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void read(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");      
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);      
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
           /*sql = "select * from sow";*/
            ResultSet rs = stmt.executeQuery(sql);        
            // 展开结果集数据库
            while(rs.next()){
            	// 通过字段检索
				String smallpig = rs.getString("smallpig");
				String num = rs.getString("num");
				String flag = rs.getString("flag");
				int id = rs.getInt("id");
				// 输出数据
				System.out.print("仔猪个数" + smallpig);
				 int i = Integer.valueOf(smallpig);
				//if (smallpig.compareTo("0") > 0) {
				 if("True".equals(flag)){
					AlertHttpClientUtil httpclientutil = new AlertHttpClientUtil();
					try {
						String message = num + "号第" + smallpig + "头仔猪出生";
						System.out.println(message);
						//httpclientutil.send(message);
						System.out.println("发送报警短信成功");
						String upSql = "update alert t set t.flag='false' where t.id = "+id+"";
						JDBC jdbc = new JDBC();
						jdbc.write(upSql);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    public void read1(String sql) {
        Connection conn = null;
        Statement stmt = null;
     
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");      
            // 打开链接
            //System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);      
            // 执行查询
            //System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
             /*sql = "select count(*) from alert1";*/
            ResultSet rs = stmt.executeQuery(sql);        
            
            // 展开结果集数据库
            while(rs.next()){
            	count = rs.getInt(1); // count = rs.getInt("result");  
            }
            //System.out.println(count);
           
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    public void write(String sql) throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        ps = conn.prepareStatement(sql);
	        int a=ps.executeUpdate();//a代表改变数据库的条数  
	         if(a>0){  
	             System.out.println("添加成功");  
	         }else{  
	             System.out.println("添加失败");  
	         }  
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(ps!=null){  
	               ps.close();  
	        }if(conn!=null){  
	               conn.close();  
	        }  
		}   
    }
   
   
}




