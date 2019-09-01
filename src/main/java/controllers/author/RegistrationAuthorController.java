
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConferenceService;
import services.CustomizationService;
import services.RegistrationService;
import controllers.AbstractController;
import domain.Conference;
import domain.Registration;

@Controller
@RequestMapping("/registration/author")
public class RegistrationAuthorController extends AbstractController {

	@Autowired
	private RegistrationService		RegistrationService;
	@Autowired
	private ConferenceService		conferenceService;
	@Autowired
	private CustomizationService	customizationService;
	@Autowired
	private ActorService			actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView res;
		Collection<Registration> registrations;

		final int logueadoId = this.actorService.findByPrincipal().getId();
		registrations = this.RegistrationService.findByAuthor(logueadoId);

		res = new ModelAndView("registration/list");
		res.addObject("registrations", registrations);
		res.addObject("requestURI", "registration/author/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		Registration pro;

		final Collection<Conference> conferences = this.conferenceService.findForthcomingConferences();
		final Collection<String> brands = this.customizationService.getCustomization().getBrandNames();

		pro = this.RegistrationService.create();
		res = this.createEditModelAndView(pro);

		res.addObject("conferences", conferences);
		res.addObject("brands", brands);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView Show(@RequestParam final Integer registrationId) {
		ModelAndView result;

		Registration s;

		s = this.RegistrationService.findOne(registrationId);

		result = new ModelAndView("registration/show");

		result.addObject("registration", s);

		return result;
	}

	// Save---------------------------------------------------------------

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Registration c, final BindingResult binding) {
		ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.findForthcomingConferences();
		final Collection<String> brands = this.customizationService.getCustomization().getBrandNames();

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(c);
			System.out.println(binding.getAllErrors());
			result.addObject("conferences", conferences);
			result.addObject("brands", brands);
		} else
			try {
				this.RegistrationService.save(c);
				result = new ModelAndView("redirect:/registration/author/list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(c, "registration.commit.error");
				result.addObject("conferences", conferences);
				result.addObject("brands", brands);
			}

		return result;
	}

	//Ancilalry methods

	protected ModelAndView createEditModelAndView(final Registration a) {
		ModelAndView res;

		res = this.createEditModelAndView(a, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Registration a, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("registration/create");
		res.addObject("registration", a);
		res.addObject("message", messageCode);

		return res;
	}

}
