package model.card.magic.deluyi;

import model.card.magic.Magic;
import controller.Controller;

/**
 * ����
 * */
public class Activation extends Magic{
	public Activation() {
		super(0,"����");
		this.desc = "���ڱ��غϻ������ˮ��";
		
	}
	public boolean effect(Controller con, int nowId) {
		con.changeCosts(1, nowId, 2);
		return true;
	}
}
