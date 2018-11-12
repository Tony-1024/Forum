package com.forum.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.forum.actionform.SearchForm;
import com.forum.dao.OpDB;
import com.forum.model.CreatePage;
import com.forum.tools.Change;

public class SearchAction extends MySuperAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.setParams(request);		
		HttpSession session=request.getSession();
		session.setAttribute("mainPage","/pages/show/bbs/searchListShow.jsp");		
		
		SearchForm searchform=(SearchForm)form;
		String subsql=searchform.getSubsql();
		String sqlvalue=searchform.getSqlvalue();
		String searchType=searchform.getSearchType();
		
		String validate=request.getParameter("validate");
		if(validate==null||validate.equals("")||!validate.equals("yes")){
			subsql=(String)session.getAttribute("subsql");
			sqlvalue=(String)session.getAttribute("sqlvalue");
			sqlvalue=(String)session.getAttribute("sqlvalue");
			searchform.setSubsql(subsql);
			searchform.setSqlvalue(sqlvalue);
			searchform.setSearchType(searchType);
		}
		else{
			session.setAttribute("subsql",subsql);
			sqlvalue=Change.HTMLChange(searchform.getSqlvalue());
			session.setAttribute("sqlvalue",sqlvalue);
			session.setAttribute("searchType",searchType);
		}
		
		String opname="";
		String param="";
		if(searchType.equals("all")){
			opname=" = ";
			param=sqlvalue;			
		}
		else{
			opname=" like ";
			param="%"+sqlvalue+"%";
		}
		
		int perR=5;
		String currentP=request.getParameter("showpage");
		if(currentP==null||currentP.equals(""))
			currentP=(String)session.getAttribute("currentPsh");
		else
			session.setAttribute("currentPsh",currentP);
		String gowhich="user/search.do";	
		
		OpDB myOp=new OpDB();
		myOp.setMark(true);
		myOp.setPageInfo(perR, currentP, gowhich);
		
		String sql="select * from post where "+subsql+opname+"'"+param+"'";
		Object[] params={};		

		List searchlist=myOp.OpBbsListShow(sql,params);
		CreatePage page=myOp.getPage();
		
		session.setAttribute("searchlist",searchlist);
		session.setAttribute("page",page);
		
		return mapping.findForward("success");
	}
}
