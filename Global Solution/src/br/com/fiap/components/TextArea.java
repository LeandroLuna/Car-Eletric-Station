package br.com.fiap.components;

import javax.swing.JTextArea;

public class TextArea extends JTextArea {
	private static final long serialVersionUID = 1L;
	private int columns = 10;
	private int rows = 1;

	public TextArea() {
		init();
	}

	private void init() {
		this.setColumns(columns);
		this.setRows(rows);
	}
}