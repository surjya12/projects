package org.surjya.userapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.surjya.userapp.dto.User;

public class UserDao {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("dev");
	EntityManager em=emf.createEntityManager();
	
	public User saveUser(User user) {
		EntityTransaction t=em.getTransaction();
		em.persist(user);
		t.begin();
		t.commit();
		return user;
	}
	public User updateUser(User user) {
		EntityTransaction t=em.getTransaction();
		em.merge(user);
		t.begin();
		t.commit();
		return user;
	}
	public User getUserById(int id) {
		return em.find(User.class, id);
	}
	public boolean deleteUserById(int id) {
	    User u=getUserById(id);
	    if(u!=null) {
	    	EntityTransaction t=em.getTransaction();
	    	em.remove(u);
	    	t.begin();
	    	t.commit();
	    	return true;
	    }
	    return false;
	
	}
	public User verifyUser(long phone,String password) {
		String hql="select u from User u where u.phone=?1 and u.password=?2";
		javax.persistence.Query q=em.createQuery(hql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		List<User> users=q.getResultList();
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}
	
	
	
}
