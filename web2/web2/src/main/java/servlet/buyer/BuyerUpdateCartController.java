package servlet.buyer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.Cart;
import cart.CartDao;
import cart.CartItem;
@WebServlet("/user/updateCart")

public class BuyerUpdateCartController extends HttpServlet{
	
	CartDao cartDao = new CartDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bidString = req.getParameter("bid");
		String qtyString = req.getParameter("qty");
		
		int bid = Integer.parseInt(bidString);
		int qty = Integer.parseInt(qtyString);
		
		//Lấy cart từ session trước
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		
		//lấy CartItem có bookid = bid
		CartItem ci = cart.getCartItemById(bid);
		// update object cart
		ci.setQuantity(qty);
		// update trong db
		cartDao.updateCartItem(ci);
		
	}
}
