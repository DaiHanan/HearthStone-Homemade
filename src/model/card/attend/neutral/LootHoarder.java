package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ս��Ʒ������
 * */
public class LootHoarder extends Attend {
	public LootHoarder() {
		super(1, 2, 2, "ս��Ʒ������", Nature.None, "����:��һ����");
		this.features.add(Feature.NORMAL);
		
		hasDeadWords = true;
	}
	
	@Override
		public void deadWords(Controller con, int nowId) {
			super.deadWords(con, nowId);
			
			//����
			con.drawCard(nowId, 1);
			
		}
}
