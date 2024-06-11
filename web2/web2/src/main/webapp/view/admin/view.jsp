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
        
		<style>
		textarea {
			resize: none;
			overflow: hidden;
		}

		.rounded-border {
			border: 1px solid #ced4da;
			border-radius: 7px;
			padding: 15px;
		}

		select {
			color: var(--bs-card-color);
			background-color: white;
		}

		select:disabled {
			color: var(--bs-card-color);
			background-color: RGB(235, 235, 235);
		}
		</style>
		<script>
			function autoExpand(textarea) {
				textarea.style.height = 'auto';
				textarea.style.height = textarea.scrollHeight + 'px';
			}
	
			window.addEventListener('DOMContentLoaded', function() {
				var textareas = document.getElementsByTagName('textarea');
				for (var i = 0; i < textareas.length; i++) {
					autoExpand(textareas[i]);
				}
			});
		</script>     
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="nav.jsp"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container">
                    <c:if test="${sessionScope.account == null }">
                    	<h5 class="text-center mt-3">Đăng nhập bằng tài khoản admin <a href="<c:url value="/admin/login"/>">tại đây</a> để thực hiện chức năng này!</h5>
                    </c:if>
                    <c:if test="${sessionScope.account.isAdmin == 1 }">
                    	<div class="row justify-content-center">
                            <div class="col-lg-12">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Thông tin sách</h3></div>
                                    <input type="hidden" value="${sessionScope.msg }" id="msg">
                                    <%
                                    	String msg = (String)session.getAttribute("msg");
                                    	if(msg != null){
                                    		session.removeAttribute("msg");
                                    	}
                                    %>
                                    <div class="card-body">
                                        <form action="<c:url value="/admin/view?bid=${bid}"/>" method="post" enctype="multipart/form-data" onsubmit="return checkFormBook()">
                                            <div class="row mb-3">
                                            	<div class="col-md-6 row">
                                            		<div class="col-md-6">
                                                        <label for="title">Tiêu đề</label><span style="color:red"> *</span>
	                                                    <div class="form-floating mb-3 mb-md-0">
	                                                        <input ${status=='view'?"disabled":"" } class="form-control p-1" id="title" name="title" type="text" value="${book.title}"/>
	                                                    </div>
	                                                </div>
	                                                
	                                                <div class="col-md-6">
                                                        <label for="author">Tác giả</label><span style="color:red"> *</span>
	                                                    <div class="form-floating ">
	                                                        <input ${status=='view'?"disabled":"" } class="form-control p-1" id="author" name="author" type="text" value="${book.author}"/>
	                                                    </div>
	                                                </div>
	                                                
   	                                                <div class="col-md-12 mt-3">
                                                        <label for="description">Description</label>
	                                                    <div class="form-floating mb-3 mb-md-0">
	                                                        <textarea ${status=='view'?"disabled":"" } class="form-control p-1" id="description" name="description" oninput="autoExpand(this)" rows="3" >${book.description}</textarea>
	                                                    </div>
	                                                </div>
	                                                
	                                                <div class="col-md-6 mt-3">
                                                        <label for="releasedate">Ngày phát hành</label><span style="color:red"> *</span>
	                                                    <div class="form-floating mb-3 mb-md-0">
	                                                        <input ${status=='view'?"disabled":"" } class="form-control p-1" id="releasedate" name="releasedate" type="date" value="${book.releasedate}"/>
	                                                    </div>
	                                                </div>
	                                                
	                                                <div class="col-md-6 mt-3">
                                                        <label for="pages">Số trang</label>
	                                                    <div class="form-floating">
	                                                        <input ${status=='view'?"disabled":"" } class="form-control p-1" id="pages" name="pages" type="number" value="${book.pages}"/>
	                                                    </div>
	                                                </div>
	                                                
	                                                <div class="col-md-12 mt-3">
	                                            	<label for="category">Thể loại</label>
	                                                    <div class="form-floating ">
	                                                        <select class="rounded-border w-100" name="category" id="category" ${status=='view'?"disabled":"" }>
	                                                        	<option disabled value="0">Chọn một thể loại</option>
	                                                        	<c:forEach var="cate" items="${categories}">
	                                                        		<c:choose>
	                                                        			<c:when test="${book.category.id == cate.id}">
	                                                        				<option selected value="${cate.id}">${cate.name}</option>
	                                                        			</c:when>
	                                                        			<c:otherwise>
	                                                        				<option value="${cate.id}">${cate.name}</option>
	                                                        			</c:otherwise>
	                                                        		</c:choose>
	                                                        	</c:forEach>
															</select>														
	                                                    </div>
	                                                </div>
                                            	</div>
                                            	
                                            	<div class="col-md-6 row">
                                            		<div class="col-md-12 text-center">
                                            			<label class="form-label font-weight-bold fw-bold font-size-xl">Bìa sách</label>
													  <input ${status=='view'?"disabled":"" } class="form-control" type="file" id="cover" name="cover" accept="image/png, image/jpeg, image/jpg">
                                            		</div>
                                            		
                                            		<div class="col-md-12 m-3 md-3 d-flex justify-content-center align-items-center" id="showcover" style="border: 1px solid #ced4da; height: 83%; width: 95%; border-radius: 7px">
                                            			<img id="cover_image" style="max-width:200px; max-height:400px !important" src="${pageContext.request.contextPath}/${book.cover}">
                                            		</div>
                                            	</div>
                                            </div>
                                            
                                            <div class=" text-center">
                                                <c:if test="${status=='add'}"><button class="btn btn-info mt-4 ">Thêm sách</button></c:if>
                                                <button class="btn btn-success mt-4" style="display : none" id="btnSave">Lưu sách</button>
                                            </div>
                                        </form>
                                        <div class="text-center">
                                                <c:if test="${status=='view'}"><button id="btnEdit" class="btn btn-warning mt-4 " onclick="clickedit(this)">Sửa thông tin sách</button></c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    </div>
                </main>
            </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${url}/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${url}/js/datatables-simple-demo.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="${url}/js/myapp.js"></script>
        <script>
        	let msg = document.getElementById("msg").value;
        	if(msg == 'addbookerror'){
        		alert('Tiêu đề hoặc tên tác giả đã tồn tại')
        		document.getElementById("msg").value = '';
        	} else if(msg == 'invalidTitle'){
        		alert('Tiêu đề không hợp lệ')
        		document.getElementById("msg").value = '';
        	} else if(msg == 'invalidAuthor'){
        		alert('Tác giả không hợp lệ')
        		document.getElementById("msg").value = '';
        	} else if(msg == 'invalidReleaseDate'){
        		alert('Ngày xuất bản không hợp lệ')
        		document.getElementById("msg").value = '';
        	} 
        	
        	function clickedit(element){
        		let form = document.querySelector('form');
        		let inputs = form.getElementsByTagName('input');
        		for(let i = 0 ; i < inputs.length ; i++){
        			inputs[i].disabled = false;
        		}
        		form.querySelector('textarea').disabled = false;
        		form.querySelector('select').disabled = false;
        		document.getElementById('btnSave').style.display = 'inline';
        		document.getElementById('btnEdit').style.display = 'none';
        		
        	}
        </script>
    </body>
</html>
