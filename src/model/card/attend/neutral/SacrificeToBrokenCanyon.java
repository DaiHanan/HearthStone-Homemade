package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * �����������
 * */
public class SacrificeToBrokenCanyon extends Attend {
	public SacrificeToBrokenCanyon() {
		super(2, 3, 2, "�����������", Nature.None, "ս��:ʹһ���ѷ���ӻ��+1/+1");
		this.features.add(Feature.NORMAL);
		
		this.hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		int[] IdIdx = con.inquiryTarget(5, nowId);
		if(IdIdx[0] == -1)
			return false;
		//�޸Ĺ�������Ѫ��
		Attend attend = con.mainV.stands[IdIdx[0]][IdIdx[1]].attend;
		attend.atk += 1;
		attend.hp += 1;
		con.mainV.setStandCard(attend, IdIdx[1], IdIdx[0]);
		
		return true;
	}
}
