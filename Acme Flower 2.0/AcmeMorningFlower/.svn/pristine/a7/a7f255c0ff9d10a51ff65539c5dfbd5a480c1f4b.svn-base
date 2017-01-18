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

import domain.Flower;
import domain.Tag;

import services.FlowerService;
import services.TagService;

@Controller
@RequestMapping("/flower/administrator")
public class AdministratorFlowerController extends AbstractController{
	@Autowired
	private FlowerService flowerService;
	@Autowired
	private TagService tagService;	
	//List
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView flowerList() {
		ModelAndView result;
		String requestUri="flower/administrator/list.do";	
		
		Collection<Flower> flowers=flowerService.findAll();
		
		result = new ModelAndView("flower/list");
		result.addObject("requestURI", requestUri);
		result.addObject("flowers", flowers);
		return result;
	}

	//Delete and undeleting
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int flowerId) {
		ModelAndView result;
		
		try{
			flowerService.delete(flowerId);
			result=new ModelAndView("redirect:list.do");
		} catch(Throwable oops){
			result=new ModelAndView("redirect:list.do");
			result.addObject("message","flower.delete.error");
		}
		return result;
	}
	@RequestMapping(value="/undelete", method = RequestMethod.GET)
	public ModelAndView undelete(@RequestParam int flowerId) {
		ModelAndView result;
		
		try{
			flowerService.undelete(flowerId);
			result=new ModelAndView("redirect:list.do");
		} catch(Throwable oops){
			result=new ModelAndView("redirect:list.do");
			result.addObject("message","flower.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;	
		Flower flower=flowerService.create();	
			
		result = createEditModelAndView(flower);
		return result;
	}
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int flowerId) {
		ModelAndView result;	
		Flower flower=flowerService.findOne(flowerId);	
			
		result = createEditModelAndView(flower);
		return result;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Flower flower,BindingResult binding) {
		ModelAndView result;
		Assert.notNull(flower);
		if(binding.hasErrors()){
			result=createEditModelAndView(flower);
		} else{
			try{
				flowerService.save(flower);
				result=new ModelAndView("redirect:list.do");
			} catch(Throwable oops){
				result=createEditModelAndView(flower,"flower.commit.error");
			}
		}
		return result;
	}
	// Ancillary methods
	protected ModelAndView createEditModelAndView(Flower flower){
		ModelAndView result;
		result=createEditModelAndView(flower,null);		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Flower flower, String message){
		ModelAndView result;
		
		String returnUri="flower/administrator/list.do";
		String requestUri="flower/administrator/edit.do";	
		Collection<Tag> tags=tagService.findActives();
		
		result=new ModelAndView("flower/administrator/edit");
		result.addObject("flower", flower);
		result.addObject("tags", tags);
		result.addObject("message", message);
		result.addObject("requestURI", requestUri);
		result.addObject("returnURI", returnUri);
		
		return result;
	}
}
