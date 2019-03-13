$(document).ready(function(){

loadItems();


  var totalMoney = 0.00;
  var selectedId = '';

  var quarters;
  var dimes;
  var nickels;
  var pennies;
  var displayValue;
  var temp = true;

  $('.panel').hover(
    function() {
      $(this).css( 'background-color', 'grey');
    },
    function() {
      $(this).css('background-color', '')
    }
  );

  document.getElementById('total-money-display').value = totalMoney.toFixed(2);
  document.getElementById('item-display').value = selectedId;

  $('.panel').click(function(event) {
        selectedId = $(this).closest('div').attr('id');
        document.getElementById('item-display').value = selectedId;
    });

  $("#dollar-button").click(function(){
        totalMoney += 1.00;
        document.getElementById('total-money-display').value = totalMoney.toFixed(2);
  });
  $("#quarter-button").click(function(){
        totalMoney += 0.25;
        document.getElementById('total-money-display').value = totalMoney.toFixed(2);
  });
  $("#dime-button").click(function(){
        totalMoney += 0.10;
        document.getElementById('total-money-display').value = totalMoney.toFixed(2);
  });
  $("#nickel-button").click(function(){
        totalMoney += 0.05;
        document.getElementById('total-money-display').value = totalMoney.toFixed(2);
  });


  $("#purchase-button").click(function(){
    purchaseItems(totalMoney, selectedId);
    document.getElementById('total-money-display').value = totalMoney.toFixed(2);
  });

  $("#change-button").click(function(){
    quarters = Math.floor(totalMoney / 0.25);
    totalMoney -= (quarters * 0.25);
    dimes = Math.floor(totalMoney / 0.10);
    totalMoney -= (dimes * 0.10);
    nickels = Math.floor(totalMoney / 0.05);
    totalMoney -= (nickels * 0.05);
    penny = Math.floor(totalMoney / 0.01);
    totalMoney -= (penny * 0.01);

    displayValue = '';
    if (quarters > 0) {
      displayValue += quarters + ' Quarter ';
    }
    if (dimes > 0) {
      displayValue += dimes + ' Dime ';
    }
    if (nickels > 0) {
      displayValue += nickels + ' Nickel ';
    }
    if (pennies > 0) {
      displayValue += pennies + ' Penny';
    }

    totalMoney = 0;
    document.getElementById('total-money-display').value = totalMoney.toFixed(2);
    document.getElementById('change-display').value = displayValue;
    document.getElementById('message-display').value = '';
    document.getElementById('item-display').value = '';

    clearContents();
    loadItems();
  });
});

function purchaseItems(totalMoney, selectedId) {

    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/money/' + totalMoney + '/item/' + selectedId,
      success: function(data) {
        quarters = data.quarters;
        dimes = data.dimes;
        nickels = data.nickels;
        pennies = data.pennies;

        displayValue = '';

        if (quarters > 0) {
          displayValue += quarters + ' Quarter ';
        }
        if (dimes > 0) {
          displayValue += dimes + ' Dime ';
        }
        if (nickels > 0) {
          displayValue += nickels + ' Nickel ';
        }
        if (pennies > 0) {
          displayValue += pennies + ' Penny';
        }

        document.getElementById('change-display').value = displayValue;
        document.getElementById('message-display').value = 'Thank you!!!';
      },
      error: function(data) {
        console.log(JSON.stringify(data));
        var message = data.responseJSON.message;
        document.getElementById('message-display').value = message;
      }
    });
  }

function clearContents() {
  for (i = 1; i < 10; i++) {
    $('#pId' + i).empty();
    $('#pName' + i).empty();
    $('#pPrice' + i).empty();
    $('#pQuantity' + i).empty();
  }
}


function loadItems() {
  $.ajax ({
      type: 'GET',
      url: 'http://localhost:8080/items',
      success: function(data) {
          $.each(data, function (index, dvd) {
              var id = data[index].id;
              var name = data[index].name;
              var price = data[index].price;
              var quantity = data[index].quantity;

              $('#pId' + id).append(id);
              $('#pName' + id).append(name);
              $('#pPrice' + id).append('$' + price.toFixed(2));
              $('#pQuantity' + id).append('Quantity Left: ' + quantity);
          });
      },
      error: function() {
        alert('error load function');
      }
  });
}
