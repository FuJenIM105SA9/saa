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

    <title>Jackie Chen產品管理</title>
</head>
<body>
<body style="background-color: #000 ;">
	<%@include file="navbarLogin.jspf" %>
    <div class="container theme-showcase" role="main">
    
      <div class="jumbotron" >    
        <h1>Jackie Chen產品管理系統</h1>
        <p class="lead">本系統為Jackie Chen研發之程式</p>


         <p class="lead">Upload File Request Page</p>
      
</div>
 
    <form method="POST" action="uploadFile" enctype="multipart/form-data">
    <input type="hidden" name = "id" value="${product.id}">
        File to upload: <input type="file" name="file"><br /> 
        <input type="submit" value="Upload"> Press here to upload the file! (<200mb)
    </form>
    ${message}<p>
    <img src="resources\fileUpload\<c:out value="${product.id}"/>.jpg" width="30%">
    <div class="col-md-12">

    	<a class="btn btn-primary" href="return">reutrn</a>
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
</body>
<script src="http://www.istayreal.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://www.istayreal.com/js/jquery.snow/snow.js"></script>
<script type="text/javascript">
	$(function() {
		$().jSnow();
	});
	
</script>


</html>