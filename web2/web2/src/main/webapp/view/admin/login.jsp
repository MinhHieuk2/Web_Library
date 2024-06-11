<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/admin" var="url"/>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="quan ly sach" />
        <meta name="author" content="hieu" />
        <link rel="icon" type="image/png" href="${url}/assets/img/toolbox-solid.png">
        <title>ADMIN</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="${url}/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    </head>
    
    <body class="sb-nav-fixed">
        <jsp:include page="nav.jsp"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <div class="card mb-4">
                            <div class="row justify-content-center">
                                <div class="col-lg-5">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-header"><h3 class="text-center font-weight-light my-4">Đăng nhập</h3></div>
                                        <div class="card-body">
                                        	<input type="hidden" value="${sessionScope.msg }" id="msg"/>
                                        	<%
                                        		String msg = (String)session.getAttribute("msg");
                                        		if(msg != null){
                                        			session.removeAttribute("msg");
                                        		}
                                        	%>
                                        	
                                            <form action="<c:url value="/admin/login"/>" method="POST" onsubmit="return checkLogin()">
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" id="inputEmail" type="text" placeholder="Email/Tài khoản" name="username"/>
                                                    <label for="inputEmail">Email/Tài khoản</label>
                                                </div>
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" id="inputPassword" type="password" placeholder="Mật khẩu" name="password" />
                                                    <label for="inputPassword">Mật khẩu</label>
                                                </div>
                                                <div class="d-flex align-items-center justify-content-center mt-4 mb-0">
                                                    <button class="btn btn-primary"  >Đăng nhập</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${url }/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="${url }/assets/demo/chart-area-demo.js"></script>
        <script src="${url }/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${url }/js/datatables-simple-demo.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script type="text/javascript">        
		    $(document).ready(function(){
		    	let msg = $('#msg').val();
				if(msg=='loginError' ){
// 					$('#msg').attr('value','');
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
        		// Điều chỉnh CSS để canh giữa nút Login
	  			  let swalButtons = document.getElementsByClassName('swal-button-container')[0];
	  			  swalButtons.style.display = 'flex';
	  			  swalButtons.style.justifyContent = 'center';
        	});
        	
        </script>
        
    </body>
</html>
