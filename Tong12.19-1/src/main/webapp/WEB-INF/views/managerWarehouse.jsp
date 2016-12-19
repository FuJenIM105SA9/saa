<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="http://www.istayreal.com/css/themes/2016xmas/2016xmas.css"/>
    

    <title>Jackie Chen 3C</title>
</head>
<body>
<body style="background-color: #000 ;">
	<%@include file="navbarWarehouse.jspf" %>
    <div class="container theme-showcase" role="main">
    
    
      <div class="jumbotron" >    
        <h1>Jackie Chen產品管理系統</h1>
        <p class="lead">本系統為Jackie Chen研發之程式</p>
      </div>
     
	<div class="container">
		<div class="row">
			<br>
			<div class="col-md-12">
	 
				<table class="table">
				  	<tr>
				  		<th>會員編號</th>
				  		<th>會員姓名</th>
				  		<th>TEL</th>
				  		<th>地址</th>
				  		<th>密碼</th>
				  		
				  	</tr>
				  	<c:forEach items="${managerList}" var="manager">
					  	<tr>
					  		<td>${manager.id}</td>
					  		<td>${manager.name}</td>
					  		<td>${manager.phone}</td>
					  		<td><textarea rows="6" cols="75">${manager.address}</textarea></td>
					  		<td>${manager.password}</td>
					  	
					  		
					  		
					  	</tr>
				  	</c:forEach>
				</table>
			</div>
		</div>
	</div>
	
<div class="footer-snow">
	<div class="footer-snow1">
		<div class="footer-snow2">
			<div class="footer-tree1" style="left: 0px; top: -40px;"></div>
			<div class="footer-elk" style="left: 48px; top: -40px;"></div>
			<div class="footer-tree2" style="left: 420px; top: -20px;"></div>
			<div class="footer-tree1" style="left: 480px; top: -27px;"></div>
			<div class="footer-tree2" style="left: 680px; top: -5px;"></div>
			<div class="footer-tree2" style="left: 1000px; top: -12px;"></div>
			<div class="footer-tree1" style="left: 1030px; top: -28px;"></div>
		</div>
	</div>
</div>
    </div><!-- /.container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>
<script src="http://www.istayreal.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://www.istayreal.com/js/jquery.snow/snow.js"></script>
<script type="text/javascript">
	$(function() {
		$().jSnow();
	});
	
</script>


</html>