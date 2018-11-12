package com.forum.tools;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

public class ToChangeEncode extends RequestProcessor {
	protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return true;			
	}
}
