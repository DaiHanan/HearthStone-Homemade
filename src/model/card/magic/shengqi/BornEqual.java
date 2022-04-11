package model.card.magic.shengqi;

import config.Config;
import controller.Controller;
import model.card.attend.Attend;
import model.card.magic.Magic;

/**
 * 生而平等
 * */
public class BornEqual extends Magic {
	public BornEqual() {
		super(2, "生而平等");
		this.desc = "将所有随从的生命值变为1";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		for(int i = 0; i < 2; i++) {
			for (int j = 1; j < Config.standMaxCount + 1; j++) {
				if(con.mainV.stands[i][j] != null) {
					Attend attend = con.mainV.stands[i][j].attend;
					attend.hp = attend.hpMax = 1;
					con.mainV.setStandCard(attend, j, i);
				}
			}
		}
		
		return true;
	}
}
