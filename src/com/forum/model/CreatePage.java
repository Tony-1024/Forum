package com.forum.model;

public class CreatePage {
	private int CurrentP;			//current page number
	private int AllP;				//total page number
	private int AllR;				//total records
	private int PerR;				//records per page
	private String PageLink;		//pagination navigator information
	private String PageInfo;		//pagination status info
	
	public CreatePage(){
		CurrentP=1;
		AllP=1;
		AllR=0;
		PerR=3;
		PageLink="";
		PageInfo="";
	}
	
	/** set amount of records per page*/
	public void setPerR(int PerR){
		this.PerR=PerR;
	}
	
	/** set the amount of records */
	public void setAllR(int AllR){
		this.AllR=AllR;
	}
	/** calculate the amount of pages*/
	public void setAllP(){
		AllP=(AllR%PerR==0)?(AllR/PerR):(AllR/PerR+1);
	}
	
	/** set the current page number */
	public void setCurrentP(String currentP) {		
		if(currentP==null||currentP.equals(""))
			currentP="1";
		try{
			CurrentP=Integer.parseInt(currentP);
		}catch(NumberFormatException e){
			CurrentP=1;
			e.printStackTrace();
		}
		if(CurrentP<1)
			CurrentP=1;
		if(CurrentP>AllP)
			CurrentP=AllP;		
	}

	/** Set the pagination information */
	public void setPageInfo(){
		if(AllP>=1){
			PageInfo="<table border='0' cellpadding='3'><tr><td>";
			PageInfo+="Show: "+PerR+"/"+AllR+" Records!&nbsp;";
			PageInfo+="Current page: "+CurrentP+"/"+AllP;
			PageInfo+="</td></tr></table>";			
		}				
	}
	
    /** Set the pagination navigator information */
	public void setPageLink(String gowhich){
		if(gowhich==null)
			gowhich="";
		if(gowhich.indexOf("?")>=0)
			gowhich+="&";
		else
			gowhich+="?";
		if(AllP>1){
			PageLink="<table border='0' cellpadding='3'><tr><td>";
			if(CurrentP>1){
				PageLink+="<a href='"+gowhich+"showpage=1'>��First��</a>&nbsp;";
				PageLink+="<a href='"+gowhich+"showpage="+(CurrentP-1)+"'>��Previous��</a>&nbsp;";
			}
			if(CurrentP<AllP){
				PageLink+="<a href='"+gowhich+"showpage="+(CurrentP+1)+"'>��Next��</a>&nbsp;";
				PageLink+="<a href='"+gowhich+"showpage="+AllP+"'>��Last��</a>";
			}
			PageLink+="</td></tr></table>";			
		}		
	}
	
	/** records Per page */
	public int getPerR(){
		return PerR;
	}
	
	/** total records */
	public int getAllR() {
		return AllR;
	}	
	
    /** all pages */
	public int getAllP() {		
		return AllP;
	}

	/** current page number*/
	public int getCurrentP() {
		return CurrentP;
	}
	
	/** pagination information */
	public String getPageInfo(){
		return PageInfo;
	}

	/** pagination navigator info */
	public String getPageLink(){
		return PageLink;
	}
}
