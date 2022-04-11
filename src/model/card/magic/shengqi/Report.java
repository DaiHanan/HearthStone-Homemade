package model.card.magic.shengqi;

import controller.Controller;
import model.card.attend.shengqi.ReportSoldier;
import model.card.magic.Magic;

/**
 * 报告
 * */
public class Report extends Magic {
	public Report() {
		super(2, "报告");
		this.desc = "召唤一个1/1的白银新兵";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//召唤随从
		//如果场上没有位置，使用失败
		return con.addStandCard(new ReportSoldier(), nowId, -1);
		
	}
}
