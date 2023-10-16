package org.java.controller;

import java.util.List;

import org.java.db.pojo.Pizza;
import org.java.db.service.IngredientService;
import org.java.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
public class ControllerMain {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;

	@GetMapping("/")
	public String getIndex(
			Model model,
			@RequestParam(required = false) String pizzaName
			) {


		List<Pizza> arrayPizze = pizzaName == null
				? pizzaService.findAll()
				: pizzaService.findByName(pizzaName);


		int countPizze = arrayPizze.size();


		model.addAttribute("arrayPizze", arrayPizze);
		model.addAttribute("countPizze", countPizze);

		return "index";
	}

	@GetMapping("/pizza/{id}")
	public String show(@PathVariable int id,Model model) {

		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		

		return "pizza/show";
	}

	@GetMapping("admin/pizza/create")
	public String create(Model model) {

		model.addAttribute("newPizza" , new Pizza());

		return "pizza/create";
	}


	@PostMapping("/admin/pizza/create")
	public String store(
			@Valid @ModelAttribute ("newPizza") Pizza pizza,
			BindingResult bindingResult,
			Model model){
		
		if(bindingResult.hasErrors()) {
			return "pizza/create";
			}
		
		try {
			pizzaService.save(pizza);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("New Pizza saved on db");

		return "redirect:/";
	}
	
	@GetMapping("admin/pizza/update/{id}")
	public String getUpdate(
			@PathVariable int id,
			Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("newPizza", pizza);
		model.addAttribute("ingredients", ingredientService.findAll());
		
		return "pizza/create";
	}
	
	@PostMapping("admin/pizza/update/{id}")
	public String update(
			@Valid @ModelAttribute ("newPizza") Pizza pizza,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return "pizza/create";
			}
		
		try {
			pizzaService.save(pizza);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("New Pizza updated on db");
	   
		return "redirect:/";
		
	}
	
	@PostMapping("admin/pizza/delete/{id}")
	public String delete(@PathVariable int id) {
		
		Pizza pizzaToDelete = pizzaService.findById(id);
		pizzaService.deletePizza(pizzaToDelete);
		
		return "redirect:/";
	}

}
