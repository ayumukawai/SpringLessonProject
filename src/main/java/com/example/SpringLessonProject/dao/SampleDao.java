package com.example.SpringLessonProject.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.SpringLessonProject.entity.EntForm;

@Repository
public class SampleDao {

	private final JdbcTemplate db;
	public SampleDao(JdbcTemplate db) {
		this.db = db;
	}

	public void insertDb(EntForm entform) {
		db.update("INSERT INTO sample (name) VALUES(?)",entform.getName() );
	}
}