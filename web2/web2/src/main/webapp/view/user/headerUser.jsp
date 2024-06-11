<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<c:url value="/view/user" var="url"/>
	
	<!-- Topbar Start -->	
	<div class="container-fluid">
	       <div class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
	           <div class="col-lg-4">
	               <a href="<c:url value = "/user"></c:url>" class="text-decoration-none">
	                   <span class="h1 text-uppercase text-primary bg-dark px-2">thư viện sách</span>
	                   <!-- <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Sách</span> -->
	               </a>
	           </div>
	           <div class="col-lg-4 col-6 m-3">
	           </div>
	           <div class="navbar-nav ml-auto py-0 d-none d-lg-block">
	               <a href="<c:url  value="/user/cart"/>" class="btn px-0 m-3">
	                   <i class="fas fa-shopping-cart text-primary"></i>
	                   <span class="badge" id="sizecart">${fn:length(sessionScope.cart.cartItems)}</span>
	               </a>
	           </div>
	       </div>
	   </div>
	   <!-- Topbar End -->
	   
	   
	   <!-- Navbar Start -->    
	   <div class="container-fluid bg-dark mb-30 ">
            <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                    	<a href="<c:url  value="/user/home"/>" class="nav-item nav-link ${nav == 'home' ? 'active' : ''}">Trang chủ</a>
                        <c:if test="${nav == 'detail'}">
                        	<a href="#" class="nav-item nav-link ${nav == 'detail' ? 'active' : ''}">Chi tiết</a>
                        </c:if>
                        <a href="<c:url  value="/user/cart"/>" class="nav-item nav-link ${nav == 'cart' ? 'active' : ''}">Giỏ hàng</a>
                        <a href="<c:url value="/user/bills"/>" class="nav-item nav-link">Đơn hàng</a>
                    </div>
                    
                    <div class="navbar-nav ml-auto py-0 ">
                       	<c:if test="${sessionScope.account == null }">
                       		<a href='<c:url value = "/user/login"></c:url>' class="nav-item nav-link">Đăng nhập</a>
                       		<a href='<c:url value = "/user/register"></c:url>' class="nav-item nav-link">Đăng ký</a>
                        </c:if>
                        
                        <c:if test="${sessionScope.account.isAdmin == 0 }">
                        	<a href="" class="nav-item nav-link">Xin chào ${sessionScope.account.fullname}</a>
                        	<a href='<c:url value = "/user/logout"></c:url>' class="nav-item nav-link">Đăng xuất</a>
                        </c:if>
                       </div>
                       
                </div>
            </nav>	           
	    </div>
	    <!-- Navbar End -->