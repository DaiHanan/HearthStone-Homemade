package model.card.attend.neutral;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * ���鲩ʿ
 * */
public class DrPengPeng extends Attend {
	public DrPengPeng() {
		super(7, 7, 7, "���鲩ʿ", Nature.None, "ս�� �ٻ�����1/1���������");
		this.features.add(Feature.NORMAL);
		
		hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		for (int i = 0; i < 2; i++)
		{
			Attend robot=new PengPengRobot();
			con.addStandCard(robot, nowId, nowIdx);
		}
		
		return true ;
	}
}