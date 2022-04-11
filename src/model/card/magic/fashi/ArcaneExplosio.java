package model.card.magic.fashi;

import config.Config;
import controller.Controller;
import model.card.magic.Magic;

/**
 * 魔爆术
 * */
public class ArcaneExplosio extends Magic {
	public ArcaneExplosio() {
		super(2, "魔爆术");
		this.desc = "对所有敌方随从造成1点伤害";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int oppoId = 1 - nowId;
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[oppoId][i] != null)
				con.changeStandCardHp(oppoId, i, -1);
		}
		
		return true;
	}
}
