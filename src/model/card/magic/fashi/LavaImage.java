package model.card.magic.fashi;

import controller.Controller;
import model.card.attend.Attend;
import model.card.magic.Magic;

/**
 * ���Ҿ���
 * */
public class LavaImage extends Magic {
	public LavaImage() {
		super(3, "���Ҿ���");
		this.desc = "ѡ��һ���ѷ���ӣ��ٻ�һ������ӵ���Ч������";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int IdIdx[] = con.inquiryTarget(5, nowId);
		if(IdIdx[0] == -1)
			return false;
		
		//������Ӳ��޸�����
		Attend attend = new Attend(con.mainV.stands[IdIdx[0]][IdIdx[1]].attend);
		attend.desc = "����";
		//���û��λ����ʹ��ʧ��
		if(con.addStandCard(attend, nowId, -1) == false)
			return false;
		
		return true;
	}
}