package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * á÷ÁÔÈ®
 * */
public class HuntingDog extends Attend {
	public HuntingDog(){
		super(1, 2, 2, "á÷ÁÔÈ®", Nature.None, "ÎŞ");
		this.features.add(Feature.Dash);
	}
}