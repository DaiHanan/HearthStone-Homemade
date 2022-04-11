package model.card.attend.shengqi;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 正义保护者
 * */
public class ProtectorOfJustice extends Attend {
	public ProtectorOfJustice() {
		super(1, 1, 1, "正义保护者", Nature.None, "");
		this.features.add(Feature.Taunt);
		this.features.add(Feature.Shield);
	}
}
