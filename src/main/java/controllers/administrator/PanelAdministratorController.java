
package controllers.administrator;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.PanelService;
import controllers.AbstractController;
import domain.Conference;
import domain.Panel;

@Controller
@RequestMapping("/panel/administrator")
public class PanelAdministratorController extends AbstractController {

	@Autowired
	private PanelService	panelService;
	
	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId) {
		ModelAndView res;
		final Panel t;

		t = this.panelService.create();
		res = this.createEditModelAndView(t, conferenceId);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int panelId) {
		ModelAndView res;
		Panel panel;

		panel = this.panelService.findOne(panelId);
		res = this.editModelAndView(panel);

		res.addObject("panel", panel);
		
		final Conference c = this.conferenceService.findConferenceByActivity(panelId);
		
		if(c.getStartDate().before(new Date())){
			res = new ModelAndView("redirect:/conference/administrator/list.do");
		}

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int panelId) {
		ModelAndView result;
		Panel panel;
		panel = this.panelService.findOne(panelId);
		result = new ModelAndView("panel/show");
		result.addObject("panel", panel);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int panelId) {
		ModelAndView result;
		try{

		Panel res;

		res = this.panelService.findOne(panelId);

		this.panelService.delete(res);
		result = new ModelAndView("redirect:/conference/administrator/list.do");

		return result;
		}catch(Throwable oops){
			return new ModelAndView("redirect:/conference/administrator/list.do");
		}
		
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int conferenceId, @Valid final Panel sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp, conferenceId);
		} else
			try {
				this.panelService.save(sp, conferenceId);
				res = new ModelAndView("redirect:/conference/administrator/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, conferenceId, "panel.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Panel sp, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.editModelAndView(sp);
		} else
			try {
				this.panelService.saveEdit(sp);
				res = new ModelAndView("redirect:/conference/administrator/list.do");
			} catch (final Throwable oops) {
				res = this.editModelAndView(sp, "panel.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Panel sp, final int conferenceId) {
		ModelAndView res;

		res = this.createEditModelAndView(sp, conferenceId, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Panel sp, final int conferenceId, final String messageCode) {
		ModelAndView res;

		if (sp.getId() == 0)
			res = new ModelAndView("panel/create");
		else
			res = new ModelAndView("panel/edit");
		res.addObject("panel", sp);
		res.addObject("conferenceId", conferenceId);
		res.addObject("message", messageCode);

		return res;
	}

	protected ModelAndView editModelAndView(final Panel sp) {
		ModelAndView res;

		res = this.editModelAndView(sp, null);

		return res;
	}

	protected ModelAndView editModelAndView(final Panel sp, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("panel/edit");
		res.addObject("panel", sp);
		res.addObject("message", messageCode);

		return res;
	}

}
