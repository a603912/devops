<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Products</h1>





		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Name</th>
					<th>Price</th>

				</tr>
			</thead>

			<c:forEach var="product" items="${products}">
				<tr>
				Hello deployed
					<td>${product.id}</td>
					<td>${product.productName}</td>
					<td>${product.productPrice}</td>

					<td><spring:url value="/products/${product.id}"
							var="productUrl" /> <spring:url
							value="/products/${product.id}/delete" var="deleteUrl" /> <spring:url
							value="/products/${product.id}/update" var="updateUrl" />

						<button class="btn btn-info"
							onclick="location.href='${productUrl}'">Get Product Detail</button>
						<button class="btn btn-primary"
							onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger"
							onclick="location.href='${deleteUrl}'">Delete</button></td>
				</tr>
			</c:forEach>
		</table>



	</div>



</body>
</html>