<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CORONA KIT</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="${pageContext.request.contextPath}/header" />
	<p><strong>---------------------------Order Summary !---------------------------</strong></p>
	
	<section class="container-fluid p-4">
	<c:choose>
		<c:when test="${kitdetaillist == null || kitdetaillist.isEmpty() }">
			<p class="well well-info">No Products Found </p>
		</c:when>
		<c:otherwise>
			<table class="table table-striped table-hover table-border">
				<tr>
					<th>KitProduct#</th>
					<th>Cost</th>
					<th>Quantity</th>
				</tr>
				<c:forEach items="${kitdetaillist}" var="kitproduct">
					<tr>
						<td>${kitproduct.id }</td>
						<td>${kitproduct.amount }</td>
						<td>${kitproduct.quantity}</td>		
					</tr>
				</c:forEach>
			</table>
			<p><strong>---------------------------Your Order Details !---------------------------</strong></p>
		   <p><strong>Order Date:: ${coronaKitItem.orderDate} </strong></p>
		   <p><strong>Delivery Address:: ${coronaKitItem.deliveryAddress} </strong></p>
		   <p><strong>Total Order Cost:: ${coronaKitItem.totalAmount} </strong></p>
			<a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/user/finalize">CheckOut</a>
		</c:otherwise>
	</c:choose>
	</section>
</body>
</html>