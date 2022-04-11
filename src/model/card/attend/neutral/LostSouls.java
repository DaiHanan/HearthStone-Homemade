package model.card.attend.neutral;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ��ʧ���Ļ�
 * */
public class LostSouls extends Attend {
	public LostSouls() {
		super(1, 2, 2, "��ʧ���Ļ�", Nature.None, "���ʹ���������ӻ��+1������");
		this.features.add(Feature.NORMAL);
		
		hasDeadWords = true;
	}
	
	@Override
	public void deadWords(Controller con, int nowId) {
		super.deadWords(con, nowId);
		//�ӹ�����
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[nowId][i] != null) {
				con.changeStandCardAtk(nowId, i, 1);
			}
		}
		
	}
}
