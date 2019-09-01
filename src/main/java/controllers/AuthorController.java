
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AuthorService;
import domain.Author;

@Controller
@RequestMapping("/author")
public class AuthorController extends AbstractController {

	@Autowired
	private AuthorService	authorService;

	@Autowired
	private ActorService	actorService;


	//List ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final String message) {

		final Collection<Author> authors;

		authors = this.authorService.findAll();

		final ModelAndView result = new ModelAndView("author/list");
		result.addObject("authors", authors);
		result.addObject("message", message);
		result.addObject("requestURI", "author/list.do");

		return result;
	}

	// Create -------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final Author author = this.authorService.create();

		final ModelAndView result = this.createEditModelAndView(author);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Author author, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(author);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.authorService.save(author);
				result = new ModelAndView("redirect:/");

			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(author, "author.commit.error");
			}
		return result;

	}
	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		final Author author = this.authorService.findOne(this.actorService.findByPrincipal().getId());
		result = this.editModelAndView(author);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Author author, final BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.editModelAndView(author);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.authorService.save(author);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.editModelAndView(author, "author.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}

		return result;
	}

	//Ancillary methods ------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Author author) {

		ModelAndView result;
		result = this.createEditModelAndView(author, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final Author author, final String message) {

		ModelAndView result;
		result = new ModelAndView("author/create");
		result.addObject("author", author);
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView editModelAndView(final Author author) {

		ModelAndView result;
		result = this.editModelAndView(author, null);
		return result;

	}

	protected ModelAndView editModelAndView(final Author author, final String message) {

		ModelAndView result;
		result = new ModelAndView("author/edit");
		result.addObject("author", author);
		result.addObject("message", message);
		return result;
	}

}
