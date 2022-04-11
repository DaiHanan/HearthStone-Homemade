package model.card.magic.fashi;

import controller.Controller;
import model.card.attend.Attend;
import model.card.magic.Magic;

/**
 * 熔岩镜像
 * */
public class LavaImage extends Magic {
	public LavaImage() {
		super(3, "熔岩镜像");
		this.desc = "选择一个友方随从，召唤一个该随从的无效果复制";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int IdIdx[] = con.inquiryTarget(5, nowId);
		if(IdIdx[0] == -1)
			return false;
		
		//创建随从并修改属性
		Attend attend = new Attend(con.mainV.stands[IdIdx[0]][IdIdx[1]].attend);
		attend.desc = "镜像";
		//如果没有位置则使用失败
		if(con.addStandCard(attend, nowId, -1) == false)
			return false;
		
		return true;
	}
}