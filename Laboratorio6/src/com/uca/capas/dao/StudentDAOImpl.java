package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;

	@Override
	public List<Student> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.student");
		Query query = entityManager.createNativeQuery(sb.toString(),Student.class);
		List<Student> resulset= query.getResultList();
		
		return resulset;
	}

	@Override
	public Student findOne(Integer code) throws DataAccessException {
		// TODO Auto-generated method stub
		Student student = entityManager.find(Student.class, code);
		return student;
	}
	
	@Transactional
	public int save(Student s, Integer newRow) throws DataAccessException{
		try {
			if(newRow == 1) entityManager.persist(s);//nueva fila uso persist
			else entityManager.merge(s);//Ya existe la fila por lo tanto se hara un update para ello uso merge
			entityManager.flush();//sincronizo la base de datos ocn los cambios
			return 1;
			
		}catch(Throwable e) {
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	public int delete(Student s) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}