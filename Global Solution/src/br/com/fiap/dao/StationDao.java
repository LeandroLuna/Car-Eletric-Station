package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.model.EletricStation;

public class StationDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("EletricStation");
	EntityManager manager = factory.createEntityManager();

	public void insert(EletricStation station) {
		manager.getTransaction().begin();
		manager.persist(station);
		manager.getTransaction().commit();
	}

	public List<EletricStation> showAll() {
		return manager
				.createQuery("SELECT es FROM EletricStation es", EletricStation.class)
				.getResultList();
	}

	public void delete(Long id) {
		EletricStation station = manager.find(EletricStation.class, id);

		manager.getTransaction().begin();
		manager.remove(station);
		manager.getTransaction().commit();
	}
}
