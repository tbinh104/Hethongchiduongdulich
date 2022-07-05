<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ page session="true" %>
	
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>HỆ THỐNG HỖ TRỢ TÌM KIẾM ĐỊA ĐIỂM DU LỊCH</title>

		<link rel="stylesheet" href="https://unpkg.com/leaflet@1.8.0/dist/leaflet.css" integrity="sha512-hoalWLoI8r4UszCkZ5kL8vayOGVae1oxXe/2A4AO6J9+580uKHDO3JdHb7NzwwzK5xr/Fs0W40kiNHxM9vyTtQ==" crossorigin=""/>
    	<script src="https://unpkg.com/leaflet@1.8.0/dist/leaflet.js" integrity="sha512-BB3hKbKWOc9Ez/TAwyWxNXeoV9c1v6FIeYiBieIWkpLjauysF18NzgR1MBNBXf8/KABdlkX68nAhlwcDFLGPCQ==" crossorigin=""></script>
   		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet-routing-machine/3.2.5/leaflet-routing-machine.css" />
   		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/perliedman-leaflet-control-geocoder/1.5.5/Control.Geocoder.min.css" />
   		<script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet-routing-machine/3.2.5/leaflet-routing-machine.js"></script> 
   		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/perliedman-leaflet-control-geocoder/1.5.5/Control.Geocoder.min.js"></script> 
   		<script src="https://d3js.org/d3.v3.min.js"></script>
    	<script src="https://d3js.org/topojson.v0.min.js"></script>
     	<script src="https://cdn.jsdelivr.net/npm/leaflet.locatecontrol@0.76.0/dist/L.Control.Locate.min.js" charset="utf-8"></script>
    	
    	<link rel="stylesheet" href="https://unpkg.com/leaflet.markercluster@1.3.0/dist/MarkerCluster.css" />
		<link rel="stylesheet" href="https://unpkg.com/leaflet.markercluster@1.3.0/dist/MarkerCluster.Default.css" />
    	<script src="https://unpkg.com/leaflet.markercluster@1.3.0/dist/leaflet.markercluster.js"></script>
    	
    	
    	<!-- sssssssss -->
    	<link rel="stylesheet" href="https://api.mapbox.com/mapbox-gl-js/v0.42.1/mapbox-gl.css" />    	
    	<script src="https://api.mapbox.com/mapbox-gl-js/v0.42.1/mapbox-gl.js" charset="utf-8"></script>
    	<script src="https://unpkg.com/osmtogeojson@2.2.12/osmtogeojson.js"></script>
    	
    	<style type="text/css"><%@include file="/resources/css/index.css" %></style>
    	<!-- Overpass -->
        <script src="${pageContext.request.contextPath}/resources/overpass/node_modules/leaflet-overpass-layer/dist/OverPassLayer.bundle.js"></script>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/overpass/node_modules/leaflet-overpass-layer/dist/OverPassLayer.css">
    	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    	
    	
</head>
<body>
	<div class="main">

		<div class="head">
			<div class="brand">
				<a href="">HỆ THỐNG HỖ TRỢ TÌM KIẾM ĐỊA ĐIỂM DU LỊCH</a>
				<ul class="menu">
					<li><a class="menu_link" href="/">Trang chủ</a></li>
					<li><a class="menu_link" href="/loginadmin">Đăng bài</a></li>
					<c:if test="${sessionScope.name != '' }">
						<li id="user_li">
							<i class="icon-user"></i>
							<span><c:out value=" ${sessionScope.name}"/></span>
							<ul class = "user_service" id="user_ul">
								<a href="/lichsu" style="text-decoration: none;color: rgb(106, 68, 36)"><li id ="show_user_history_ul">Lịch sử tìm kiếm</li></a>
								<li id="logout_li">Đăng xuất</li>
							</ul>
						</li>
					</c:if>
					
				</ul>
				<!--  <div class="dropdown">
				
				  <a class="btn btn-secondary dropdown-toggle"  role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
				    Lịch sử
				  </a>
					<c:forEach var="Lichsu1" items="${Lichsu1}">
					  <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					    <li><a class="dropdown-item" >${Lichsu1.getLsid()}</a></li>
					    <li><a class="dropdown-item" >${Lichsu1.getLsthoigian()}</a></li>
					    <li><a class="dropdown-item" >${Lichsu1.diadiemdulich.getDddl_ten()}</a></li>
					    <li><a class="dropdown-item" >${Lichsu1.taikhoan.getTktendangnhap()}</a></li>
					  </ul>
					 </c:forEach>
					 
				</div>-->
			</div>
		</div>
		<div class="content">
			<div class="image">
				<div class="image-text">
					<span>Hỗ trợ tìm kiếm thông tin địa điểm và đường đi một
						cách nhanh nhất</span>
					<button onclick="Click()">
						<span>Bắt đầu ngay</span>
					</button>
				</div>

			</div>
			<div class="location">
				<ul class="nav">
					<li><a href="/mienbac">Miền Bắc</a></li>
					<li><a href="/mientrung">Miền Trung</a></li>
					<li><a href="/">Miền Nam</a></li>
				</ul>
							
				<div class="place">
				<c:forEach var="Diadiemdulich" items="${Diadiemdulich}">
					<div class="place__card but"  onclick=" ModalClick(),MapClick(${Diadiemdulich.vitri.getVtkinhdo()},${Diadiemdulich.vitri.getVtvido()}),SubmitFR(${Diadiemdulich.getDddl_id()})">						
						<div class = "img_wraper" style='background-image: url("${pageContext.request.contextPath}${Diadiemdulich.getDddl_path()}")'>
							
						</div>
						<div class="place__content gioihanchu">
							<h3>${Diadiemdulich.getDddl_ten()}</h3>
							<h5>${Diadiemdulich.mien.getTenmien()}</h5>
							<p>${Diadiemdulich.getDddl_mota()}</p>														
						</div>
					</div>
					<form:form id="${Diadiemdulich.getDddl_id()}" 		
						method="post"
						action="/"
						modelAttribute="Lichsu">
						<form:input type="hidden" 
							value="${Diadiemdulich.getDddl_id() }"
							path="diadiemdulich"
							name="dddlid"
						/><br/>
						<form:input type="hidden"
							value="${Diadiemdulich.getDddl_ten() }"
							path="diadiemdulich"
							name="dddlten"
						/><br/>
						<form:input type="hidden" 
							value="${Diadiemdulich.getDddl_mota() }"
							path="diadiemdulich"
							name="dddlmota"
						/><br/>
						<form:input type="hidden"
							value="${sessionScope.userid}"
							path="taikhoan"
						/><br/>
				 
			  		</form:form>
			  		
			  		
				</c:forEach>

			 
										
			</div>
			<div class="infor" id="i4">
				<div class="infor__image">
					<div id="mapid1"></div>
				</div>
				<div class="infor__text">
					<span>Thể hiện thông tin địa điểm trên bảng đồ</span>
					<p>Mytour là website được thiết kế nhằm mục đích phục vụ nhu cầu tìm kiếm các địa điểm
					du lịch của mọi người, thể hiện thông tin cũng như các đặc điểm của địa điểm du lịch đó.
					Ngoài ra web còn hỗ trợ công cụ tìm kiếm các danh mục cần thiết như mà hiện thị nhà hàng, quán cafe cần thiết
					</p>
					<button>
						<span>Thêm về chúng tôi</span>
					</button>
				</div>
			</div>
			<div class="footer">
				<h3>Copyright © 2022 by MYTOUR</h3>
			</div>
			<div id="myModal" class="modal">

				<!-- Modal content -->
				<div class="modal-content" id="parenta">
						<div id="mapid" >
						<!-- content -->
						</div>
					<span style="z-index:999" class="close" onclick="CloseModal(), reloadDIV()">&times;</span>
				</div>
				
			</div>
		</div>
	</div>
	
	    <script type="text/javascript"><%@include file="/resources/js/jquery-3.6.0.min.js"%></script>
	   	<script type="text/javascript"><%@include file="/resources/js/map_index1.js"%></script>
	   	<script type="text/javascript"><%@include file="/resources/js/index.js"%></script>
		<script type="text/javascript"><%@include file="/resources/js/map.js"%></script>
		
	  
</body>
</html>