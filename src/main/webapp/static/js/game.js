$(document).ready(function() {
	initialize();
});

var sing, xSing, oSing, turnNumber, enabled, gameId;

/* Start game */
function initialize() {
	gameId = $('#game_id').val();

	enabled = true;
	turnNumber = 1;
	xSing = 'X';
	oSing = 'O';
	
	initFields();
	loadGame(gameId);
	initActionListener(gameId);
}

/* Initialize game fields */
function initFields() {
	var fields = $('.fields');
	fields.empty();
	for (i = 1; i < 10; i++) {
		fields.append('<div class="block cell" position="'+ i +'"></div>');
	}
}

/* Initialize listener for turn action */
function initActionListener(gameId) {
	$('.block').click(function() {
		if(enabled && $(this).text() == '') {
			sing = sing = turnNumber % 2 == 1;
			$(this).text(sing ? xSing : oSing);
			$.ajax({
				type : "PUT",
	        	contentType : "application/json",
	        	url : "/api/games/add-turn",
	        	data: JSON.stringify(getTurnObject($(this))),
	        	dataType : "json",
	        	success : function(data) {
	        		sing = !sing;
	        		turnNumber++;
	        		displayStatus(data);
	        	}
	        });
		}
	});
}

/* Load saved game */
function loadGame(gameId) {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/api/games/" + gameId,
		success : function(data) {
			data.turns.forEach(function(turn){
				turnNumber = turn.turnNumber;
				sing = turnNumber % 2 == 1;
				changeText(turn.position);
				turnNumber++;
			});
			enabled = data.status.text == 'In Progress';
			$('#status_text').text(data.status.text);
		}
	});
}

/* Change game status*/
function displayStatus(status) {
	if (status.text != 'In Progress')
		enabled = false;
	$('#status_text').text(status.text);
}

/* Set text to position */
function changeText(position) {
	$(".block[position*='" + position + "']").text(sing ? xSing : oSing);
}

/* Create turn object */
function getTurnObject(_this) {
	return {
		gameId : gameId,
    	turnNumber : turnNumber,
    	position : _this.attr('position')
    }
}

/* Restart listener */
$('#restart').click(function() {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/api/games/" + gameId + "/remove-turns",
		success : function() {
			initialize();
		}
	});
});
