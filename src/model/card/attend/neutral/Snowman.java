package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 达利乌斯克罗雷
 * */
public class Snowman extends Attend {
	public Snowman(){
		super(5,4,4,"雪人",Nature.None,"无");
		this.features.add(Feature.NORMAL);
	}
	
}
