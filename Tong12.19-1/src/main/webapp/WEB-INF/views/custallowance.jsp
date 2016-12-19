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
	<%@include file="navbar2.jspf" %>
    <div class="container theme-showcase" role="main">
      <div class="jumbotron" >    
        <h1>Jackie Chen 3C</h1>
        <p class="lead">Viewing Pleasure</p>
      </div>  
     
 
    <form method="POST" action="uploadAllowanceFile" enctype="multipart/form-data">
  <input type="hidden" name = "autoid" value="${SalesOrder.autoid}">
        File to upload: <input type="file" name="file"><br /> 
        <input type="submit" value="Upload"> Press here to upload the file! (<200mb)
    </form>
    ${message}<p>
    <img src="resources\allowanceFileUpload\<c:out value="${SalesOrder.autoid}"/>.jpg" width="30%">
   <div class="col-md-3"></div>
	<div class="col-md-6">
				<form method="get" action="aConfirm" id="aConfirmForm">
				<input type="hidden" name = "autoid" value="${SalesOrder.autoid}">
             <input type="hidden" name = "pid" value="${SalesOrder.productId}">
              <input type="hidden" name = "soid" value="${SalesOrder.soid}">
						<label>折讓狀況描述:</label>
				
						<input type="text" name="detail" placeholder="輸入產品情況" required>
				
			  		<button type="submit" class="btn btn-default">確認折讓</button>
				</form>
			</div>
			<div class="col-md-3"></div>
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