package view;

import java.awt.BorderLayout;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import config.Config;
import config.Tool;
import model.Feature;
import model.Player;
import model.card.Card;
import model.card.attend.Attend;
import model.card.magic.Magic;

public class DescPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Card card;//����ʾ�����
	public int x, y;//ģ������
	
	JPanel topPanel;//���Ժ����Ƶ��Զ���Panel
	JPanel bottonPanel;//ֵ�����Ե��Զ���Panel
	JPanel featurePanel;//�����Զ���Panel
	JPanel hpArmorPanel;//Ѫ���ͻ����Զ���Panel
	//��ǩ
	public JLabel natureLabel;//����
	//public JLabel featureLabel;//����
	public JLabel nameLabel;//����
	public JLabel costLabel;//����
	public JLabel hpLabel;//����ֵ
	public JLabel armorLabel;//����
	public JLabel atkLabel;//������
	public JLabel descLable;//��Ƭ����

	public DescPane(Card card, int x, int y) {
		//����λ�ô�С
		this.setBounds(x, y, Config.descPaneWeight, Config.descPaneHeight);
		//�����Ű�
		this.setLayout(new BorderLayout());
		//���Ժ����Ƶ��Զ���Panel
		topPanel = new JPanel(new BorderLayout());
		topPanel.setOpaque(false);
		this.add(topPanel, BorderLayout.NORTH);
		/**������Ա�ǩ*/
		natureLabel = new JLabel("", JLabel.CENTER);
		natureLabel.setFont(Config.natureFont);
		//natureLable.setSize(Config.attendWeight, 1);
		topPanel.add(natureLabel, BorderLayout.WEST);
		/**������Ʊ�ǩ*/
		nameLabel = new JLabel("", JLabel.CENTER);
		nameLabel.setForeground(Config.nameColor);
		nameLabel.setFont(Config.nameFont);
		//nameLable.setSize(Config.attendWeight, 1);
		topPanel.add(nameLabel, BorderLayout.CENTER);
		/**��ӷ��ñ�ǩ*/
		costLabel = new JLabel("", JLabel.CENTER);
		costLabel.setForeground(Config.costColor);
		costLabel.setFont(Config.costFont);
		topPanel.add(costLabel, BorderLayout.EAST);
		/**���������ǩ*/
		descLable = new JLabel("", JLabel.CENTER);
		descLable.setForeground(Config.descColor);
		descLable.setFont(Config.descFont);
		this.add(descLable, BorderLayout.CENTER);
		//����ֵ�����Ե��Զ���Panel
		bottonPanel = new JPanel(new BorderLayout());
		bottonPanel.setOpaque(false);
		this.add(bottonPanel, BorderLayout.SOUTH);
		/**��ӹ�������ǩ*/
		atkLabel = new JLabel("", JLabel.LEFT);
		atkLabel.setFont(Config.atkFont);
		atkLabel.setForeground(Config.atkColor);
		bottonPanel.add(atkLabel, BorderLayout.WEST);
		//���������Զ���Panel
		featurePanel = new JPanel();
		featurePanel.setOpaque(false);
		bottonPanel.add(featurePanel, BorderLayout.CENTER);
//		/**������Ա�ǩ*/
//		featureLabel = new JLabel("", JLabel.CENTER);
//		featureLabel.setFont(Config.featureFont);
//		bottonPanel.add(featureLabel);
		/**�������ֵ��ǩ*/
		hpLabel = new JLabel("", JLabel.RIGHT);
		hpLabel.setFont(Config.hpFont);
		bottonPanel.add(hpLabel, BorderLayout.EAST);
		/**��ӻ��ױ�ǩ*/
		armorLabel = new JLabel("", JLabel.CENTER);
		armorLabel.setFont(Config.armorFont);
		armorLabel.setForeground(Config.armorColor);
		
		setCard(card);
		this.setVisible(false);
	}
	
	public void setCard(Card card) {
		this.card = card;
		/**���÷��ñ�ǩ*/
		costLabel.setText("" + card.cost);
		/**����������ǩ*/
		Tool.setJLabelText(descLable, card.desc);
		//descLable.setText(card.desc);
		/**�������Ʊ�ǩ*/
		nameLabel.setText(card.name);
		//����ÿ�Ƭ�����
		if(card instanceof Attend) {
			Attend attend = (Attend)card;
			//�����������ñ�����ɫ
			this.setBackground(Config.standBackColor[attend.nature.ordinal()]);
			//����ֵ�����Ե��Զ���Panel
			//topPanel.setBackground(Config.standBackColor[attend.feature.ordinal()]);
			/**�������Ա�ǩ*/
			natureLabel.setText(Config.natureName[attend.nature.ordinal()]);
			natureLabel.setForeground(Config.natureColor[attend.nature.ordinal()]);
			//����ֵ�����Ե��Զ���Panel
			//bottonPanel.setBackground(Config.standBackColor[attend.feature.ordinal()]);
			/**���ù�������ǩ*/
			atkLabel.setText("" + attend.atk);
			/**�������Ա�ǩ*/
			//�Ȱ�֮ǰ���е����Ա�ǩɾ��
			featurePanel.removeAll();
			//��ѭ�����
			for(ListIterator<Feature> iter = attend.features.listIterator(); iter.hasNext();) {
				Feature feature = iter.next();
				JLabel featureLabel = new JLabel(Config.featureName[feature.ordinal()]);
				featureLabel.setFont(Config.featureFont);
				featureLabel.setForeground(Config.featureColor[feature.ordinal()]);
				featurePanel.add(featureLabel);
			}
			/**��������ֵ��ǩ*/
			if(attend.hp >= attend.hpMax)//��Ѫ��ɫ
				hpLabel.setForeground(Config.hpMAXColor);
			else//��Ѫ��ɫ
				hpLabel.setForeground(Config.hpColor);
			hpLabel.setText("" + attend.hp);
			/**���û��ױ�ǩ*/
			//�����Ӣ�����л���ʱ����ʾ���׶�����ʾ����
			if(attend instanceof Player && ((Player) attend).armor > 0) {
				armorLabel.setText("" + ((Player) attend).armor);
				bottonPanel.add(armorLabel, BorderLayout.CENTER);
				bottonPanel.remove(featurePanel);
			}
			else {
				bottonPanel.add(featurePanel, BorderLayout.CENTER);
				bottonPanel.remove(armorLabel);
				//armorLabel.setText("");
			}
		}
		//����Ƿ�����ȡ����ʾ������������ֵ�����Ժ�����
		else if(card instanceof Magic) {
			this.setBackground(Config.magicDescColor);
			atkLabel.setText("");
			hpLabel.setText("");
			natureLabel.setText("");
			featurePanel.removeAll();
			//featureLabel.setText("");
		}
	}
}
