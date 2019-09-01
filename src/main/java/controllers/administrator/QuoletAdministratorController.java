
package controllers.administrator;

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

import services.ActorService;
import services.AdministratorService;
import services.ConferenceService;
import services.QuoletService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Conference;
import domain.Quolet;

@Controller
@RequestMapping("/quolet/administrator")
public class QuoletAdministratorController extends AbstractController {

	@Autowired
	private QuoletService			QuoletService;
	@Autowired
	private ConferenceService		conferenceService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		Collection<Quolet> Quolets;

		final int logueadoId = this.actorService.findByPrincipal().getId();
		Quolets = this.QuoletService.getQuoletsByAdministrator(logueadoId);

		res = new ModelAndView("quolet/list");
		res.addObject("quolets", Quolets);
		res.addObject("requestURI", "quolet/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/listConference", method = RequestMethod.GET)
	public ModelAndView listConference(@RequestParam final int conferenceId) {

		ModelAndView res;
		Collection<Quolet> OneMonthOlds;
		Collection<Quolet> TwoMonthOlds;
		Collection<Quolet> ThreeMonthOlds;

		OneMonthOlds = this.QuoletService.getQuoletsByAntiquity(conferenceId, 1);
		TwoMonthOlds = this.QuoletService.getQuoletsByAntiquity(conferenceId, 2);
		ThreeMonthOlds = this.QuoletService.getQuoletsByAntiquity(conferenceId, 3);

		res = new ModelAndView("quolet/listConference");
		res.addObject("oneMonthOlds", OneMonthOlds);
		res.addObject("twoMonthOlds", TwoMonthOlds);
		res.addObject("threeMonthOlds", ThreeMonthOlds);
		res.addObject("requestURI", "quolet/administrator/listConference.do?conferenceId=" + conferenceId);

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Quolet pro;

		final Administrator principal = this.administratorService.findByPrincipal();

		final Collection<Conference> conferences = this.conferenceService.findPublicConferences();

		pro = this.QuoletService.create();
		res = this.createEditModelAndView(pro);
		res.addObject("conferences", conferences);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int quoletId) {
		ModelAndView res;
		Quolet a;

		a = this.QuoletService.findOne(quoletId);
		res = this.createEditModelAndView(a);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		if (a.getDraftMode() == false)
			res = new ModelAndView("redirect:list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int quoletId) {
		ModelAndView result;
		Quolet a;
		a = this.QuoletService.findOne(quoletId);
		result = new ModelAndView("quolet/show");
		result.addObject("quolet", a);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int quoletId) {

		ModelAndView res;
		Quolet a;
		a = this.QuoletService.findOne(quoletId);
		this.QuoletService.delete(quoletId);
		res = new ModelAndView("redirect:list.do");
		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Quolet a, final BindingResult binding) {

		ModelAndView res;

		final Administrator principal = this.administratorService.findByPrincipal();

		final Collection<Conference> r2s = this.conferenceService.findPublicConferences();

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(a);
			res.addObject("conferences", r2s);
		} else
			try {
				this.QuoletService.save(a);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				res = this.createEditModelAndView(a, "quolet.commit.error");
				res.addObject("conferences", r2s);
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Quolet a) {
		ModelAndView res;

		res = this.createEditModelAndView(a, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Quolet a, final String messageCode) {
		ModelAndView res;

		if (a.getId() == 0)
			res = new ModelAndView("quolet/create");
		else
			res = new ModelAndView("quolet/edit");

		res.addObject("quolet", a);
		res.addObject("message", messageCode);

		return res;
	}

}
