package model.card.magic.fashi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * ������
 * */
public class FrostBolt extends Magic {
	public FrostBolt() {
		super(2, "������");
		this.desc = "��һ����ɫ���3���˺�����ʹ�䶳��";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int[] IdIdx = con.inquiryTarget(1, nowId);
		
		if(IdIdx[0] == -1)
			return false;
		//�ı�Ѫ��
		con.changeStandCardHp(IdIdx[0], IdIdx[1], -3);
		//���ñ���
		con.mainV.stands[IdIdx[0]][IdIdx[1]].attend.isFroozen = true;
		con.setAttendFroozen(IdIdx[0], IdIdx[1], true);
		return true;
	}
}
