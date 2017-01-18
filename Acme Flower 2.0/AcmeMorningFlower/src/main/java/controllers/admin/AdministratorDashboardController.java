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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Currency;
import domain.Doughnut;
import domain.Flower;

import services.CurrencyService;
import services.DoughnutService;
import services.FlowerOrderService;
import services.FlowerService;

@Controller
@RequestMapping("/administrator")
public class AdministratorDashboardController extends AbstractController {
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private FlowerOrderService flowerOrderService;
	@Autowired
	private DoughnutService doughnutService;
	@Autowired
	private CurrencyService currencyService;
	
	// Constructors -----------------------------------------------------------
	
	public AdministratorDashboardController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/dashboard")
	public ModelAndView dashboard(@RequestParam(required=false, defaultValue="EUR") String currency) {
		ModelAndView result;
		Collection<Flower> bestSellingFlowers=flowerService.bestSelledFlowers();
		Collection<Flower> neverOrderedFlowers=flowerService.notOrderedFlowers();
		Collection<Object[]> flowerOrderAvg=flowerOrderService.avgOrdersByCustomer();
		Collection<Object[]> numOrdersFlowers=flowerOrderService.numOrdersFlowers();
		
		Double ratioOrdersNotIncludeDoughnut=flowerOrderService.ratioOrdersNotIncludeDoughnut();
		Double ratioNonCancelledOrdersNotIncludeDoughnut=flowerOrderService.ratioNonCancelledOrdersNotIncludeDoughnut();
		Collection<Object[]> ratioOrdersNotIncludeDoughnutByCustomer=flowerOrderService.ratioOrdersNotIncludeDoughnutByCustomer();
		Collection<Object[]> ratioOrdersNotCancelledNotIncludeDoughnutByCustomer=flowerOrderService.ratioOrdersNotCancelledNotIncludeDoughnutByCustomer();
		Double ratioNotAvaliableFlowers=flowerService.notAvaliableFlowers();
		Double ratioNotActiveDoughnut=doughnutService.ratioNotActives();
		
		result = new ModelAndView("administrator/dashboard");
		result.addObject("requestURI", "administrator/dashboard.do");
		result.addObject("bestSellingFlowers", bestSellingFlowers);
		result.addObject("neverOrderedFlowers", neverOrderedFlowers);
		result.addObject("flowerOrderAvg", flowerOrderAvg);
		result.addObject("flowerOrderNumber", numOrdersFlowers);
		
		result.addObject("ratioOrdersNotIncludeDoughnut", ratioOrdersNotIncludeDoughnut);
		result.addObject("ratioNonCancelledOrdersNotIncludeDoughnut", ratioNonCancelledOrdersNotIncludeDoughnut);
		result.addObject("ratioOrdersNotIncludeDoughnutByCustomer", ratioOrdersNotIncludeDoughnutByCustomer);
		result.addObject("ratioOrdersNotCancelledNotIncludeDoughnutByCustomer", ratioOrdersNotCancelledNotIncludeDoughnutByCustomer);
		result.addObject("ratioNotAvaliableFlowers", ratioNotAvaliableFlowers);
		result.addObject("ratioNotActiveDoughnut", ratioNotActiveDoughnut);
		
		String message="ok";
		Currency actual=currencyService.findByName(currency);
		if(actual==null){
			message="flower.currencyProblem";
			actual=currencyService.findByName("EUR");
		}
		Collection<Currency> currencies=currencyService.findAll();
		result.addObject("actual",actual);
		result.addObject("currencies", currencies);
		result.addObject("key", false);
		result.addObject("message",message);
		return result;
	}
	
	
}