package org.java.controller;

import org.java.db.pojo.Ingredient;
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

import jakarta.validation.Valid;

@Controller
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	
	// INDEX
	@GetMapping("ingredient/index")
	public String getIndex(Model model) {
		
		model.addAttribute("ingredients", ingredientService.findAll());
		
		return "ingredient/index";
	}
	
	// CREATE
	@GetMapping("ingredient/create")
	public String create(
			Model model) {
			
		model.addAttribute("newIngredient", new Ingredient());
		model.addAttribute("allPizze", pizzaService.findAll());
		
		return "ingredient/create";
	}
	
	// STORE 
	@PostMapping("ingredient/create")
	public String store(
			Model model,
			@Valid @ModelAttribute Ingredient ingredient,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "ingredient/create";
		}
		
		
		ingredientService.save(ingredient);
		
		
		
		return "redirect:/ingredient/index";
	}
	
	// UPDATE
	@GetMapping("ingredient/update/{id}")
	public String getUpdate(
			@PathVariable int id,
			Model model) {
		
		model.addAttribute("newIngredient", ingredientService.findById(id));
		
		return "ingredient/create";
	}
	
	@PostMapping("ingredient/update/{id}")
	public String update(
			@Valid @ModelAttribute ("newIngredient") Ingredient ingredient,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return "ingredient/create";
			}
		
		try {
			ingredientService.save(ingredient);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("New ingredient updated on db");
	   
		return "redirect:/ingredient/index";
	}
	
	@PostMapping("ingredient/delete/{id}")
	public String delete(
			@PathVariable int id) {
		
		Ingredient ingredientToDelete = ingredientService.findById(id);
		
		for (Pizza p : ingredientToDelete.getPizze()) {
			
			p.deleteIngredient(ingredientToDelete);
			pizzaService.save(p);
		}
		
		ingredientService.delete(ingredientToDelete);
		
		
		return "redirect:/ingredient/index";
	}

}
