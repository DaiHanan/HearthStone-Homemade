package model.card.magic.shengqi;

import controller.Controller;
import model.card.attend.shengqi.ReportSoldier;
import model.card.magic.Magic;

/**
 * ����
 * */
public class Report extends Magic {
	public Report() {
		super(2, "����");
		this.desc = "�ٻ�һ��1/1�İ����±�";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//�ٻ����
		//�������û��λ�ã�ʹ��ʧ��
		return con.addStandCard(new ReportSoldier(), nowId, -1);
		
	}
}
