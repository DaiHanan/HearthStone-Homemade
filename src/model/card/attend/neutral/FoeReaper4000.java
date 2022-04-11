package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 死神4000型
 * */
public class FoeReaper4000 extends Attend {
	public FoeReaper4000() {
		super( 4, 6, 6, "死神4000型", Nature.None, "亡语:抽一张牌并给予随机对方随从2点伤害");
		this.features.add(Feature.NORMAL);
		
		hasDeadWords = true;
	}
	
	@Override
		public void deadWords(Controller con, int nowId) {
			super.deadWords(con, nowId);
			
			//抽牌
			con.drawCard(nowId, 1);
			int index[]=con.randomTarget(6, nowId);
			if(index[0] != -1)
				con.changeStandCardHp(index[0], index[1], -2);
		}
}