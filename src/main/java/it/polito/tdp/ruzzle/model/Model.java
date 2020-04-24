package it.polito.tdp.ruzzle.model;

import java.util.List;

import it.polito.tdp.ruzzle.db.DizionarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Model {
	private final int SIZE = 4;
	private Board board;
	private List<String> dizionario;
	private StringProperty statusText;
	private StringProperty txtTime;
	private static int time;
	private Ricerca ricerca;

	public Model() {
		this.statusText = new SimpleStringProperty();

		this.board = new Board(SIZE);
		DizionarioDAO dao = new DizionarioDAO();
		this.dizionario = dao.listParola();
		statusText.set(String.format("%d parole lette", this.dizionario.size()));
		txtTime = new SimpleStringProperty("Time: 0");
		ricerca = new Ricerca();

	}

	public void reset() {
		this.board.reset();
		this.statusText.set("Board Reset");
		this.txtTime.set("Time: " + ++time);
	}

	public Board getBoard() {
		return this.board;
	}

	public final StringProperty statusTextProperty() {
		return this.statusText;
	}

	public final String getStatusText() {
		return this.statusTextProperty().get();
	}

	public final void setStatusText(final String statusText) {
		this.statusTextProperty().set(statusText);
	}

	public final StringProperty bindTxtTime() {
		return this.txtTime;
	}
	
	public boolean trovaParola(String parola) {
		return ricerca.trovaParola(parola, board).size() > 0;
	}

}
