package model.card.attend.neutral;


import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 沼泽飞龙
 * */
public class DragonSwamp extends Attend {
	public DragonSwamp(){
		super(3,5,4,"沼泽飞龙",Nature.Dragon,"战吼：为敌方召唤一个2/1且具有剧毒的飞龙猎手。");
		this.features.add(Feature.NORMAL);
		hasWarCry = true;
	}
	
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		Attend card = new DragonHunter();
	    con.addStandCard(card, 1 - nowId, -1);
	    
	    return true;
	}
}
