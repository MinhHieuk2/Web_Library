<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/user" var="url"/>
<html lang="en">

	<head>
	    <meta charset="utf-8">
	    <link rel="icon" type="image/png" href="${url}/img/book-solid.png">
	    <title>Giao diện người dùng</title>
	    <meta content="width=device-width, initial-scale=1.0" name="viewport">
	    <meta content="Free HTML Templates" name="keywords">
	    <meta content="Free HTML Templates" name="description">
	    <link href="${url}/img/favicon.ico" rel="icon">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
	    <link href="${url}/lib/animate/animate.min.css" rel="stylesheet">
	    <link href="${url}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
	    <link href="${url}/css/style.css" rel="stylesheet">
	</head>
	
	<body>
	    
	    <jsp:include page="headerUser.jsp"/>

	    <!-- Products Start -->
	    <div class="container-fluid">
	        <div class="row justify-content-center">
	            <div class="col-lg-12">
	            
	            	<c:if test="${sessionScope.account == null }">
	            		<h6 class="text-center m-5">Bạn cần đăng nhập <a href="<c:url value="/user/login"/>">tại đây</a> để thực hiện thanh toán</h6>
	            	</c:if>
	            	
	            	
	            	<c:if test="${sessionScope.account.isAdmin == 0}">
	            	<!-- Checkout Start -->
					    <div class="container-fluid pt-5">
					        <div class="row px-xl-5">
					            <div class="col-lg-8">
					                <div class="mb-4">
					                
					                	<input type="hidden" value="${sessionScope.msg }" id="msg"/>
				                    	<%
				                    		String msg = (String)session.getAttribute("msg");
				                    		if(msg != null){
				                    			session.removeAttribute("msg");
				                    		}
				                    	%>
					                
					                    <h4 class="font-weight-semi-bold mb-4">Thông tin hóa đơn</h4>
					                    <div class="row">
					                        <div class="col-md-6 form-group">
					                            <label>Tên người nhận</label>
					                            <input class="form-control" type="text" placeholder="Nguyễn Minh Hiếu" name="receiver" id="receiver">
					                        </div>
					                        <div class="col-md-6 form-group">
					                            <label>Số điện thoại</label>
					                            <input class="form-control" type="text" placeholder="0123456789" name="telReceiver" id="telReceiver">
					                        </div>
					                        <div class="col-md-12 form-group">
					                            <label>Địa chỉ nhận hàng</label>
					                            <input class="form-control" type="text" placeholder="Số 41 - Ngõ 35 - Ngô Thì Sỹ - Văn Quán - Hà Đông - Hà Nội" 
					                            name="addressReceiver" id="addressReceiver">
					                        </div>
					                        <div class="col-md-6 form-group">
					                            <label>E-mail</label>
					                            <input class="form-control" type="text" placeholder="example@email.com" name="emailReceiver" id="emailReceiver">
					                        </div>
					                        <div class="col-md-6 form-group">
					                            <label>ZIP Code</label>
					                            <input class="form-control" type="text" placeholder="123456" name="zipcode" id="zipcode">
					                        </div>
					                        <div class="card border-secondary mb-5 col-12">
							                    <div class="card-footer border-secondary bg-transparent">
							                        <button class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3" onclick="checkout()">Đặt hàng</button>
							                    </div>
							                </div>
					                    </div>
					                </div>
					            </div>
					            <div class="col-lg-4">
					                <div class="card border-secondary mb-5">
					                    <div class="card-header bg-secondary border-0">
					                        <h4 class="font-weight-semi-bold m-0"></h4>
					                    </div>
					                    <div class="card-body">
					                        <h5 class="font-weight-medium mb-3">Sản phẩm</h5>
					                        
					                        <c:if test="${sessionScope.buynow == null }">
						                        <c:forEach var="item" items="${sessionScope.cart.cartItems}">
							                        <div class="d-flex justify-content-between">
							                            <p>${item.book.title} - ${item.book.author }</p>
							                            <p>x ${item.quantity}</p>
							                        </div>
						                        </c:forEach>
					                        </c:if>
					                        <c:if test="${sessionScope.buynow != null }">
						                        <div class="d-flex justify-content-between">
						                            <p>${sessionScope.buynow.book.title} - ${sessionScope.buynow.book.author }</p>
						                            <p>x ${sessionScope.buynow.quantity}</p>
						                        </div>
					                        </c:if>
					                        
					                        <hr class="mt-0">
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					    <!-- Checkout End -->
	            	</c:if>                  
	            </div>
	        </div>
	    </div>
	    <!-- Products End -->
	
	    <!-- Footer Start -->
	    <jsp:include page="footerUser.jsp"/>
	    <!-- Footer End -->
	    
	    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
	    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	    <script src="${url}/lib/easing/easing.min.js"></script>
	    <script src="${url}/lib/owlcarousel/owl.carousel.min.js"></script>
	    <script src="${url}/mail/jqBootstrapValidation.min.js"></script>
	    <script src="${url}/mail/contact.js"></script>
	    <script src="${url}/js/main.js"></script>
   		<script src="${url}/js/myJS.js"></script>
	</body>

</html>