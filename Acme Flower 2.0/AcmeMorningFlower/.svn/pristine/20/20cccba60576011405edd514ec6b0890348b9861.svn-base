/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Flower;

import services.FlowerOrderService;
import services.FlowerService;

@Controller
@RequestMapping("/administrator")
public class AdministratorDashboardController extends AbstractController {
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private FlowerOrderService flowerOrderService;
	// Constructors -----------------------------------------------------------
	
	public AdministratorDashboardController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/dashboard")
	public ModelAndView action1() {
		ModelAndView result;
		Collection<Flower> bestSellingFlowers=flowerService.bestSelledFlowers();
		Collection<Flower> neverOrderedFlowers=flowerService.notOrderedFlowers();
		Collection<Object[]> flowerOrderAvg=flowerOrderService.avgOrdersByCustomer();
		Collection<Object[]> numOrdersFlowers=flowerOrderService.numOrdersFlowers();
		result = new ModelAndView("administrator/dashboard");
		result.addObject("bestSellingFlowers", bestSellingFlowers);
		result.addObject("neverOrderedFlowers", neverOrderedFlowers);
		result.addObject("flowerOrderAvg", flowerOrderAvg);
		result.addObject("flowerOrderNumber", numOrdersFlowers);
		return result;
	}
	
	
}