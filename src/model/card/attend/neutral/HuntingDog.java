package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ����Ȯ
 * */
public class HuntingDog extends Attend {
	public HuntingDog(){
		super(1, 2, 2, "����Ȯ", Nature.None, "��");
		this.features.add(Feature.Dash);
	}
}