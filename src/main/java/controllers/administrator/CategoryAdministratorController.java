/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

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

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CategoryAdministratorController() {
		super();
	}


	// Services ---------------------------------------------------------------
	@Autowired
	private CategoryService	categoryService;


	// List ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final String message) {

		final Collection<Category> categories;

		categories = this.categoryService.findAll();

		final ModelAndView result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		result.addObject("message", message);
		result.addObject("requestURI", "category/administrator/list.do");

		return result;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final Category category = this.categoryService.create();

		final ModelAndView result = this.createEditModelAndView(category);

		return result;
	}

	// Save del create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Category category, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(category);
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.categoryService.save(category);
				result = new ModelAndView("redirect:/category/administrator/list.do");
			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(category, "category.commit.error");
			}

		return result;
	}

	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		ModelAndView result;
		Category category;

		category = this.categoryService.findOne(categoryId);
		result = this.createEditModelAndView(category);

		if (this.categoryService.isRootCategory(category))
			result = new ModelAndView("redirect:/category/administrator/list.do");

		return result;
	}

	// Delete ---------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int categoryId) {
		ModelAndView result;

		final Category category = this.categoryService.findOne(categoryId);

		this.categoryService.delete(category);

		result = new ModelAndView("redirect:/category/administrator/list.do");

		return result;
	}

	// Show ---------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer categoryId) {
		ModelAndView result;
		Category category;

		category = this.categoryService.findOne(categoryId);
		
		Collection<Category> hijas = this.categoryService.findCategoryHijas(categoryId);

		result = new ModelAndView("category/show");
		result.addObject("category", category);
		result.addObject("hijas", hijas);
		result.addObject("categoryId", categoryId);

		return result;
	}

	// Ancillary methods ---------------------------------------------------------------

	private ModelAndView createEditModelAndView(final Category category) {

		return this.createEditModelAndView(category, null);
	}

	private ModelAndView createEditModelAndView(final Category category, final String message) {

		final Collection<Category> categories = this.categoryService.findAll();
		categories.remove(category);

		ModelAndView res;

		if (category.getId() == 0)
			res = new ModelAndView("category/create");
		else
			res = new ModelAndView("category/edit");

		res.addObject("categories", categories);
		res.addObject("category", category);
		res.addObject("message", message);

		return res;

	}

}
