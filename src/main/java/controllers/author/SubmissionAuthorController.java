
package controllers.author;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AuthorService;
import services.ConferenceService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Actor;
import domain.Author;
import domain.Conference;
import domain.Submission;

@Controller
@RequestMapping("/submission/author")
public class SubmissionAuthorController extends AbstractController {

	@Autowired
	private SubmissionService	SubmissionService;
	@Autowired
	private ConferenceService	conferenceService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private AuthorService		authorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView res;
		Collection<Submission> submissions;

		final Date d = new Date();

		final int logueadoId = this.actorService.findByPrincipal().getId();
		submissions = this.SubmissionService.findByAuthor(logueadoId);

		res = new ModelAndView("submission/list");
		res.addObject("submissions", submissions);
		res.addObject("d", d);
		res.addObject("requestURI", "submission/author/list.do");

		return res;
	}

	@RequestMapping(value = "/listByConference", method = RequestMethod.GET)
	public ModelAndView listByConference(@RequestParam final Integer conferenceId) {

		ModelAndView res;
		Collection<Submission> accepted;
		Collection<Submission> rejected;
		Collection<Submission> underReview;

		accepted = this.SubmissionService.findStatusByConferenceId(conferenceId, "ACCEPTED");
		rejected = this.SubmissionService.findStatusByConferenceId(conferenceId, "REJECTED");
		underReview = this.SubmissionService.findStatusByConferenceId(conferenceId, "UNDER-REVIEW");

		final Conference c = this.conferenceService.findOne(conferenceId);

		final Boolean b = c.getSubmissionDeadline().before(new Date());

		res = new ModelAndView("submission/listConference");
		res.addObject("b", b);
		res.addObject("accepted", accepted);
		res.addObject("rejected", rejected);
		res.addObject("underReview", underReview);

		res.addObject("requestURI", "submission/author/listByConference.do?conferenceId=" + conferenceId);

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		Submission pro;

		final Collection<Conference> conferences = this.conferenceService.findAvailableConferences();

		pro = this.SubmissionService.create();
		res = this.createEditModelAndView(pro);

		res.addObject("conferences", conferences);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView Show(@RequestParam final Integer submissionId) {
		ModelAndView result;

		Submission s;

		s = this.SubmissionService.findOne(submissionId);

		result = new ModelAndView("submission/show");

		result.addObject("submission", s);

		return result;
	}

	@RequestMapping(value = "/cameraReady", method = RequestMethod.GET)
	public ModelAndView uploadCameraVersion(@RequestParam final int submissionId) {

		ModelAndView result;

		Submission s;

		s = this.SubmissionService.findOne(submissionId);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);
		final Author a = this.authorService.findByPrincipal();

		if (!s.getAuthor().equals(a))
			result = new ModelAndView("redirect:list.do");

		result = new ModelAndView("submission/camera");

		result.addObject("submission", s);

		return result;
	}

	// Save---------------------------------------------------------------

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Submission c, final BindingResult binding) {
		ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.findAvailableConferences();

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(c);
			System.out.println(binding.getAllErrors());
			result.addObject("conferences", conferences);
		} else
			try {
				this.SubmissionService.save(c);
				result = new ModelAndView("redirect:/submission/author/list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(c, "submission.commit.error");
				result.addObject("conferences", conferences);
			}

		return result;
	}

	// Save Camera ---------------------------------------------------------------

	@RequestMapping(value = "/saveCamera", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCamera(@Valid final Submission c, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createCameraModelAndView(c);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.SubmissionService.save(c);
				result = new ModelAndView("redirect:/submission/author/list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createCameraModelAndView(c, "submission.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/decission", method = RequestMethod.GET)
	public ModelAndView makeDecission(@RequestParam final int submissionId) {

		ModelAndView result;

		this.SubmissionService.makeDecission(submissionId);

		final int conferenceId = this.SubmissionService.findOne(submissionId).getConference().getId();

		result = new ModelAndView("redirect:/submission/author/listByConference.do?conferenceId=" + conferenceId);

		return result;
	}

	@RequestMapping(value = "/assignReviewers", method = RequestMethod.GET)
	public ModelAndView assignReviewers(@RequestParam final int submissionId) {

		ModelAndView result;

		this.SubmissionService.assignReviewers(submissionId);

		final int conferenceId = this.SubmissionService.findOne(submissionId).getConference().getId();

		result = new ModelAndView("redirect:/submission/author/listByConference.do?conferenceId=" + conferenceId);

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Submission a) {
		ModelAndView res;

		res = this.createEditModelAndView(a, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Submission a, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("submission/create");
		res.addObject("submission", a);
		res.addObject("message", messageCode);

		return res;
	}

	protected ModelAndView createCameraModelAndView(final Submission a) {
		ModelAndView res;

		res = this.createCameraModelAndView(a, null);

		return res;
	}

	protected ModelAndView createCameraModelAndView(final Submission a, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("submission/camera");
		res.addObject("submission", a);
		res.addObject("message", messageCode);

		return res;
	}

}
