package br.com.fiap.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.fiap.dao.StationDao;
import br.com.fiap.model.EletricStation;
import br.com.fiap.views.Window;

public class ButtonController implements ActionListener {
	StationDao dao = new StationDao();
	private Window window;

	public ButtonController(Window window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Save") {
			EletricStation station = new EletricStation();
			station.setStationName(window.getNameInput().getText());
			station.setStreet(window.getStreetInput().getText());
			station.setNeighborhood(window.getNeighborhoodInput().getText());
			station.setCity(window.getCityInput().getText());
			station.setState(window.getStatesSelector().getSelectedItem().toString());
			station.setCarPlug(window.getCheckedboxes());
			station.setPriceKwh(Float.parseFloat(window.getPriceKwhInput().getText()));
			station.setRating(window.getRating().getSelection());
			dao.insert(station);
			window.loadData();
			JOptionPane.showMessageDialog(null, "Successfully saved!");
		}
		if (e.getActionCommand() == "Cancel") {
			window.cleanData();
		}
		if (e.getActionCommand() == "Order") {
			window.loadDataOrdered();
		}
	}
}
