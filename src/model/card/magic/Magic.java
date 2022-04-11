package model.card.magic;

import controller.Controller;
import model.card.Card;

public class Magic extends Card {
	
	public Magic() {}
	public Magic(int cost, String name) {
		this.cost = cost;
		this.name = name;
	}
	
	public boolean effect(Controller con, int nowId) {
		System.out.println("≥ı º∑® ı");
		return true;
	}

}
