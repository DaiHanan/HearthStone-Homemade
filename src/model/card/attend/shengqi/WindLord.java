package model.card.attend.shengqi;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ������
 * */
public class WindLord extends Attend {
	public WindLord() {
		super(5, 3, 8, "������", Nature.None, "");
		this.features.add(Feature.Dash);
		this.features.add(Feature.Shield);
		this.features.add(Feature.Taunt);
		this.features.add(Feature.Wind);
	}
}
