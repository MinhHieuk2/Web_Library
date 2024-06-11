package servlet.buyer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import account.Account;
import book.BookDao;
import cart.Cart;
import cart.CartDao;
import cart.CartItem;

@WebServlet("/user/addToCart")
public class BuyerAddToCartServlet extends HttpServlet {
	CartDao cartDao = new CartDao();
	BookDao bookDao = new BookDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account == null) { // chua dang nhap thi ko thuc hien add to cart
			// yeu cau dang nhap

			JSONObject jsonObject = new JSONObject();
			jsonObject.append("redirect", req.getContextPath() + "/user/login");

			PrintWriter pr = resp.getWriter();
			resp.setContentType("application/json");

			pr.print(jsonObject);
			pr.flush();

		} else {
			if (account.getIsAdmin() == 0) {
				// thuc hiện thêm vào giỏ hàng
				String bidString = req.getParameter("bid");

				String quantityString = req.getParameter("qty");
				int quantity = Integer.parseInt(quantityString);

				if (bidString != null) {
					// lấy cart từ session ra
					Cart cart = (Cart) session.getAttribute("cart");
					List<CartItem> cartItems = cart.getCartItems();
					int bid = Integer.parseInt(bidString);
					// kiểm tra :
					// kiểm tra quyển sách vừa bấm addToCart đã có trong cart hay chưa

					CartItem ci = cart.getCartItemById(bid);

					if (ci == null) {// TH1: chưa có
						// Tạo 1 CartItem mới, set các thuộc tính -> thêm vào Cart
						ci = new CartItem();
						ci.setBookid(bid);
						ci.setCartid(cart.getId());
						ci.setQuantity(quantity);
						ci.setBook(bookDao.getBookById(bid));
						// việc 1: thêm cartitem mới vào object Cart
						cart.addCartItem(ci);
						// việc 2: thêm cartitem mới vào csdl
						cartDao.addCartItem(ci);

					} else {// Th2: đã có
							// Lấy cartItem từ cart -> set lại số lượng cho cartItem
							// tồn tại cartitem trong cart rồi
							// việc 1 : update số lượng cartitem đó
						ci.setQuantity(ci.getQuantity() + quantity);
						// việc 2: update lại trong database
						cartDao.updateCartItem(ci);

					}

					session.setAttribute("cart", cart);

					JSONObject jsonObject = new JSONObject();
					jsonObject.append("sizecart", cart.getCartItems().size());
					PrintWriter pr = resp.getWriter();
					resp.setContentType("application/json");
					resp.setCharacterEncoding("utf-8");
					pr.print(jsonObject);
					pr.flush();

				}
			}
		}

	}
}
