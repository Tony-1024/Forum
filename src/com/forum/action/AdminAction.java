package com.forum.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.forum.actionform.AbleForm;
import com.forum.actionform.BoardForm;
import com.forum.actionform.ClassForm;
import com.forum.actionform.UserForm;
import com.forum.dao.OpDB;
import com.forum.tools.Change;

public class AdminAction extends DispatchAction {	
	
	/** Top the post */
    public ActionForward setTopBbs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("mainPage","/pages/show/bbs/openRootShow.jsp");
		String forwardPath="error";		
		
		String bbsId=request.getParameter("bbsId");
		if(bbsId!=null&&!bbsId.equals("")){
			Date date=new Date();
			String today=Change.dateTimeChange(date);
			String sql="update post set bbs_isTop='1', bbs_toTopTime="+today+" where bbs_id="+bbsId;
//			Object[] params={today,bbsId};
			
			ActionMessages messages=new ActionMessages();
			
			OpDB myOp=new OpDB();
			int i=myOp.OpUpdate(sql, null);
			if(i<=0){
				forwardPath="error";
				messages.add("userOpR",new ActionMessage("luntan.bbs.top.E"));
			}
			else{
				forwardPath="success";
				messages.add("userOpR",new ActionMessage("luntan.bbs.top.S"));
			}			
			saveErrors(request,messages);
		}
		return mapping.findForward(forwardPath);
	}
    
    /** Set post tobe essence */
    public ActionForward setGoodBbs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String forwardPath="error";		
		
		String bbsId=request.getParameter("bbsId");
		if(bbsId!=null&&!bbsId.equals("")){
			Date date=new Date();
			String today=Change.dateTimeChange(date);
			String sql="update post set bbs_isGood='1', bbs_toTopTime='"+today+"' where bbs_id="+bbsId;
//			Object[] params={today,bbsId};
			
			ActionMessages messages=new ActionMessages();
			
			OpDB myOp=new OpDB();
			int i=myOp.OpUpdate(sql, null);
			if(i<=0){
				forwardPath="error";
				messages.add("userOpR",new ActionMessage("luntan.bbs.good.E"));
			}
			else{
				forwardPath="success";
				messages.add("userOpR",new ActionMessage("luntan.bbs.good.S"));
			}			
			saveErrors(request,messages);
		}
		return mapping.findForward(forwardPath);
	}
    
    /** delete replied post */
    public ActionForward deleteAnswerBbs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String forwardPath="error";
    	
    	String delbbsId=request.getParameter("delbbsAnswerId");
    	if(delbbsId!=null&&!delbbsId.equals("")){
    		String sql="delete from reply where bbsAnswer_id="+delbbsId;
//    		Object[] params={delbbsId};
    		
    		ActionMessages messages=new ActionMessages();
    		
    		OpDB myOp=new OpDB();
    		int i=myOp.OpUpdate(sql, null);
    		if(i<=0){
    			forwardPath="error";
    			messages.add("userOpR",new ActionMessage("luntan.bbs.deleteAnswerR.E"));
    		}
    		else{
    			forwardPath="success";
    			messages.add("userOpR",new ActionMessage("luntan.bbs.deleteAnswerR.S"));    		
    		}
    		saveErrors(request,messages);    		
    	}
		return mapping.findForward(forwardPath);
    }
    
    /** view category list */
    public ActionForward getClassList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	HttpSession session=request.getSession();
    	session.setAttribute("backMainPage","../class/classListShow.jsp");
    	
    	OpDB myOp=new OpDB();		
		List classList=myOp.OpClassListShow();
		session.setAttribute("backClassList",classList);
    	return mapping.findForward("success");
    }

    /** update category list */
    public ActionForward modifyClass(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){    	
		HttpSession session=request.getSession();
		session.setAttribute("backMainPage","../class/classModify.jsp");
		
		String forwardPath="";
    	String classId=request.getParameter("classId");
    	if(classId==null)
    		classId="";
		
    	OpDB myOp=new OpDB();    	
    	ClassForm classForm=(ClassForm)form;
    	String validate=request.getParameter("validate");    	
    	if(validate==null||validate.equals("")||!validate.equals("yes")){
    		forwardPath="showModifyJSP";
    		String sql="select * from category where class_id="+classId;
//    		Object[] params={classId};
    		
    		ClassForm classSingle=myOp.OpClassSingleShow(sql, null);
    		classForm.setClassId(classSingle.getClassId());
    		classForm.setClassName(classSingle.getClassName());
    		classForm.setClassIntro(classSingle.getClassIntro()); 
    	}
    	else{
    		ActionMessages messages=new ActionMessages();
    		classId	= classForm.getClassId();
    		String 	className=Change.HTMLChange(classForm.getClassName());
    		String 	classIntro=Change.HTMLChange(classForm.getClassIntro());
    		
    		String sql="update category set class_name="+className+",class_intro="+classIntro+" where class_id="+classId;
//    		Object[] paramsMo={className,classIntro,classId};
    		int i=myOp.OpUpdate(sql, null);
    		if(i<=0){
    			forwardPath="error";
    			messages.add("adminOpR",new ActionMessage("luntan.admin.modify.class.E"));
    		}
    		else{
    			session.setAttribute("backMainPage","../class/classListShow.jsp");
    			forwardPath="success";
    			messages.add("adminOpR",new ActionMessage("luntan.admin.modify.class.S"));
    		}
    		saveErrors(request,messages); 
    	}    	
    	return mapping.findForward(forwardPath);
    }
    
    /** add category list*/
    public ActionForward addClass(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	HttpSession session=request.getSession();
    	session.setAttribute("backMainPage","../class/classAdd.jsp");
    	
    	String validate=request.getParameter("validate");
    	if(validate==null||validate.equals("")||!validate.equals("yes")){
    		return mapping.findForward("result");
    	}
    	else{
    		ActionMessages messages=new ActionMessages();
    		ClassForm classForm=(ClassForm)form;
    		
    		String className=Change.HTMLChange(classForm.getClassName());
    		String classIntro=Change.HTMLChange(classForm.getClassIntro());
    		
    		String sql="";
    		Object[] params=null;
    		OpDB myOp=new OpDB();
    		
    		sql="select * from category where class_name='"+className+"'";
//    		params=new Object[1];
//    		params[0]=className;
    		
    		if(myOp.OpClassSingleShow(sql, null)!=null){
    			messages.add("adminOpR",new ActionMessage("luntan.admin.add.class.exist",className));
    		}
    		else{
    			sql="insert into category values(nextval('all_ids'),'"+className+"','"+classIntro+"')";
//    			params=new Object[2];
//    			params[0]=className;
//    			params[1]=classIntro;
    			
    			int i=myOp.OpUpdate(sql, null);
    			if(i<=0){
    				messages.add("adminOpR",new ActionMessage("luntan.admin.add.class.E"));
    			}
    			else{
    				classForm.clear();
    				messages.add("adminOpR",new ActionMessage("luntan.admin.add.class.S"));
    			}
    		}
    		saveErrors(request,messages);    		
    		return mapping.findForward("result");
    	}    	
    }
    
    /** delete the category */
    public ActionForward deleteClass(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){    	
    	String classId=request.getParameter("classId");
    	String forwardPath="";
    	ActionMessages messages=new ActionMessages();
    	if(classId==null||classId.equals(""))
		{
    		System.out.println("Error to delete category!");
    		forwardPath="error";
    		messages.add("adminOpR",new ActionMessage("luntan.admin.delete.class.E"));
    		saveErrors(request,messages);
        	return mapping.findForward(forwardPath);
		}
    	String sql="delete from category where class_id="+classId;
//    	Object[] params={classId};
    	
    	OpDB myOp=new OpDB();
    	int i=myOp.OpUpdate(sql, null);
    	
    	
    	if(i<=0){
    		System.out.println("Fail to delete category!");
    		forwardPath="error";
    		messages.add("adminOpR",new ActionMessage("luntan.admin.delete.class.E"));
    	}
    	else{
    		System.out.println("Success to delete category!");
    		forwardPath="success";
    		messages.add("adminOpR",new ActionMessage("luntan.admin.delete.class.S"));
    	}
    	saveErrors(request,messages);
    	return mapping.findForward(forwardPath);
    }
    
    /** look over the boards */
    public ActionForward getBoardList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){    	
    	HttpSession session=request.getSession();
    	session.setAttribute("backMainPage","../board/boardListShow.jsp");

    	List boardList=null;
    	OpDB myOp=new OpDB();    	
    	String getType=request.getParameter("type");
    	
    	if(getType==null||getType.equals("")||!getType.equals("show")){
    		List classList=myOp.OpClassListShow();
    		session.setAttribute("backClassList",classList);    		
    	}    	
    	else{    		
    		ClassForm classform=(ClassForm)form;
    		String classId=classform.getClassId();
    		
    		if(classId==null||classId.equals("")){
    			classId=(String)session.getAttribute("boardListId");
    			classform.setClassId(classId);
    		}
    		else{
    			session.setAttribute("boardListId",classId);
    		}
    		
    		String sql="select * from board where board_classID="+classId;
//    		Object[] params={classId};		
    		boardList=myOp.OpBoardListShow(sql,null);
    	}
    	session.setAttribute("backBoardList",boardList);    		
    	return mapping.findForward("success");
    }
    
    /** modify boards */
    public ActionForward modifyBoard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	HttpSession session=request.getSession();
    	session.setAttribute("backMainPage","../board/boardModify.jsp");
    	
    	OpDB myOp=new OpDB();
    	List classList=myOp.OpClassListShow();
    	session.setAttribute("backClassList",classList);
    	
		String forwardPath="";
		String boardId=request.getParameter("boardId");
		if(boardId==null)
			boardId="";
		
		BoardForm boardForm=(BoardForm)form;
    	String validate=request.getParameter("validate");
    	if(validate==null||validate.equals("")||!validate.equals("yes")){
    		forwardPath="showModifyJSP";    		
    		String sql="select * from board where board_id="+boardId;
//    		Object[] params={boardId};    		
    		BoardForm select=myOp.OpBoardSingleShow(sql, null); 
    		
    		boardForm.setBoardId(select.getBoardId());
    		boardForm.setBoardClassID(select.getBoardClassID());
    		boardForm.setBoardName(select.getBoardName());
    		boardForm.setBoardMaster(select.getBoardMaster());
    		boardForm.setBoardPcard(select.getBoardPcard());
    	}
    	else{    		
    		ActionMessages messages=new ActionMessages();    		

    		String 	boardClassId=boardForm.getBoardClassID();
    		boardId = boardForm.getBoardId();
    		String 	boardName=Change.HTMLChange(boardForm.getBoardName());
    		String 	boardMaster=Change.HTMLChange(boardForm.getBoardMaster());
    		String 	boardPcard=Change.HTMLChange(boardForm.getBoardPcard());    	
    		
    		String sql="select * from users where user_name='"+boardMaster+"'";
//    		Object[] paramsS={boardMaster};
    		
    		UserForm userform=myOp.OpUserSingleShow(sql, null);
    		if(userform==null){
    			forwardPath="error";
    			messages.add("adminOpR",new ActionMessage("luntan.admin.update.board.no.user",boardMaster));
    		}
    		else if(userform.getUserAble().equals("0")){
    			forwardPath="error";
    			messages.add("adminOpR",new ActionMessage("luntan.admin.update.board.user.able",boardMaster));
    		}
    		else{
    			sql="update board set board_classID="+boardClassId+",board_name='"+boardName+"',board_master='"+boardMaster+"',board_pcard='"+boardPcard+"' where board_id="+boardId;
//    			Object[] paramsM={boardClassId,boardName,boardMaster,boardPcard,boardId};
    			
    			int i=myOp.OpUpdate(sql, null);
    			if(i<=0){
    				forwardPath="error";
    				messages.add("adminOpR",new ActionMessage("luntan.admin.modify.board.E"));
    			}
    			else{
    				forwardPath="success";
    				messages.add("adminOpR",new ActionMessage("luntan.admin.modify.board.S"));
    			}			
    		}
    		saveErrors(request,messages); 		
    	}
    	return mapping.findForward(forwardPath);    		
    }
    
    /** add board */
    public ActionForward addBoard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	HttpSession session=request.getSession();
    	session.setAttribute("backMainPage","../board/boardAdd.jsp");

    	OpDB myOp=new OpDB();
    	
    	String validate=request.getParameter("validate");
    	if(validate==null||validate.equals("")||!validate.equals("yes")){
    		List classList=myOp.OpClassListShow();
    		session.setAttribute("backClassList",classList);
    		return mapping.findForward("result");
    	}
    	else{
    		BoardForm boardform=(BoardForm)form;
    		String classId=boardform.getBoardClassID();
    		String boardName=Change.HTMLChange(boardform.getBoardName());
    		String boardMaster=Change.HTMLChange(boardform.getBoardMaster());
    		String boardPcard=Change.HTMLChange(boardform.getBoardPcard());
    		
    		ActionMessages messages=new ActionMessages();    		
    		String sql="select * from board where board_name='"+boardName+"' and board_classID="+classId;
//    		Object[] params={boardName,classId};
    		
    		if(myOp.OpBoardSingleShow(sql, null)!=null){
    			messages.add("adminOpR",new ActionMessage("luntan.admin.add.board.exist",boardName));
    		}
    		else{
    			sql="select * from users where user_name='"+boardMaster+"'";
//    			Object[] params1={boardMaster};
    			
    			UserForm userform=myOp.OpUserSingleShow(sql, null);
    			if(userform==null){
    				messages.add("adminOpR",new ActionMessage("luntan.admin.update.board.no.user",boardMaster));
    			}
    			else if(userform.getUserAble().equals("0")){
    				messages.add("adminOpR",new ActionMessage("luntan.admin.update.board.user.able",boardMaster));
    			}
    			else{
    				sql="insert into board values(nextval('all_ids'),"+classId+",'"+boardName+"','"+boardMaster+"','"+boardPcard+"')";
//    				Object[] params2={classId,boardName,boardMaster,boardPcard};
    				
    				int i=myOp.OpUpdate(sql, null);
    				if(i<=0){
    					messages.add("adminOpR",new ActionMessage("luntan.admin.add.board.E"));
    				}
    				else{
    					boardform.clear();
    					messages.add("adminOpR",new ActionMessage("luntan.admin.add.board.S"));
    				}    			
    			}    			
    		}    		
    		saveErrors(request,messages);
    		return mapping.findForward("result");
    	}
    }
    
    /**delete board */
    public ActionForward deleteBoard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	String boardId=request.getParameter("boardId");
    	if(boardId==null||boardId.equals(""))
    		boardId="-1";
    	String sql="delete from board where board_id="+boardId;
//    	Object[] params={boardId};
    	
    	OpDB myOp=new OpDB();
    	int i=myOp.OpUpdate(sql, null);
    	
    	ActionMessages messages=new ActionMessages();
    	String forwardPath="";
    	
    	if(i<=0){
    		System.out.println("Fail to delete board!");
    		forwardPath="error";
    		messages.add("adminOpR",new ActionMessage("luntan.admin.delete.board.E"));
    	}
    	else{
    		System.out.println("Success to delete board!");
    		forwardPath="success";
    		messages.add("adminOpR",new ActionMessage("luntan.admin.delete.board.S"));
    	}
    	saveErrors(request,messages);
    	return mapping.findForward(forwardPath);
    }
    
    /** view user */
    public ActionForward getUserList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	HttpSession session=request.getSession();
    	session.setAttribute("backMainPage","../user/userListShow.jsp");    	
    	
    	String getType=request.getParameter("type");
    	if(getType==null||getType.equals("")||!getType.equals("show")){
    		List ableList=new ArrayList();
    		ableList.add(new LabelValueBean("All","all"));
    		ableList.add(new LabelValueBean("Administrator","2"));
    		ableList.add(new LabelValueBean("Board Manager","1"));
    		ableList.add(new LabelValueBean("User","0"));
    		session.setAttribute("backListAble",ableList);    		
    	}
    	else{
    		AbleForm ableform=(AbleForm)form;
    		String able=ableform.getShowAble();
    		
    		if(able==null||able.equals("")){
    			able=(String)session.getAttribute("userAble");
    			ableform.setShowAble(able);
    		}
    		else
    			session.setAttribute("userAble",able);
    		
    		String sql="";
//    		Object[] params=null;
    		if(able.equals("all")){
    			sql="select * from users order by user_able DESC";
    		}
    		else{
    			sql="select * from users where user_able='"+able+"'";
//    			params=new Object[1];
//    			params[0]=able;    			                  
    		}
    		
    		OpDB myOp=new OpDB();
    		List userlist=myOp.OpUserListShow(sql, null);
    		request.setAttribute("backUserList",userlist);
    	}    	
    	return mapping.findForward("success");    	
    }
    
    /**update user */
    public ActionForward modifyUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	HttpSession session=request.getSession();
    	session.setAttribute("backMainPage","../user/userModify.jsp");
    	
    	List backUserAble=new ArrayList();    	
    	backUserAble.add(new LabelValueBean("Administrator","2"));
    	backUserAble.add(new LabelValueBean("Board Manager","1"));
    	backUserAble.add(new LabelValueBean("User","0"));
    	session.setAttribute("backUserAble",backUserAble);    		

		String forwardPath="";		
		String userId=request.getParameter("userId");
		if(userId==null)
			userId="";
    	
		UserForm userForm=(UserForm)form;
    	String validate=request.getParameter("validate");
    	if(validate==null||validate.equals("")||!validate.equals("yes")){        	
    		forwardPath="showModifyJSP";    		
    		String sql="select * from users where id="+userId;
//        	Object[] params={userId};
        	
        	OpDB myOp=new OpDB();
        	UserForm select=myOp.OpUserSingleShow(sql, null);
        	
        	userForm.setId(select.getId());
        	userForm.setUserName(select.getUserName());
        	userForm.setOldPassword(select.getOldPassword()); 
        	userForm.setUserFace(select.getUserFace());
        	userForm.setUserSex(select.getUserSex());
        	userForm.setUserPhone(select.getUserPhone());
        	userForm.setUserFB(select.getUserFB());
        	userForm.setUserEmail(select.getUserEmail());
        	userForm.setUserFrom(select.getUserFrom());
        	userForm.setUserAble(select.getUserAble());        	
    	}
    	else{    		
    		ActionMessages messages=new ActionMessages();
    		
    		userId = userForm.getId();
    		String userName=Change.HTMLChange(userForm.getUserName());
    		String userPassword=Change.HTMLChange(userForm.getUserPassword());    	
    		String userFace=userForm.getUserFace();
    		String userSex=userForm.getUserSex();
    		String userPhone=userForm.getUserPhone();
    		String userFB=userForm.getUserFB();
    		String userEmail=userForm.getUserEmail();
    		String userFrom=Change.HTMLChange(userForm.getUserFrom());
    		String userAble=userForm.getUserAble();
    		
    		String sql="update users set user_name='"+userName+"',user_password='"+userPassword+"',user_face='"+userFace+"',user_sex='"+userSex+"',user_phone='"+userPhone+"',user_FB='"+userFB+"',user_email='"+userEmail+"',user_from='"+userFrom+"',user_able='"+userAble+"' where id='"+userId+"'";
//    		Object[] params={userName,userPassword,userFace,userSex,userPhone,userFB,userEmail,userFrom,userAble,userId};
    		
    		OpDB myOp=new OpDB();
    		int i=myOp.OpUpdate(sql, null);    		

    		if(i<=0){
    			forwardPath="error";
    			messages.add("adminOpR",new ActionMessage("luntan.admin.modify.user.E"));
    		}
    		else{
    			System.out.println("success to update user!");
    			forwardPath="success";
    			messages.add("adminOpR",new ActionMessage("luntan.admin.modify.user.S"));
    		}
    		saveErrors(request,messages);
    	}
    	return mapping.findForward(forwardPath);
    }
    
    /**delete user */
    public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    	String userId=request.getParameter("userId");
    	if(userId==null||userId.equals(""))
    		userId="-1";
    	String sql="delete from users where id="+userId;
//    	Object[] params={userId};
    	
    	OpDB myOp=new OpDB();
    	int i=myOp.OpUpdate(sql, null);
    	
    	ActionMessages messages=new ActionMessages();
    	String forwardPath="";
    	
    	if(i<=0){
    		System.out.println("Fail to delete user!");
    		forwardPath="error";
    		messages.add("adminOpR",new ActionMessage("luntan.amdin.delete.user.E"));
    	}
    	else{
    		System.out.println("Success to delete user!");
    		forwardPath="success";
    		messages.add("adminOpR",new ActionMessage("luntan.amdin.delete.user.S"));
    	}
    	saveErrors(request,messages);
    	return mapping.findForward(forwardPath);
    }
 }
