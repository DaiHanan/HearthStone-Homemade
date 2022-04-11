package model.card.magic.deluyi;

import config.Config;
import controller.Controller;
import model.card.magic.Magic;

/**
 * 横扫
 * */
public class Sweep extends Magic {
	public Sweep() {
		super(4, "横扫");
		this.desc = "对一个敌人造成4点伤害，并对所有其他敌人造成1点伤害";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		
		int oppoId = 1 - nowId;
		
		int[] IdIdx = con.inquiryTarget(2, nowId);
		if(IdIdx[0] == -1)
			return false;
		//对其选择的随从4伤害，其他1伤害
		for(int i = 0; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[oppoId][i] != null) {
				int value = -1;
				if(i == IdIdx[1])
					value = -4;
				con.changeStandCardHp(oppoId, i, value);
			}
		}
		return true;
	}
}
