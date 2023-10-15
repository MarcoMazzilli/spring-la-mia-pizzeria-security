package org.java.db.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;

@Entity
public class SpecialOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = false)
	@Future
	private LocalDate expirationDate;
	
	@Column(nullable = false)
	@Length(min = 1, max= 255 , message = "Length must be from 1 to 255 character")
	private String title;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	public SpecialOffer() {};
	public SpecialOffer(LocalDate startDate,LocalDate expirationDate,String title , Pizza pizza) {
		
		setStartDate(startDate);
		setExpirationDate(expirationDate);
		setTitle(title);
		setPizza(pizza);
	};
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	

	
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	// HTML DATE
	
	public String getHtmlStartDate() {

		return getStartDate() == null
				? null
				: getStartDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
	
	public void setHtmlStartDate(String date) {
		setStartDate(LocalDate.parse(date));
	}
	
	public String getHtmlExpirationDate() {

		return getExpirationDate() == null
				? null
				: getExpirationDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
	
	public void setHtmlExpirationDate(String date) {
		setExpirationDate(LocalDate.parse(date));
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	// GET ~ SET Pizza
	
	public Pizza getPizza() {
		return pizza;
	}
	
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String toString() {
		return "Title : " + getTitle() +
				"StartDate : " + getStartDate() +
				"ExpirationDate" + getExpirationDate();
	}
}
