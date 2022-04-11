package model.card.attend.shengqi;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 护盾机器人
 * */
public class ShieldRobot extends Attend {
	public ShieldRobot() {
		super(2, 2, 2, "护盾机器人", Nature.None, "");
		this.features.add(Feature.Shield);
	}
}
