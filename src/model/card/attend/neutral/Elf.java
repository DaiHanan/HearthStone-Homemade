package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * С����
 * */
public class Elf extends Attend {
	public Elf(){
		super(1, 1, 1, "С����", Nature.None, "��");
		this.features.add(Feature.NORMAL);
	}
	
}
