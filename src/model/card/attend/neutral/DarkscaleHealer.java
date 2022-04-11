package model.card.attend.neutral;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ����������
 * */
public class DarkscaleHealer extends Attend {
	public DarkscaleHealer() {
		super(5, 4, 5, "����������", Nature.None, "ս��Ϊ�����ѷ���ɫ�ָ�2������ֵ");
		this.features.add(Feature.NORMAL);
		
		this.hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		//�ı������ѷ�Ѫ��
		for(int i = 0; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[nowId][i] != null) {
				con.changeStandCardHp(nowId, i, 2);
			}
		}
		
		return true;
	}
}
