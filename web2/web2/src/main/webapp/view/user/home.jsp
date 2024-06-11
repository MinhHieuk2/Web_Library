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

	    <!-- Products Start -->
	    <div class="container-fluid pt-5 pb-3" >
		    <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Sách nổi bật</span></h2>
		    <div class="row px-xl-5 d-flex flex-row" style="width: 100%" >
		        <c:forEach var="book" items="${requestScope.books}">
		            <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
		                <div class="card product-item bg-light border-0">
		                    <div  class="card-header product-img position-relative overflow-hidden bg-transparent border p-0" >
		                        <img  class="img-fluid w-100" style="height: 400px" src="${pageContext.request.contextPath}/${book.cover}">
		                    </div>
		                    <div class="card-body border-left border-right text-center" >
		                        <h6 class="text" style="height: 20px">${book.title}</h6>
		                        <h6 class="text" style="height: 10px">${book.author}</h6>
		                    </div>
		                    <div class="card-footer d-flex justify-content-between bg-light border " >		                        
	                        	<button class="btn btn-sm text-dark p-0" onclick="addToCart(${book.id}, 1)" ><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ hàng</button>
		                        <a href="<c:url value="/user/detail?bid=${book.id}"/>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem thông tin</a>
		                    </div>
		                </div>
		            </div>
		        </c:forEach>
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