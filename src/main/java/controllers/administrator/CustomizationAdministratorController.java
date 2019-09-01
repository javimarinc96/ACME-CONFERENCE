
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomizationService;
import controllers.AbstractController;
import domain.Customization;

@Controller
@RequestMapping("/customization/administrator")
public class CustomizationAdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CustomizationAdministratorController() {
		super();
	}


	// Services ---------------------------------------------------------------
	@Autowired
	private CustomizationService	customizationService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Customization customization;

		customization = this.customizationService.getCustomization();
		result = this.createEditModelAndView(customization);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Customization customization, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(customization);
		else
			try {
				this.customizationService.save(customization);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(customization, "customization.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Customization customization) {
		ModelAndView result;

		result = this.createEditModelAndView(customization, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Customization customization, final String message) {
		ModelAndView result;

		result = new ModelAndView("customization/edit");

		result.addObject("message", message);
		result.addObject("customization", customization);

		return result;

	}

}
