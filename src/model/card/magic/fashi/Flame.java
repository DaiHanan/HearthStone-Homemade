package model.card.magic.fashi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * ��ʦ����
 * */
public class Flame extends Magic {
	public int damage = 1;
	
	public Flame() {
		super(2, "���");
		//��δ���Ƿ�ǿ
		this.desc = "��Ŀ����� " + damage + "�㷨���˺�";
	}
	//������ʹ��
	protected Flame(int cost, String name, int damage) {
		this.name = name;
		this.cost = cost;
		this.damage = damage;
		//��δ���Ƿ�ǿ
		this.desc = "��Ŀ����� " + damage + "�㷨���˺�";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int[] target = con.inquiryTarget(1, nowId);
		//ʹ�óɹ�
		if(target[0] != -1) {
			//�޸�����
			con.changeStandCardHp(target[0], target[1], 0 - damage);
			return true;
		}
		//ʹ��ʧ��
		return false;
	}
}
