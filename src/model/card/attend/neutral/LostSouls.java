package model.card.attend.neutral;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 迷失的幽魂
 * */
public class LostSouls extends Attend {
	public LostSouls() {
		super(1, 2, 2, "迷失的幽魂", Nature.None, "亡语：使你的所有随从获得+1攻击力");
		this.features.add(Feature.NORMAL);
		
		hasDeadWords = true;
	}
	
	@Override
	public void deadWords(Controller con, int nowId) {
		super.deadWords(con, nowId);
		//加攻击力
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[nowId][i] != null) {
				con.changeStandCardAtk(nowId, i, 1);
			}
		}
		
	}
}
