package model.card.magic.deluyi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * 德鲁伊技能
 * */
public class Distortion extends Magic {
	public Distortion() {
		super(2, "变形");
		//暂未考虑法强
		this.desc = "本回合获得1点攻击，并获得1点护甲";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//增加攻击力
		con.player[nowId].atk += 1;
		//增加护甲
		con.changePlayerArmor(nowId, 1);
		//刷新视图
		con.mainV.setStandCard(con.player[nowId], 0, nowId);
		
		return true;
	}
}
