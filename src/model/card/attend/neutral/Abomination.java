package model.card.attend.neutral;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ����
 * */
public class Abomination extends Attend {
	public Abomination() {
		super(4, 4, 5, "����", Nature.None, "����:�����н�ɫ��������˺�");
		this.features.add(Feature.Taunt);
		
		hasDeadWords = true;
	}
	
	@Override
		public void deadWords(Controller con, int nowId) {
			super.deadWords(con, nowId);
			
			for (int i = 0; i < 2; i++) {
				for(int j = 0; j < Config.standMaxCount + 1; j++) {
					if(con.mainV.stands[i][j] != null) {
						con.changeStandCardHp(i, j, -2);
					}
				}
			}
		}
}
