<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8"> 
	<title>ADMIN</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
			<style type="text/css">
 			 <%@include file="index.css" %>
		</style>		           
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fontawesome/fontawesome-free-6.1.1-web/css/all.min.css">	
	</head>
	<body>
	<!-- header -->
	<div class="web">
		<div class="header">
				<div id="text1">
					<span>Địa điểm</span>
				</div>
				<div id="search">
				</div>
				<div id="login" class="mini-login">
					<div class="login" >
						<c:if test="${sessionScope.admin != '' }">
						<li style="list-style-type:none;" id="user_li">
							<i class="icon-user"></i>
							<span ><c:out value=" ${sessionScope.admin}"/></span>
							<ul class = "user_service" id="user_ul">
								<li style="background: black;" id="logout_li">Đăng xuất</li>
							</ul>
						</li>
					</c:if>
					</div>
				</div>
				<div class="login-main">
					<div id="login-main-img">
					</div>
				</div>
		</div>
		<!-- main -->
			<div class=main>
			<div class="container-fuild">
					<div class="row " style="width: 100%;">
						<div class="col-md-3 my-sidebar" style="width: 17%;max-width:19%">
							<h4 style="text-align: center; padding:20px 0px 10px 0px; font-family: 'Space Mono', monospace; text-decoration: none;color: red !important;font-size:30px;"><span style="border-bottom:2px solid gray">Mục lục</span></h4>
					 
							<ul class="ul1">
								<a class="a1" href="/home">
									<li class="li1" style="cursor:pointer;">
									<i style="color:red;" ></i>&ensp;<i class="fa-solid fa-earth-asia"></i>&ensp;<span class="list-a" style="font-weight:bold">Địa điểm du lịch</span>
									</li >
								</a>
								<a class="a1" href="/vitri">
									<li class="li1" style="cursor:pointer;">
									<i style="color:red"></i>&ensp;<i class="fa-solid fa-location-dot"></i>&ensp;<span class="list-a" style="font-weight:bold">Vị trí</span>
									</li >
								</a>
							</ul>
						</div> 
						  <div class="col-md div-danhsachsanpham" style="width: 50%;padding-right: 0px;margin-left:50px;margin-right:40px;margin-bottom:40px;margin-top:40px;">
                <div class="card shadow mb-4" style="min-height: 800px;">
                    <div class="card-header py-3"style="box-shadow: 0 6px 12px rgb(158, 148, 154);">
                        <h1 style="color: #4d4d4d !important;"class="h2 text-gray-800 text-center m-0 font-weight-bold text-primary">Danh sách địa điểm du lịch</h1>
                    </div>
                    <div class="col-md-12 mt-3">
                        <a href="/insert"><button class="btn btn-success  button1">Insert</button></a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="tblDanhSach" class="table mx-auto table-bordered table-hover">
                                <thead class="thead-dark">
                                    <tr class="text-center" style="background:#c0c0c0">
                                        <th class="text-center align-middle">ID</th>
                                        <th class="text-center align-middle">Tên địa điểm</th>
										<th class="text-center align-middle" width=200px>Mô tả</th>
										<th class="text-center align-middle" width=200px>Hình ảnh</th>
										<th class="text-center align-middle" >Kinh độ</th>
										<th class="text-center align-middle" >Vĩ độ</th>
										<th class="text-center align-middle" >Miền</th>
										<th class="text-center align-middle" width=200px>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="Diadiemdulich" items="${Diadiemdulich}">                               	
                                     <tr>
                                       <td class="text-center align-middle">${Diadiemdulich.getDddl_id()}</td>
                                            <td class="text-center align-middle" style = width:100px; >${Diadiemdulich.getDddl_ten()}</td>
                                             <td class=" align-middle gioihan" style="overflow-y: scroll; width:300px;">${Diadiemdulich.getDddl_mota()}</td>
                                            <td class="text-center align-middle">        
												<img src="${pageContext.request.contextPath}${Diadiemdulich.getDddl_path()}" class="img-fluid" style="width:150px;height:100px"/>
                                            </td>                                     
                                            <td class="text-center align-middle"style = width:50px;>${Diadiemdulich.vitri.getVtkinhdo()}</td>
                                            <td class="text-center align-middle"style = width:50px;>${Diadiemdulich.vitri.getVtvido()}</td>
                                            <td class="text-center align-middle">${Diadiemdulich.mien.getTenmien()}</td>
                                            <td class="text-center align-middle" style = width:50px;><a href="/xem/${Diadiemdulich.vitri.getVitriid()}"><button style="margin-bottom:15px" class="btn btn-secondary button1">Xem chi tiết</button></a><br/>
                                           	<a href="/home/view/${Diadiemdulich.getDddl_id()}"><button style="margin-bottom:15px" class="btn btn-info button1">Update</button></a><br/>
                                           	<form:form 
											        method="POST"
											        action="/delete/${Diadiemdulich.getDddl_id()}"
											        onsubmit="return confirm('Bạn có chắc chắn muốn xóa địa điểm ${Diadiemdulich.getDddl_ten()} ?') ? true: false"
											        style="text-align: center;" 
											       	>
											        <input style="margin-bottom:15px" class="btn btn-danger button1" type="submit" value="Delete"/>
								   		 	</form:form>
								   		 	</td>	   		 
					                   </tr>
					            </c:forEach>
                                </tbody>
                            </table>
						</div>
				
					</div>
				</div>
			
			</div>
			</div>
		<!-- footer -->
		<div class="footer">
				<span style="color: white">Copyright © 2022 NHOM1. All Rights Reserved.</span>
			</div>
			<script>
				window.onload=function(){
					document.getElementById("user_li").onclick=function(){
						if(document.getElementById("user_ul").style.display=="block"){
							document.getElementById("user_ul").style.display="none"
						}
						else{
							document.getElementById("user_ul").style.display="block"

						}
					}
	
				}
				var logout_li = document.getElementById("logout_li")
				logout_li.addEventListener("click",function(){
					$.get("./logoutadmin")
					window.location.href = "./loginadmin"
				})
			</script>
		<script src="https://code.jquery.com/jquery-3.0.0.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		 <script type="text/javascript"><%@include file="/resources/js/jquery-3.6.0.min.js"%></script>
		
	</div>
	</body>
</html>