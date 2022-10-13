function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

var buckets = null;
$.get("buckets", function(data) {
    if (data !== '') {
        buckets = data;
    }
}).done(function() {

    var tableContent = "<tr class='header'>"+
        "<th style='width: 14%;'>Name</th>"+
        "<th style='width: 14%;'>Description</th>"+
        "<th style='width: 14%;'>Price</th>"+
        "<th style='width: 14%;'>Prepayment</th>"+
        "<th style='width: 14%;'>PurchaseDate</th>"+
        "<th style='width: 14%;'>Subscription</th>"+
        "<th style='width: 14%;'>Delete</th>"+

        "</tr>";

    jQuery.each(buckets, function(i, value) {

        tableContent+="<tr>"+
            "<td>" + value.name + "</td>"+
            "<td>" + value.description + "</td>"+
            "<td>" + value.price + "</td>"+
            "<td>" + value.prepayment + "</td>"+
            "<td>" + value.purchaseDate + "</td>"+
            "<td>" + value.subscription + "</td>"+
            "<td><button onclick='deleteOrderFromBucket(" + value.bucketId + ")'>delete</button></td>"+
            "</tr>"

    });

    $('#myTable').html(tableContent);

});



function deleteOrderFromBucket(bucketId) {
    var customUrl = '';
    var urlContent = window.location.href.split('/');
    for (var i = 0; i < urlContent.length-1; i++) {
        customUrl+=urlContent[i]+'/'
    }
    customUrl+='Bucket?bucketId='+bucketId;

    $.ajax({
        url: customUrl,
        type: 'DELETE',
        success: function(data) {
                location.reload();

        }
    });
}