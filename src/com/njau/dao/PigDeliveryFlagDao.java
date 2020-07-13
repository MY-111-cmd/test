package com.njau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.njau.db.DB;

public class PigDeliveryFlagDao {

	public static int getCountByNum(String num) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String sql = "select count(*) c from pigdeliveryflag where num='"+num+"'";
			conn = DB.getConn();
			pstmt = DB.getPStmt(conn, sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeRs(rs);
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		return count;
	}
	

	public static void savePigFlag(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConn();
			String sql = "insert into pigdeliveryflag value (?,?,?,?) "; 
			pstmt = DB.getPStmt(conn, sql);
			pstmt.setObject(1, null);
			pstmt.setString(2, num);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 1);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		
	}

	public static void updatePigFlag(String num,int flag) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConn();
			String sql = "update pigdeliveryflag set flag="+flag+" where num='"+num+"'"; 
			pstmt = DB.getPStmt(conn, sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		
	}

	public static void deletePigFlag(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConn();
			String sql = "delete from pigdeliveryflag where num='"+num+"'"; 
			pstmt = DB.getPStmt(conn, sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		
	}

	public static int getpigFlag(String num) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String sql = "select flag from pigdeliveryflag where num='"+num+"'";
			conn = DB.getConn();
			pstmt = DB.getPStmt(conn, sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("flag");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeRs(rs);
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		return count;
	}

	public static void updatePigIsAlarm(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConn();
			String sql = "update pigdeliveryflag set isalarm=1 where num='"+num+"'"; 
			pstmt = DB.getPStmt(conn, sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		
	}

	public static int getPigIsAlarm(String num) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String sql = "select isalarm from pigdeliveryflag where num='"+num+"'";
			conn = DB.getConn();
			pstmt = DB.getPStmt(conn, sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("isalarm");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeRs(rs);
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		return count;
	}
	
}
