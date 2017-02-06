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
		<div class = "row">
		<div class="col-sm-5"></div>
		<p>
			<font color="red">${ uploadResult}</font>
		</p>
		</div>
		<form action="/analysis" method="POST" role="form"
			enctype="multipart/form-data">

			<div class="form-group">
				<div class="row">

					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<div class="row">
							<div class="col-sm-7">
								<label for="file">Please select a file </label>
							</div>
							<div class="col-sm-2">
						
							</div>
							<div class="col-sm-1">
								<a class="btn" href="/download">Download Template</a>
							</div>
						</div>

						<input type="file" name="file" class="form-control" />
					</div>
					<div class="col-sm-4"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-5"></div>
					<div class="col-sm-4">
					<a href="/welcome" class="btn btn-warning">Back</a>
					<button type="submit" class="btn btn-success">Upload</button></div>
					<div class="col-sm-3"></div>
				</div>
			</div>
		</form>

		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</div>
</body>

</html>