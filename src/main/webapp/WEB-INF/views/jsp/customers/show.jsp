<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Customer Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">FIRST_NAME</label>
		<div class="col-sm-10">${customer.first_name}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">LAST_NAME</label>
		<div class="col-sm-10">${customer.last_name}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">ADDRESS_1</label>
		<div class="col-sm-10">${customer.address_1}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">ADDRESS_2</label>
		<div class="col-sm-10">${customer.address_2}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">CITY</label>
		<div class="col-sm-10">${customer.city}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">STATE</label>
		<div class="col-sm-10">${customer.state}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">COUNTRY</label>
		<div class="col-sm-10">${customer.country}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">USER_NAME</label>
		<div class="col-sm-10">${customer.user_name}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">PASSWORD</label>
		<div class="col-sm-10">${customer.password}</div>
	</div>

	

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>