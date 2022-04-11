package model.card.magic.fashi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * 寒冰箭
 * */
public class FrostBolt extends Magic {
	public FrostBolt() {
		super(2, "寒冰箭");
		this.desc = "对一个角色造成3点伤害，并使其冻结";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int[] IdIdx = con.inquiryTarget(1, nowId);
		
		if(IdIdx[0] == -1)
			return false;
		//改变血量
		con.changeStandCardHp(IdIdx[0], IdIdx[1], -3);
		//设置冰冻
		con.mainV.stands[IdIdx[0]][IdIdx[1]].attend.isFroozen = true;
		con.setAttendFroozen(IdIdx[0], IdIdx[1], true);
		return true;
	}
}
