package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ��������
 * */
public class DragonHunter extends Attend {
	public DragonHunter(){
		super(1,2,1,"��������",Nature.None,"��");
		this.features.add(Feature.Poison);
	}
	
}
