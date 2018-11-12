function check(fieldName,UseName,RemName,len){
    if(fieldName.value.length>len){
        fieldName.value=(fieldName.value).substring(0,len);
        alert("Maxmum input "+len+" characters!");
        return false;
    }
    else{
        UseName.value=eval(fieldName.value.length);
        RemName.value=len-UseName.value;
        return true;
    }
}

function doText(ob,text) {
	ob.value+=text
	ob.focus();
}

function bold(ob) {
	inTxt=prompt("Bold","Please input the bold text!");     
	if (inTxt!=null) {           
		bTxt="[b]"+inTxt+"[/b]";
		doText(ob,bTxt);
	}
}

function italic(ob) {
	inTxt=prompt("Italic","Please input the italic text!");     
	if (inTxt!=null) {           
		iTxt="[i]"+inTxt+"[/i]";
		doText(ob,iTxt);
	}
}

function line(ob) {
	inTxt=prompt("Underline","Please input the underline text!");     
	if (inTxt!=null) {           
		lTxt="[u]"+inTxt+"[/u]";
		doText(ob,lTxt);
	}
}

function showface(ob,face) {
	inTxt=prompt("Set the font "+face,"Please input the text to this font!"); 
	if (inTxt!=null) { 
		fTxt="[Fface="+face+"]"+inTxt+"[/font]";
		doText(ob,fTxt);
	}
}

function showsize(ob,size) {
	inTxt=prompt("Set the font size: "+size,"Please input the text!"); 
	if (inTxt!=null) { 
		sTxt="[Fstyle=font-size:"+size+"]"+inTxt+"[/font]";
		doText(ob,sTxt);
	}
}

function showcolor(ob,color) {
	inTxt=prompt("Set the font color!","Please input the text!"); 
	if(inTxt!=null){
		cTxt="[Fcolor="+color+"]"+inTxt+"[/font]"; 
		doText(ob,cTxt);
	}	
}

function showHead(ico){
	document.images['head'].src="images/face/user/"+ico;
}