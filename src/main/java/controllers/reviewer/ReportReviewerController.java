
package controllers.reviewer;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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
import services.AuthorService;
import services.ReportService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Actor;
import domain.Author;
import domain.Report;
import domain.Reviewer;
import domain.Submission;

@Controller
@RequestMapping("/report/reviewer")
public class ReportReviewerController extends AbstractController {

	@Autowired
	private ReportService		ReportService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private SubmissionService	submissionService;
	@Autowired
	private AuthorService		authorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		Collection<Report> reports;

		final int logueadoId = this.actorService.findByPrincipal().getId();

		reports = this.ReportService.findByReviewer(logueadoId);

		res = new ModelAndView("report/list");
		res.addObject("reports", reports);
		res.addObject("requestURI", "report/reviewer/list.do");

		return res;
	}

	@RequestMapping(value = "/listBySubmission", method = RequestMethod.GET)
	public ModelAndView listBySubmission(final int submissionId) {

		ModelAndView res;
		Collection<Report> reports;
		
		Submission s;

		s = this.submissionService.findOne(submissionId);
		
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);
		final Author a = this.authorService.findByPrincipal();

		reports = this.ReportService.findBySubmission(submissionId);

		res = new ModelAndView("report/list");
		res.addObject("reports", reports);
		res.addObject("requestURI", "report/reviewer/listBySubmission.do");
		
		if (!s.getAuthor().equals(a) || s.getStatus().equals("UNDER-REVIEW"))
			res = new ModelAndView("redirect:submission/author/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Report report;

		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Reviewer.class, logueado);

		final Reviewer r = (Reviewer) logueado;

		final Collection<Submission> submissions = r.getSubmissions();
		
		for(Submission s : submissions){
			if(s.getConference().getNotificationDeadline().before(new Date())){
				submissions.remove(s);
			}
		}

		report = this.ReportService.create();
		res = this.createEditModelAndView(report);

		res.addObject("submissions", submissions);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int reportId) {
		ModelAndView result;
		Report sp;

		sp = this.ReportService.findOne(reportId);
		result = new ModelAndView("report/show");
		result.addObject("report", sp);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int reportId) {
		ModelAndView result;
		Report sp;

		sp = this.ReportService.findOne(reportId);

		this.ReportService.delete(sp);

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Report Report, final BindingResult binding) {

		ModelAndView res;

		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Reviewer.class, logueado);

		final Reviewer r = (Reviewer) logueado;

		final Collection<Submission> submissions = r.getSubmissions();

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			res = this.createEditModelAndView(Report);
			res.addObject("submissions", submissions);
		} else
			try {
				this.ReportService.save(Report);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops);
				res = this.createEditModelAndView(Report, "report.commit.error");
				res.addObject("submissions", submissions);
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Report Report) {
		ModelAndView res;

		res = this.createEditModelAndView(Report, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Report Report, final String messageCode) {
		ModelAndView res;

		if (Report.getId() == 0)
			res = new ModelAndView("report/create");
		else
			res = new ModelAndView("report/edit");
		res.addObject("report", Report);
		res.addObject("message", messageCode);

		return res;
	}

}
