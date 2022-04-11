package model.card.magic.fashi;

import config.Config;
import controller.Controller;
import model.card.magic.Magic;

/**
 * ħ����
 * */
public class ArcaneExplosio extends Magic {
	public ArcaneExplosio() {
		super(2, "ħ����");
		this.desc = "�����ез�������1���˺�";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int oppoId = 1 - nowId;
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[oppoId][i] != null)
				con.changeStandCardHp(oppoId, i, -1);
		}
		
		return true;
	}
}
