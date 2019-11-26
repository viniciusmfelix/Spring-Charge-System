package com.spring.titlecharge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.spring.titlecharge.model.StatusTitle;
import com.spring.titlecharge.model.Title;
import com.spring.titlecharge.repository.Titles;
import com.spring.titlecharge.repository.filter.TitleFilter;

@Service
public class RegisterTitleService {

	@Autowired
	private Titles titles;
	
	public void save(Title title) {
		try {
			titles.save(title);
		}catch(DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Wrong date format!");
		}
	}

	public void delete(Long code) {
		titles.deleteById(code);
	}

	public String recieve(Long code) {
		Title title = titles.getOne(code);
		title.setStatus(StatusTitle.RECIEVED);
		titles.save(title);
		return StatusTitle.RECIEVED.getDescription();
	}
	
	public List<Title> filter(TitleFilter filter){
		String description = filter.getDescription() == null ? "" : filter.getDescription();
		return titles.findByDescriptionContaining(description);
	}
}
