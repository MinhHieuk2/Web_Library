<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/user" var="url"/>
<html lang="en">

	<head>
	    <meta charset="utf-8">
	    <link rel="icon" type="image/png" href="${url}/img/book-solid.png">
	    <title>Thư Viện Sách</title>
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

	    <div class="col-lg-12">
           	<c:if test="${sessionScope.account == null }">
           		<h6 class="text-center m-5">Bạn cần đăng nhập <a href="<c:url value="/user/login"/>">tại đây</a> để xem giỏ hàng</h6>
           	</c:if>
           	
           	
           	<c:if test="${sessionScope.account.isAdmin == 0}">
           	<!-- Cart Start -->
			    <div class="container-fluid pt-5">
			        <div class="row px-xl-5">
			            <div class="col-lg-8 table-responsive mb-5">
			                <table class="table table-bordered text-center mb-0">
			                    <thead class="bg-secondary text-dark">
			                        <tr>
			                        	<th>STT</th>
			                            <th>Sản phẩm</th>
			                            <th>Số lượng</th>
			                            <th>Xóa</th>
			                        </tr>
			                    </thead>
			                    <tbody class="align-middle">
			                    
			                    	<c:set var="stt" value="1"/>
			                    	
			                    	<c:forEach var="cartitem" items="${sessionScope.cart.cartItems}">
			                    	<tr id="${cartitem.book.id}">
			                    		<td>${stt}</td>
			                    		<c:set var="stt" value="${stt+1}"/>
			                            <td class="align-middle">
			                            	<a href="<c:url value="/user/detail?bid=${cartitem.book.id}"/>" 
			                            		style="color : black; text-decoration:none" >
				                            	<img src="${pageContext.request.contextPath}/${cartitem.book.cover}" 
				                            	alt="" style="width: 40px;"> ${cartitem.book.title }
			                            	</a>	
			                            </td>
			                            <td class="align-middle" style="width: 350px">
			                                <div class="input-group quantity mx-auto" style="width: 28%; height: 10%" onclick="updateCart(${cartitem.book.id})">
			                                    <div class="input-group-btn">
			                                        <button class="btn btn-sm btn-primary btn-minus">
			                                        <i class="fa fa-minus"></i>
			                                        </button>
			                                    </div>
			                                    <input type="text" class="form-control form-control-sm bg-secondary text-center" style="height: 24px" value="${cartitem.quantity}"  >
			                                    <div class="input-group-btn">
			                                        <button class="btn btn-sm btn-primary btn-plus" >
			                                            <i class="fa fa-plus"></i>
			                                        </button>
			                                    </div>
			                                </div>
			                            </td>
			                            <td class="align-middle" style="width: 100px"><button onclick="deleteItem(${cartitem.book.id})"  class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
			                        </tr>
			                    	</c:forEach>
			                    
			                        
			                    </tbody>
			                </table>
			            </div>
			            <div class="col-lg-4">
			                <div class="card border-secondary mb-5">
			                    <div class="card-footer border-secondary bg-transparent">
			                        <a  href="<c:url value="/user/checkout"/>"  class="btn btn-block btn-primary my-3 py-3">Tiến hành đặt hàng</a>
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
			    <!-- Cart End -->
           	</c:if>                  
           </div>
	
	
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
	    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	</body>

</html>