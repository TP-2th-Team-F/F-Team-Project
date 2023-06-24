var main = {
    init: function () {
        var _this = this;

        $('#btn-refresh').on('click', function () {
            _this.refresh();
        });
        $('#btn-start').on('click', function () {
            _this.start();
        });
        $('#btn-submit').on('click', function () {
            _this.gameOn();
        });
    },
    refresh: function () {
        $.ajax({
            type: 'GET',
            url: '/api/v1/numball/refresh',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (response) {
            alert("Complete your try");
            window.location.href='/numball/refresh';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    start: function () {
        var data = {
            player: $('#player').val(),
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/numball/refresh',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (id) {
            alert("Complete your game start");
            window.location.href='/numball/' + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    gameOn: function () {
        var data = {
            myGuess: $("#my-guess").val(),
        };
        var id = $("#id").val();
        $.ajax({
            type: 'POST',
            url: '/api/v1/numball/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("Complete your game on");
            window.location.href = '/numball/log/' + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

$(document).ready(function () {
    main.init();
});
