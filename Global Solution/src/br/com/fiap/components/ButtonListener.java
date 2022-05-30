package br.com.fiap.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.com.fiap.dao.StationDao;
import br.com.fiap.model.EletricStation;
import br.com.fiap.views.Window;


public class ButtonListener implements ActionListener, MouseListener{

	 private Window window; 
	 StationDao dao = new StationDao();
	 
	 public ButtonListener(Window janela) {
			this.window = janela;
		}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		EletricStation station = new EletricStation();
		station.setStationName(window.getNameInput().getText());
		station.setStreet(window.getStreetInput().getText());
		station.setNeighborhood(window.getNeighborhoodInput().getText());
		station.setCity(window.getCityInput().getText());
		station.setState(window.getStatesSelector().getSelectedItem().toString());
//		station.setCarPlug(window.getStreetInput().getText());
		station.setPriceKwh(Float.parseFloat(window.getPriceKwhInput().getText()));
		station.setRating(window.getRating().getSelection());
		dao.insert(station);
		window.loadData();	
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete a movie?");
			if(answer == JOptionPane.YES_OPTION) {
				JTable table = (JTable) e.getSource();
				String id = (String) table.getValueAt(table.getSelectedRow(), 0);
				window.loadData();
			}
		}	
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
