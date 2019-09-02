
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AuthorService;
import services.CategoryService;
import services.FinderService;
import controllers.AbstractController;
import domain.Actor;
import domain.Author;
import domain.Category;
import domain.Conference;
import domain.Finder;

@Controller
@RequestMapping("/finder/author")
public class FinderAuthorController extends AbstractController {

	// Constructors --------------------------------------

	public FinderAuthorController() {
		super();

	}


	// Services ----------------------------------------------

	@Autowired
	private FinderService	finderService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private AuthorService	authorService;

	@Autowired
	private CategoryService	categoryService;


	// Edit ------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);
		final Author a = this.authorService.findByPrincipal();

		final Collection<Category> cats = this.categoryService.findAll();

		final Finder f = a.getFinder();

		Assert.notNull(f);
		res = this.createEditModelAndView(f);
		res.addObject("categories", cats);

		return res;
	}
	// Save del Edit----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView res;
		final Collection<Category> cats = this.categoryService.findAll();

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(finder);
			res.addObject("categories", cats);
		} else
			try {
				this.finderService.saveResults(finder);
				res = new ModelAndView("redirect:/finder/author/edit.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(finder, "finder.commit.error");
				res.addObject("categories", cats);
			}

		return res;
	}

	// Ancillary methods
	// ---------------------------------------------------------------

	private ModelAndView createEditModelAndView(final Finder finder) {

		return this.createEditModelAndView(finder, null);
	}

	private ModelAndView createEditModelAndView(final Finder finder, final String message) {

		final Collection<Conference> conferences = finder.getConferences();

		final ModelAndView res = new ModelAndView("finder/edit");

		res.addObject("finder", finder);
		res.addObject("message", message);
		res.addObject("conferences", conferences);

		return res;

	}
}
