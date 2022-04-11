package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

public class Crocodile extends Attend {
	public Crocodile() {
		super(3, 2, 2, "Η³Λ®φω", Nature.Beast, "");
		this.features.add(Feature.NORMAL);
	}
}
