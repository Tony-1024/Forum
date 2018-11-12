<%@ page contentType="text/html; charset=utf-8"%>
<table border="0">
     <tr>
         <td>	 
         		 <img src="images/index/bfn.gif" onclick="bold(content)">
         		 <img src="images/index/ifn.gif" onclick="italic(content)">
         		 <img src="images/index/ufn.gif" onclick="line(content)">

        	     Size:
        		 <SELECT onChange="showsize(content,this.options[this.selectedIndex].value)"> 
			         <option value="12px" selected>12px</option>
			         <option value="14px">14px</option>
			         <option value="16px">16px</option>
			         <option value="18px">18px</option>
			         <option value="24px">24px</option>
			         <option value="36px">36px</option>
        		 </SELECT>
        		Color:
        		 <SELECT onChange="showcolor(content,this.options[this.selectedIndex].value)"> 
			         <option style="background-color:black;color: black" value="black" selected>black</option>
			         <option style="background-color:white;color: white" value="white">         white</option>
			         <option style="background-color:red;color: red" value="red">				red</option>
			         <option style="background-color:green;color: green" value="green">			green</option>
			         <option style="background-color:blue;color: blue" value="blue">			blue</option>
			         <option style="background-color:yellow;color: yellow" value="yellow">		yellow</option>
			         <option style="background-color:purple;color: purple" value="purple">		purple</option>
			         <option style="background-color:gold;color: gold" value="gold">			gold</option>
			         <option style="background-color:gray;color: gray" value="gray">			gray</option>
			         <option style="background-color:orange;color: orange" value="orange">		orange</option>			         			         
        		 </SELECT>
         </td>
     </tr>    
</table>

<div id="User" style="position:absolute;width:240px; height:139px;display:none;">
  <textarea rows="10" cols="30" id="temp">
  
  </textarea>
  <input type="button" onclick="copyTo(content,User,temp)">
</div>
