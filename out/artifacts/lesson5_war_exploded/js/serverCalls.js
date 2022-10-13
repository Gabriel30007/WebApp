
$("button.createProduct")
    .click(
        function() {
            let name = $("form.createProduct input.productName").val();
            let description = $("form.createProduct input.productDescription").val();
            let price = $("form.createProduct input.productPrice").val();
            let prepayment = $("form.createProduct input.productPrepayment").val();
            let product = {
                name : name,
                description : description,
                price : price,
                prepayment: prepayment,
            };
//add validation
            $.post("product", product,
                function(data) {
                    if (data == 'Success') {
                        alert('Success');
                    }
                });

        });

$("button.buy-product").click(function() {
    let productId = jQuery(this).attr("product-id");
    let subscription;
    if($("form.buyBucket input.subscription").is(":checked")){
        subscription = true;
    } else subscription=false;

    let prepayment = $("form.buyBucket input.prepayment").val();

    let bucket={
        productId: productId,
        subscription:subscription,
        prepayment:prepayment,
    };
    $.post("Bucket", bucket,
        function(data) {
                $('#buyProductModal').hide;
                alert('Success');
        });
});