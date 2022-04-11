package model.card.attend.shengqi;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

public class ThePure extends Attend {
	public ThePure() {
		super(7, 3, 7, "������Ү�����", Nature.None, "ս��ʹ���ез���ӵĹ�������Ϊ1");
		this.features.add(Feature.NORMAL);
		
		hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		int oppoId = 1 - nowId;
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[oppoId][i] != null) {
				//�Ĺ�����
				Attend attend = con.mainV.stands[oppoId][i].attend;
				attend.atk = 1;
				con.mainV.setStandCard(attend, oppoId, i);
			}
		}
		
		
		return true;
	}
}
