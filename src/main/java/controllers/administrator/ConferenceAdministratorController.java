
package controllers.administrator;

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

import services.ConferenceService;
import controllers.AbstractController;
import domain.Conference;

@Controller
@RequestMapping("/conference/administrator")
public class ConferenceAdministratorController extends AbstractController {

	@Autowired
	private ConferenceService	ConferenceService;


	@RequestMapping(value = "/listForthcoming", method = RequestMethod.GET)
	public ModelAndView listForthcoming() {

		ModelAndView res;
		Collection<Conference> forthcoming;

		forthcoming = this.ConferenceService.findForthcomingConferences();

		res = new ModelAndView("conference/listPublic");
		res.addObject("conferences", forthcoming);
		res.addObject("requestURI", "conference/administrator/listForthcoming.do");

		return res;
	}

	@RequestMapping(value = "/listRunning", method = RequestMethod.GET)
	public ModelAndView listRunning() {

		ModelAndView res;
		Collection<Conference> running;

		running = this.ConferenceService.findRunningConferences();

		res = new ModelAndView("conference/listPublic");
		res.addObject("conferences", running);
		res.addObject("requestURI", "conference/administrator/listRunning.do");

		return res;
	}

	@RequestMapping(value = "/listPast", method = RequestMethod.GET)
	public ModelAndView listPast() {

		ModelAndView res;
		Collection<Conference> past;

		past = this.ConferenceService.findPastConferences();

		res = new ModelAndView("conference/listPublic");
		res.addObject("conferences", past);
		res.addObject("requestURI", "conference/administrator/listPast.do");

		return res;
	}

	@RequestMapping(value = "/searcher", method = RequestMethod.GET)
	public ModelAndView anonymousList(@RequestParam(required = false) final String searcher) {

		ModelAndView res;
		String requestURI = "conference/administrator/searcher.do";
		Collection<Conference> searchedConferences = new HashSet<Conference>();

		if (searcher != null) {
			searchedConferences = this.ConferenceService.searcherConferences(searcher);
			requestURI += "?searcher" + searcher;
		}

		res = new ModelAndView("conference/listPublic");
		res.addObject("conferences", searchedConferences);
		res.addObject("requestURI", requestURI);

		return res;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		final Collection<Conference> all;
		Collection<Conference> camera;
		Collection<Conference> notification;
		Collection<Conference> submission;
		Collection<Conference> start;

		camera = this.ConferenceService.findCameraDeadline5Conferences();
		notification = this.ConferenceService.findNotificationDeadline5Conferences();
		submission = this.ConferenceService.findSubmissionDeadline5Conferences();
		start = this.ConferenceService.findStartDate5Conferences();
		all = this.ConferenceService.findAll();

		res = new ModelAndView("conference/list");

		res.addObject("submission", submission);
		res.addObject("notification", notification);
		res.addObject("camera", camera);
		res.addObject("start", start);
		res.addObject("all", all);

		res.addObject("requestURI", "conference/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Conference pro;

		pro = this.ConferenceService.create();
		res = this.createEditModelAndView(pro);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int conferenceId) {
		ModelAndView res;
		Conference c;

		c = this.ConferenceService.findOne(conferenceId);

		res = this.createEditModelAndView(c);

		if (c.getDraftMode() == false)
			res = new ModelAndView("redirect:list.do");

		return res;
	}

	// Save ---------------------------------------------------------------

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Conference c, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(c);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.ConferenceService.save(c);
				result = new ModelAndView("redirect:/conference/administrator/list.do");

			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(c, "conference.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Conference a) {
		ModelAndView res;

		res = this.createEditModelAndView(a, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Conference a, final String messageCode) {
		ModelAndView res;

		if (a.getId() == 0)
			res = new ModelAndView("conference/create");
		else
			res = new ModelAndView("conference/edit");
		res.addObject("conference", a);
		res.addObject("message", messageCode);

		return res;
	}

}
