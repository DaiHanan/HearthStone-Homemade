package model.card.attend.shengqi;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ���ܻ�����
 * */
public class ShieldRobot extends Attend {
	public ShieldRobot() {
		super(2, 2, 2, "���ܻ�����", Nature.None, "");
		this.features.add(Feature.Shield);
	}
}
