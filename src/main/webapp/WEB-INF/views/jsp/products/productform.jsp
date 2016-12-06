<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	
	<br />

	<spring:url value="/products" var="productActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="productForm" action="${productActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="productName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<form:input path="productName" type="text" class="form-control " id="productName" placeholder="productName" />
					<form:errors path="productName" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="productPrice">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Price</label>
				<div class="col-sm-10">
					<form:input path="productPrice" class="form-control" id="productPrice" placeholder="productPrice" />
					<form:errors path="productPrice" class="control-label" />
				</div>
			</div>
		</spring:bind>

		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${productForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
						
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>



</body>
</html>