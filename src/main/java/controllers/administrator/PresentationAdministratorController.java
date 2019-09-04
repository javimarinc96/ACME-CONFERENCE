
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
import services.PresentationService;
import controllers.AbstractController;
import domain.Conference;
import domain.Presentation;

@Controller
@RequestMapping("/presentation/administrator")
public class PresentationAdministratorController extends AbstractController {

	@Autowired
	private PresentationService	presentationService;
	
	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId) {
		ModelAndView res;
		final Presentation t;

		t = this.presentationService.create();
		res = this.createEditModelAndView(t, conferenceId);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int presentationId) {
		ModelAndView res;
		Presentation presentation;

		presentation = this.presentationService.findOne(presentationId);
		res = this.editModelAndView(presentation);

		res.addObject("presentation", presentation);
		
		final Conference c = this.conferenceService.findConferenceByActivity(presentationId);
		
		if(c.getStartDate().before(new Date())){
			res = new ModelAndView("redirect:/conference/administrator/list.do");
		}

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int presentationId) {
		ModelAndView result;
		Presentation presentation;
		presentation = this.presentationService.findOne(presentationId);
		result = new ModelAndView("presentation/show");
		result.addObject("presentation", presentation);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int presentationId) {
		ModelAndView result;
		try{
			
		

		Presentation res;

		res = this.presentationService.findOne(presentationId);

		this.presentationService.delete(res);
		result = new ModelAndView("redirect:/conference/administrator/list.do");

		return result;
		}catch(Throwable oops){
			return new ModelAndView("redirect:/conference/administrator/list.do");
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int conferenceId, @Valid final Presentation sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp, conferenceId);
		} else
			try {
				this.presentationService.save(sp, conferenceId);
				res = new ModelAndView("redirect:/conference/administrator/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, conferenceId, "presentation.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Presentation sp, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.editModelAndView(sp);
		} else
			try {
				this.presentationService.saveEdit(sp);
				res = new ModelAndView("redirect:/conference/administrator/list.do");
			} catch (final Throwable oops) {
				res = this.editModelAndView(sp, "presentation.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Presentation sp, final int conferenceId) {
		ModelAndView res;

		res = this.createEditModelAndView(sp, conferenceId, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Presentation sp, final int conferenceId, final String messageCode) {
		ModelAndView res;

		if (sp.getId() == 0)
			res = new ModelAndView("presentation/create");
		else
			res = new ModelAndView("presentation/edit");
		res.addObject("presentation", sp);
		res.addObject("conferenceId", conferenceId);
		res.addObject("message", messageCode);

		return res;
	}

	protected ModelAndView editModelAndView(final Presentation sp) {
		ModelAndView res;

		res = this.editModelAndView(sp, null);

		return res;
	}

	protected ModelAndView editModelAndView(final Presentation sp, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("presentation/edit");
		res.addObject("presentation", sp);
		res.addObject("message", messageCode);

		return res;
	}

}
