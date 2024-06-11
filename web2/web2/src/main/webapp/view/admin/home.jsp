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
                        <h1 class="mt-4">Trang chủ</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Danh sách sách
                            </div>
                            <div class="card-body row">
                            	<c:if test="${sessionScope.account.isAdmin == 1 }">
                            		<div class="col-12 text-center">
	                            		<a class="btn btn-primary" href="<c:url value="/admin/view?bid=0"/>">Thêm sách</a>
	                            	</div>
	                            	
                            	</c:if>
																							
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tiêu đề</th>
                                            <th>Tác giả</th>
                                            <th>Thể loại</th>
                                            <th>Ngày phát hành</th>
                                            <th>Số trang</th>
                                            <th>Số lượng đã bán</th>
                                            <c:if test="${sessionScope.account.isAdmin == 1 }">
                                            	<th>Thao tác</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tiêu đề</th>
                                            <th>Tác giả</th>
                                            <th>Thể loại</th>
                                            <th>Ngày phát hành</th>
                                            <th>Số trang</th>
                                            <th>Số lượng đã bán</th>
                                            <c:if test="${sessionScope.account.isAdmin == 1 }">
                                            	<th>Thao Tác</th>
                                            </c:if>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    
                                    	<c:forEach  var="book" items="${books}">
	                                    	<tr>
	                                            <td>${book.id}</td>
	                                            <td>${book.title}</td>
	                                            <td>${book.author}</td>
	                                            <td>${book.category.name}</td>
	                                            <td>${book.releasedate}</td>
	                                            <td>${book.pages}</td>
	                                            <td>${book.salesAmount}</td>
	                                            <c:if test="${sessionScope.account.isAdmin == 1}">
	                                            	<td>
	                                            		<a href = "<c:url value="/admin/view?bid=${book.id}"/>" class="btn btn-success mt-2">Xem thông tin</a>
	                                            		<button class="btn btn-warning mt-2" onclick="deletebook(${book.id})">Xóa sách</button>
	                                            	</td>
	                                            </c:if>
	                                        </tr>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${url}/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="${url}/assets/demo/chart-area-demo.js"></script>
        <script src="${url}/assets/demo/chart-bar-demo.js"></script>        
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${url}/js/datatables-simple-demo.js"></script>
        <script src="${url}/js/addcover.js"></script>
        
        <script>
        	function deletebook(bid) {
				let context = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
				
				if(confirm("Bạn chắc chắn xóa cuốn sách này chứ?")){ // đồng ý xóa
					$.ajax({
						url : context + "/admin/delete",
						method : 'get',
						data :{
							bid : bid
						},
						success :function(response){
							if(response.redirect){
								window.location.href = response.redirect
							}
						},
						error : function(err){
							cosole.log(err);
						}
					});
				}
			}
        </script>
       
        
    </body>
</html>
