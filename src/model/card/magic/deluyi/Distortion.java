package model.card.magic.deluyi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * ��³������
 * */
public class Distortion extends Magic {
	public Distortion() {
		super(2, "����");
		//��δ���Ƿ�ǿ
		this.desc = "���غϻ��1�㹥���������1�㻤��";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//���ӹ�����
		con.player[nowId].atk += 1;
		//���ӻ���
		con.changePlayerArmor(nowId, 1);
		//ˢ����ͼ
		con.mainV.setStandCard(con.player[nowId], 0, nowId);
		
		return true;
	}
}
