package com.njau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.njau.commen.Pre_alert;
import com.njau.db.DB;

public class Pre_alertDao {
	
	public static int getNewcount() {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String sql = "select count from pre_alert order by id desc limit 0,1";
			conn = DB.getConn();
			pstmt = DB.getPStmt(conn, sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count");
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
	//��ȡ����һ�����ݵ�pigId
	public static Set<String> getPigIdSet(int countsum) {
		Set<String> set = new HashSet<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String sql = "select num from pre_alert order by id desc limit 0,"+countsum+"";
			conn = DB.getConn();
			pstmt = DB.getPStmt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				set.add(rs.getString("num"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeRs(rs);
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		return set;
	}
	public static List<Pre_alert> getPigFrequency(String num) {
		List<Pre_alert> list = new ArrayList<Pre_alert>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String sql = "select * from pre_alert where num='"+num+"' limit 0,24";
			conn = DB.getConn();
			pstmt = DB.getPStmt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Pre_alert pig = new Pre_alert();
				pig.setId(rs.getInt("id"));
				pig.setNum(rs.getString("num"));
				pig.setFrequency(rs.getString("frequency"));
				
				list.add(pig);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeRs(rs);
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		return list;
	}
	public static int getPigFrequencyByNum(String num) {
		String frequency = "0";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String sql = "select frequency from pre_alert where num='"+num+"' order by id desc limit 0,1";
			conn = DB.getConn();
			pstmt = DB.getPStmt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				frequency = rs.getString("frequency");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeRs(rs);
			DB.closeStmt(pstmt);
			DB.closeConn(conn);
		}
		return Integer.valueOf(frequency);
	}

}
