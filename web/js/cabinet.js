var products = null;

$.get("Products", function(data) {
    if (data !== '') {
        products = data;
    }
}).done(function() {

    var cardsContent = "";
    jQuery.each(products, function(i, value) {

        cardsContent+="<div class='col'>" +
            "<div class='card'>" +
            "<div class='card-body'>" +
            "<h5 class='card-title'>" + value.name + "</h5>"+
            "<h6 class='card-subtitle mb-2 text-muted'>" + value.price + "</h6>"+
            "<p class='card-text'>" + value.description + "</p>"+
            "<a class='productCardElement'  href='product?id=" + value.id + "' class='card-link'>link</a>"+
            "</div>" +
            "</div>" +
            "</div>"
    });

    $('#productCards').html(cardsContent);

}).done(function() {
    $.get("user-role", function(data) {
        if (data !== '') {
            userRole = data;
        }
    }).done(function() {
        if(userRole === 'ADMINISTRATOR'){
            $('a.productCardElement').hide();
        }
    });
});