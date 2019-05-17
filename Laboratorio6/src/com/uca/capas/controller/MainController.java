package com.uca.capas.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.StudentDAO;
import com.uca.capas.domain.Student;

@Controller
public class MainController {
	
	static Logger log = Logger.getLogger(MainController.class.getName());
	
	@Autowired
	private StudentDAO studentDao;
	
	@RequestMapping("/")
	public ModelAndView initMain(){
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
		 students = studentDao.findAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("students",students);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	public ModelAndView insrt() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("student", new Student());
		mav.setViewName("form");
		return mav;
	}

	@RequestMapping(value="/formData" , method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
			log.info("Agrego un nuevo usuario");
			studentDao.save(s, 1);
			
		}catch(Exception e) {
			log.info("Error: "+e.toString());
			
		}
		students = studentDao.findAll();
		log.info(students.get(0).getlName());
		mav.addObject("students" , students);
		mav.setViewName("main");
		return mav;
	}
}