package com.example.SpringLessonProject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.SpringLessonProject.entity.EntForm;

@Repository
public class SampleDao {

	private final JdbcTemplate db;
	
  public SampleDao(JdbcTemplate db) {
		this.db = db;
	}

  public List<EntForm> searchDb(){
		String sql = "SELECT * FROM sample";

		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		List<EntForm> resultDb2 = new ArrayList<EntForm>();

		for(Map<String,Object> result1:resultDb1) {

			EntForm entformdb = new EntForm();

			entformdb.setId((int)result1.get("id"));
			entformdb.setName((String)result1.get("name"));

			resultDb2.add(entformdb);
		}

		return resultDb2;
	}

	public void insertDb(EntForm entform) {
		db.update("INSERT INTO sample (name) VALUES(?)",entform.getName() );
	}

  public void deleteDb(Long id) {
		System.out.println("削除しました");
		db.update("DELETE FROM sample WHERE id=?", id);
	}

	public List<EntForm> selectOne(Long id) {
		System.out.println("編集画面を出します");

		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM sample where id=?", id);

		List<EntForm> resultDb2=new ArrayList<EntForm>();

		for(Map<String,Object> result1:resultDb1) {
			EntForm entformdb = new EntForm();
			entformdb.setId((int)result1.get("id"));
			entformdb.setName((String)result1.get("name"));
			resultDb2.add(entformdb);
		}

		return resultDb2;
	}

	public void updateDb(Long id, EntForm entform) {
		System.out.println("編集の実行");
		db.update("UPDATE sample SET name = ? WHERE id = ?",entform.getName(), id);
	}


}