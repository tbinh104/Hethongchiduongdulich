var logout_li = document.getElementById("logout_li")
logout_li.addEventListener("click",function(){
	$.get("./logout")
	window.location.href = "./login-admin"
})