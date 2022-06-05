package br.com.fiap.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.com.fiap.dao.StationDao;
import br.com.fiap.views.Window;

public class TableController implements MouseListener{

	StationDao dao = new StationDao();
	private Window window;

	public TableController(Window window) {
		this.window = window;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete a movie?");
			if (answer == JOptionPane.YES_OPTION) {
				JTable table = (JTable) e.getSource();
				String stationId = (String) table.getValueAt(table.getSelectedRow(), 0);
				dao.delete(Long.valueOf(stationId));
				window.loadData();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	
}
