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
@RequestMapping("/tag/administrator")
public class AdministratorTagController extends AbstractController{
	@Autowired
	private TagService tagService;	
	//List and keywords
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView tagList() {
		ModelAndView result;
		String requestUri="tag/administrator/list.do";	
		
		Collection<Tag> tags=tagService.findAll();
		
		result = new ModelAndView("tag/administrator/list");
		result.addObject("requestURI", requestUri);
		result.addObject("tags", tags);
		return result;
	}

	//cancell and uncancell
	@RequestMapping(value="/cancell", method = RequestMethod.GET)
	public ModelAndView cancell(@RequestParam int tagId) {
		ModelAndView result;
		
		try{
			tagService.delete(tagId);
			result=new ModelAndView("redirect:list.do");
		} catch(Throwable oops){
			result=new ModelAndView("redirect:list.do");
			result.addObject("message","tag.cancell.error");
		}
		return result;
	}
	@RequestMapping(value="/uncancell", method = RequestMethod.GET)
	public ModelAndView uncancell(@RequestParam int tagId) {
		ModelAndView result;
		
		try{
			tagService.unDelete(tagId);
			result=new ModelAndView("redirect:list.do");
		} catch(Throwable oops){
			result=new ModelAndView("redirect:list.do");
			result.addObject("message","tag.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;	
		Tag tag=tagService.create();	
			
		result = createEditModelAndView(tag);
		return result;
	}
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int tagId) {
		ModelAndView result;	
		Tag tag=tagService.findOne(tagId);	
			
		result = createEditModelAndView(tag);
		return result;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Tag tag,BindingResult binding) {
		ModelAndView result;
		Assert.notNull(tag);
		if(binding.hasErrors()){
			result=createEditModelAndView(tag);
		} else{
			try{
				tagService.save(tag);
				result=new ModelAndView("redirect:list.do");
			} catch(Throwable oops){
				result=createEditModelAndView(tag,"tag.commit.error");
			}
		}
		return result;
	}
	// Ancillary methods
	protected ModelAndView createEditModelAndView(Tag tag){
		ModelAndView result;
		result=createEditModelAndView(tag,null);		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Tag tag, String message){
		ModelAndView result;
		
		String returnUri="tag/administrator/list.do";
		String requestUri="tag/administrator/edit.do";	
		Collection<Flower> flowers=tag.getFlowers();
		
		result=new ModelAndView("tag/administrator/edit");
		result.addObject("tag", tag);
		result.addObject("flowers", flowers);
		result.addObject("message", message);
		result.addObject("requestURI", requestUri);
		result.addObject("returnURI", returnUri);
		
		return result;
	}
}
