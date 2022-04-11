package model.card.magic.fashi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * ������
 * */
public class FlameCannon extends Magic {
	public FlameCannon() {
		super(2, "������");
		this.desc = "��һ���з����������4���˺�";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//������Ŀ��
		int IdIdx[] = con.randomTarget(6, nowId);
		if(IdIdx[0] == -1)
			return false;
		
		//�޸ĸ����Ѫ��
		con.changeStandCardHp(IdIdx[0], IdIdx[1], -1);
		
		return true;
	}
}
