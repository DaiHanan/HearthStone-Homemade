package model.card.magic.shengqi;

import controller.Controller;
import model.Feature;
import model.card.attend.Attend;
import model.card.magic.Magic;

/**
 * Ӣ��ʥӡ
 * */
public class SealOfChampions extends Magic {
	public SealOfChampions() {
		super(3, "Ӣ��ʥӡ");
		this.desc = "ʹһ����ӻ��+3�����������ʥ��";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int IdIdx[] = con.inquiryTarget(7, nowId);
		if(IdIdx[0] == -1)
			return false;
		//�޸Ĺ��������������
		Attend attend = con.mainV.stands[IdIdx[0]][IdIdx[1]].attend;
		attend.atk += 3;
		if(!attend.features.contains(Feature.Shield)) {
			//���ԭ�����ԣ�ɾ��������
			if(attend.features.contains(Feature.NORMAL))
				attend.features.remove(Feature.NORMAL);
			attend.features.add(Feature.Shield);
		}
		con.mainV.setStandCard(attend, IdIdx[1], IdIdx[0]);
		
		return true;
	}
}
