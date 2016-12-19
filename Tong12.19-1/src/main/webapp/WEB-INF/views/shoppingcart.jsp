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
      
	<div class="container">
		<div class="row">
			<br>
			<div class="col-md-12">
			<a class="btn btn-primary" href="checkout?id=${product.id}">結帳</a>
				<table class="table">
				  	<tr>
				  	  
					  	<th>圖片</th>	
				  		<th>產品編號  </th>
				  		<th>類別</th>
				  		<th>描述</th>
				  		<th>數量</<th>
				  		<th>刪除</th>
				  	</tr>
				  	<c:forEach items="${shoppingCart}" var="product" varStatus="status">
					  	<tr>
					  	   <td>
					  		<img src="resources\fileUpload\<c:out value="${product.id}"/>.jpg" width="30%">
					  		</td>
					  		<td>${product.id}</td>
					  		<td>${product.category}</td>
					  		<td>${product.desc}</td>
					  		<td><div class="form-group">
							<select class="form-control" name="Quantity">
  							<option>1</option>
  							<option>2</option>
  							<option>3</option>
  							<option>4</option>
  							<option>5</option>
							</select>
					</div></td>
							
					  		<td> <a class="btn btn-danger" href="deleteShopping?id=${status.index}">刪除</a>
					  		
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