<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${game.name}</title>

<link href="../../static/css/style.css" rel="stylesheet">
<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body class="container">
	<div class="row text-center">
		<div class="col-sm-3"><h4><a href="/">HOME</a></h4></div>
		<div class="col-sm-6"><h4 id="status_text">${game.status.text}</h4></div>
		
	</div>

	<div class="row text-center">
		<input id="game_id" type="hidden" value="${game.id}">
		<div class="fields"></div>
	</div>
	<div class="row text-center">
		<button id="restart" type="button" class="btn btn-success">Restart</button>
	</div>

	<script src="../../static/js/game.js"></script>
</body>

</html>