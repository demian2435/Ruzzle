package it.polito.tdp.ruzzle.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ricerca {
	private String parola;
	private Board board;
	private Map<Pos, Boolean> result;

	public Map<Pos, Boolean> trovaParola(String parola, Board board) {
		this.parola = parola.toUpperCase();
		this.board = board;
		this.result = new HashMap<Pos, Boolean>();
		String primaLettera = (this.parola.charAt(0) + "");
		List<Pos> puntiInizio = board.contains(primaLettera);
		if (puntiInizio.size() == 0) {
			return result;
		}

		for (Pos p : puntiInizio) {
			Map<Pos, Boolean> percorso = new HashMap<Pos, Boolean>();
			percorso.put(p, true);
			String parziale = primaLettera;
			ricorsione(parziale, percorso, p, 1);
		}

		return result;
	}

	private void ricorsione(String parziale, Map<Pos, Boolean> percorso, Pos ultimaPos, int livello) {
		if (livello == parola.length()) {
			if(parola.equals(parziale))
			result = new HashMap<Pos, Boolean>(percorso);
			return;
		}
		
		if(result.size() != 0) {
			return;
		}
				
		for (Pos pos : board.getAdjacencies(ultimaPos)) {
			String letteraDaTrovare = (parola.charAt(livello) + "").toUpperCase();
			if (!percorso.containsKey(pos)) {
				String letteraVicina = board.getCellValueProperty(pos).get();
				if (letteraVicina.equals(letteraDaTrovare)) {
					String newParziale = parziale + letteraVicina;
					Map<Pos, Boolean> newPercorso = new HashMap<Pos, Boolean>(percorso);
					newPercorso.put(pos, true);
					ricorsione(newParziale, newPercorso, pos, livello + 1);
				}
			}
		}
	}

}
