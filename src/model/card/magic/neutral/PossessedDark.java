package model.card.magic.neutral;

import model.card.magic.Magic;
import controller.Controller;

/**
 * �ڰ�����
 * */
public class PossessedDark extends Magic{
	public PossessedDark() {
		super(1,"�ڰ�����");
		this.desc="��һ���ѷ���ɫ���2���˺�����������";
		// TODO �Զ����ɵĹ��캯�����
	}
	public boolean effect(Controller con, int nowId) {
		int IdIdx[] = con.inquiryTarget(3, nowId);
		if(IdIdx[0] == -1)
			return false;
		con.changeStandCardHp(IdIdx[0], IdIdx[1], -2);
		con.drawCard(nowId, 2);
		return true;
	}
}
