package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ShadePane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Color from, to;
	public ShadePane(String title, Color from, Color to) {
		this.setLayout(null);
		
		this.from = from;
		this.to = to;
		
		this.setBorder(BorderFactory.createTitledBorder(null, title, TitledBorder.LEFT, 
				TitledBorder.DEFAULT_POSITION, new Font("ºÚÌå", Font.BOLD, 25), to));
	}
	
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D)g1;
		//»æÖÆ±³¾°
		g.setPaint(new GradientPaint(0, 0, from, 0, this.getHeight(), to, true));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
}
