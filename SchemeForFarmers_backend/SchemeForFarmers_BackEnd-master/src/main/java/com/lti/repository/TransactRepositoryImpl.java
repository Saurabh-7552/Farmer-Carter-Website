package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.TransactBids;

@Repository
public class TransactRepositoryImpl implements TransactRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(TransactBids bids) {
		entityManager.persist(bids);

	}

	@Override
	public TransactBids getTransactionByCropId(int id) {

		return (TransactBids) entityManager.createQuery("select t from TransactBids t where t.crop.id= :id")
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public List<TransactBids> CropsPurchasedbyBidder(int id) {
		return (List<TransactBids>) entityManager.createQuery("select t from TransactBids t where t.user.id= :id")
				.setParameter("id", id).getResultList();
	}

}
