package model.card.magic.deluyi;

import model.card.magic.Magic;
import controller.Controller;

/**
 * 激活
 * */
public class Activation extends Magic{
	public Activation() {
		super(0,"激活");
		this.desc = "仅在本回合获得两个水晶";
		
	}
	public boolean effect(Controller con, int nowId) {
		con.changeCosts(1, nowId, 2);
		return true;
	}
}
