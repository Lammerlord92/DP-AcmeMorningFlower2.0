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

import domain.Doughnut;

import services.DoughnutService;

@Controller
@RequestMapping("/doughnut/administrator")
public class AdministratorDoughnutController extends AbstractController {
	@Autowired
	private DoughnutService doughnutService;

	// List and keywords
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView doughnutList() {
		ModelAndView result;
		String requestUri = "doughnut/administrator/list.do";

		Collection<Doughnut> doughnuts = doughnutService.findAll();

		result = new ModelAndView("doughnut/administrator/list");
		result.addObject("requestURI", requestUri);
		result.addObject("doughnuts", doughnuts);
		return result;
	}

	// cancell and uncancell
	@RequestMapping(value = "/cancell", method = RequestMethod.GET)
	public ModelAndView cancell(@RequestParam int doughnutId) {
		ModelAndView result;

		try {
			doughnutService.delete(doughnutId);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("message", "doughnut.cancell.error");
		}
		return result;
	}

	@RequestMapping(value = "/uncancell", method = RequestMethod.GET)
	public ModelAndView uncancell(@RequestParam int doughnutId) {
		ModelAndView result;

		try {
			doughnutService.undelete(doughnutId);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result.addObject("message", "doughnut.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Doughnut doughnut = doughnutService.create();

		result = createEditModelAndView(doughnut);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int doughnutId) {
		ModelAndView result;
		Doughnut doughnut = doughnutService.findOne(doughnutId);

		result = createEditModelAndView(doughnut);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Doughnut doughnut, BindingResult binding) {
		ModelAndView result;
		Assert.notNull(doughnut);
		if (binding.hasErrors()) {
			result = createEditModelAndView(doughnut);
		} else {
			try {
				doughnutService.save(doughnut);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(doughnut,
						"doughnut.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods
	protected ModelAndView createEditModelAndView(Doughnut doughnut) {
		ModelAndView result;
		result = createEditModelAndView(doughnut, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Doughnut doughnut,
			String message) {
		ModelAndView result;

		String returnUri = "doughnut/administrator/list.do";
		String requestUri = "doughnut/administrator/edit.do";

		result = new ModelAndView("doughnut/administrator/edit");
		result.addObject("doughnut", doughnut);
		result.addObject("message", message);
		result.addObject("requestURI", requestUri);
		result.addObject("returnURI", returnUri);

		return result;
	}
}
