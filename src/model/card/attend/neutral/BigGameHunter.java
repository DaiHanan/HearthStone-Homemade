package model.card.attend.neutral;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 王牌猎人
 * */
public class BigGameHunter extends Attend {
	public BigGameHunter() {
		super(2, 4, 5, "王牌猎人", Nature.None, "战吼:消灭一个受伤随从");
		this.features.add(Feature.NORMAL);
		
		this.hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		int i = 0;
		for (i = 0; i < Config.standMaxCount + 1; i++){
			if (con.mainV.stands[1-nowId][i].attend.hp!=con.mainV.stands[1-nowId][i].attend.hpMax){
				con.changeStandCardHp(1-nowId, i, 0 - con.mainV.stands[1-nowId][i].attend.hpMax);
			    break;
			}
		}
		
		if(i == Config.standMaxCount + 1)
			return false;
		
		return true;
	}
}