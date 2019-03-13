$(document).ready(function(){
  $('#hiddenData').hide();


  $('#get-weather-button').click(function(){
    clearData();
    $('#errorMessages').empty();

    var zip = $('#zipcode').val();
    var units = $('#select-unit').val();
    var temp = 'C';

    if ($('#select-unit').val() == 'Imperial') {
      temp = 'F'
    }
    var speed = 'km/hr';
    if ($('#select-unit').val() == 'Imperial') {
      speed = 'mi/hr'
    }



    getCurrentWeather(zip, units, temp, speed);
    getForecastFive(zip, units, temp, speed);

    $('#hiddenData').show();

    if (zip.length < 5) {
      errorMessage();
      $('#hiddenData').hide();
    }
  })



});

function clearData() {
  $('#cityHeader').empty();
  $('#currentWeatherDiv').empty();
  $('#iconDescription').empty();
  $('#p1').empty();
  $('#p2').empty();
  $('#p3').empty();
  $('#p4').empty();
  $('#p5').empty();
}

function errorMessage() {
    $('#errorMessages')
       .append($('<li>')
       .attr({class: 'list-group-item list-group-item-danger'})
       .text('Zip code: please enter a 5-digit zip code'));
}

function getCurrentWeather(zip, units, temp, speed) {

  $.ajax({
    type: 'GET',
    url: 'http://api.openweathermap.org/data/2.5/weather?zip=' + zip + '&units=' + units + '&APPID=13dff16f8edaf1508e9125f56d71efc1',
    success: function(weatherArray) {
      $('#iconDescription').append('<img id="currentImage" >' + weatherArray.weather[0].main + ': ' + weatherArray.weather[0].description);
      $('#currentImage').attr('src', 'http://openweathermap.org/img/w/' + weatherArray.weather[0].icon + '.png');
      $('#cityHeader').append('Current Conditions in ' + weatherArray.name);
      $('#currentWeatherDiv').append('<p>Temperature: ' + weatherArray.main.temp + ' ' + temp + '</p>');
      $('#currentWeatherDiv').append('<p>Humidity: ' + weatherArray.main.humidity + '%' + '</p>');
      $('#currentWeatherDiv').append('<p>Wind: ' + weatherArray.wind.speed + ' ' + speed + '</p>');
    },
    error: function() {

    }
  });
}

function getForecastFive(zip, units, temp, speed) {

  $.ajax({
    type: 'GET',
    url: 'http://api.openweathermap.org/data/2.5/forecast?zip=' + zip + '&units=' + units + '&APPID=13dff16f8edaf1508e9125f56d71efc1',
    success: function(weatherFive) {

      var month = new Array();
      month[0] = "January";
      month[1] = "February";
      month[2] = "March";
      month[3] = "April";
      month[4] = "May";
      month[5] = "June";
      month[6] = "July";
      month[7] = "August";
      month[8] = "September";
      month[9] = "October";
      month[10] = "November";
      month[11] = "December";

      var d1 = new Date(weatherFive.list[0].dt_txt);
      $('#p1').append('<p>' + d1.getDate() + ' ' + month[d1.getMonth()] + '</p>');
      $('#p1').append('<p id="iconOne"><img id="imageOne" ></p>');
      $('#imageOne').attr('src', 'http://openweathermap.org/img/w/' + weatherFive.list[0].weather[0].icon + '.png');
      $('#iconOne').append(weatherFive.list[0].weather[0].main);
      $('#p1').append('<p>' + 'H' + weatherFive.list[0].main.temp_max + temp + ' ' + 'L' + weatherFive.list[0].main.temp_min + temp + '</p>');

      var d2 = new Date(weatherFive.list[8].dt_txt);
      $('#p2').append('<p>' + d2.getDate() + ' ' + month[d2.getMonth()] + '</p>');
      $('#p2').append('<p id="iconTwo"><img id="imageTwo" ></p>');
      $('#imageTwo').attr('src', 'http://openweathermap.org/img/w/' + weatherFive.list[8].weather[0].icon + '.png');
      $('#iconTwo').append(weatherFive.list[8].weather[0].main);
      $('#p2').append('<p>' + 'H' + weatherFive.list[8].main.temp_max + temp + ' ' + 'L' + weatherFive.list[8].main.temp_min + temp + '</p>');

      var d3 = new Date(weatherFive.list[14].dt_txt);
      $('#p3').append('<p>' + d3.getDate() + ' ' + month[d3.getMonth()] + '</p>');
      $('#p3').append('<p id="iconThree"><img id="imageThree" ></p>');
      $('#imageThree').attr('src', 'http://openweathermap.org/img/w/' + weatherFive.list[16].weather[0].icon + '.png');
      $('#iconThree').append(weatherFive.list[16].weather[0].main);
      $('#p3').append('<p>' + 'H' + weatherFive.list[16].main.temp_max + temp + ' ' + 'L' + weatherFive.list[16].main.temp_min + temp + '</p>');

      var d4 = new Date(weatherFive.list[24].dt_txt);
      $('#p4').append('<p>' + d4.getDate() + ' ' + month[d4.getMonth()] + '</p>');
      $('#p4').append('<p id="iconFour"><img id="imageFour" ></p>');
      $('#imageFour').attr('src', 'http://openweathermap.org/img/w/' + weatherFive.list[24].weather[0].icon + '.png');
      $('#iconFour').append(weatherFive.list[24].weather[0].main);
      $('#p4').append('<p>' + 'H' + weatherFive.list[24].main.temp_max + temp + ' ' + 'L' + weatherFive.list[24].main.temp_min + temp + '</p>');

      var d5 = new Date(weatherFive.list[32].dt_txt);
      $('#p5').append('<p>' + d5.getDate() + ' ' + month[d5.getMonth()] + '</p>');
      $('#p5').append('<p id="iconFive"><img id="imageFive" ></p>');
      $('#imageFive').attr('src', 'http://openweathermap.org/img/w/' + weatherFive.list[32].weather[0].icon + '.png');
      $('#iconFive').append(weatherFive.list[32].weather[0].main);
      $('#p5').append('<p>' + 'H' + weatherFive.list[32].main.temp_max + temp + ' ' + 'L' + weatherFive.list[32].main.temp_min + temp + '</p>');
    },
    error: function() {

    }
  });
}
