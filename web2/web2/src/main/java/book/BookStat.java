package book;

import java.sql.Date;

import category.Category;

public class BookStat extends Book{
	private int salesAmount;
	public BookStat() {
	}
	public BookStat(int salesAmount) {
		super();
		this.salesAmount = salesAmount;
	}

	

	public BookStat(int id, String title, String author, String description, Date releasedate, int pages, String cover,
			Category category, int salesAmount) {
		super(id, title, author, description, releasedate, pages, cover, category);
		this.salesAmount = salesAmount;
	}
	public int getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}

	@Override
	public String toString() {
		return "BookStat [salesAmount=" + salesAmount + ", getTitle()=" + getTitle() + ", getAuthor()=" + getAuthor()
				+ ", getCategory()=" + getCategory() + ", getRelease()=" + getReleasedate() + ", getCover()=" + getCover()
				+ ", getPages()=" + getPages() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	

}
