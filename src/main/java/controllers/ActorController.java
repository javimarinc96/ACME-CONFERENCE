
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
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private ActorService	actorService;


	//List ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final String message) {

		final Collection<Actor> actors;

		actors = this.actorService.findAll();

		final ModelAndView result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("message", message);
		result.addObject("requestURI", "actor/list.do");

		return result;
	}

	//Edit------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView save() {

		ModelAndView result;
		final Actor actor = this.actorService.findByPrincipal();
		result = this.createEditModelAndView(actor);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Actor actor, final BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(actor);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.actorService.save(actor);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actor, "actor.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}

		return result;
	}

	//Ancillary methods------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Actor actor) {

		ModelAndView result;
		result = this.createEditModelAndView(actor, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final Actor actor, final String message) {

		ModelAndView result;
		result = new ModelAndView("actor/edit");
		result.addObject("actor", actor);
		result.addObject("message", message);
		return result;
	}

}
