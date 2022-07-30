package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Address;
import com.lti.entity.LandDetails;
import com.lti.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(User user) {
		entityManager.persist(user);

	}

	@Override
	@Transactional
	public void saveLandDetails(LandDetails landDetails) {
		entityManager.persist(landDetails);

	}

	@Override
	public int findByEmailAndPass(String email, String pass) {

		return (Integer) entityManager.createNamedQuery("logincheck").setParameter("em", email).setParameter("pw", pass)
				.getSingleResult();
	}

	@Override
	public boolean isUserAvailable(String email) {

		return (Long) entityManager.createQuery("select count(u.id) from User u where u.email = :em")
				.setParameter("em", email).getSingleResult() == 1 ? true : false;
	}

	@Override
	public int getUserId(String email) {

		return (Integer) entityManager.createQuery("select u.id from User u where u.email = :em")
				.setParameter("em", email).getSingleResult();
	}

	@Override
	public String getUserRole(int id) {

		return (String) entityManager.createQuery("select u.role from User u where u.id = :em").setParameter("em", id)
				.getSingleResult();
	}

	@Override
	public String getUserName(int id) {

		return (String) entityManager.createQuery("select u.fullname from User u where u.id = :em")
				.setParameter("em", id).getSingleResult();
	}

	@Override
	public User findbyId(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	@Transactional
	public void saveAddress(Address address) {
		entityManager.persist(address);
	}

}
