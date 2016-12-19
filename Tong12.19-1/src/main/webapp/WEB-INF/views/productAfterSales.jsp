<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <style>
  select {
    max-width: 100px;
}
  </style> 
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

	<%@include file="navbarAfterSales.jspf" %>
    <div class="container theme-showcase" role="main">
    
      <div class="jumbotron" >    
        <h1>Jackie Chen 3C</h1>
        <p class="lead">Viewing Pleasure</p>
      </div>
	<div class="container">
	<form method="get" action="search" id="searchForm">
	
	<div class="form-group">
	
						類別:<select class="form-control" name="stype">
  						<option>description</option>
  						<option>details</option>
  		
  					
						</select>
					</div>
<div class="form-group">
<input type="text" name="search" placeholder="輸入關鍵字" required>	
</div>
<div class="form-group">
<button type="submit" class="glyphicon glyphicon-search"></button>
</div>

</form>
	</div>
	<div class="container">
	<form action="searchCat">
	<div  class="form-group">
							<select class="form-control" name="c"> 
  							<option>Horror</option>
  							<option>Action</option>
  							<option>Drama</option>
  							<option>True Story</option>
  							<option>Comedy</option>
							</select><button  type="submit" class="glyphicon glyphicon-search"></button>							
					</div>	

	</form>
	</div>
	
	
	<c:forEach items="${productList}" var="product">
		<div class="row">
			<br>
			<div class="col-md-4">
                <center><img src="resources\fileUpload\<c:out value="${product.id}"/>.jpg" width="30%"></center>
            </div>
            <div class="col-md-8">
            	<h3>${product.desc}</h3>
                <h4>${product.category}</h4>
                <h5>${product.details}</h5>
                <h5>TWD ${product.price}</h5>
            </div>
       </div> 
    </c:forEach>    
	  			
			
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
