
package controllers;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CommentService;
import services.ConferenceService;
import domain.Activity;
import domain.Comment;
import domain.Conference;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	// Constructors --------------------------------------

	public CommentController() {
		super();

	}


	// Services ----------------------------------------------

	@Autowired
	private CommentService		commentService;

	@Autowired
	private ActivityService		activityService;

	@Autowired
	private ConferenceService	conferenceService;


	// List ------------------------------------------------------

	@RequestMapping(value = "/listConference", method = RequestMethod.GET)
	public ModelAndView listConference(@RequestParam final int conferenceId) {

		final Collection<Comment> comments = this.commentService.findByConference(conferenceId);

		final ModelAndView result = new ModelAndView("comment/listConference");
		result.addObject("comments", comments);
		result.addObject("conferenceId", conferenceId);
		result.addObject("requestURI", "comment/listConference.do?conferenceId=" + conferenceId);

		return result;

	}

	// List ------------------------------------------------------

	@RequestMapping(value = "/listActivity", method = RequestMethod.GET)
	public ModelAndView listActivity(@RequestParam final int activityId) {

		final Activity a = this.activityService.findOne(activityId);

		final Collection<Comment> comments = new HashSet<Comment>();

		for (final Comment c : this.commentService.findAll())
			if (c.getActivity().equals(a))
				comments.add(c);

		final ModelAndView result = new ModelAndView("comment/listActivity");
		result.addObject("comments", comments);
		result.addObject("activityId", activityId);
		result.addObject("requestURI", "comment/listConference.do?activityId=" + activityId);

		return result;

	}

	@RequestMapping(value = "/createConference", method = RequestMethod.GET)
	public ModelAndView createConference(@RequestParam final int conferenceId) {

		ModelAndView res;
		final Comment c;

		final Conference con = this.conferenceService.findOne(conferenceId);

		c = this.commentService.create();

		c.setConference(con);

		res = this.createConferenceModelAndView(c, conferenceId);

		return res;
	}

	@RequestMapping(value = "/createActivity", method = RequestMethod.GET)
	public ModelAndView createActivity(@RequestParam final int activityId) {

		ModelAndView res;
		final Comment c;

		final Activity a = this.activityService.findOne(activityId);

		c = this.commentService.create();

		c.setActivity(a);

		res = this.createActivityModelAndView(c, activityId);

		return res;
	}

	// Save del Edit----------------------------------------------

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comment comment, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			if (comment.getActivity() != null)
				res = this.createActivityModelAndView(comment, comment.getActivity().getId());
			else
				res = this.createConferenceModelAndView(comment, comment.getConference().getId());
		} else
			try {
				this.commentService.save(comment);
				if (comment.getActivity() != null)
					res = new ModelAndView("redirect:/comment/listActivity.do?activityId=" + comment.getActivity().getId());
				else
					res = new ModelAndView("redirect:/comment/listConference.do?conferenceId=" + comment.getConference().getId());

			} catch (final Throwable oops) {
				if (comment.getActivity() != null)
					res = this.createActivityModelAndView(comment, comment.getActivity().getId(), "comment.commit.error");
				else
					res = this.createConfereceModelAndView(comment, comment.getConference().getId(), "comment.commit.error");
			}

		return res;
	}

	// Ancillary methods
	// ---------------------------------------------------------------

	protected ModelAndView createConferenceModelAndView(final Comment sp, final int conferenceId) {
		ModelAndView res;

		res = this.createConfereceModelAndView(sp, conferenceId, null);

		return res;
	}

	protected ModelAndView createConfereceModelAndView(final Comment sp, final int conferenceId, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("comment/create");
		res.addObject("comment", sp);
		res.addObject("conferenceId", conferenceId);
		res.addObject("message", messageCode);

		return res;
	}

	protected ModelAndView createActivityModelAndView(final Comment sp, final int activityId) {
		ModelAndView res;

		res = this.createConfereceModelAndView(sp, activityId, null);

		return res;
	}

	protected ModelAndView createActivityModelAndView(final Comment sp, final int activityId, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("comment/create");
		res.addObject("comment", sp);
		res.addObject("activityId", activityId);
		res.addObject("message", messageCode);

		return res;
	}

}
