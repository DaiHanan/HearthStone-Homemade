package model.card.magic.shengqi;

import config.Config;
import controller.Controller;
import model.card.magic.Magic;

/**
 * 奉献
 * */
public class Dedication extends Magic {
	public Dedication() {
		super(4, "奉献");
		this.desc = "对所有敌人造成2点伤害";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int oppoId = 1 - nowId;
		for(int i = 0; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[oppoId][i] != null) {
				con.changeStandCardHp(oppoId, i, -2);
			}
		}
		
		return true;
	}
}
