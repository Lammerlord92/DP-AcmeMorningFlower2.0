package controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdditionalCostService;
import services.CommentService;
import controllers.AbstractController;
import domain.AdditionalCost;
import domain.AdditionalCost;
import forms.CommentForm;

@Controller
@RequestMapping("/additionalCost")
public class AdministratorAdditionalCostController extends AbstractController{
	@Autowired
	private AdditionalCostService additionalCostService;
	//Update costs
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;	
		AdditionalCost additionalCost=additionalCostService.findAdditionalCost();	
		
			
		result = createEditModelAndView(additionalCost);
		return result;
	}
		
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="save")
	public ModelAndView save(@Valid AdditionalCost additionalCost,BindingResult binding) {
		ModelAndView result;
		Assert.notNull(additionalCost);
		if(binding.hasErrors()){
			result=createEditModelAndView(additionalCost);
		} else{
			try{
				additionalCostService.save(additionalCost);
				result=new ModelAndView("redirect:../");
			} catch(Throwable oops){
				result=createEditModelAndView(additionalCost,"additionalCost.commit.error");
			}
		}
		return result;
	}
	
	// Ancillary methods
	protected ModelAndView createEditModelAndView(AdditionalCost additionalCost){
		ModelAndView result;
		result=createEditModelAndView(additionalCost,null);		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(AdditionalCost additionalCost, String message){
		ModelAndView result;

		String requestUri="additionalCost/edit";	
		
		result=new ModelAndView("additionalCost/edit");
		result.addObject("additionalCost", additionalCost);
		result.addObject("message", message);
		result.addObject("requestUri", requestUri);
		return result;
	}
}
