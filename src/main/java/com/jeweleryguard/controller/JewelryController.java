package com.jeweleryguard.controller;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;
import com.jeweleryguard.service.JewelryService;
import com.jeweleryguard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/jewelry")
public class JewelryController {

	@Autowired
	private UserService userService;
	@Autowired
	private JewelryService jewelryService;


	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newJewelry(){
		ModelAndView modelAndView = new ModelAndView();
		Jewelry jewelry = new Jewelry();
		modelAndView.addObject("jewelry", jewelry);
		modelAndView.setViewName("jewelry/new");
		return modelAndView;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newJewelry(@Valid Jewelry jewelry, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		jewelry.setUser(user);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("jewelry/new");
		} else {
			jewelryService.saveJewelry(jewelry);
			modelAndView.addObject("successMessage", "Dodano do biblioteki.");
			modelAndView.addObject("jewelry", new Jewelry());
			modelAndView.setViewName("jewelry/new");

		}
		return modelAndView;
	}

	@RequestMapping(value={"/", "/list"}, method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Jewelry> jewelryList = user.getJewelryList();
		modelAndView.addObject("jewelry", jewelryList );
		modelAndView.setViewName("jewelry/list");
		return modelAndView;
	}


}
