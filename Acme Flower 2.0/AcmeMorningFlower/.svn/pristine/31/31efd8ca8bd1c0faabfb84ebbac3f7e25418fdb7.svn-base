package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Currency;
import domain.Flower;

import services.CurrencyService;
import services.FlowerService;

@Controller
@RequestMapping("/flower")
public class UnregisteredFlowerController extends AbstractController{
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private CurrencyService currencyService;
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView flowerList(@RequestParam(required=false, defaultValue="EUR") String currency) {
		ModelAndView result;
		String requestUri="flower/list.do";	
		
		Collection<Flower> flowers=flowerService.findAllUndeleted();
		
		
		result = new ModelAndView("flower/list");
		result.addObject("requestURI", requestUri);
		result.addObject("flowers", flowers);
		
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
	@RequestMapping(value="/searchByKeyword", method = RequestMethod.GET)
	public ModelAndView searchByKeyword(@RequestParam String key,@RequestParam(required=false, defaultValue="EUR") String currency) {
		ModelAndView result;
		String requestUri="flower/searchByKeyword.do?key="+key;	
		
		Collection<Flower> flowers=flowerService.findByKeyword(key);
		
		result = new ModelAndView("flower/list");
		result.addObject("requestURI", requestUri);
		result.addObject("flowers", flowers);
		
		String message="ok";
		Currency actual=currencyService.findByName(currency);
		if(actual==null){
			message="flower.currencyProblem";
			actual=currencyService.findByName("EUR");
		}
		Collection<Currency> currencies=currencyService.findAll();
		result.addObject("actual",actual);
		result.addObject("currencies", currencies);
		result.addObject("key", true);
		result.addObject("message",message);
		return result;
	}
	
}
