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
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>Customer Report</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>FIRST_NAME</th>
					<th>LAST_NAME</th>
					<th>ADDRESS_1</th>
					<th>ADDRESS_2</th>
					<th>CITY</th>
					<th>STATE</th>
					<th>COUNTRY</th>
					<th>REG_DATE</th>
				</tr>
			</thead>

			<c:forEach var="customer" items="${customers}">
				<tr>
					<td>${customer.first_name}</td>
					<td>${customer.last_name}</td>
					<td>${customer.address_1}</td>
					<td>${customer.address_2}</td>
					<td>${customer.city}</td>
					<td>${customer.state}</td>
					<td>${customer.country}</td>
					<td>${customer.reg_date}</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>