 document.getElementById("myModal").addEventListener('click', e => {
  if(e.target !== e.currentTarget){
}
  else {
	CloseModal();
}
});
function Click() {
	var in4 = document.getElementById("i4")
	// in4.scrollIntoView({behavior: "smooth", block: "center", inline: "nearest"});
	console.log(in4.offsetTop)
	window.scrollTo(0, in4.offsetTop + 10);
}
function ModalClick() {
	var modal = document.getElementById("myModal");
	modal.style.display = "block";
}
function CloseModal() {
	var modal = document.getElementById("myModal");
	modal.style.display = "none";

}

function SubmitFR(tamp) {
	$(document).ready(function() {
		var dddlid = document.getElementById(tamp).elements[0].value;
		var dddlten = document.getElementById(tamp).elements[1].value;
		var dddlmota = document.getElementById(tamp).elements[2].value;
		var tk = document.getElementById(tamp).elements[3].value;
		$.post("./",
			{
				dddlid: dddlid,
				dddlten: dddlten,
				dddlmota: dddlmota,
				tk: tk,
			}
		)
	});

}
window.onload = function(){
	$("#user_li").click(function(){
		if($("#user_ul").css("display")=="none"){
			$("#user_ul").css("display","block")
		}
		else if($("#user_ul").css("display")=="block"){
			$("#user_ul").css("display","none")
		}
	})
	$("#close_user_history_ul").click(function(){
		$("#user_history_ul").css("display","none");
	})
	$("#show_user_history_ul").click(function(){
		$("#user_ul").css("display","none")
		$("#user_history_ul").css("display","block");
	})
}
var logout_li = document.getElementById("logout_li")
logout_li.addEventListener("click",function(){
	$.get("./logout")
	window.location.href = "./login"
})