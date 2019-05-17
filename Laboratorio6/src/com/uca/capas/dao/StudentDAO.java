package com.uca.capas.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Student;

public interface StudentDAO {
	
	public int save(Student s, Integer newRow) throws DataAccessException;
	
	public int delete(Student s) throws DataAccessException;
	
	public List<Student> findAll() throws DataAccessException;
	
	public Student findOne(Integer code) throws DataAccessException;

}