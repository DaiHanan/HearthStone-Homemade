package model.card.attend.neutral;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 小精灵
 * */
public class Elf extends Attend {
	public Elf(){
		super(1, 1, 1, "小精灵", Nature.None, "无");
		this.features.add(Feature.NORMAL);
	}
	
}
