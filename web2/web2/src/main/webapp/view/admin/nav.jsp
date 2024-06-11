<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/admin" var="url"/>
		<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3 m-5" href="<c:url value="/admin"/>">ADMIN</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar-->

            <span class="nav-link" id="" role="button" style="color: white">Xin chào ${sessionScope.account.fullname }</span>
        </nav>
        
        <div id="layoutSidenav">
        
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading text-center">Chức năng</div>
                            <a class="nav-link" href="<c:url value="/admin/home"/>">
                                <div class="sb-nav-link-icon"><i class="fa-solid fa-house"></i></div>
                                Trang chủ
                            </a>
                            <c:if test="${sessionScope.account == null }">
                            	<a class="nav-link" href="<c:url value="/admin/login"/>">
	                                <div class="sb-nav-link-icon"><i class="fa-solid fa-right-to-bracket"></i></div>
	                                Đăng nhập
	                            </a>
                            </c:if>
                            <c:if test="${sessionScope.account != null }">
                            	<a class="nav-link" href="<c:url value="/admin/logout"/>">
	                                <div class="sb-nav-link-icon"><i class="fa-solid fa-right-from-bracket"></i></div>
	                                Đăng xuất
	                            </a>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>
            