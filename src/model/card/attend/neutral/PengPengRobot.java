package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 砰砰机器人
 * */
public class PengPengRobot extends Attend {
	public PengPengRobot() {
		super(1, 1, 1, "砰砰机器人", Nature.None, "亡语：亡语对一个随机敌人造成1-4点伤害");
		this.features.add(Feature.NORMAL);
		
		hasDeadWords = true;
	}
	
	@Override
	public void deadWords(Controller con, int nowId) {
		super.deadWords(con, nowId);
		int ran = (int)Math.floor(Math.random() * 4) + 1;
		int[] IdIdx = con.randomTarget(6, nowId);
		if(IdIdx[0] != -1) {
			con.changeStandCardHp(IdIdx[0], IdIdx[1], 0 - ran);
		}
	}
		
}

