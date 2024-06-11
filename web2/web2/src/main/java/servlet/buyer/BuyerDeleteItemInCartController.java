package servlet.buyer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import cart.Cart;
import cart.CartDao;
@WebServlet("/user/deteleItemInCart")
public class BuyerDeleteItemInCartController extends HttpServlet{
	CartDao cartDao = new CartDao();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bidString = req.getParameter("bid");
		int bid = Integer.parseInt(bidString);
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		cartDao.removeCartItem(cart.getCartItemById(bid));
		
		cart.removeCartItem(bid);
		
		session.setAttribute("cart", cart);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("sizecart", cart.getCartItems().size());
		resp.setContentType("application/json");
		PrintWriter pr =  resp.getWriter();
		pr.print(jsonObject);
		pr.flush();		
	}
}
