package model.card.attend.shengqi;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ���屣����
 * */
public class ProtectorOfJustice extends Attend {
	public ProtectorOfJustice() {
		super(1, 1, 1, "���屣����", Nature.None, "");
		this.features.add(Feature.Taunt);
		this.features.add(Feature.Shield);
	}
}
