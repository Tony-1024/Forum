package com.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	private String className;
	private String url;
	private String username;
	private String password;
	private Connection con;
	private PreparedStatement pstm;
	public DB(){
		className="org.postgresql.Driver";
		url="jdbc:postgresql://localhost:5432/stu_forum";
		username="postgres";
		password="123456";
		
/*		className="org.postgresql.Driver";
		url="jdbc:postgresql://localhost:5432/myonla5_stuforum";
		username="myonla5_stuforum_db";
		password="stuforum_db";
*/		
/*		className="com.mysql.jdbc.Driver";
		url="jdbc:mysql://localhost:3306/db_bbs";
		username="root";
		password="admin";*/
		try{
			Class.forName(className);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public void getCon(){
		try {
			con=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void doPstm(String sql,Object[] params){
		if(sql!=null&&!sql.equals("")){
			System.out.println(sql);
			getCon();
			try {
				pstm=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				if(params==null){
					params=new Object[0];
				}
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1,params[i]);
				}
				pstm.execute();
			} catch (SQLException e) {
				System.out.println("Error when invoking doPstm method of DB class!");
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet getRs(){
		try {			
			return pstm.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	public int getUpdate(){
		try {
			return pstm.getUpdateCount();
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		}
	}
	
	public void closed(){
		try{
			if(pstm!=null)
				pstm.close();
		}catch(Exception e){
			System.out.println("fail to close pstm!");
		}
		try{
			if(con!=null){
				con.close();
			}
		}catch(Exception e){
			System.out.println("fail to close con!");
		}
	}
	

}
