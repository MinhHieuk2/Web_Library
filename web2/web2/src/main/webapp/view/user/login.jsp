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
	    <div class="container-fluid pt-5 pb-3">
	        <div class="row justify-content-center">
	            <div class="col-lg-5">
	                <div class="card shadow-lg border-0 rounded-lg mt-5">
	                    <div class="card-header"><h2 class="text-center font-weight-light my-4">Đăng nhập</h2></div>
	                    <div class="card-body">
	                    
	                    	<input type="hidden" value="${sessionScope.msg}" id="msg">
	                    	<%
                          		String msg = (String)session.getAttribute("msg");
                          		if(msg != null){
                          			session.removeAttribute("msg");
                          		}
                          	%>
	                        <form action="<c:url value="/user/login"/>" method="POST" onsubmit="return checkLogin()">
	                            <div class="form-group mb-3">	                            	
	                                <input class="form-control" id="inputEmail" type="text" placeholder="Email/Tên đăng nhập" name="username">	                                
	                            </div>
	                            <div class="form-group mb-3">	                            	
	                                <input class="form-control" id="inputPassword" type="password" placeholder="Mật khẩu" name="password">	                                
	                            </div>
	                            <div class="d-flex align-items-center justify-content-center mt-4 mb-0">
	                                <button class="btn btn-primary">Đăng nhập</button>
	                            </div>	                            
	                            <p class="text-center m-3">Chưa có tài khoản?  <a href="<c:url value = "/user/register"></c:url>"> Đăng ký</a></p>
	                        </form>
	                    </div>
	                </div>
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
	    <script src="${url}/mail/contact.js"></script>>
	    <script src="${url}/js/main.js"></script>
	    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	    <script type="text/javascript">	   
	    	let msg = document.getElementById("msg").value;
			if(msg=='loginError' ){				
				swal({text : 'Sai thông tin đăng nhập!', icon: 'error' });
			}
			function checkLogin(){
	    		let username = $('#inputEmail').val();
	    		let password = $('#inputPassword').val();
	    		if(username!='' && username.length > 0 && password != '' && password.length > 0){
	    			return true;
	    		}else{
	    			swal({text : "Vui lòng điền đầy đủ thông tin", icon: "error" });
	    			return false;
	    		}	    		
			}
	    </script>
	</body>

</html>