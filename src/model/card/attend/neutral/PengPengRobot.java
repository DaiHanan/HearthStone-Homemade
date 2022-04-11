package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ���������
 * */
public class PengPengRobot extends Attend {
	public PengPengRobot() {
		super(1, 1, 1, "���������", Nature.None, "��������һ������������1-4���˺�");
		this.features.add(Feature.NORMAL);
		
		hasDeadWords = true;
	}
	
	@Override
	public void deadWords(Controller con, int nowId) {
		super.deadWords(con, nowId);
		int ran = (int)Math.floor(Math.random() * 4) + 1;
		int[] IdIdx = con.randomTarget(6, nowId);
		if(IdIdx[0] != -1) {
			con.changeStandCardHp(IdIdx[0], IdIdx[1], 0 - ran);
		}
	}
		
}

