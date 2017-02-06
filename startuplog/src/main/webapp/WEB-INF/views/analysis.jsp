<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<title>Startuplog</title>
</head>

<body>
	<div class="container">
		<div class="jumbotron">
			<h2>Startuplog</h2>
			<p>Data Analysis</p>
		</div>
		<div class="row">
			<div class="col-sm-5"></div>
			<p>
				<font color="red">${errorMessage}</font>
			</p>
		</div>
		<div class = "row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<table class="table table-striped">
			
			
				<caption align="bottom">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Analysis Data - Undergo change in future</caption>
				<thead>
					<tr>
						<th>Seller</th>
						<th>Customer</th>
						<th>Sales</th>
						<th>Region</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${recordList}" var="record">
						<tr>
							<c:forEach items="${record}" var="clm">
								<td>${clm}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</div>

		</div>
		<div class="row">
		<div class="col-sm-5"></div>
		<a href="/welcome" class="btn btn-warning">Back</a>
		</div>
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</div>
</body>

</html>