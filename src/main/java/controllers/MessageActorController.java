
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageService;
import services.TopicService;
import domain.Actor;
import domain.Message;
import domain.Topic;

@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController {

	//Services ----------------------------------------------------------
	@Autowired
	MessageService	messageService;

	@Autowired
	ActorService	actorService;

	@Autowired
	TopicService	topicService;


	//List----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Message> messages;

		final Actor actor = this.actorService.findByPrincipal();

		messages = this.messageService.findByActor(actor.getId());

		result = new ModelAndView("message/list");

		result.addObject("messages", messages);

		result.addObject("requestURI", "message/actor/list.do");

		return result;

	}

	//Create ---------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		System.out.println(this.actorService.findByPrincipal().getName());
		System.out.println(this.actorService.findByPrincipal().getId());

		final Collection<Topic> topics = this.topicService.findAll();

		final Message newMessage = this.messageService.create();

		res = this.createEditModelAndView(newMessage);
		res.addObject("topics", topics);

		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message newMessage, final BindingResult binding) {

		ModelAndView res;
		final Collection<Topic> topics = this.topicService.findAll();

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(newMessage);
			res.addObject("topics", topics);
		} else
			try {
				this.messageService.save(newMessage);
				res = new ModelAndView("redirect:/message/actor/list.do");
			} catch (final Throwable error) {
				error.printStackTrace();
				res = this.createEditModelAndView(newMessage, "folder.error.save");
				res.addObject("topics", topics);
			}
		return res;
	}

	//Broadcast ---------------------------------------------------------
	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	public ModelAndView broadcast() {

		ModelAndView res;

		final Message newMessage = this.messageService.create();

		final Collection<Topic> topics = this.topicService.findAll();

		res = this.broadcastModelAndView(newMessage);

		res.addObject("topics", topics);

		res.addObject("action", "message/actor/broadcast.do");

		return res;
	}

	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "save")
	public ModelAndView broadcast(final Message newMessage, final BindingResult binding) {
		ModelAndView res;
		final Collection<Topic> topics = this.topicService.findAll();

		if (binding.hasErrors()) {
			res = this.broadcastModelAndView(newMessage);
			System.out.println(binding.getAllErrors());
			res.addObject("topics", topics);
			res.addObject("action", "message/actor/broadcast.do");
		} else

			try {
				this.messageService.broadcastAll(newMessage);
				res = new ModelAndView("redirect:/message/actor/list.do");
			} catch (final Throwable error) {
				res = this.broadcastModelAndView(newMessage, "folder.error.save");
				error.printStackTrace();
				res.addObject("topics", topics);
				res.addObject("action", "message/actor/broadcast.do");
			}

		return res;

	}

	@RequestMapping(value = "/broadcastAuthors", method = RequestMethod.GET)
	public ModelAndView broadcastAuthors() {

		ModelAndView res;

		final Message newMessage = this.messageService.create();

		final Collection<Topic> topics = this.topicService.findAll();

		res = this.broadcastModelAndView(newMessage);

		res.addObject("topics", topics);

		res.addObject("action", "message/actor/broadcastAuthors.do");

		return res;
	}

	@RequestMapping(value = "/broadcastAuthors", method = RequestMethod.POST, params = "save")
	public ModelAndView broadcastAuthors(final Message newMessage, final BindingResult binding) {
		ModelAndView res;
		final Collection<Topic> topics = this.topicService.findAll();
		if (binding.hasErrors()) {
			res = this.broadcastModelAndView(newMessage);
			System.out.println(binding.getAllErrors());
			res.addObject("topics", topics);
			res.addObject("action", "message/actor/broadcastAuthors.do");
		} else
			try {
				this.messageService.broadcastAuthors(newMessage);
				res = new ModelAndView("redirect:/message/actor/list.do");
			} catch (final Throwable error) {
				error.printStackTrace();
				res = this.broadcastModelAndView(newMessage, "folder.error.save");
				res.addObject("topics", topics);
				res.addObject("action", "message/actor/broadcastAuthors.do");
			}

		return res;

	}

	@RequestMapping(value = "/broadcastRegisteredAuthors", method = RequestMethod.GET)
	public ModelAndView broadcastRegisteredAuthors() {

		ModelAndView res;

		final Message newMessage = this.messageService.create();

		final Collection<Topic> topics = this.topicService.findAll();

		res = this.broadcastModelAndView(newMessage);

		res.addObject("topics", topics);

		res.addObject("action", "message/actor/broadcastRegisteredAuthors.do");

		return res;
	}

	@RequestMapping(value = "/broadcastRegisteredAuthors", method = RequestMethod.POST, params = "save")
	public ModelAndView broadcastRegisteredAuthors(final Message newMessage, final BindingResult binding) {
		ModelAndView res;
		final Collection<Topic> topics = this.topicService.findAll();
		if (binding.hasErrors()) {
			res = this.broadcastModelAndView(newMessage);
			System.out.println(binding.getAllErrors());
			res.addObject("topics", topics);
			res.addObject("action", "message/actor/broadcastRegisteredAuthors.do");
		} else
			try {
				this.messageService.broadcastRegisteredAuthors(newMessage);
				res = new ModelAndView("redirect:/message/actor/list.do");
			} catch (final Throwable error) {
				error.printStackTrace();
				res = this.broadcastModelAndView(newMessage, "folder.error.save");
				res.addObject("topics", topics);
				res.addObject("action", "message/actor/broadcastRegisteredAuthors.do");
			}

		return res;

	}

	@RequestMapping(value = "/broadcastSubmissionAuthors", method = RequestMethod.GET)
	public ModelAndView broadcastSubmissionAuthors() {

		ModelAndView res;

		final Message newMessage = this.messageService.create();

		final Collection<Topic> topics = this.topicService.findAll();

		res = this.broadcastModelAndView(newMessage);

		res.addObject("topics", topics);

		res.addObject("action", "message/actor/broadcastSubmissionAuthors.do");

		return res;
	}

	@RequestMapping(value = "/broadcastSubmissionAuthors", method = RequestMethod.POST, params = "save")
	public ModelAndView broadcastSubmissionAuthors(final Message newMessage, final BindingResult binding) {
		ModelAndView res;
		final Collection<Topic> topics = this.topicService.findAll();
		if (binding.hasErrors()) {
			res = this.broadcastModelAndView(newMessage);
			System.out.println(binding.getAllErrors());
			res.addObject("topics", topics);
			res.addObject("action", "message/actor/broadcastSubmissionAuthors.do");
		} else
			try {
				this.messageService.broadcastSubmissionAuthors(newMessage);
				res = new ModelAndView("redirect:/message/actor/list.do");
			} catch (final Throwable error) {
				error.printStackTrace();
				res = this.broadcastModelAndView(newMessage, "folder.error.save");
				res.addObject("topics", topics);
				res.addObject("action", "message/actor/broadcastSubmissionAuthors.do");
			}

		return res;

	}

	//Display ------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView Display(@RequestParam final Integer messageId) {
		ModelAndView result;

		final Message newMessage = this.messageService.findOne(messageId);

		result = new ModelAndView("message/display");
		result.addObject("newMessage", newMessage);

		return result;
	}

	//Delete-----------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int messageId) {

		ModelAndView res;

		final Message message = this.messageService.findOne(messageId);

		this.messageService.delete(message);

		res = new ModelAndView("redirect:/message/actor/list.do");

		return res;

	}

	//Ancillary methods-------------------------------------------------------------
	private ModelAndView createEditModelAndView(final Message newMessage) {
		return this.createEditModelAndView(newMessage, null);
	}

	private ModelAndView createEditModelAndView(final Message newMessage, final String text) {
		ModelAndView res;

		final Collection<Actor> recipients = this.actorService.findAll();
		final Actor actor = this.actorService.findByPrincipal();
		recipients.remove(actor);

		res = new ModelAndView("message/create");
		res.addObject("mess", newMessage);
		res.addObject("message", text);
		res.addObject("recipients", recipients);

		return res;
	}

	private ModelAndView broadcastModelAndView(final Message newMessage) {
		return this.broadcastModelAndView(newMessage, null);
	}

	private ModelAndView broadcastModelAndView(final Message newMessage, final String text) {
		ModelAndView res;

		res = new ModelAndView("message/broadcast");
		res.addObject("mess", newMessage);
		res.addObject("message", text);

		return res;
	}
}
