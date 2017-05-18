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

<title>TicTacToe</title>

<link href="../../static/css/style.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body class="container">
	<div class="col-sm-12">
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#exampleModalLong">Create new game</button>

		<!-- Modal -->
		<div class="modal fade" id="exampleModalLong" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLongTitle"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-body">
						<div class="form-group">
							<label for="name">Name:</label> <input type="text" class="form-control" id="name">
						</div>
					</div>
					<div class="modal-footer">
						<button id="createGameBtn" type="button" class="btn btn-primary">Create</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-12">
		<table id="games_table"
			class="table table-striped table-bordered text-left">
			<thead>
				<th>Id</th>
				<th>Game name</th>
				<th>Status</th>
				<th>Action</th>
			</thead>
			<tbody>
				<c:forEach items="${games}" var="game">
					<tr id="game_${game.id}">
						<td>${game.id}</td>
						<td>${game.name}</td>
						<td>${game.status.text}</td>
						<td><a href="/game/${game.id}" class="btn btn-success">
								Play </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>

<script type="text/javascript">
	$('#createGameBtn').click(function() {
		var name = $('#name').val();
		if (name.length > 0) {
			$.ajax({
				type : "PUT",
				url : "/api/games/create",
				data : {
					name : name
				},
				success : function(data) {
					window.location = '/game/' + data.id;
				}
			});
		} else {
			$('#name').css({'border-color':'red'});
		}
	});
</script>