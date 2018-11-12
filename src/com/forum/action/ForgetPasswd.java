package com.forum.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.forum.actionform.UserForm;
import com.forum.dao.OpDB;
import com.forum.tools.MailUtil;

public class ForgetPasswd extends DispatchAction{
	
	public ActionForward getPasswd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		ActionMessages messages=new ActionMessages();
		String userName=request.getParameter("userName");
		if(userName==null || userName.trim().length()==0){
			messages.add("loginR",new ActionMessage("luntan.bbs.getpasswd.no_username.E"));
			saveErrors(request,messages);
			return mapping.findForward("getPasswd");
		}
		
		String sql="select * from users where user_name='"+userName+"'";
		
		OpDB myOp=new OpDB();
		UserForm logoner=myOp.OpUserSingleShow(sql, null);
		if(logoner!=null){
			//send email to user
			String emailAddr = logoner.getUserEmail();
			if(emailAddr==null || emailAddr.trim().length()==0){
				messages.add("loginR",new ActionMessage("luntan.bbs.getpasswd.no_email.E"));
				saveErrors(request,messages);
				return mapping.findForward("getPasswd");
			}
			// send email to user
			MailUtil.sendEMail(emailAddr, logoner.getOldPassword());
			
			messages.add("loginR",new ActionMessage("luntan.bbs.getpasswd.S"));
			saveErrors(request,messages);
			return (mapping.findForward("getPasswd"));
		}
		else{			
			messages.add("loginR",new ActionMessage("luntan.bbs.getpasswd.no_username.E"));
			saveErrors(request,messages);
			return mapping.findForward("getPasswd");
		}		
	}

}
