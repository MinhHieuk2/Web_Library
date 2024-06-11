package book;

import java.sql.Date;

import category.Category;

public class Book {
	private int id;
	private String title;
	private String author;
	private String description;
	private Date releasedate;
	private int pages;
	private String cover;
	private Category category;
	
	public Book() {
		super();
	}
	
	

	
	public Book(int id, String title, String author, String description, Date releasedate, int pages, String cover,
			Category category) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.releasedate = releasedate;
		this.pages = pages;
		this.cover = cover;
		this.category = category;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", category=" + category + ", release=" + releasedate
				+ ", pages=" + pages + "]";
	}
	
}
