
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
import services.TopicService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Topic;

@Controller
@RequestMapping("/topic/administrator")
public class TopicAdministratorController extends AbstractController {

	@Autowired
	private TopicService	TopicService;
	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		Collection<Topic> topics;

		topics = this.TopicService.findAll();

		res = new ModelAndView("topic/list");
		res.addObject("topics", topics);
		res.addObject("requestURI", "topic/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Topic topic;

		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, logueado);

		topic = this.TopicService.create();
		res = this.createEditModelAndView(topic);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int topicId) {
		ModelAndView res;
		Topic Topic;

		Topic = this.TopicService.findOne(topicId);

		res = this.createEditModelAndView(Topic);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int topicId) {
		ModelAndView result;
		Topic sp;

		sp = this.TopicService.findOne(topicId);
		result = new ModelAndView("topic/show");
		result.addObject("topic", sp);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int topicId) {

		ModelAndView res;
		Topic sp;
		sp = this.TopicService.findOne(topicId);

		this.TopicService.delete(sp);
		res = new ModelAndView("redirect:list.do");
		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Topic Topic, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(Topic);
		} else
			try {
				this.TopicService.save(Topic);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops);
				res = this.createEditModelAndView(Topic, "topic.commit.error");
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Topic Topic) {
		ModelAndView res;

		res = this.createEditModelAndView(Topic, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Topic Topic, final String messageCode) {
		ModelAndView res;

		if (Topic.getId() == 0)
			res = new ModelAndView("topic/create");
		else
			res = new ModelAndView("topic/edit");
		res.addObject("topic", Topic);
		res.addObject("message", messageCode);

		return res;
	}

}
