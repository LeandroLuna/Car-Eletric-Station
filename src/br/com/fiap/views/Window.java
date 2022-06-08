package br.com.fiap.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException; // Precisa colocar tanto na View.java, quanto no App.java. Não estamos manipulando erros - e nem manipularemos LOL. 
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.components.Input;
import br.com.fiap.components.Label;
import br.com.fiap.components.StarRater;
import br.com.fiap.controller.ButtonController;
import br.com.fiap.controller.MapsController;
import br.com.fiap.controller.TableController;
import br.com.fiap.dao.StationDao;
import br.com.fiap.model.EletricStation;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	// Register main panel
	JPanel register = new JPanel(new GridLayout(0, 3));

	// Instance to include tabs in application itself
	JTabbedPane tabs = new JTabbedPane();
	JPanel tablePanel = new JPanel(new BorderLayout());

	// Left panel for register tab
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
	String[] statesArr = { "Pick one", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MS", "MT", "PA",
			"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
	JComboBox<String> statesSelector = new JComboBox<>(statesArr);

	// Middle panel for register tab
	JPanel carPlug = new JPanel(new GridLayout(0, 1));
	JCheckBox type1 = new JCheckBox("type1");
	JCheckBox type2 = new JCheckBox("type2");
	JCheckBox css2 = new JCheckBox("CSS2");
	JCheckBox chaDeMo = new JCheckBox("CHAdeMO");
	List<String> checkedBoxes = new ArrayList<String>();

	// Right panel for register tab
	JPanel others = new JPanel(new GridLayout(0, 1));
	Label priceKwh = new Label("Price kWh");
	Input priceKwhInput = new Input();
	Label ratingLabel = new Label("Rating");
	StarRater rating = new StarRater();

	// Buttons
	JPanel buttons = new JPanel();
	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	ButtonController buttonController = new ButtonController(this);

	// Table
	String[] columns = { "Id", "StationName", "Street", "Neighborhood", "City", "State", "Plug", "PriceKwh", "Rating" };
	DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
	JTable table = new JTable(tableModel);
	TableController tableController = new TableController(this);
	JButton orderStates = new JButton("Order by states!");

	// Maps API
	JPanel maps = new JPanel();
	MapsController mapsController = new MapsController();
	JLabel stations = new Label("Stations");
	JComboBox<String> stationsSelector = new JComboBox<>(mapsController.getDatabaseStations());

	public Window() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {
		}
		setSize(790, 280);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cars Eletric Station");
	}

	public void init() throws IOException {
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

		others.setBorder(BorderFactory.createTitledBorder("Additional"));
		others.add(priceKwh);
		others.add(priceKwhInput);
		others.add(ratingLabel);
		others.add(rating);
		save.setActionCommand("Save");
		save.addActionListener(buttonController);
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(buttonController);
		buttons.add(save);
		buttons.add(cancel);
		others.add(buttons);
		register.add(others);

		orderStates.setActionCommand("Order");
		orderStates.addActionListener(buttonController);
		tablePanel.add(orderStates, BorderLayout.SOUTH);
		table.addMouseListener(tableController);
		table.setDefaultEditor(Object.class, null);
		tablePanel.add(new JScrollPane(table));

		tabs.add("Register", register);
		tabs.add("List", tablePanel);

		// Mapa
		maps.add(stations);
		maps.add(stationsSelector);
		stationsSelector.addItemListener(mapsController);
		maps.add(mapsController.getMapsIcon());
		tabs.add("Maps", maps);
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

	public JComboBox<String> getStatesSelector() {
		return statesSelector;
	}

	public List<String> getCheckedboxes() {
		for (java.awt.Component child : carPlug.getComponents()) { // Get all components from carPlug panel
			if (child instanceof JCheckBox) { // Get all JCheckbox instances from specified panel (carPlug)
				JCheckBox checkBox = (JCheckBox) child;
				if (checkBox.isSelected()) {
					checkedBoxes.add(checkBox.getText());
				}
			}
		}
		return checkedBoxes;
	}

	public Input getPriceKwhInput() {
		return priceKwhInput;
	}

	public StarRater getRating() {
		return rating;
	}

	public void cleanData() {
		nameInput.setText("");
		streetInput.setText("");
		neighborhoodInput.setText("");
		cityInput.setText("");
		statesSelector.setSelectedIndex(0);
		type1.setSelected(false);
		type2.setSelected(false);
		css2.setSelected(false);
		chaDeMo.setSelected(false);
		priceKwhInput.setText("");
		rating.setSelection(0);
	}

	public void loadData() {
		tableModel.setRowCount(0);
		List<EletricStation> list = new StationDao().showAll();
		list.forEach(station -> tableModel.addRow(station.getData()));
		ArrayList<String> mapsAddress = new ArrayList<String>();
		list.forEach(station -> mapsAddress.add(station.getData().get(2)));
		mapsController.setMapsAddressTest(mapsAddress);
	}

	public void loadDataOrdered() {
		tableModel.setRowCount(0);
		List<EletricStation> list = new StationDao().orderByStates();
		list.forEach(station -> tableModel.addRow(station.getData()));
	}
}
