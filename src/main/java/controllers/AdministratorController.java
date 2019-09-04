
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final Administrator administrator = this.administratorService.create();

		final ModelAndView result = this.createEditModelAndView(administrator);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(administrator);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:/");

			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(administrator, "administrator.commit.error");
			}

		return result;
	}

	// Edit ------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		final Administrator admin = this.administratorService.findByPrincipal();

		Assert.notNull(admin);
		res = this.editModelAndView(admin);

		return res;
	}

	// Save del Edit----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Administrator admin, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.editModelAndView(admin);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.administratorService.save(admin);
				res = new ModelAndView("redirect:/");

			} catch (final Throwable oops) {
				res = this.editModelAndView(admin, "administrator.commit.error");
			}

		return res;
	}
	// Dashboard -----------------------------------------------------------
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {

		final ModelAndView result;

		// Queries
		final Double avgFeeConference = this.administratorService.avgFeeConference();
		final int maxFeeConference = this.administratorService.maxFeeConference();
		final int minFeeConference = this.administratorService.minFeeConference();
		final Double stddevFeeConference = this.administratorService.stddevFeeConference();

		final Double avgRegistrationsConference = this.administratorService.avgRegistrationsConference();
		final int maxRegistrationsConference = this.administratorService.maxRegistrationsConference();
		final int minRegistrationsConference = this.administratorService.minRegistrationsConference();
		final Double stddevRegistrationsConference = this.administratorService.stddevRegitrationsConference();

		final Double avgSubmissionsConference = this.administratorService.avgSubmissionsConference();
		final int maxSubmissionsConference = this.administratorService.maxSubmissionsConference();
		final int minSubmissionsConference = this.administratorService.minSubmissionsConference();
		final Double stddevSubmissionsConference = this.administratorService.stddevSubmissionsConference();

		final Double avgDaysConference = this.administratorService.avgDaysConference();
		final int maxDaysConference = this.administratorService.maxDaysConference();
		final int minDaysConference = this.administratorService.minDaysConference();
		final Double stddevDaysConference = this.administratorService.stddevDaysConference();
		
		final Double avgConferenceCategory = this.administratorService.avgConferenceCategory();
		final int maxConferenceCategory = this.administratorService.maxConferenceCategory();
		final int minConferenceCategory = this.administratorService.minConferenceCategory();
		final Double stddevConferenceCategory = this.administratorService.stddevConferenceCategory();
		
		final Double avgCommentsConference = this.administratorService.avgCommentsConference();
		final int maxCommentsConference = this.administratorService.maxCommentsConference();
		final int minCommentsConference = this.administratorService.minCommentsConference();
		final Double stddevCommentsConference = this.administratorService.stddevCommentsConference();
		
		final Double avgCommentsActivity = this.administratorService.avgCommentsActivity();
		final int maxCommentsActivity = this.administratorService.maxCommentsActivity();
		final int minCommentsActivity = this.administratorService.minCommentsActivity();
		final Double stddevCommentsActivity = this.administratorService.stddevCommentsActivity();

		result = new ModelAndView("administrator/dashboard");

		result.addObject("avgFeeConference", avgFeeConference);
		result.addObject("maxFeeConference", maxFeeConference);
		result.addObject("minFeeConference", minFeeConference);
		result.addObject("stddevFeeConference", stddevFeeConference);

		result.addObject("avgRegistrationsConference", avgRegistrationsConference);
		result.addObject("maxRegistrationsConference", maxRegistrationsConference);
		result.addObject("minRegistrationsConference", minRegistrationsConference);
		result.addObject("stddevRegistrationsConference", stddevRegistrationsConference);

		result.addObject("avgSubmissionsConference", avgSubmissionsConference);
		result.addObject("maxSubmissionsConference", maxSubmissionsConference);
		result.addObject("minSubmissionsConference", minSubmissionsConference);
		result.addObject("stddevSubmissionsConference", stddevSubmissionsConference);

		result.addObject("avgDaysConference", avgDaysConference);
		result.addObject("maxDaysConference", maxDaysConference);
		result.addObject("minDaysConference", minDaysConference);
		result.addObject("stddevDaysConference", stddevDaysConference);
		
		result.addObject("avgConferenceCategory", avgConferenceCategory);
		result.addObject("maxConferenceCategory", maxConferenceCategory);
		result.addObject("minConferenceCategory", minConferenceCategory);
		result.addObject("stddevConferenceCategory", stddevConferenceCategory);
		
		result.addObject("avgCommentsConference", avgCommentsConference);
		result.addObject("maxCommentsConference", maxCommentsConference);
		result.addObject("minCommentsConference", minCommentsConference);
		result.addObject("stddevCommentsConference", stddevCommentsConference);
		
		result.addObject("avgCommentsActivity", avgCommentsActivity);
		result.addObject("maxCommentsActivity", maxCommentsActivity);
		result.addObject("minCommentsActivity", minCommentsActivity);
		result.addObject("stddevCommentsActivity", stddevCommentsActivity);

		return result;
	}

	//Ancillary methods ------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Administrator administrator) {

		ModelAndView result;
		result = this.createEditModelAndView(administrator, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String message) {

		ModelAndView result;
		result = new ModelAndView("administrator/create");
		result.addObject("administrator", administrator);
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView editModelAndView(final Administrator administrator) {

		ModelAndView result;
		result = this.editModelAndView(administrator, null);
		return result;

	}

	protected ModelAndView editModelAndView(final Administrator administrator, final String message) {

		ModelAndView result;
		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("message", message);
		return result;
	}

}
