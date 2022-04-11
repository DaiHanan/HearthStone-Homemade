package model.card.attend.deluyi;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

public class LeopardKnight extends Attend {
	public LeopardKnight() {
		super(2, 3, 2, "����ʿ", Nature.None, "ս��:���һ���յ�ˮ��������:ʧȥһ��ˮ��");
		this.features.add(Feature.NORMAL);
		
		this.hasWarCry = true;
		this.hasDeadWords = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		//���һ���շ���ˮ��
		con.changeCosts(2, nowId, 1);
		
		return true;
	}
	
	@Override
	public void deadWords(Controller con, int nowId) {
		super.deadWords(con, nowId);
		
		//ʧȥһ���غ�ˮ��
		con.changeCosts(2, nowId, -1);
	}
}
