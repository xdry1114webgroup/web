function logout(){
	var xmlhttp;
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}else{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			var resp = xmlhttp.responseText.trim();
			if(resp=="OK"){
				window.location.reload();
			}
		}
	};
	xmlhttp.open("GET", "/Outsourcing/logout", true);
	xmlhttp.send();
}

function login(){
	var url = window.location.href;
	window.location.href = "/Outsourcing/login.jsp?url="+url;
}