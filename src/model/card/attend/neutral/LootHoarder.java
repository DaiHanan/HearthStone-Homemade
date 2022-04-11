package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 战利品贮藏者
 * */
public class LootHoarder extends Attend {
	public LootHoarder() {
		super(1, 2, 2, "战利品贮藏者", Nature.None, "亡语:抽一张牌");
		this.features.add(Feature.NORMAL);
		
		hasDeadWords = true;
	}
	
	@Override
		public void deadWords(Controller con, int nowId) {
			super.deadWords(con, nowId);
			
			//抽牌
			con.drawCard(nowId, 1);
			
		}
}
