package edu.fra.uas.model;

import java.util.ArrayList;
import java.util.List;

public class Card {

	private List<Card> cards;

	public Card(String title, String description) {
		// TODO Auto-generated constructor stub
	}

	public void User() {
		cards = new ArrayList<>();
	}

	public void addCard(String title, String description) {
		Card card = new Card(title, description);
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

}
