package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

public class BowMan extends Attend {
	public BowMan() {
		super(1, 1, 1, "弓箭手", Nature.None, "战吼：造成1点伤害");
		this.features.add(Feature.NORMAL);
		
		hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		int[] target = con.inquiryTarget(1, nowId);
		//使用成功
		if(target[0] != -1) {
			int damage = -1;
			//如果特性有剧毒
			Attend oppoAttend = con.mainV.stands[target[0]][target[1]].attend;
			if(features.contains(Feature.Poison) && oppoAttend.nature != Nature.Player)
				damage = 0 - oppoAttend.hp;
			con.changeStandCardHp(target[0], target[1], damage);
			return true;
		}
		return false;
	}
}
