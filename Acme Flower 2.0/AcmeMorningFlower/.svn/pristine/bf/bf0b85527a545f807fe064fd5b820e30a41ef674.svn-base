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

import domain.FlowerOrder;
import domain.Tag;

import services.FlowerOrderService;
import services.TagService;

@Controller
@RequestMapping("/order/administrator")
public class AdministratorFlowerOrderController extends AbstractController{
	@Autowired
	private FlowerOrderService flowerOrderService;
	//List
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView flowerList() {
		ModelAndView result;
		String requestUri="order/administrator/list.do";	
		
		Collection<FlowerOrder> flowerOrders=flowerOrderService.findAll();
		
		result = new ModelAndView("order/administrator/list");
		result.addObject("requestURI", requestUri);
		result.addObject("flowerOrders", flowerOrders);
		return result;
	}

	//Mark as deliver
	@RequestMapping(value="/deliver", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int orderId) {
		ModelAndView result;
		
		try{
			flowerOrderService.deliver(orderId);
			result=new ModelAndView("redirect:list.do");
		} catch(Throwable oops){
			result=new ModelAndView("redirect:list.do");
		}
		return result;
	}
	
}
