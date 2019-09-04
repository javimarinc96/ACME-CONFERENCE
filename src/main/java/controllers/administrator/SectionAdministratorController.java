
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

import services.SectionService;
import services.TutorialService;
import controllers.AbstractController;
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/section/administrator")
public class SectionAdministratorController extends AbstractController {

	//Constructors ----------------------

	public SectionAdministratorController() {
		super();
	}


	//Services -----------------------------

	@Autowired
	private SectionService	sectionService;

	@Autowired
	private TutorialService	tutorialService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final Integer tutorialId) {
		final Collection<Section> sections;
		final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
		sections = tutorial.getSections();

		final ModelAndView result = new ModelAndView("section/list");
		result.addObject("sections", sections);
		result.addObject("requestURI", "section/administrator/list.do?tutorialId=" + tutorialId);

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView Display(@RequestParam final Integer sectionId) {
		ModelAndView result;
		Section section;

		section = this.sectionService.findOne(sectionId);

		result = new ModelAndView("section/show");
		result.addObject("section", section);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer tutorialId) {

		ModelAndView res;
		Section pro;

		pro = this.sectionService.create();
		res = this.createEditModelAndView(pro, tutorialId);

		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int tutorialId, @Valid final Section c, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(c, tutorialId);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.sectionService.save(c, tutorialId);
				result = new ModelAndView("redirect:/tutorial/administrator/show.do?tutorialId=" + tutorialId);

			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(c, tutorialId, "submission.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int sectionId) {
		
		try{
			
		
		ModelAndView result;

		Section res;

		res = this.sectionService.findOne(sectionId);

		this.sectionService.delete(res);

		result = new ModelAndView("redirect:/conference/administrator/list.do");

		return result;
		}catch(Throwable oops){
			return new ModelAndView("redirect:/conference/administrator/list.do");
		}
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Section a, final int tutorialId) {
		ModelAndView res;

		res = this.createEditModelAndView(a, tutorialId, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Section a, final int tutorialId, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("section/create");
		res.addObject("section", a);
		res.addObject("tutorialId", tutorialId);
		res.addObject("message", messageCode);

		return res;
	}

}
