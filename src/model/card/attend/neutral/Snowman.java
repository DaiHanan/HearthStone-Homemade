package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ������˹������
 * */
public class Snowman extends Attend {
	public Snowman(){
		super(5,4,4,"ѩ��",Nature.None,"��");
		this.features.add(Feature.NORMAL);
	}
	
}
