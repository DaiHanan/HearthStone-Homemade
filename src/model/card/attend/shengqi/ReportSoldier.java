package model.card.attend.shengqi;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 报告兵
 * */
public class ReportSoldier extends Attend {
	public ReportSoldier() {
		super(1, 1, 1, "白银新兵", Nature.None, "");
		this.features.add(Feature.NORMAL);
	}
}
