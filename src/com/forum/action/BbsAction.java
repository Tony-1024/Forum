package com.forum.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import com.forum.actionform.BbsAnswerForm;
import com.forum.actionform.BbsForm;
import com.forum.actionform.BoardForm;
import com.forum.actionform.UserForm;
import com.forum.dao.OpDB;
import com.forum.model.CreatePage;
import com.forum.tools.Change;

public class BbsAction extends MySuperAction {
	
	public ActionForward rootListShow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){		
		super.setParams(request);
		HttpSession session=request.getSession();
		session.setAttribute("mainPage","/pages/show/bbs/listRootShow.jsp");
		
		String classId=request.getParameter("classId");
		String boardId=request.getParameter("boardId");
		if(classId==null||classId.equals(""))
			classId=(String)session.getAttribute("classId");
		else
			session.setAttribute("classId",classId);			
		if(boardId==null||boardId.equals(""))
			boardId=(String)session.getAttribute("boardId");
		else
			session.setAttribute("boardId",boardId);		
		
		Vector<LabelValueBean> jumpBoard=new Vector<LabelValueBean>();		
		List boardlist=(ArrayList)session.getAttribute("class"+classId);
		if(boardlist!=null&&boardlist.size()!=0){			
			for(int i=0;i<boardlist.size();i++){
				BoardForm boardSingle=(BoardForm)boardlist.get(i);
				jumpBoard.add(new LabelValueBean(boardSingle.getBoardName(),boardSingle.getBoardId()));
				if(boardId.equals(boardSingle.getBoardId())){ 
					session.setAttribute("boardMaster",boardSingle.getBoardMaster()); 
					session.setAttribute("boardPcard",boardSingle.getBoardPcard());	 
				}
			}
		}
		session.setAttribute("jumpBoard",jumpBoard);
		
		String sql="";
//		Object[] params={};
		OpDB myOp=new OpDB();
		
		myOp.setMark(false); 
		sql="select * from post where bbs_boardID="+boardId+" and bbs_isTop='1' order by bbs_toTopTime DESC";
		List topbbslist=myOp.OpBbsListShow(sql, null);
		session.setAttribute("topbbslist",topbbslist);
		
		int perR=5;
		String currentP=request.getParameter("showpage");
		if(currentP==null||currentP.equals(""))
			currentP=(String)session.getAttribute("currentP");
		else
			session.setAttribute("currentP",currentP);
		String gowhich="user/listShow.do?method=rootListShow";	
		
		myOp.setMark(true); 
		myOp.setPageInfo(perR, currentP, gowhich); 
		
		sql="select * from post where bbs_boardID="+boardId+" and (bbs_isTop='0' or bbs_isGood='1') order by bbs_opTime DESC";
		List otherbbslist=myOp.OpBbsListShow(sql, null);		
		CreatePage page=myOp.getPage();
		
		session.setAttribute("otherbbslist",otherbbslist);
		session.setAttribute("page",page);
		
		return mapping.findForward("success");
	}
	
	/** �鿴ĳ������ */
	public ActionForward openShow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){		
		HttpSession session=request.getSession();
		session.setAttribute("mainPage","/pages/show/bbs/openRootShow.jsp");
		
		String bbsId=request.getParameter("bbsId");
		if(bbsId==null||bbsId.equals(""))
			bbsId=(String)session.getAttribute("bbsId");
		else
			session.setAttribute("bbsId",bbsId);
		
		String sql="";
//		Object[] params={};
		OpDB myOp=new OpDB();
		
		sql="select * from post where bbs_id="+bbsId;
		BbsForm bbsRootSingle=myOp.OpBbsSingleShow(sql, null);
		session.setAttribute("bbsRootSingle",bbsRootSingle);
		
		String asker=bbsRootSingle.getBbsSender();
		sql="select * from users where user_name='"+asker+"'";
//		params[0]=asker;
		UserForm askUser=myOp.OpUserSingleShow(sql, null);
		session.setAttribute("askUser",askUser);		
		
		int perR=6;
		String currentP=request.getParameter("showpage");
		if(currentP==null||currentP.equals(""))
			currentP=(String)session.getAttribute("currentPopen");
		else
			session.setAttribute("currentPopen",currentP);
		String gowhich="user/openShow.do?method=openShow";	
		
		myOp.setMark(true); 
		myOp.setPageInfo(perR, currentP, gowhich); 
		
		sql="select * from reply where bbsAnswer_rootID="+bbsId+" order by bbsAnswer_sendTime";
//		params[0]=bbsId;
		List answerbbslist=myOp.OpBbsAnswerListShow(sql, null);
		CreatePage page=myOp.getPage();
		
		session.setAttribute("answerbbslist",answerbbslist);		
		session.setAttribute("page",page);
		
		Map answerMap=new HashMap();
		for(int i=0;i<answerbbslist.size();i++){			
			String answerer=((BbsAnswerForm)answerbbslist.get(i)).getBbsAnswerSender();
			if(!answerMap.containsKey(answerer)){
//				params[0]=answerer;
				sql="select * from users where user_name='"+answerer+"'";
				UserForm answerUser=myOp.OpUserSingleShow(sql, null);
				answerMap.put(answerer,answerUser);				
			}
		}
		session.setAttribute("answerMap",answerMap);
	
		return mapping.findForward("success");
	}
	
	public ActionForward goodListShow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		super.setParams(request);
		HttpSession session=request.getSession();
		session.setAttribute("mainPage","/pages/show/bbs/goodListShow.jsp");
		OpDB myOp=new OpDB();
		
		int perR=5;
		String currentP=request.getParameter("showpage");
		if(currentP==null||currentP.equals(""))
			currentP=(String)session.getAttribute("currentPgood");
		else
			session.setAttribute("currentPgood",currentP);
		String gowhich="user/goodListShow.do?method=goodListShow";	
		
		myOp.setMark(true); 
		myOp.setPageInfo(perR, currentP, gowhich); 
		
		String sql="select * from post where bbs_isGood='1' order by bbs_toGoodTime DESC";
		List goodlist=myOp.OpBbsListShow(sql,null);
		CreatePage page=myOp.getPage();
		
		session.setAttribute("goodlist",goodlist);
		session.setAttribute("page",page);
		
		return mapping.findForward("success");
	}
	
	public ActionForward addBbs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();		
		session.setAttribute("mainPage","/pages/add/bbsAdd.jsp");
		
		String validate=request.getParameter("validate");
		if(validate==null||validate.equals("")||!validate.equals("yes")){
			return mapping.findForward("showAddJSP");
		}
		else{			
			BbsForm bbsForm=(BbsForm)form;
			
			String	boardId=(String)session.getAttribute("boardId");
			String 	bbsTitle=Change.HTMLChange(bbsForm.getBbsTitle());
			String 	bbsContent=Change.HTMLChange(bbsForm.getBbsContent());
			String 	bbsSender=((UserForm)session.getAttribute("logoner")).getUserName();
			String 	bbsSendTime=Change.dateTimeChange(new Date());
			String 	bbsFace=bbsForm.getBbsFace();
			String 	bbsOpTime=bbsSendTime;
			String 	bbsIsTop="0";
			String 	bbsToTopTime="";
			String 	bbsIsGood="0";
			String 	bbsToGoodTime="";
			
			bbsTitle=StringEscapeUtils.escapeSql(bbsTitle);
			bbsContent=StringEscapeUtils.escapeSql(bbsContent);
			
			String sql="insert into post values(nextval('all_ids'),"+boardId+",'"+bbsTitle+"','"+bbsContent+"','"+bbsSender+"',now(),'"+bbsFace+"','"+bbsOpTime+"','"+bbsIsTop+"',null,'"+bbsIsGood+"',null)";
//			Object[] params={boardId,bbsTitle,bbsContent,bbsSender,bbsFace,bbsOpTime,bbsIsTop,bbsIsGood};
			
			ActionMessages messages=new ActionMessages();			
			OpDB myOp=new OpDB();
			int i=myOp.OpUpdate(sql,null);
			if(i<=0){
				messages.add("userOpR",new ActionMessage("luntan.bbs.add.E"));
				saveErrors(request,messages);
				return mapping.findForward("error");				
			}
			else{
				session.setAttribute("currentP","1");
				messages.add("userOpR",new ActionMessage("luntan.bbs.add.S"));
				bbsForm.clear();
				saveErrors(request,messages);
				return mapping.findForward("success");
			}			
		}	
	}
	
	public ActionForward answerBbs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();		
		BbsAnswerForm bbsAnswerForm=(BbsAnswerForm)form;
		
		String  rootId=(String)session.getAttribute("bbsId");		
		String 	bbsTitle=Change.HTMLChange(bbsAnswerForm.getBbsAnswerTitle());
		String 	bbsContent=Change.HTMLChange(bbsAnswerForm.getBbsAnswerContent());
		String 	bbsSender=((UserForm)session.getAttribute("logoner")).getUserName();
		String 	bbsSendTime=Change.dateTimeChange(new Date());
		String 	bbsFace=bbsAnswerForm.getBbsFace();
		
		bbsTitle=StringEscapeUtils.escapeSql(bbsTitle);
		bbsContent=StringEscapeUtils.escapeSql(bbsContent);
		
		String sql="insert into reply values(nextval('all_ids'),"+rootId+",'"+bbsTitle+"','"+bbsContent+"','"+bbsSender+"','"+bbsSendTime+"','"+bbsFace+"')";
		Object[] params=new Object[6];
		
/*		params[0]=rootId;
		params[1]=bbsTitle;
		params[2]=bbsContent;
		params[3]=bbsSender;
		params[4]=bbsSendTime;
		params[5]=bbsFace;*/
		
		ActionMessages messages=new ActionMessages();
		String forwardPath="";
		
		OpDB myOp=new OpDB();
		int i=myOp.OpUpdate(sql, null);
		if(i<=0){
			forwardPath="error";
			messages.add("userOpR",new ActionMessage("luntan.bbs.answerR.E"));
		}
		else{
			forwardPath="success";
			messages.add("userOpR",new ActionMessage("luntan.bbs.answerR.S"));			
			bbsAnswerForm.clear();			
		}		
		saveErrors(request,messages);
		return mapping.findForward(forwardPath);
	}
	
	 /**  @throws UnsupportedEncodingException */
	public ActionForward toFirstBbs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		HttpSession session=request.getSession();			
		UserForm logoner=(UserForm)session.getAttribute("logoner");		
		
		String bbsId=request.getParameter("bbsId");	 
		String bbsSender=request.getParameter("bbsSender");
		bbsSender=new String(bbsSender.getBytes("utf-8"));	
		String time=Change.dateTimeChange(new Date());
		String lognerAble=logoner.getUserAble();
		String lognerName=logoner.getUserName();
		String master=(String)session.getAttribute("boardMaster");
		
		if(bbsId==null)
			bbsId="-1";
		if(bbsSender==null)
			bbsSender="";	
		
		String forwardPath="";
		ActionMessages messages=new ActionMessages();
		
		if(lognerAble.equals("2")||lognerName.equals(master)||lognerName.equals(bbsSender)){
			if(bbsId!=null&&!bbsId.equals("")){
//				Object[] params={time,bbsId};
				String sql="update post set bbs_opTime="+time+" where bbs_id="+bbsId;
				OpDB myOp=new OpDB();
				int i=myOp.OpUpdate(sql,null);
				if(i<=0){
					forwardPath="error";
					messages.add("userOpR",new ActionMessage("luntan.bbs.first.E"));					
				}
				else{
					forwardPath="success";
					messages.add("userOpR",new ActionMessage("luntan.bbs.first.S"));					
				}				
			}
			else{
				forwardPath="error";
			}
		}
		else{
			forwardPath="error";
			messages.add("userOpR",new ActionMessage("luntan.bbs.first.N"));
		}
		saveErrors(request,messages);
		return mapping.findForward(forwardPath);
	}
	
	/** delete post
	 * @throws UnsupportedEncodingException */
	public ActionForward deleteRootBbs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{		
		HttpSession session=request.getSession();		
		UserForm logoner=(UserForm)session.getAttribute("logoner");
		
		String bbsId=request.getParameter("bbsId");	
		String bbsSender=request.getParameter("bbsSender");
		bbsSender=new String(bbsSender.getBytes("utf-8"));
		String lognerAble=logoner.getUserAble();
		String lognerName=logoner.getUserName();
		String master=(String)session.getAttribute("boardMaster");
		
		if(bbsId==null)
			bbsId="-1";
		if(bbsSender==null)
			bbsSender="";		
		
		ActionMessages messages=new ActionMessages();
		
		if(lognerAble.equals("2")||lognerName.equals(master)||lognerName.equals(bbsSender)){
			if(bbsId!=null&&!bbsId.equals("")){						
				String sql="delete from post where bbs_id="+bbsId;				
//				Object[] params={bbsId};
				
				OpDB myOp=new OpDB();
				int i=myOp.OpUpdate(sql,null);
				if(i<=0){
					messages.add("userOpR",new ActionMessage("luntan.bbs.deleteRoot.E"));
					saveErrors(request,messages);					
				}
				else{
					messages.add("userOpR",new ActionMessage("luntan.bbs.deleteRoot.S"));
					saveErrors(request,messages);
					ActionForward forward=new ActionForward("/"+session.getAttribute("servletPath")+"?method="+session.getAttribute("method"));		//��Ϊ���ص�ҳ���������������������Է��ص���ͼҪ�ڳ����ж�ָ̬��
					return forward;
				}				
			}
			return mapping.findForward("error");
		}
		else{
			messages.add("userOpR",new ActionMessage("luntan.bbs.deleteRoot.N"));
			saveErrors(request,messages);
			return mapping.findForward("error");
		}		
	}
	
	public ActionForward getUserSingle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		
		String userName=request.getParameter("userName");
		if(userName==null)
			userName="";		
		/*try {
			userName=new String(userName.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			userName="";
			e.printStackTrace();
		}*/
		
		ActionMessages messages=new ActionMessages();
		String forwardPath="";
		
		String sql="select * from users where user_name='"+userName+"'";
//		Object[] params={userName};
		
		OpDB myOp=new OpDB();
		UserForm bbsUser=myOp.OpUserSingleShow(sql, null);		
		
		if(bbsUser==null){
			forwardPath="error";
			messages.add("userOpR",new ActionMessage("luntan.bbs.sender.E"));
		}
		else{
			forwardPath="success";
			session.setAttribute("bbsUserSingle",bbsUser);
			session.setAttribute("mainPage","/pages/show/user/bbsUserSingle.jsp");
		}
		saveErrors(request,messages);
		return mapping.findForward(forwardPath);
	}	
}
