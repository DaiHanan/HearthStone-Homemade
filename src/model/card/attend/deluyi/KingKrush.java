package model.card.attend.deluyi;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ������
 * */
public class KingKrush extends Attend {
	public KingKrush() {
		super(8, 8, 9, "������", Nature.Beast, "");
		this.features.add(Feature.Dash);
	}
}
