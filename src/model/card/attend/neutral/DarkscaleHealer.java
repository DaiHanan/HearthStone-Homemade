package model.card.attend.neutral;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 暗鳞治愈者
 * */
public class DarkscaleHealer extends Attend {
	public DarkscaleHealer() {
		super(5, 4, 5, "暗鳞治愈者", Nature.None, "战吼：为所有友方角色恢复2点生命值");
		this.features.add(Feature.NORMAL);
		
		this.hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		//改变所有友方血量
		for(int i = 0; i < Config.standMaxCount + 1; i++) {
			if(con.mainV.stands[nowId][i] != null) {
				con.changeStandCardHp(nowId, i, 2);
			}
		}
		
		return true;
	}
}
