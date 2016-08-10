<%@include file="admin.html" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="apple-touch-icon" sizes="57x57"
	href="/apple-touch-icon-57x57.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="/apple-touch-icon-114x114.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="/apple-touch-icon-144x144.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="/apple-touch-icon-60x60.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="/apple-touch-icon-120x120.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="/apple-touch-icon-76x76.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="/apple-touch-icon-152x152.png">
<link rel="icon" type="image/png" href="/favicon-196x196.png"
	sizes="196x196">
<link rel="icon" type="image/png" href="/favicon-160x160.png"
	sizes="160x160">
<link rel="icon" type="image/png" href="/favicon-96x96.png"
	sizes="96x96">
<link rel="icon" type="image/png" href="/favicon-16x16.png"
	sizes="16x16">
<link rel="icon" type="image/png" href="/favicon-32x32.png"
	sizes="32x32">
<meta name="msapplication-TileColor" content="#2b5797">
<meta name="msapplication-TileImage" content="/mstile-144x144.png">

<title>Bootstrap 3 Datepicker</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href="./css/prettify-1.0.css" rel="stylesheet">
<link href="./css/base.css" rel="stylesheet">
<link
	href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
<script type="text/javascript"
	src="//code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script
	src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
</head>

<body>
	<h3 id="no-icon-input-field-only">Choose a new Date:</h3>
	<div class="container">
		<div class="row">
		<form action="overrideDue" method="post">
			<div class='col-sm-6'>
				<input type='text' class="form-control" id='datetimepicker4' name = 'datepicker'/>
				<input type="hidden" name="branchId" value="<%=request.getParameter("branchId")%>">
				<input type="hidden" name="bookId" value="<%=request.getParameter("bookId")%>">
				<input type="hidden" name="cardNo" value="<%=request.getParameter("cardNo")%>">
			</div>
			<script type="text/javascript">
            $(function () {
                $('#datetimepicker4').datetimepicker();
            });
        </script>
        <button type="submit">Override Due!</button>
		</form>
		</div>
	</div>
	<script src="./js/prettify-1.0.min.js"></script>
	<script src="./js/base.js"></script>
	<script>
		if (top != self) { top.location.replace(self.location.href); }
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

		ga('create', 'UA-47462200-1', 'eonasdan.github.io');
		ga('send', 'pageview');
		</script>
</body>


</html>