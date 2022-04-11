package model.card.attend.deluyi;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

public class LeopardKnight extends Attend {
	public LeopardKnight() {
		super(2, 3, 2, "豹骑士", Nature.None, "战吼:获得一个空的水晶，亡语:失去一个水晶");
		this.features.add(Feature.NORMAL);
		
		this.hasWarCry = true;
		this.hasDeadWords = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		//获得一个空法术水晶
		con.changeCosts(2, nowId, 1);
		
		return true;
	}
	
	@Override
	public void deadWords(Controller con, int nowId) {
		super.deadWords(con, nowId);
		
		//失去一个回合水晶
		con.changeCosts(2, nowId, -1);
	}
}
