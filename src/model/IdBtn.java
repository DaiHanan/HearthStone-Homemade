package model;

import javax.swing.JButton;
//创建有id的按钮代替原按钮，方便获得id
public class IdBtn extends  JButton {
	private static final long serialVersionUID = 1L;
	
	public int id;//所属玩家id
	
	public IdBtn(int id) {
		super();
		this.id = id;
	}
	public IdBtn(int id, String name){
		super(name);
		this.id = id;
	}
}
