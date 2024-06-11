package review;

import java.sql.Date;

import account.Account;

public class Review {
	private Date created;
	private String content;
	private float star;
	private int bookid;
	private Account account;
	public Review() {
		super();
	}
	public Review(String content, float star) {
		super();
		this.content = content;
		this.star = star;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getStar() {
		return star;
	}
	public void setStar(float star) {
		this.star = star;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
