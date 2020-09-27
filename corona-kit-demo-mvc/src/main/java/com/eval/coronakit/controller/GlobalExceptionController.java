package com.eval.coronakit.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.exception.ContactException;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(ContactException.class)
	public ModelAndView handleContactException(ContactException exp) {
		return new ModelAndView("errPage", "errMsg", exp.getMessage());
	}
}
