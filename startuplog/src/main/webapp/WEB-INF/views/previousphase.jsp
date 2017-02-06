<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<title>Startuplog</title>
<style type="text/css">
.seperator {
	margin-left: 1px;
	margin-right: 1px
}
.seperator1 {
	margin-left: 5px;
	margin-right: 5px
}

.font-awesome {
	font-size: 16px;
	font-style: italic;
}

.font-awesome-small {
	font-size: 13px;
}
table {
  border-collapse:separate;
  border-spacing:8px 8px;
  border:3px;
}​​​​​​​​​​​​​
</style>
</head>

<body>
	<div class="container">
		<div class="jumbotron">
			<img src="resources/images/logo.jpg">

			<h2>Startuplog</h2>
		</div>
		<div class="row">
			<div class="col-sm-5"></div>
			<p>
				<font color="red">${errorMessage}</font>
			</p>
		</div>


		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="text-center">Previous Phases</h4>
			</div>
			<div class="panel-body">

				<form:form action="/previous-phase" method="POST"
					commandName="option" role="form">

					<div class="row">
						<div class="col-sm-4"></div>
						<div class="col-sm-6">
							<table>
								<tr>

									<td><label>Please Select</label></td>
									<td>&nbsp;&nbsp;</td>
									<td><form:select class="form-control" path="selection">
											<form:options items="${optionList}" />
										</form:select></td>
									<td>&nbsp;&nbsp;</td>
									<td><input type="submit" class="btn btn-success input-sm"
										name="submit" value="Search"></td>
								</tr>
							</table>
						</div>
					</div>
				</form:form>
				<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-10">
				<table class="table table-striped">
					<caption></caption>

					<thead>
						<tr class="seperator1">
							
							<th >User</th>
							<th >Date</th>
							<th >Phase</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${list}" var="phaseModel">
							<tr class="seperator1">
								<td>${phaseModel.user}</td>
								<td>${phaseModel.date}</td>
								<td>${phaseModel.stage}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				</div>
				
			</div>
		</div>
		<div class= "row">
		<div class="col-sm-6"></div>
		<a href="/welcome" class="btn btn-warning">Back</a>
		</div>
	</div>
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>

</html>