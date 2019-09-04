
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.ConferenceService;
import controllers.AbstractController;
import domain.Activity;
import domain.Conference;
import domain.Panel;
import domain.Presentation;
import domain.Tutorial;

@Controller
@RequestMapping("/activity/administrator")
public class ActivityAdministratorController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private ActivityService		activityService;


	//List ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final Integer conferenceId) {

		final Collection<Activity> activities;

		activities = this.activityService.findActivitiesByConference(conferenceId);

		final List<Tutorial> tutorials = new ArrayList<Tutorial>();
		final List<Panel> panels = new ArrayList<Panel>();
		final List<Presentation> presentations = new ArrayList<Presentation>();

		for (final Activity a : activities)
			if (a instanceof Tutorial) {
				final Tutorial t = (Tutorial) a;
				tutorials.add(t);
			} else if (a instanceof Panel) {
				final Panel p = (Panel) a;
				panels.add(p);
			} else if (a instanceof Presentation) {
				final Presentation p = (Presentation) a;
				presentations.add(p);
			}

		final Conference c = this.conferenceService.findOne(conferenceId);

		ModelAndView result = new ModelAndView("activity/list");

		if (c.getDraftMode().equals(true))
			result = new ModelAndView("redirect:welcome/index.do");

		result.addObject("conference", c);
		
		result.addObject("tutorials", tutorials);
		result.addObject("panels", panels);
		result.addObject("presentations", presentations);

		result.addObject("conferenceId", conferenceId);

		result.addObject("requestURI", "activity/administrator/list.do?conferenceId=" + conferenceId);

		return result;
	}
}
