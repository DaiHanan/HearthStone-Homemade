package model.card.attend.shengqi;

import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * �����
 * */
public class ReportSoldier extends Attend {
	public ReportSoldier() {
		super(1, 1, 1, "�����±�", Nature.None, "");
		this.features.add(Feature.NORMAL);
	}
}
