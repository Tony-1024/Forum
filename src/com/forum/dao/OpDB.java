package com.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.forum.actionform.BbsAnswerForm;
import com.forum.actionform.BbsForm;
import com.forum.actionform.BoardForm;
import com.forum.actionform.ClassForm;
import com.forum.actionform.UserForm;
import com.forum.model.CreatePage;
import com.forum.tools.Change;

public class OpDB {
	private boolean mark=false;
	private int perR=5;
	private String currentP="1";
	private String gowhich="";
	private CreatePage page=null;
	
	public List<ClassForm> OpClassListShow(){
		List<ClassForm> listshow=null;
		String sql="select * from category";
		DB mydb=new DB();
		mydb.doPstm(sql,null);
		ResultSet rs=mydb.getRs();
		if(rs!=null){
			try {
				listshow=new ArrayList<ClassForm>();
				while(rs.next()){
					ClassForm classSingle=new ClassForm();
					classSingle.setClassId(String.valueOf(rs.getInt(1)));
					String name=rs.getString(2);
					if(name!=null && name.length()!=0)
						name=name.replace("&nbsp;", " ");
					classSingle.setClassName(name);					
					classSingle.setClassIntro(rs.getString(3));
					listshow.add(classSingle);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mydb.closed();
			}
		}
		return listshow;
	}	
	
	public ClassForm OpClassSingleShow(String sql,Object[] params){
		ClassForm classform=null;
		DB mydb=new DB();
		mydb.doPstm(sql,params);
		ResultSet rs=mydb.getRs();
		try {
    		if(rs!=null&&rs.next()){
    			classform=new ClassForm();
    			classform.setClassId(String.valueOf(rs.getInt(1)));
    			classform.setClassName(rs.getString(2));    			
    			classform.setClassIntro(rs.getString(3));
    		}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mydb.closed();
		}
		return classform;
	}
	
	public List OpBoardListShow(String sql,Object[] params){
		List<BoardForm> listshow=null;
		DB mydb=new DB();
		mydb.doPstm(sql,params);
		ResultSet rs=mydb.getRs();
		if(rs!=null){
			try {
				listshow=new ArrayList<BoardForm>();
				while(rs.next()){
					BoardForm boardSingle=new BoardForm();
					boardSingle.setBoardId(String.valueOf(rs.getInt(1)));
					boardSingle.setBoardClassID(String.valueOf(rs.getInt(2)));
					boardSingle.setBoardName(rs.getString(3));
					boardSingle.setBoardMaster(rs.getString(4));
					boardSingle.setBoardPcard(rs.getString(5));					
					
					String bbsNum="";
					String undoNum="";
					String goodNum="";
					
					String sql1="";					
					Object[] params1={boardSingle.getBoardId()};
					ResultSet rs1;
					
					sql1="select count(bbs_id) from post where bbs_boardID="+boardSingle.getBoardId();
					mydb.doPstm(sql1, null);
					rs1=mydb.getRs();
					if(rs1!=null&&rs1.next()){
						bbsNum=String.valueOf(rs1.getInt(1));						
					}
					mydb.closed();
					
					sql1="SELECT COUNT(bbs_id) AS num FROM post WHERE (bbs_boardID = "+boardSingle.getBoardId()+") AND (bbs_id NOT IN (SELECT bbsAnswer_rootID FROM reply))";
					mydb.doPstm(sql1, null);
					rs1=mydb.getRs();
					if(rs1!=null&&rs1.next()){
						undoNum=String.valueOf(rs1.getInt(1));
					}
					mydb.closed();
				
					sql1="select count(bbs_id) from post where bbs_boardID="+boardSingle.getBoardId()+" and bbs_isGood='1'";
					mydb.doPstm(sql1, null);
					rs1=mydb.getRs();
					if(rs1!=null&&rs1.next()){
						goodNum=String.valueOf(rs1.getInt(1));
					}
					mydb.closed();
									
					boardSingle.setBoardBbsnum(bbsNum);
					boardSingle.setBoardBbsundonum(undoNum);
					boardSingle.setBoardBbsgoodnum(goodNum);					
					
					listshow.add(boardSingle);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mydb.closed();
			}
		}
		return listshow;
	}
	
	public BoardForm OpBoardSingleShow(String sql,Object[] params){
		BoardForm boardForm=null;
		DB mydb=new DB();
		mydb.doPstm(sql,params);
		ResultSet rs=mydb.getRs();
		try {
    		if(rs!=null&&rs.next()){
    			boardForm=new BoardForm();
    			boardForm.setBoardId(String.valueOf(rs.getInt(1)));
    			boardForm.setBoardClassID(String.valueOf(rs.getInt(2)));
    			boardForm.setBoardName(rs.getString(3));
    			boardForm.setBoardMaster(rs.getString(4));
    			boardForm.setBoardPcard(rs.getString(5));    			
    		}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mydb.closed();
		}
		return boardForm;		
	}
	
	public List<BbsForm> OpBbsListShow(String sql,Object[] params){
		DB mydb1=new DB();
		ResultSet rs=getRs(sql,params,mydb1);
		
		List<BbsForm> listshow=null;
		int i=1;
		if(rs!=null){
			listshow=new ArrayList<BbsForm>();
			DB mydb=new DB();
			try {
				while(rs.next()&&(!mark||i<=perR)){					
					BbsForm bbsform=new BbsForm();
					bbsform.setBbsId(String.valueOf(rs.getInt(1)));
					bbsform.setBbsBoardID(String.valueOf(rs.getInt(2)));					
					bbsform.setBbsTitle(rs.getString(3));
					bbsform.setBbsContent(rs.getString(4));
					bbsform.setBbsSender(rs.getString(5));
					bbsform.setBbsSendTime(Change.dateTimeChange(rs.getTimestamp(6)));
					bbsform.setBbsFace(rs.getString(7));
					bbsform.setBbsOpTime(Change.dateTimeChange(rs.getTimestamp(8)));
					bbsform.setBbsIsTop(rs.getString(9));
					bbsform.setBbsToTopTime(Change.dateTimeChange(rs.getTimestamp(10)));
					bbsform.setBbsIsGood(rs.getString(11));
					bbsform.setBbsToGoodTime(Change.dateTimeChange(rs.getTimestamp(12)));					
					
					String bbsId=bbsform.getBbsId();					
					String answerNum="0";
					String lastUpdateUser="";
					String lastUpdateTime="";					
					
					String sql1="select count(bbsAnswer_id) from reply where bbsAnswer_rootID="+bbsId;					
					Object[] params1={};					
					
					mydb.doPstm(sql1, params1);
					ResultSet rs1=mydb.getRs();					

					try {
						if(rs1!=null&&rs1.next())
							answerNum=(String.valueOf(rs1.getInt(1)));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					mydb.closed();
					String sql2="select bbsAnswer_sender, bbsAnswer_sendTime from reply where bbsAnswer_rootID = "+bbsId+" order by bbsAnswer_sendTime";
					
					mydb.doPstm(sql2, null);
					ResultSet rs2=mydb.getRs();
					
					try {
						if(rs2!=null&&rs2.next()){
							rs2.last();
							lastUpdateUser=rs2.getString(1);
							lastUpdateTime=Change.dateTimeChange(rs2.getTimestamp(2));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					mydb.closed();
					bbsform.setBbsAnswerNum(answerNum);
					bbsform.setBbsLastUpdateUser(lastUpdateUser);
					bbsform.setBbsLastUpdateTime(lastUpdateTime);					
					listshow.add(bbsform);					
					i++;
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			} finally{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mydb1.closed();
			}
		}
		return listshow;
}
	
	public List OpBbsAnswerListShow(String sql,Object[] params){
		DB mydb=new DB();
		ResultSet rs=getRs(sql,params,mydb);
		
		List listshow=null;
		if(rs!=null){
			try {				
				listshow=new ArrayList<BbsForm>();
				int i=1;
				while(rs.next()&&(!mark||i<=perR)){					
					BbsAnswerForm bbsAnswerform=new BbsAnswerForm();
					bbsAnswerform.setBbsAnswerId(String.valueOf(rs.getInt(1)));
					bbsAnswerform.setBbsAnswerRootID(String.valueOf(rs.getInt(2)));					
					bbsAnswerform.setBbsAnswerTitle(rs.getString(3));
					bbsAnswerform.setBbsAnswerContent(rs.getString(4));
					bbsAnswerform.setBbsAnswerSender(rs.getString(5));
					bbsAnswerform.setBbsAnswerSendTime(Change.dateTimeChange(rs.getTimestamp(6)));
					bbsAnswerform.setBbsFace(rs.getString(7));					
					listshow.add(bbsAnswerform);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mydb.closed();
			}
		}
		return listshow;
	}
	
	public BbsForm OpBbsSingleShow(String sql,Object[] params){
		BbsForm bbsform=null;
		DB mydb=new DB();
		mydb.doPstm(sql,params);
		ResultSet rs=mydb.getRs();
		try {
    		if(rs!=null&&rs.next()){
    			bbsform=new BbsForm();
				bbsform.setBbsId(String.valueOf(rs.getInt(1)));
				bbsform.setBbsBoardID(String.valueOf(rs.getInt(2)));				
				bbsform.setBbsTitle(rs.getString(3));
				bbsform.setBbsContent(rs.getString(4));
				bbsform.setBbsSender(rs.getString(5));
				bbsform.setBbsSendTime(Change.dateTimeChange(rs.getTimestamp(6)));				
				bbsform.setBbsFace(rs.getString(7));
				bbsform.setBbsOpTime(Change.dateTimeChange(rs.getTimestamp(8)));
				bbsform.setBbsIsTop(rs.getString(9));
				bbsform.setBbsToTopTime(Change.dateTimeChange(rs.getTimestamp(10)));
				bbsform.setBbsIsGood(rs.getString(11));
				bbsform.setBbsToGoodTime(Change.dateTimeChange(rs.getTimestamp(12)));				
    		}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mydb.closed();
		}
		return bbsform;		
	}
	
	public BbsAnswerForm OpBbsAnswerSingleShow(String sql,Object[] params){
		BbsAnswerForm bbsAnswerform=null;
		DB mydb=new DB();
		mydb.doPstm(sql,params);
		ResultSet rs=mydb.getRs();
        try{
        	if(rs!=null&&rs.next()){
        		bbsAnswerform=new BbsAnswerForm();
        		bbsAnswerform.setBbsAnswerId(String.valueOf(rs.getInt(1)));
        		bbsAnswerform.setBbsAnswerRootID(String.valueOf(rs.getInt(2)));					
        		bbsAnswerform.setBbsAnswerTitle(rs.getString(3));
        		bbsAnswerform.setBbsAnswerContent(rs.getString(4));
        		bbsAnswerform.setBbsAnswerSender(rs.getString(5));
        		bbsAnswerform.setBbsAnswerSendTime(Change.dateTimeChange(rs.getTimestamp(6)));
        		bbsAnswerform.setBbsFace(rs.getString(7));					
        	} 
        }catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mydb.closed();
		}
		return bbsAnswerform;
	}
	
	public List OpUserListShow(String sql,Object[] params){
		List userlist=new ArrayList();
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		ResultSet rs=mydb.getRs();
		try {
			if(rs!=null){
				while(rs.next()){
					UserForm userform=new UserForm();			
					userform.setId(String.valueOf(rs.getInt(1)));
					userform.setUserName(rs.getString(2));
					userform.setOldPassword(rs.getString(3));
					userform.setUserFace(rs.getString(4));
					userform.setUserSex(rs.getString(5));
					userform.setUserPhone(rs.getString(6));
					userform.setUserFB(rs.getString(7));
					userform.setUserEmail(rs.getString(8));
					userform.setUserFrom(rs.getString(9));
					userform.setUserAble(rs.getString(10));
					userlist.add(userform);
				}
				rs.close();					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mydb.closed();
		}
		return userlist;
	}
	
	public UserForm OpUserSingleShow(String sql,Object[] params){
		DB mydb=new DB();
		UserForm userform=null;
		mydb.doPstm(sql, params);
		ResultSet rs=mydb.getRs();
		try {
			if(rs!=null&&rs.next()){
				userform=new UserForm();			
				userform.setId(String.valueOf(rs.getInt(1)));
				userform.setUserName(rs.getString(2));
				userform.setOldPassword(rs.getString(3));
				userform.setUserFace(rs.getString(4));
				userform.setUserSex(rs.getString(5));
				userform.setUserPhone(rs.getString(6));
				userform.setUserFB(rs.getString(7));
				userform.setUserEmail(rs.getString(8));
				userform.setUserFrom(rs.getString(9));
				userform.setUserAble(rs.getString(10));	
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mydb.closed();
		}
		return userform;
	}
	
	public int OpUpdate(String sql,Object[] params){
		DB mydb=new DB();
		mydb.doPstm(sql,params);
		int i=mydb.getUpdate();
		mydb.closed();
		return i;
	}
	
	public void setMark(boolean mark){
		this.mark=mark;
	}
	
	public void setPageInfo(int perR,String currentP,String gowhich){
		this.perR=perR;
		this.currentP=currentP;
		this.gowhich=gowhich;
	}
	
	public ResultSet getRs(String sql,Object[] params,DB mydb){
		mydb.doPstm(sql,params);
		ResultSet rs=mydb.getRs();
		if(rs!=null){
			try {
				if(mark){
					OpCreatePage(rs);
					perR=page.getPerR();
					int currentP=page.getCurrentP();
					int row=(currentP-1)*perR+1;			
					rs.absolute(row);
					rs.previous();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public void OpCreatePage(ResultSet rs){
		page=new CreatePage();
		page.setPerR(perR);			
		try {
			if(rs!=null&&rs.next()){
				rs.last();					
				page.setAllR(rs.getRow());
				page.setAllP();
				page.setCurrentP(currentP);
				page.setPageInfo();
				page.setPageLink(gowhich);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public CreatePage getPage(){
		return this.page;
	}
}
