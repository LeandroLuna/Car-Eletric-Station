package br.com.fiap.views;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.components.ButtonListener;
import br.com.fiap.components.Input;
import br.com.fiap.components.Label;
import br.com.fiap.components.StarRater;
import br.com.fiap.dao.StationDao;
import br.com.fiap.model.EletricStation;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel register = new JPanel(new GridLayout(0, 3));

	JTabbedPane tabs = new JTabbedPane(); 
	
	JPanel address = new JPanel(new GridLayout(0, 1));
	Label name = new Label("Station name: ");
	Input nameInput = new Input();
	Label street = new Label("Street");
	Input streetInput = new Input();
	Label neighborhood = new Label("Neighborhood");
	Input neighborhoodInput = new Input();
	Label city = new Label("City");
	Input cityInput = new Input();
	Label state = new Label("State");
	String[] statesArr = {"Pick one", "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MS","MT","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO",};
    JComboBox<String> statesSelector = new JComboBox<>(statesArr);
	
    JPanel carPlug = new JPanel(new GridLayout(0, 1));
    JCheckBox type1 = new JCheckBox("type1");
    JCheckBox type2 = new JCheckBox("type2");
    JCheckBox css2 = new JCheckBox("CSS2");
    JCheckBox chaDeMo  = new JCheckBox("CHAdeMO");
	
    JPanel others = new JPanel(new GridLayout(0, 1));
    Label priceKwh = new Label("Price kWh");
    Input priceKwhInput = new Input();
    Label ratingLabel = new Label("Rating");
    StarRater rating = new StarRater();
    
    JPanel buttons = new JPanel();
    JButton save = new JButton("Save");
    JButton cancel = new JButton("Cancel");

    ButtonListener buttonListener = new ButtonListener(this);
    String[] columns =  {"Id", "StationName", "Street", "Neighborhood", "City", "State", "Plug", "PriceKwh", "Rating"};
    DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
    JTable table = new JTable(tableModel);
    
    
	public Window() {
		setSize(500, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void init() {
		register.setBorder(BorderFactory.createTitledBorder("Eletric Station Register"));
		
		address.setBorder(BorderFactory.createTitledBorder("Info"));
		address.add(name);
		address.add(nameInput);
		address.add(street);
		address.add(streetInput);
		address.add(neighborhood);
		address.add(neighborhoodInput);
		address.add(city);
		address.add(cityInput);
		address.add(state);
		address.add(statesSelector);
		register.add(address);
		
		carPlug.setBorder(BorderFactory.createTitledBorder("Car plug"));
		carPlug.add(type1);
		carPlug.add(type2);
		carPlug.add(css2);
		carPlug.add(chaDeMo);
		register.add(carPlug);
		
		others.setBorder(BorderFactory.createTitledBorder("Others"));
		others.add(priceKwh);
		others.add(priceKwhInput);
		others.add(ratingLabel);
		others.add(rating);
		buttons.add(save);
		buttons.add(cancel);
		others.add(buttons);
		register.add(others);

		tabs.add("Register", register);
		
		save.addActionListener(buttonListener);
		
		tabs.add("List", new JScrollPane(table));
		tabs.add("Maps", new Label("another text"));
		this.add(tabs);
		setVisible(true);
	}
	
	public Input getNameInput() {
		return nameInput;
	}
	
	public Input getStreetInput() {
		return streetInput;
	}
	
	public Input getNeighborhoodInput() {
		return neighborhoodInput;
	}
	
	public Input getCityInput() {
		return cityInput;
	}
	
	public JComboBox<String> getStatesSelector(){
		return statesSelector;
	}
	
	public JCheckBox getCarPlug(){
		return carPlug;
	}
	
	public Input getPriceKwhInput(){
		return priceKwhInput;
	}
	
	public StarRater getRating(){
		return rating;
	}

	public void loadData() {
		// TODO Auto-generated method stub
		 tableModel.setRowCount(0);
	     List<EletricStation> list = new StationDao().showAll();
	     list.forEach(station -> tableModel.addRow(station.getData()));
	}
}
