package model.card.magic.shengqi;

import config.Config;
import controller.Controller;
import model.card.attend.shengqi.ReportSoldier;
import model.card.magic.Magic;

public class Mistress extends Magic {
	public Mistress() {
		super(1, "迷失丛林");
		this.desc = "召唤两个1/1的白银新兵";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//如果满场无法使用
		if(con.getStandCardCount(nowId) >= Config.standMaxCount)
			return false;
		
		for(int i = 0; i < 2; i++) {
			con.addStandCard(new ReportSoldier(), nowId, -1);
		}
		
		return true;
	}
}
