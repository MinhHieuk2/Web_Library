package cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private int id;
	private List<CartItem> cartItems = null;

	public Cart() {
		if (cartItems == null)
			cartItems = new ArrayList<>();
	}

	public Cart(List<CartItem> cartItems) {
		super();
		this.cartItems = cartItems;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem getCartItemById(int id) {
		for (CartItem ci : cartItems) {
			if (ci.getBookid() == id) {
				return ci;
			}
		}
		return null;
	}

	public int getQuantityById(int id) {
		return getCartItemById(id).getQuantity();
	}

	public void addCartItem(CartItem ci) {
		if (getCartItemById(ci.getBookid()) != null) {
			CartItem citemp = getCartItemById(ci.getBookid());
			citemp.setQuantity(citemp.getQuantity() + ci.getQuantity());
		} else {
			cartItems.add(ci);
		}

	}

	public void removeCartItem(int id) {
		if (getCartItemById(id) != null) {
			cartItems.remove(getCartItemById(id));
		}
	}

}
