package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Flower;

import services.FlowerService;

@Controller
@RequestMapping("/flower")
public class UnregisteredFlowerController extends AbstractController{
	@Autowired
	private FlowerService flowerService;
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView flowerList() {
		ModelAndView result;
		String requestUri="flower/list.do";	
		
		Collection<Flower> flowers=flowerService.findAllUndeleted();
		
		result = new ModelAndView("flower/list");
		result.addObject("requestURI", requestUri);
		result.addObject("flowers", flowers);
		return result;
	}
	@RequestMapping(value="/searchByKeyword", method = RequestMethod.GET)
	public ModelAndView searchByKeyword(@RequestParam String key) {
		ModelAndView result;
		String requestUri="flower/searchByKeyword.do?key="+key;	
		
		Collection<Flower> flowers=flowerService.findByKeyword(key);
		
		result = new ModelAndView("flower/list");
		result.addObject("requestURI", requestUri);
		result.addObject("flowers", flowers);
		return result;
	}
	
}
