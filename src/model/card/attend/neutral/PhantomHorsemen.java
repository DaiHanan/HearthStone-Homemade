package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 幽灵战马
 * */
public class PhantomHorsemen extends Attend {
	public PhantomHorsemen(){
		super(4,3,5,"幽灵战马",Nature.None,"无");
		this.features.add(Feature.Dash);
		this.features.add(Feature.Shield);
	}
}