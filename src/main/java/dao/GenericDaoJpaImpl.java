package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Person;

public  class  GenericDaoJpaImpl<T> 
implements GenericDao<T>{
	
    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

	public T create(T t) {
		 this.entityManager.persist(t);
	        return t;
	}

	
	public T read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}


	public void delete(T t) {
		// TODO Auto-generated method stub
		
	}






}