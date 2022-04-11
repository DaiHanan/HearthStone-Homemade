package model.card.magic.shengqi;

import controller.Controller;
import model.Feature;
import model.card.attend.Attend;
import model.card.magic.Magic;

/**
 * 英勇圣印
 * */
public class SealOfChampions extends Magic {
	public SealOfChampions() {
		super(3, "英勇圣印");
		this.desc = "使一个随从获得+3攻击力并获得圣盾";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int IdIdx[] = con.inquiryTarget(7, nowId);
		if(IdIdx[0] == -1)
			return false;
		//修改攻击力，添加特性
		Attend attend = con.mainV.stands[IdIdx[0]][IdIdx[1]].attend;
		attend.atk += 3;
		if(!attend.features.contains(Feature.Shield)) {
			//如果原无特性，删除无特性
			if(attend.features.contains(Feature.NORMAL))
				attend.features.remove(Feature.NORMAL);
			attend.features.add(Feature.Shield);
		}
		con.mainV.setStandCard(attend, IdIdx[1], IdIdx[0]);
		
		return true;
	}
}
