<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">
	<c:choose>
		<c:when test="${customerForm['new']}">
			<h1>Add User</h1>
		</c:when>
		<c:otherwise>
			<h1>Update User</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/customers" var="customerActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="customerForm" action="${customerActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="first_name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">First Name</label>
				<div class="col-sm-10">
					<form:input path="first_name" type="text" class="form-control " id="first_name" placeholder="First Name" />
					<form:errors path="first_name" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="last_name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Last Name</label>
				<div class="col-sm-10">
					<form:input path="last_name" type="text" class="form-control" id="last_name" placeholder="Last Name" />
					<form:errors path="last_name" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="address_1">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">ADDRESS 1</label>
				<div class="col-sm-10">
					<form:input path="address_1" type="text" class="form-control" id="address_1" placeholder="123 Sample rd" />
					<form:errors path="address_1" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="address_2">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">ADDRESS 2</label>
				<div class="col-sm-10">
					<form:input path="address_2" type="text" class="form-control" id="address_2" placeholder="ADDRESS 2" />
					<form:errors path="address_2" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="city">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">CITY</label>
				<div class="col-sm-10">
					<form:input path="city" rows="5" class="form-control" id="city" placeholder="CITY" />
					<form:errors path="city" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="state">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">STATE</label>
				<div class="col-sm-10">
					<form:input path="state" rows="5" class="form-control" id="state" placeholder="TX" />
					<form:errors path="state" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="country">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">COUNTRY</label>
				<div class="col-sm-10">
					<form:input path="country" rows="5" class="form-control" id="country" placeholder="USA" />
					<form:errors path="country" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="zip_code">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">ZIP CODE</label>
				<div class="col-sm-10">
					<form:input path="zip_code" rows="5" class="form-control" id="zip_code" placeholder="12135" />
					<form:errors path="zip_code" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">EMAIL</label>
				<div class="col-sm-10">
					<form:input path="email" rows="5" class="form-control" id="email" placeholder="abc@abc.com" />
					<form:errors path="email" class="control-label" />
				</div>
			</div>
		</spring:bind>



		<spring:bind path="user_name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">USER NAME</label>
				<div class="col-sm-10">
					<form:input path="user_name" rows="5" class="form-control" id="user_name" placeholder="USER_NAME" />
					<form:errors path="user_name" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<form:password path="password" rows="5" class="form-control" id="password" placeholder="PASSWORD" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${customerForm['new']}">
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

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>