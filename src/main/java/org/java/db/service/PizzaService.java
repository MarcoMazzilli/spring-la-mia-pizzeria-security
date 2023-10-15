package org.java.db.service;

import java.util.List;

import org.java.db.pojo.Pizza;
import org.java.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;

	public void save(Pizza pizza) {
		pizzaRepo.save(pizza);
	}

	public List<Pizza> findAll(){

		List<Pizza> arrayPizze = pizzaRepo.findAll();

		return arrayPizze;
	}

	public Pizza findById(int id) {

		return pizzaRepo.findById(id).get();
	}

	public List<Pizza> findByName(String string){

		return pizzaRepo.findByNameContaining(string);
	}
	
	public void deletePizza(Pizza pizza) {
		
		pizzaRepo.delete(pizza);
	}

}
