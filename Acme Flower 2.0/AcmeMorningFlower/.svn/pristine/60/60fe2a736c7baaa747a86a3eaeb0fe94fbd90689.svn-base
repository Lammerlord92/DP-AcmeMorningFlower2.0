package controllers.admin;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Currency;
import domain.Doughnut;

import services.CurrencyService;

@Controller
@RequestMapping("/currency/administrator")
public class AdministratorCurrencyController extends AbstractController {
	@Autowired
	private CurrencyService currencyService;

	@RequestMapping(value = "/currencies", method = RequestMethod.GET)
	public ModelAndView currencyList(@RequestParam(required=false, defaultValue="0") int currencyId) {
		ModelAndView result;
		
		String message=null;
		Currency currency;
		if(currencyId==0){
			currency=currencyService.create();
		}else{
			currency=currencyService.findOne(currencyId);
			if(currency.getName()=="EUR"){
				message="currency.notEditable";
				currency=currencyService.create();
			}
		}
		result=createEditModelAndViewAdd(currency,message);
		return result;
	}	
	
	//	Add currency  ----------------------------------------------------------
	
	@RequestMapping(value="/currencies",method=RequestMethod.POST,params="save")		
	public ModelAndView save(@Valid Currency currency, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result=createEditModelAndViewAdd(currency);
		} else{
			try{
				currencyService.save(currency);
				result=createEditModelAndViewAdd(currency);
			} catch(Throwable oops){
				result=createEditModelAndViewAdd(currency,"currency.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods
	protected ModelAndView createEditModelAndViewAdd(Currency currency) {
		ModelAndView result;
		result = createEditModelAndViewAdd(currency, null);
		return result;
	}

	protected ModelAndView createEditModelAndViewAdd(Currency currency,
			String message) {
		ModelAndView result;

		String requestUri = "currency/administrator/currencies.do?currencyId="+currency.getId();
		Collection<Currency> currencies=currencyService.findAll();
		result = new ModelAndView("currency/administrator/currencies");
		result.addObject("currency", currency);
		result.addObject("currencies", currencies);
		result.addObject("message", message);
		result.addObject("requestURI", requestUri);

		return result;
	}
}
