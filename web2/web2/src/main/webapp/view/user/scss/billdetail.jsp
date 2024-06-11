<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url value="/view/user" var="url"/>
<!DOCTYPE html>
<html lang="en" >

<body>

    <!-- content bill detail-->
    <div class="container-fluid">
        <div class="row justify-content-center">
        	<div class="col-2"></div>
        	<div class="col-8">
	            <div class="col-12 border border-dark bd-dark d-flex justify-content-between bg-secondary text-dark">
	            	<strong>Mã hóa đơn #${bill.id}</strong>
	            	<span>Ngày mua : ${bill.saleday}</span>
	            </div>
	            <div class="col-12 border border-dark">
	            	<div class="row">
		            	<div class="col-6"><p>Tên người nhận</p></div>
		            	<div class="col-6"><p>${bill.receiver}</p></div>
	            	</div>
	            	<div class="row">
		            	<div class="col-6"><p>Sđt người nhận</p></div>
		            	<div class="col-6"><p>${bill.telReceiver}</p></div>
	            	</div>
	            	<div class="row">
		            	<div class="col-6"><p>Địa chỉ</p></div>
		            	<div class="col-6"><p>${bill.addressReceiver}</p></div>
	            	</div>
	            	<div class="row">
		            	<div class="col-6"><p>Email</p></div>
		            	<div class="col-6"><p>${bill.emailReceiver}</p></div>
	            	</div>
	            	<div class="row">
		            	<div class="col-6"><p>Zipcode</p></div>
		            	<div class="col-6"><p>${bill.zipcode}</p></div>
	            	</div>
	            </div>
	            <div class="col-12 border border-dark">
	            	<h1>các sản phẩm</h1>
	            	<table class="table text-center">
	            		<thead class="bg-secondary text-dark">
	            			<th>Sản phẩm</th>
	            			<th>Số lượng</th>
	            		</thead>
	            		<tbody class="align-middle">
	            			
	            			<c:forEach var="billdetail" items="${bill.billDetails}">
	            				<tr>
		            				<td class="align-middle">
		            					<a href="<c:url value="/user/detail?bid=${billdetail.book.id}"/>" 
				                            style="color : black; text-decoration:none" >
			            					<img src="${pageContext.request.contextPath}/${billdetail.book.cover}" 
			            					alt="" style="width: 50px;"> ${billdetail.book.title }
		            					</a>
	            					</td>
		            				<td>${billdetail.quantity}</td>
		            			</tr>	
	            			
	            			</c:forEach>
	            		
	            		</tbody>
	            	</table>
	            </div>
	            <div class="col-12 border border-dark bg-dark text-center" >
	            	<button class="btn btn-secondary" onclick="cancelBill(${bill.id})">Hủy đơn hàng</button>
	            </div>
            </div>
            <div class="col-2"></div>
        </div>
    </div>
    <!-- content bill detail End -->
</body>

</html>