package model;

import javax.swing.JButton;
//������id�İ�ť����ԭ��ť��������id
public class IdBtn extends  JButton {
	private static final long serialVersionUID = 1L;
	
	public int id;//�������id
	
	public IdBtn(int id) {
		super();
		this.id = id;
	}
	public IdBtn(int id, String name){
		super(name);
		this.id = id;
	}
}
