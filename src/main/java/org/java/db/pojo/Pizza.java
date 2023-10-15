package org.java.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotBlank(message = "Il nome e un campo obbligatorio")
	@Length(
			max = 100, 
			message = "il nome non puo' contenere piu di 100 caratteri"
		)
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "La descrizione e un campo obbligatiorio")
	@Length(
			max = 255,
			message = "la descrizione non puo' contenere piu di 255 caratteri"
			)
	private String description;

	@Column(nullable = false)
	@NotBlank(message = "La foto e un campo obbligatiorio")
	private String photo;

	@Column(nullable = false)
	@DecimalMin(value = "0.5" , message = "price too low")
	@DecimalMax(value ="100.0", message = "Price too high")
	private double price;
	
	@OneToMany(mappedBy = "pizza")
	private List<SpecialOffer> specialOffer;
	
	@ManyToMany
	private List<Ingredient> ingredients;

	
	public Pizza() {}
	public Pizza(String name, String description, String photo, double price, Ingredient... ingredients) throws Exception {

		setName(name);
		setDescription(description);
		setPhoto(photo);
		setPrice(price);
		setIngredients(Arrays.asList(ingredients));
	}

	//	GETTER SETTER

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public List<SpecialOffer> getSpecialOffer() {
		return specialOffer;
	}
	public void setSpecialOffer(List<SpecialOffer> specialOffer) {
		this.specialOffer = specialOffer;
	}
	public String getExplodedName() {
		String expName = name.replace(" ", "");
		return expName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public double getPrice() {
		return price;
	}
	public String getFormattedPrice() {
		
		String formattedPrice = String.format("%.2f", price);
		return formattedPrice;
	}
	public void setPrice(double price) throws Exception {
//		if (price < 0.5) {
//			throw new Exception("price must be above 0.5");
//		}
		this.price = price;
	}
	//
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void deleteIngredient(Ingredient ingredient) {
		
		getIngredients().remove(ingredient);
	}

	@Override
	public String toString() {

		return "id" + getId() + "\n" +
				"name" + getName() + "\n" +
				"price" + getPrice() + "\n" +
				"description" + getDescription() + "\n";
	}


}
