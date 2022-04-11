package model.card.attend.shengqi;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ����������ķ
 * */
public class Tallim extends Attend {
	public Tallim() {
		super(7, 3, 6, "����������ķ", Nature.None, "����ս��ʹ����������ӵĹ�����������ֵ���3");
		this.features.add(Feature.Taunt);
		
		hasWarCry = true;
	}
	
	@Override
		public boolean warCry(Controller con, int nowId, int nowIdx) {
			super.warCry(con, nowId, nowIdx);
			
			//�޸�����������ӵ�����
			for(int i = 0; i < 2; i++) {
				for(int j = 1; j < Config.standMaxCount + 1; j++)
					if(con.mainV.stands[i][j] != null) {
						Attend attend = con.mainV.stands[i][j].attend;
						attend.hp = attend.hpMax = 3;
						attend.atk = 3;
						con.mainV.setStandCard(attend, j, i);
					}
			}
			
			return true ;
		}
}