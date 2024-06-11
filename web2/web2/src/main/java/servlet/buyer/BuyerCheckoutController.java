package servlet.buyer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import account.Account;
import bill.Bill;
import bill.BillDao;
import bill.BillDetail;
import cart.Cart;
import cart.CartDao;
import cart.CartItem;
import validation.Validation;

@WebServlet("/user/checkout")
public class BuyerCheckoutController extends HttpServlet{
	CartDao cartDao = new CartDao();
	BillDao billDao = new BillDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/user/checkout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String receiver = req.getParameter("receiver");
		String telReceiver = req.getParameter("telReceiver");
		String addressReceiver = req.getParameter("addressReceiver");
		String emailReceiver = req.getParameter("emailReceiver");
		String zipcode = req.getParameter("zipcode");
		
		// lấy account người tạo đơn
		HttpSession session= req.getSession();
		Account account = (Account) session.getAttribute("account");
		// tạo Bill
		Bill bill = new Bill();
		java.util.Date currentDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
		bill.setSaleday(sqlDate);
		
		bill.setReceiver(receiver);
		bill.setTelReceiver(telReceiver);
		bill.setAddressReceiver(addressReceiver);
		bill.setEmailReceiver(emailReceiver);
		bill.setZipcode(zipcode);
		bill.setAccount(account);
		// validate trước khi đưa vào db
		if(Validation.isBlank(receiver)) {
			sendMsgToAjax(resp, "invalidReceiver");return;
		}
		if(Validation.isBlank(telReceiver) || !Validation.isPhoneNumber(telReceiver)) {
			sendMsgToAjax(resp, "invalidTelReceiver");return;
		}
		if(Validation.isBlank(addressReceiver)) {
			sendMsgToAjax(resp, "invalidAddressReceiver");return;
		}
		if(Validation.isBlank(emailReceiver) || !Validation.isEmail(emailReceiver)) {
			sendMsgToAjax(resp, "invalidEmailReceiver");return;
		}
		if(Validation.isBlank(zipcode) || !Validation.isZipcode(zipcode)) {
			sendMsgToAjax(resp, "invalidZipcode");return;
		}
		// đẩy Bill mới này vào db
		
		billDao.createBill(bill);
		
		// thêm billdetail
		// TH1 : buynow
		if(session.getAttribute("buynow") != null) {
			BillDetail billDetail = new BillDetail();
			CartItem cartItem = (CartItem) session.getAttribute("buynow");
			billDetail.setBillid(bill.getId());
			billDetail.setBook(cartItem.getBook());
			billDetail.setQuantity(cartItem.getQuantity());
			billDao.addBillDetail(billDetail);
			
			session.removeAttribute("buynow");
		}else {		// TH2 : buy from cart
			Cart cart = (Cart) session.getAttribute("cart");
			for(CartItem ci : cart.getCartItems()) {
				BillDetail billDetail = new BillDetail();
				billDetail.setQuantity(ci.getQuantity());
				billDetail.setBook(ci.getBook());
				billDetail.setBillid(bill.getId());
				billDao.addBillDetail(billDetail);
				cartDao.removeCartItem(ci); // xóa cartitem trong db
			}
			cart.getCartItems().clear(); // xóa sạch giỏ hàng
			session.setAttribute("cart", cart);
		}

		//tạo 1 phản hồi (response) với msg là tạo thành công
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("msg", "successNewBill");
		jsonObject.append("redirect", req.getContextPath() + "/user/home");
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.print(jsonObject);
		pr.flush();
	}
	private static void sendMsgToAjax( HttpServletResponse resp, String msg) throws ServletException, IOException  {
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("msg", msg);
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.print(jsonObject);
		pr.flush();
	}
}
