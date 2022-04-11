package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ����ս��
 * */
public class PhantomHorsemen extends Attend {
	public PhantomHorsemen(){
		super(4,3,5,"����ս��",Nature.None,"��");
		this.features.add(Feature.Dash);
		this.features.add(Feature.Shield);
	}
}