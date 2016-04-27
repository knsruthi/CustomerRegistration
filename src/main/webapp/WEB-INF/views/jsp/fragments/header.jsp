<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Registration Form (HelloWorld)</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/customers/add" var="urlAddCustomer" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlAddCustomer}">REGISTER HERE</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlHome}">Reports</a></li>
			</ul>
		</div>
	</div>
</nav>