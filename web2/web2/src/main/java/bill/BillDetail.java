package bill;

import book.Book;

public class BillDetail {
	private int quantity;
	private Book book;
	private int billid; // cái này có vẻ ko cần. nhưng cứ thêm . tý nữa mà ko cần thì xóa
	public BillDetail() {
		super();
	}
	public BillDetail(int quantity, Book book, int billid) {
		super();
		this.quantity = quantity;
		this.book = book;
		this.billid = billid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getBillid() {
		return billid;
	}
	public void setBillid(int billid) {
		this.billid = billid;
	}
	@Override
	public String toString() {
		return "BillDetail [quantity=" + quantity + ", book=" + book + ", billid=" + billid + "]";
	}
	
	
}
