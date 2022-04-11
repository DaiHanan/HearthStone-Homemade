package model.card.attend.shengqi;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 守日者塔林姆
 * */
public class Tallim extends Attend {
	public Tallim() {
		super(7, 3, 6, "守日者塔林姆", Nature.None, "嘲讽，战吼：使其他所有随从的攻击力和生命值变成3");
		this.features.add(Feature.Taunt);
		
		hasWarCry = true;
	}
	
	@Override
		public boolean warCry(Controller con, int nowId, int nowIdx) {
			super.warCry(con, nowId, nowIdx);
			
			//修改所有其他随从的属性
			for(int i = 0; i < 2; i++) {
				for(int j = 1; j < Config.standMaxCount + 1; j++)
					if(con.mainV.stands[i][j] != null) {
						Attend attend = con.mainV.stands[i][j].attend;
						attend.hp = attend.hpMax = 3;
						attend.atk = 3;
						con.mainV.setStandCard(attend, j, i);
					}
			}
			
			return true ;
		}
}