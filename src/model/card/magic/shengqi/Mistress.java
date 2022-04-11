package model.card.magic.shengqi;

import config.Config;
import controller.Controller;
import model.card.attend.shengqi.ReportSoldier;
import model.card.magic.Magic;

public class Mistress extends Magic {
	public Mistress() {
		super(1, "��ʧ����");
		this.desc = "�ٻ�����1/1�İ����±�";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//��������޷�ʹ��
		if(con.getStandCardCount(nowId) >= Config.standMaxCount)
			return false;
		
		for(int i = 0; i < 2; i++) {
			con.addStandCard(new ReportSoldier(), nowId, -1);
		}
		
		return true;
	}
}
