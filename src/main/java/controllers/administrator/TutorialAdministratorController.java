
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TutorialService;
import controllers.AbstractController;
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/tutorial/administrator")
public class TutorialAdministratorController extends AbstractController {

	@Autowired
	private TutorialService	tutorialService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId) {
		ModelAndView res;
		final Tutorial t;

		t = this.tutorialService.create();
		res = this.createEditModelAndView(t, conferenceId);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId) {
		ModelAndView res;
		Tutorial tutorial;

		tutorial = this.tutorialService.findOne(tutorialId);
		res = this.editModelAndView(tutorial);

		res.addObject("tutorial", tutorial);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int tutorialId) {
		ModelAndView result;
		Tutorial tutorial;
		tutorial = this.tutorialService.findOne(tutorialId);
		final Collection<Section> sections = tutorial.getSections();
		result = new ModelAndView("tutorial/show");
		result.addObject("tutorial", tutorial);
		result.addObject("sections", sections);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int tutorialId) {
		ModelAndView result;

		Tutorial res;

		res = this.tutorialService.findOne(tutorialId);

		this.tutorialService.delete(res);
		result = new ModelAndView("redirect:/conference/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int conferenceId, @Valid final Tutorial sp, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(sp, conferenceId);
		} else
			try {
				this.tutorialService.save(sp, conferenceId);
				res = new ModelAndView("redirect:/conference/administrator/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sp, conferenceId, "tutorial.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Tutorial sp, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.editModelAndView(sp);
		} else
			try {
				this.tutorialService.saveEdit(sp);
				res = new ModelAndView("redirect:/conference/administrator/list.do");
			} catch (final Throwable oops) {
				res = this.editModelAndView(sp, "tutorial.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Tutorial sp, final int conferenceId) {
		ModelAndView res;

		res = this.createEditModelAndView(sp, conferenceId, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Tutorial sp, final int conferenceId, final String messageCode) {
		ModelAndView res;

		if (sp.getId() == 0)
			res = new ModelAndView("tutorial/create");
		else
			res = new ModelAndView("tutorial/edit");
		res.addObject("tutorial", sp);
		res.addObject("conferenceId", conferenceId);
		res.addObject("message", messageCode);

		return res;
	}

	protected ModelAndView editModelAndView(final Tutorial sp) {
		ModelAndView res;

		res = this.editModelAndView(sp, null);

		return res;
	}

	protected ModelAndView editModelAndView(final Tutorial sp, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("tutorial/edit");
		res.addObject("tutorial", sp);
		res.addObject("message", messageCode);

		return res;
	}

}
