package model.card.magic.deluyi;


import model.card.magic.Magic;
import controller.Controller;

/**
 * 野性成长
 * */
public class WildGrowth extends Magic{
	public WildGrowth() {
		super(2, "野性成长");
		this.desc = "增加一个空的水晶";
	}
	public boolean effect(Controller con, int nowId) {
		con.changeCosts(2, nowId, 1);
		return true;
	}
}
