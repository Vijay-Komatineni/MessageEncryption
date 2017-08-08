//Check passwords match
function checkPasswords(){

	var pwd1 = document.getElementById("uPwd").value;
	var pwd2 = document.getElementById("cnfrmPwd").value;
	
	if(pwd1!=pwd2){
	 document.getElementById("pwdMisMatch").innerHTML = "<br/><p class=\"reg_pwd-info\">* Passwords do not match</p>";
	 return false;
	}
	else{
return true;
	}
}


//Check Email id availability
function checkAvail(){
	var http;
	var reg_emailID = document.getElementById("uEmail").value;
	var url="CheckIdAvailable.jsp?uEmail="+reg_emailID;
	if (window.XMLHttpRequest) {
	   
	    http = new XMLHttpRequest();
	 } else {
	    
	    http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	  http.onreadystatechange = callback;
	function callback() {
	    	if (http.readyState < 4)
	    		{
	    		return;
	    		}
	 	    if(http.status !== 200) {
		    	return;
		    	}
	 	     
	 	    var str = http.responseText;
	 	    
	 	   if(str.includes("not available")){
	 		  document.getElementById("error").innerHTML = str;
	 	   }
	 	   else{
	 		  document.getElementById("regID").action = "register_passwords.jsp?hidden-emailid="+reg_emailID;
	 		  document.getElementById("regID").method="post";
		 		 document.getElementById("regId-Btn").type="submit";
		 		document.getElementById("regId-Btn").removeAttribute("onclick");
		 		document.getElementById("regId-Btn").value="Continue";
		 		document.getElementById("uEmail").readOnly = true;
		 		document.getElementById("error").innerHTML = str;
	 		  //document.getElementById("hidden-value").value = reg_emailID;
	 	   }

	   }
	
	http.open('GET', url, true);
	http.send(''); 
	}

//Populate recepient list in Compose page
function populateList(){
	var http;
	var email = document.getElementById("hidden-emailid").innerHTML;
	var url="GetUserID?emailid="+email;
	if (window.XMLHttpRequest) {
		   
	    http = new XMLHttpRequest();
	 } else {
	    
	    http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	 http.onreadystatechange = callback;
		
	 function callback() {
		    	if (http.readyState < 4)
		    		{
		    		return;
		    		}
		 	    if(http.status !== 200) {
			    	return;
			    	}
		 	   var str= http.responseText;
		 	   var jsonData = JSON.parse(str);
		 	   var parent = document.getElementById("to");
		 	   
		 	  for( var i=0;i<jsonData.userIDs.length;i++){
		 		 var boolean = true;
		 		 for(var j=0;j<parent.length;j++){
		 		 if(jsonData.userIDs[i]==parent.options[j].text)
		 		 {
		 		 boolean = false;
		 		 break;
		 		 }//endIf

		 		 }//endInnerfor

		 		 if(boolean){
		 		 		 	   var option = document.createElement("option");
		 		 		 	   option.text = jsonData.userIDs[i];	 		 		 		
		 		 		 	   parent.appendChild(option);
		 		 }

		 		 }//endOuterfor	 
		
	 }
		http.open('GET', url, true);
		http.send(''); 
}


//Load Compose
function loadCompose(){
	var http;
	var url="compose.jsp";
	if (window.XMLHttpRequest) {
		   
	    http = new XMLHttpRequest();
	 } else {
	    
	    http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	 http.onreadystatechange = callback;
		
	 function callback() {
		    	if (http.readyState < 4)
		    		{
		    		return;
		    		}
		 	    if(http.status !== 200) {
			    	return;
			    	}
		 	   var str = http.responseText;
		 	    document.getElementById("dashboard-body").innerHTML = str;
		 	    
	 }
		http.open('GET', url, true);
		http.send('');
}


//Load Inbox

/*function loadInbox(){
	var http;
	var email = document.getElementById("hidden-emailid").innerHTML;
	var url="GetMessages.jsp?id="+email;
	if (window.XMLHttpRequest) {
		   
	    http = new XMLHttpRequest();
	 } else {
	    
	    http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	 http.onreadystatechange = callback;
		
	 function callback() {
		    	if (http.readyState < 4)
		    		{
		    		return;
		    		}
		 	    if(http.status !== 200) {
			    	return;
			    	}
		 	   var str = http.responseText;
		 	    document.getElementById("dashboard-body").innerHTML = str;
		 	    
	 }
		http.open('GET', url, true);
		http.send('');
}*/