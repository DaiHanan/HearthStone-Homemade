package model.card.magic.neutral;

import controller.Controller;
import model.card.magic.Magic;

public class LuckyCoin extends Magic {
	public LuckyCoin() {
		super(0, "����Ӳ��");
		this.desc = "�ڸûغϻ��һ����ʱ��ˮ��";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		con.changeCosts(1, nowId, 1);
		
		return true;
	}
}
