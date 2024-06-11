<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/user" var="url"/>
	<div class="container-fluid bg-dark text-secondary mt-5 pt-5">
        <div class="row px-xl-5 pt-5">
            <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                <h5 class="h1 text-uppercase text-primary bg-dark px-2">Thư viện sách</h5>
                <p class="mb-4"></p>
                <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Số 41, ngõ 35, Ngô Thì Sỹ, Văn Quán, Hà Đông, Hà Nội</p>
                <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>info@example.com</p>
                <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>012 345 6789</p>
            </div>
             <div class="col-lg-8 col-md-12">
                <div class="row">
                    <div class="col-md-6 mb-5 text-center">
                        <h5 class="font-weight-bold text-white mb-4">Đường dẫn nhanh</h5>
                        <div class="d-flex flex-column justify-content-start">
                            <a class="text-white mb-2" href="<c:url  value="/user/home"/>"><i class="fa fa-angle-right mr-2"></i>Trang chủ</a>
                            <a class="text-white mb-2" href="<c:url  value="/user/cart"/>"><i class="fa fa-angle-right mr-2"></i>Giỏ hàng</a>
                            <a class="text-white mb-2" href="<c:url value="/user/bills"/>"><i class="fa fa-angle-right mr-2"></i>Đơn hàng</a>
<!--                             <a class="text-white mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a> -->
<!--                             <a class="text-white mb-2" href="checkout.html"><i class="fa fa-angle-right mr-2"></i>Checkout</a> -->
<!--                             <a class="text-white" href="contact.html"><i class="fa fa-angle-right mr-2"></i>Contact Us</a> -->
                        </div>
                    </div>
                    <div class="col-md-6 mb-5">
                        <h5 class="font-weight-bold text-white mb-4">Nhận thông tin mới nhất</h5>
                        <form action="">
                            <div class="form-group">
                                <input type="text" class="form-control border-0 py-4" placeholder="Tên của bạn" required="required" />
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control border-0 py-4" placeholder="Email của bạn"
                                    required="required" />
                            </div>
                            <div>
                                <button class="btn btn-primary btn-block border-0 py-3" type="submit">Đăng ký ngay</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>