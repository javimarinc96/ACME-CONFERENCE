
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ReviewerService;
import domain.Reviewer;

@Controller
@RequestMapping("/reviewer")
public class ReviewerController extends AbstractController {

	@Autowired
	private ReviewerService	reviewerService;

	@Autowired
	private ActorService	actorService;


	//List ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final String message) {

		final Collection<Reviewer> reviewers;

		reviewers = this.reviewerService.findAll();

		final ModelAndView result = new ModelAndView("reviewer/list");
		result.addObject("reviewers", reviewers);
		result.addObject("message", message);
		result.addObject("requestURI", "reviewer/list.do");

		return result;
	}

	// Create -------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final Reviewer reviewer = this.reviewerService.create();

		final ModelAndView result = this.createEditModelAndView(reviewer);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Reviewer reviewer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(reviewer);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.reviewerService.save(reviewer);
				result = new ModelAndView("redirect:/");

			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(reviewer, "reviewer.commit.error");
			}
		return result;

	}
	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		final Reviewer reviewer = this.reviewerService.findOne(this.actorService.findByPrincipal().getId());
		result = this.editModelAndView(reviewer);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Reviewer reviewer, final BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.editModelAndView(reviewer);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.reviewerService.save(reviewer);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.editModelAndView(reviewer, "reviewer.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}

		return result;
	}

	//Ancillary methods ------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Reviewer reviewer) {

		ModelAndView result;
		result = this.createEditModelAndView(reviewer, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final Reviewer reviewer, final String message) {

		ModelAndView result;
		result = new ModelAndView("reviewer/create");
		result.addObject("reviewer", reviewer);
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView editModelAndView(final Reviewer reviewer) {

		ModelAndView result;
		result = this.editModelAndView(reviewer, null);
		return result;

	}

	protected ModelAndView editModelAndView(final Reviewer reviewer, final String message) {

		ModelAndView result;
		result = new ModelAndView("reviewer/edit");
		result.addObject("reviewer", reviewer);
		result.addObject("message", message);
		return result;
	}

}
