package model.card.magic.fashi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * 烈焰轰击
 * */
public class FlameCannon extends Magic {
	public FlameCannon() {
		super(2, "烈焰轰击");
		this.desc = "对一个敌方随机随从造成4点伤害";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//获得随机目标
		int IdIdx[] = con.randomTarget(6, nowId);
		if(IdIdx[0] == -1)
			return false;
		
		//修改该随从血量
		con.changeStandCardHp(IdIdx[0], IdIdx[1], -1);
		
		return true;
	}
}
