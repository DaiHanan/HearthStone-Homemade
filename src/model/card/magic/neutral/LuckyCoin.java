package model.card.magic.neutral;

import controller.Controller;
import model.card.magic.Magic;

public class LuckyCoin extends Magic {
	public LuckyCoin() {
		super(0, "幸运硬币");
		this.desc = "在该回合获得一个临时的水晶";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		con.changeCosts(1, nowId, 1);
		
		return true;
	}
}
