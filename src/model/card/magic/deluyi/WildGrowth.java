package model.card.magic.deluyi;


import model.card.magic.Magic;
import controller.Controller;

/**
 * Ұ�Գɳ�
 * */
public class WildGrowth extends Magic{
	public WildGrowth() {
		super(2, "Ұ�Գɳ�");
		this.desc = "����һ���յ�ˮ��";
	}
	public boolean effect(Controller con, int nowId) {
		con.changeCosts(2, nowId, 1);
		return true;
	}
}
