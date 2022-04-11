package model.card.attend.fashi;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ����˿
 * */
public class RagnarosFirelord extends Attend {
	public RagnarosFirelord() {
		super(8, 8, 8, "��ħ֮��", Nature.None, "�޷����� �ڻغϽ���ʱ����Ե��� ���8���˺�");
		this.features.add(Feature.NORMAL);
		
		this.canAtk=false;
		this.hasEndTurnEffect = true;
	}
	
	@Override
	public void endTurnEffect(Controller con, int nowId) {
		super.endTurnEffect(con, nowId);
		//վ���±�����
		int oppoId = 1 - nowId;
		int idxs[] = new int[Config.standMaxCount + 1];
		//��õз����ϵ�λ����,���洢�±�
		int size = 0;
		for(int j = 0; j < Config.standMaxCount + 1; j++)
			if(con.mainV.stands[oppoId][j] != null) {
				idxs[size] = j;
				size++;
			}
		//��������
		int ran = (int)Math.floor(Math.random() * size);
		//�޸ĸ����Ѫ��
		con.changeStandCardHp(oppoId, idxs[ran], -8);
	
		
	}
}
