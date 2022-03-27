package br.com.m3Tech.solucoesFromtis.dao;
//package br.com.m3Tech.solucoesFromtis.dao;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//
//import br.com.m3Tech.solucoesFromtis.config.EntityManagerProvider;
//
//public abstract class GenericDao<T, I extends Serializable> {
//		
//	private Class<T> persistedClass;
//
//	protected GenericDao() {
//	}
//
//	protected GenericDao(Class<T> persistedClass) {
//		this();
//		this.persistedClass = persistedClass;
//		
//	}
//
//	public T save( T entity) {
//		EntityManagerFactory factory = EntityManagerProvider.getFactory();
//		EntityManager entityManager = factory.createEntityManager();
//		EntityTransaction t = entityManager.getTransaction();
//		t.begin();
//		entityManager.persist(entity);
//		entityManager.flush();
//		t.commit();
//		entityManager.close();
//		return entity;
//	}
//
//	public T update( T entity) {
//		EntityManagerFactory factory = EntityManagerProvider.getFactory();
//		EntityManager entityManager = factory.createEntityManager();
//		EntityTransaction t = entityManager.getTransaction();
//		t.begin();
//		entityManager.merge(entity);
//		entityManager.flush();
//		t.commit();
//		entityManager.close();
//		return entity;
//	}
//
//	public void remove(I id) {
//		EntityManagerFactory factory = EntityManagerProvider.getFactory();
//		EntityManager entityManager = factory.createEntityManager();
//		T entity = find(id);
//		EntityTransaction tx = entityManager.getTransaction();
//		tx.begin();
//		T mergedEntity = entityManager.merge(entity);
//		entityManager.remove(mergedEntity);
//		entityManager.flush();
//		tx.commit();
//		entityManager.close();
//	}
//
//	public List<T> findAll() {
//		EntityManagerFactory factory = EntityManagerProvider.getFactory();
//		EntityManager entityManager = factory.createEntityManager();
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<T> query = builder.createQuery(persistedClass);
//		query.from(persistedClass);
//		List<T> resultList = entityManager.createQuery(query).getResultList();
//		entityManager.close();
//		return resultList;
//	}
//
//	public T find(I id) {
//		EntityManagerFactory factory = EntityManagerProvider.getFactory();
//		EntityManager entityManager = factory.createEntityManager();
//		T result = entityManager.find(persistedClass, id);
//		entityManager.close();
//		return result;
//	}
//
//}
