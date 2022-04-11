package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 破碎残阳祭祀
 * */
public class SacrificeToBrokenCanyon extends Attend {
	public SacrificeToBrokenCanyon() {
		super(2, 3, 2, "破碎残阳祭祀", Nature.None, "战吼:使一个友方随从获得+1/+1");
		this.features.add(Feature.NORMAL);
		
		this.hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		int[] IdIdx = con.inquiryTarget(5, nowId);
		if(IdIdx[0] == -1)
			return false;
		//修改攻击力和血量
		Attend attend = con.mainV.stands[IdIdx[0]][IdIdx[1]].attend;
		attend.atk += 1;
		attend.hp += 1;
		con.mainV.setStandCard(attend, IdIdx[1], IdIdx[0]);
		
		return true;
	}
}
