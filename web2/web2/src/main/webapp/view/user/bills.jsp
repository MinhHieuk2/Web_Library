<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url value="/view/user" var="url"/>
<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="utf-8">
    <link rel="icon" type="image/png" href="${url}/img/book-solid.png">
    <title>Thư Viện Sách</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="${url}/img/favicon.ico" rel="icon">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="${url}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="${url}/css/style.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="headerUser.jsp"/>

    <!-- Products Start -->
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-lg-12" >
            
            	<c:if test="${sessionScope.account == null }">
            		<h6 class="text-center m-5">Bạn cần đăng nhập <a href="<c:url value="/user/login"/>">tại đây</a> để xem hóa đơn</h6>
            	</c:if>
            	
            	
            	<c:if test="${sessionScope.account.isAdmin == 0}">
            	<!-- Cart Start -->
				    <div class="container-fluid pt-5" >
				        <div class="row px-xl-5" id="content">
				            <div class="col-lg-12 table-responsive mb-5">
				                <table class="table table-bordered text-center mb-0">
				                    <thead class="bg-secondary text-dark">
				                        <tr>
				                        	<th>Mã đơn hàng</th>
				                            <th>Ngày mua</th>
				                            <th>Sản phẩm</th>
				                            <th>Thao tác</th>
				                        </tr>
				                    </thead>
				                    <tbody class="align-middle">
				                    
				                    	<c:forEach var="bill" items="${sessionScope.bills }">
				                    		<tr>
					                    		<td class="align-middle">${bill.id}</td>
					                    		<td class="align-middle">${bill.saleday}</td>
					                    		
				                    			<c:if test="${fn:length(bill.billDetails) == 1}">
			                    					<td class="align-middle">${bill.billDetails[0].book.title}</td>
				                    			</c:if>
				                    			<c:if test="${fn:length(bill.billDetails) > 1}">
			                    					<td class="align-middle">${bill.billDetails[0].book.title} và ${fn:length(bill.billDetails) - 1} sản phẩm khác</td>
				                    			</c:if>
					                    		<td class="align-middle">
					                    			<button class="btn border-dark" onclick="showBill(${bill.id})">Chi tiết</button>
					                    		</td>
					                    	</tr>
				                    	</c:forEach>
				                    
				                    </tbody>
				                </table>
				            </div>
				        </div>
				    </div>
				    <!-- Cart End -->
            	</c:if>                  
            </div>
        </div>
    </div>
    <!-- Products End -->

	<!-- Footer Start -->
    <jsp:include page="footerUser.jsp"/>
    <!-- Footer End -->


    <!-- Back to Top -->
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