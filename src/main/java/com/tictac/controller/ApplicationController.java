package com.tictac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tictac.service.GameService;

@Controller
public class ApplicationController {

	@Autowired
	private GameService gameService;
	
	private final String INDEX_VIEW = "index";
	
	@RequestMapping(value = { "", "/", "/home" })
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(INDEX_VIEW);
		model.addObject("games", gameService.getAll());
		return model;
	}
	
	@RequestMapping(value = "/game/{id}")
	public ModelAndView gamePage(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("game");
		model.addObject("game", gameService.getById(id));
		return model;
	}

}
