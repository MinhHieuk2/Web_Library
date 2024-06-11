package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import book.BookDao;
import dao.ConnectDB;

public class CartDao {
	
	BookDao bookDao =  new BookDao();
	
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public void createNewCart(int accountid) {
		String sql = "insert into cart(id, accountid) values(?, ?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, accountid);
			pstm.setInt(2, accountid);
			pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Cart getCartById(int accountid) {
		String sql = "select * \r\n"
				+ "from cart c join cartitem ci join book b\r\n"
				+ "on c.id = ci.cartid and ci.bookid = b.id and b.is_availabel = 1 "
				+ " and c.accountid = " + accountid;
		Cart cart = new Cart();
		cart.setId(accountid);
		List<CartItem> cartItems = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				CartItem ci = new CartItem();
				ci.setCartid(rs.getInt("cartid"));
				ci.setBookid(rs.getInt("bookid"));
				ci.setQuantity(rs.getInt("quantity"));
				ci.setBook(bookDao.getBookById(rs.getInt("bookid")));
				cartItems.add(ci);				
			}
			cart.setCartItems(cartItems);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	public void addCartItem(CartItem ci) {
		String sql = "insert into cartitem(cartid, bookid, quantity) "
				+ " values(?, ?, ?) ";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ci.getCartid());
			pstm.setInt(2, ci.getBookid());
			pstm.setInt(3, ci.getQuantity());
			
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateCartItem(CartItem ci ) {
		String sql = "update cartitem set quantity = ? where bookid = ? and cartid = ?";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,  ci.getQuantity());
			pstm.setInt(2, ci.getBookid());
			pstm.setInt(3, ci.getCartid());
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeCartItem(CartItem ci) {
		String sql = "delete from cartitem where bookid = ? and cartid = ?";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ci.getBookid());
			pstm.setInt(2, ci.getCartid());
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
