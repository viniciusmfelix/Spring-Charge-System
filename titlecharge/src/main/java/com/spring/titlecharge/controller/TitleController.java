package com.spring.titlecharge.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.titlecharge.model.StatusTitle;
import com.spring.titlecharge.model.Title;
import com.spring.titlecharge.repository.filter.TitleFilter;
import com.spring.titlecharge.service.RegisterTitleService;

@Controller
@RequestMapping("/title")
public class TitleController {
	
	public static final String REGISTER_VIEW = "titleRegister";

	//Repo for database direct operation
	
	//Service layer for business rules --Test Only
	@Autowired
	private RegisterTitleService registerTitleService;
	
	@RequestMapping("/new")
	public ModelAndView newTitle() {
		ModelAndView mv = new ModelAndView(REGISTER_VIEW);
		mv.addObject(new Title());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Validated Title title, Errors errors, RedirectAttributes redirect) {
		if(errors.hasErrors()) {
			return REGISTER_VIEW;
		}
		try {
			registerTitleService.save(title);
			redirect.addFlashAttribute("message", "Title Saved Successfully!");
			return "redirect:/title/new";
		}catch(IllegalArgumentException e) {
			errors.rejectValue("deadline", null,e.getMessage());
			return REGISTER_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") TitleFilter filter) {
		List<Title> allTitles = registerTitleService.filter(filter);
		ModelAndView mv = new ModelAndView("titleSearch");
		mv.addObject("titles", allTitles);
		return mv;
	}
	
	@RequestMapping("{code}")
	public ModelAndView edition(@PathVariable("code") Title title) {
		ModelAndView mv = new ModelAndView(REGISTER_VIEW);
		mv.addObject(title);
		return mv;
	}
	
	@RequestMapping(value = "{code}",method = RequestMethod.DELETE)
	public String delete(@PathVariable Long code, RedirectAttributes redirect) {
		registerTitleService.delete(code);
		redirect.addFlashAttribute("message", "Title Deleted Successfully!");
		return "redirect:/title";
	}
	
	@RequestMapping(value = "/{code}/recieve", method = RequestMethod.PUT)
	public @ResponseBody String recieve(@PathVariable Long code) {
		return registerTitleService.recieve(code);
	}

	@ModelAttribute("allTitleStatus")
	public List<StatusTitle> allTitleStatus() {
		return Arrays.asList(StatusTitle.values());
	}
}