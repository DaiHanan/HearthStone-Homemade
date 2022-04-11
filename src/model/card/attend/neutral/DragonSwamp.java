package model.card.attend.neutral;


import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * �������
 * */
public class DragonSwamp extends Attend {
	public DragonSwamp(){
		super(3,5,4,"�������",Nature.Dragon,"ս��Ϊ�з��ٻ�һ��2/1�Ҿ��о綾�ķ������֡�");
		this.features.add(Feature.NORMAL);
		hasWarCry = true;
	}
	
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		Attend card = new DragonHunter();
	    con.addStandCard(card, 1 - nowId, -1);
	    
	    return true;
	}
}
