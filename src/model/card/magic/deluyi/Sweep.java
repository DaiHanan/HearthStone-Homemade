package model.card.magic.deluyi;

import config.Config;
import controller.Controller;
import model.card.magic.Magic;

/**
 * ��ɨ
 * */
public class Sweep extends Magic {
	public Sweep() {
		super(4, "��ɨ");
		this.desc = "��һ���������4���˺����������������������1���˺�";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		
		int oppoId = 1 - nowId;
		
		int[] IdIdx = con.inquiryTarget(2, nowId);
		if(IdIdx[0] == -1)
			return false;
		//����ѡ������4�˺�������1�˺�
		for(int i = 0; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[oppoId][i] != null) {
				int value = -1;
				if(i == IdIdx[1])
					value = -4;
				con.changeStandCardHp(oppoId, i, value);
			}
		}
		return true;
	}
}
