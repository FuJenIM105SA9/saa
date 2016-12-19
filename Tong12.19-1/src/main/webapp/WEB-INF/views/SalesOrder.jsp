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
        <h2>已購買</h2>
        <p class="lead">Viewing Pleasure</p>
      </div>
      <form action="search">
	<input type="text" name="search" placeholder="Search Name...">
	<button  type="submit" class="glyphicon glyphicon-search"></button>
	</form>
	<div class="container">
		<div class="row">
			<br>
			<div class="col-md-12">
				<table class="table">
				  	<tr>
				  		<th>商品類別</th><th>商品名稱</th><th>商品價格</th><th>購買時間</th><th>到貨</th><th>退貨</th><th>換貨</th><th>折讓</th>
				  	</tr>
				  	<c:forEach items="${SalesOrderList}" var="SalesOrder">
					  	<tr>
					  	   <td>${SalesOrder.category}</td>
					  		<td>${SalesOrder.desc}</td>
					  		<td>${SalesOrder.price}</td>
					    	<td>${SalesOrder.orderTime}</td>
					  		<td>
					  		<c:choose>
					  				<c:when test="${empty SalesOrder.custArrivalTime && SalesOrder.state == 'Paid'}">
										<a class="btn btn-default" href="arrive?id=${SalesOrder.id}">到貨</a>
    								</c:when>
    								<c:when test="${SalesOrder.state == 'Return Requested'}">
										已申請退貨
    								</c:when>
    								<c:when test="${SalesOrder.state == 'Change Requested'}">
										已申請換貨
    								</c:when>
    								<c:otherwise>
        								${SalesOrder.custArrivalTime}
    								</c:otherwise>
							</c:choose>			
								
					  		<td><c:choose>
					  				<c:when test="${SalesOrder.state=='Paid'||SalesOrder.state=='Arrived'}">
										<a class="btn btn-default" href="returnProduct?id=${SalesOrder.id}">退貨</a>
    								</c:when>
    								<c:when test="${SalesOrder.state == 'Change Requested'}">
										已申請換貨
    								</c:when>
    								<c:otherwise>
        							處理中
    								</c:otherwise>
    								
								     </c:choose>			
					  		<td><c:choose>
					  				<c:when test="${SalesOrder.state=='Arrived'}">
										<a class="btn btn-default" href="changeProduct?id=${SalesOrder.id}">換貨</a>
    								</c:when>
    								<c:when test="${SalesOrder.state =='Return Requested'}">
										已申請退貨
    								</c:when>
    								<c:when test="${SalesOrder.state=='Paid'}">
										貨未到
    								</c:when>
    								<c:otherwise>
        							處理中
    								</c:otherwise>
    								
								     </c:choose>	
					  		<td><a class="btn btn-default" href="uploadAllowanceFile?soid=${SalesOrder.soid}&autoid=${SalesOrder.autoid}&pid=${SalesOrder.productId}">折讓</a>
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