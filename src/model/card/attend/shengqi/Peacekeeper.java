package model.card.attend.shengqi;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * �¶�����ʿ
 * */
public class Peacekeeper extends Attend {
	public Peacekeeper() {
		super(3, 3, 3, "�¶�����ʿ", Nature.None, "ս��ʹһ���з���ӹ�������Ϊ1");
		this.features.add(Feature.NORMAL);
		
		hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		int IdIdx[] = con.inquiryTarget(6, nowId);
		if(IdIdx[0] == -1)
			return false;
		
		//�Ĺ�����
		Attend attend = con.mainV.stands[IdIdx[0]][IdIdx[1]].attend;
		attend.atk = 1;
		con.mainV.setStandCard(attend, IdIdx[1], IdIdx[0]);
		
		return true;
	}
}
