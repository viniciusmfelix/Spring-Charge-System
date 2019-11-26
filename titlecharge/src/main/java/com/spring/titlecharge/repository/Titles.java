package com.spring.titlecharge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.titlecharge.model.Title;

public interface Titles extends JpaRepository<Title, Long>{

	public List<Title> findByDescriptionContaining(String description);
	
}
