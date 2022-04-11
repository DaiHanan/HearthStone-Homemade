package model.card.magic.neutral;

import model.card.magic.Magic;
import controller.Controller;

/**
 * 黑暗附体
 * */
public class PossessedDark extends Magic{
	public PossessedDark() {
		super(1,"黑暗附体");
		this.desc="对一个友方角色造成2点伤害。抽两张牌";
		// TODO 自动生成的构造函数存根
	}
	public boolean effect(Controller con, int nowId) {
		int IdIdx[] = con.inquiryTarget(3, nowId);
		if(IdIdx[0] == -1)
			return false;
		con.changeStandCardHp(IdIdx[0], IdIdx[1], -2);
		con.drawCard(nowId, 2);
		return true;
	}
}
