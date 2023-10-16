package org.java;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.java.db.auth.pojo.Role;
import org.java.db.auth.pojo.User;
import org.java.db.auth.pojo.role.RoleService;
import org.java.db.auth.pojo.user.service.UserService;
import org.java.db.pojo.Ingredient;
import org.java.db.pojo.Pizza;
import org.java.db.pojo.SpecialOffer;
import org.java.db.service.IngredientService;
import org.java.db.service.PizzaService;
import org.java.db.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService spService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String imgPizza = "https://images.pexels.com/photos/10790638/pexels-photo-10790638.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1";
		
		Ingredient ingrediente1 = new Ingredient("pomodoro");
		Ingredient ingrediente2 = new Ingredient("mozzarella");
		Ingredient ingrediente3 = new Ingredient("farina");
		
		ingredientService.save(ingrediente1);
		ingredientService.save(ingrediente2);
		ingredientService.save(ingrediente3);

		List<Pizza> arrayPizze = Arrays.asList(
				new Pizza("margherita", "La regina delle pizze", imgPizza, 5.50, ingrediente1,ingrediente2,ingrediente3),
				new Pizza("diavola", "La piccantissima", imgPizza, 6.50),
				new Pizza("ananas", "L'insulto gravissimo", imgPizza, 0.50),
				new Pizza("ananas", "L'insulto gravissimo", imgPizza, 0.50),
				new Pizza("funghi e prosciutto", "La regina delle pizze", imgPizza, 5.50),
				new Pizza("vegetariana", "La piccantissima", imgPizza, 6.50),
				new Pizza("capricciosa ", "L'insulto gravissimo", imgPizza, 0.50),
				new Pizza("napoletana", "La regina delle pizze", imgPizza, 5.50),
				new Pizza("senza lattosio", "La piccantissima", imgPizza, 6.50),
				new Pizza("gourmet", "L'insulto gravissimo", imgPizza, 0.50)
				);

		arrayPizze.forEach(p -> pizzaService.save(p));
		Pizza p1 = new Pizza("Test oneToMany", "tesTest", imgPizza, 8.00, ingrediente1,ingrediente2,ingrediente3);
		pizzaService.save(p1);
		
		SpecialOffer sp1 = new SpecialOffer(LocalDate.now(), LocalDate.parse("2024-01-01"), "Offerta della settimana",p1);
		spService.save(sp1);
		
		SpecialOffer sp2 = new SpecialOffer(LocalDate.now(), LocalDate.parse("2024-01-01"), "Offerta del mese",p1);
		spService.save(sp2);
		
		String psw = new BCryptPasswordEncoder() .encode("password");
		
		Role admin = new Role("ADMIN");
		Role user = new Role("User");
		
		roleService.save(admin);
		roleService.save(user);
		
		User mvmAdmin = new User("Admin", psw, admin,user);
		User mvmUser = new User("User", psw, user);
		
		userService.save(mvmAdmin);
		userService.save(mvmUser);


		System.out.println("Salvataggio dell'elemento andato a buon fine");

	}

}
