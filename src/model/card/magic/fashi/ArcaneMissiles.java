package model.card.magic.fashi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * �����ɵ�
 * */
public class ArcaneMissiles extends Magic {
	public ArcaneMissiles() {
		super(1, "�����ɵ�");
		this.desc = "���3���˺������������з���ɫ";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//������Ŀ��
		int IdIdx[] = con.randomTarget(2, nowId);
		if(IdIdx[0] == -1)
			return false;
		for(int i = 0; i < 3; i++) {
			//�޸ĸ����Ѫ��
			if(IdIdx[0] != -1)
				con.changeStandCardHp(IdIdx[0], IdIdx[1], -1);
			//������Ŀ��
			IdIdx = con.randomTarget(2, nowId);
//			//վ���±�����
//			int idxs[] = new int[Config.standMaxCount + 1];
//			//��õз����ϵ�λ����,���洢�±�
//			int size = 0;
//			for(int j = 0; j < Config.standMaxCount + 1; j++)
//				if(con.mainV.stands[oppoId][j] != null) {
//					idxs[size] = j;
//					size++;
//				}
//			//��������
//			int ran = (int)Math.floor(Math.random() * size);
		}
		
		return true;
	}
}
