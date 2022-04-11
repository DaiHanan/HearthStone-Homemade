package model.card.attend.shengqi;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

public class ThePure extends Attend {
	public ThePure() {
		super(7, 3, 7, "纯洁者耶德瑞克", Nature.None, "战吼：使所有敌方随从的攻击力变为1");
		this.features.add(Feature.NORMAL);
		
		hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		int oppoId = 1 - nowId;
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[oppoId][i] != null) {
				//改攻击力
				Attend attend = con.mainV.stands[oppoId][i].attend;
				attend.atk = 1;
				con.mainV.setStandCard(attend, oppoId, i);
			}
		}
		
		
		return true;
	}
}
