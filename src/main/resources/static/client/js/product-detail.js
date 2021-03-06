$(document).ready(function() {


    $(".add-to-cart").on("click", function () {
        var dataCart = {};
        var product = $(this).attr("prodid");
        dataCart.amount = document.getElementById('getAmount').value;
        dataCart.productId = product;

        NProgress.start();

        var linkPost = "/api/carts";

        axios.post(linkPost, dataCart).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.replace("/cart/add");
                });
            } else {
                swal(
                    'Bạn cần đăng nhập!',
                    res.data.message,
                    'error'
                ).then(function() {
                    location.replace("/login");
                });
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });

});