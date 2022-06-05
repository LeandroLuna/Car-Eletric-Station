package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.model.EletricStation;

public class StationDao {
	
	EntityManager manager = JpaManager.getManager();

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
	
	public List<EletricStation> orderByStates() {
		return manager
				.createQuery("SELECT es FROM EletricStation es ORDER BY es.state", EletricStation.class)
				.getResultList();
	}
	
	public void delete(Long stationId) {
		EletricStation station = manager.find(EletricStation.class, stationId);
		manager.getTransaction().begin();
		manager.remove(station);
		manager.getTransaction().commit();
	}
	
	
		
}
