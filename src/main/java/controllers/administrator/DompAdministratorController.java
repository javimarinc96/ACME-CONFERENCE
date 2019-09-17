
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
import services.DompService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Conference;
import domain.Domp;

@Controller
@RequestMapping("/domp/administrator")
public class DompAdministratorController extends AbstractController {

	@Autowired
	private DompService				DompService;
	@Autowired
	private ConferenceService		conferenceService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		Collection<Domp> Domps;

		final int logueadoId = this.actorService.findByPrincipal().getId();
		Domps = this.DompService.getDompsByAdministrator(logueadoId);

		res = new ModelAndView("domp/list");
		res.addObject("domps", Domps);
		res.addObject("requestURI", "domp/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/listConference", method = RequestMethod.GET)
	public ModelAndView listConference(@RequestParam final int conferenceId) {

		ModelAndView res;
		Collection<Domp> OneMonthOlds;
		Collection<Domp> TwoMonthOlds;
		Collection<Domp> ThreeMonthOlds;

		OneMonthOlds = this.DompService.getDompsByOneMonthAntiquity(conferenceId);
		TwoMonthOlds = this.DompService.getDompsByOneTwoMonthAntiquity(conferenceId);
		ThreeMonthOlds = this.DompService.getDompsByTwoMonthAntiquity(conferenceId);

		res = new ModelAndView("domp/listConference");
		res.addObject("oneMonthOlds", OneMonthOlds);
		res.addObject("twoMonthOlds", TwoMonthOlds);
		res.addObject("threeMonthOlds", ThreeMonthOlds);
		res.addObject("requestURI", "domp/administrator/listConference.do?conferenceId=" + conferenceId);

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Domp pro;

		final Administrator principal = this.administratorService.findByPrincipal();

		final Collection<Conference> conferences = this.conferenceService.findPublicConferences();

		pro = this.DompService.create();
		res = this.createEditModelAndView(pro);
		res.addObject("conferences", conferences);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int dompId) {
		ModelAndView res;
		Domp a;

		a = this.DompService.findOne(dompId);
		res = this.createEditModelAndView(a);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		if (a.getDraftMode() == false)
			res = new ModelAndView("redirect:list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int dompId) {
		ModelAndView result;
		Domp a;
		a = this.DompService.findOne(dompId);
		result = new ModelAndView("domp/show");
		result.addObject("domp", a);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int dompId) {
		try{
		ModelAndView res;
		Domp a;
		a = this.DompService.findOne(dompId);
		this.DompService.delete(dompId);
		res = new ModelAndView("redirect:list.do");
		return res;
		}catch(Throwable oops){
			return new ModelAndView("redirect:list.do");
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Domp a, final BindingResult binding) {

		ModelAndView res;

		final Administrator principal = this.administratorService.findByPrincipal();

		final Collection<Conference> r2s = this.conferenceService.findPublicConferences();

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(a);
			res.addObject("conferences", r2s);
		} else
			try {
				this.DompService.save(a);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				res = this.createEditModelAndView(a, "Domp.commit.error");
				res.addObject("conferences", r2s);
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Domp a) {
		ModelAndView res;

		res = this.createEditModelAndView(a, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Domp a, final String messageCode) {
		ModelAndView res;

		if (a.getId() == 0)
			res = new ModelAndView("domp/create");
		else
			res = new ModelAndView("domp/edit");

		res.addObject("domp", a);
		res.addObject("message", messageCode);

		return res;
	}

}
