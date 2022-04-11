package model.card.attend.shengqi;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 
 * */
public class Quartermaster extends Attend {
	public Quartermaster() {
		super(5, 2, 5, "军需官", Nature.None, "战吼：使你的白银新兵获得+2/+2");
		this.features.add(Feature.NORMAL);
		
		hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			//找到白银新兵
			if(con.mainV.stands[nowId][i] != null && 
					con.mainV.stands[nowId][i].attend instanceof ReportSoldier) {
				Attend attend = con.mainV.stands[nowId][i].attend;
				attend.hp += 2;
				attend.hpMax += 2;
				attend.atk += 2;
				con.mainV.setStandCard(attend, i, nowId);
			}
		}
		
		return true;
	}
}
