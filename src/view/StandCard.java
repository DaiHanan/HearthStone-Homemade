package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import config.Config;
import controller.Listen;
import model.Feature;
import model.Player;
import model.card.attend.Attend;

public class StandCard extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Attend attend;//����ʾ���
	public int id;//�������id
	public int x, y;//ģ������
	
	JPanel topPanel;//���Ժ�λ�õ��Զ���Panel
	JPanel midPanel;//���ƺͻ��׵��Զ���Panel
	JPanel bottonPanel;//ֵ�����Ե��Զ���Panel
	//JPanel featurePanel;//�����Զ���Panel
	//��ǩ
	public JLabel numberLabel;//λ��
	public JLabel natureLabel;//����
	//public LinkedList<JLabel> featureLabel;//����
	public JLabel featureLabel;//����
	public JLabel nameLabel;//����
	public JLabel armorLabel;//����
	public JLabel hpLabel;//����ֵ
	public JLabel atkLabel;//������

	public StandCard(int id, Attend attend, int x, int y, int idx) {
		this.id = id;
		this.attend = attend;
//		this.setBounds(x, y, Config.attendWeight, Config.attendHeight);
//		//�����������ñ�����ɫ
//		this.setBackground(Config.standBackColor[attend.feature.ordinal()]);
//		//�����Ű�
//		this.setLayout(new BorderLayout());
//		/**������Ա�ǩ*/
//		natureLabel = new JLabel(Config.natureName[attend.nature.ordinal()], JLabel.CENTER);
//		natureLabel.setForeground(Config.natureColor[attend.nature.ordinal()]);
//		natureLabel.setFont(Config.natureFont);
//		//natureLable.setSize(Config.attendWeight, 1);
//		this.add(natureLabel, BorderLayout.NORTH);
//		/**������Ʊ�ǩ*/
//		nameLabel = new JLabel(attend.name, JLabel.CENTER);
//		nameLabel.setForeground(Config.nameColor);
//		nameLabel.setFont(Config.nameFont);
//		//nameLable.setSize(Config.attendWeight, 1);
//		this.add(nameLabel, BorderLayout.CENTER);
//		//����ֵ�����Ե��Զ���Panel
//		JPanel bottonPanel = new JPanel(new GridLayout(1, 3));
//		bottonPanel.setBackground(Config.standBackColor[attend.feature.ordinal()]);
//		this.add(bottonPanel, BorderLayout.SOUTH);
//		/**��ӹ�������ǩ*/
//		atkLabel = new JLabel("" + attend.atk, JLabel.LEFT);
//		atkLabel.setFont(Config.atkFont);
//		atkLabel.setForeground(Config.atkColor);
//		bottonPanel.add(atkLabel);
//		/**������Ա�ǩ*/
//		featureLabel = new JLabel(Config.featureName[attend.feature.ordinal()], JLabel.CENTER);
//		featureLabel.setFont(Config.featureFont);
//		bottonPanel.add(featureLabel);
//		/**�������ֵ��ǩ*/
//		hpLabel = new JLabel("" + attend.hp, JLabel.RIGHT);
//		hpLabel.setFont(Config.hpFont);
//		hpLabel.setForeground(Config.hpColor);
//		bottonPanel.add(hpLabel);
		
		//����λ�ô�С
		this.setBounds(x, y, Config.attendWeight, Config.attendHeight);
		//�����Ű�
		this.setLayout(new BorderLayout());
		//�������Ժ�λ�õ��Զ���Panel
		topPanel = new JPanel(new GridLayout(1, 3));
		topPanel.setOpaque(false);
		this.add(topPanel, BorderLayout.NORTH);
		/**���λ�ñ�ǩ*/
		numberLabel = new JLabel("", JLabel.CENTER);
		if(idx != 0) 
			numberLabel.setText(idx + "��λ");
		numberLabel.setFont(Config.numberFont);
		numberLabel.setForeground(Config.numberColor);
		topPanel.add(numberLabel);
		/**������Ա�ǩ*/
		natureLabel = new JLabel("", JLabel.CENTER);
		natureLabel.setFont(Config.natureFont);
		//natureLable.setSize(Config.attendWeight, 1);
		topPanel.add(natureLabel);
		/**��topPanel���͸����ǩ*/
		topPanel.add(new JLabel());
		//�������ƺͻ��׵��Զ���Panel
		midPanel = new JPanel(new BorderLayout());
		midPanel.setOpaque(false);
		this.add(midPanel, BorderLayout.CENTER);
//		/**��midPanel���͸����ǩ*/
//		midPanel.add(new JLabel(), BorderLayout.WEST);
		/**������Ʊ�ǩ*/
		nameLabel = new JLabel("", JLabel.CENTER);
		nameLabel.setForeground(Config.nameColor);
		//nameLabel.setFont(Config.nameFont);
		//nameLable.setSize(Config.attendWeight, 1);
		//this.add(nameLabel, BorderLayout.CENTER);
		midPanel.add(nameLabel, BorderLayout.CENTER);
		/**��ӻ��ױ�ǩ��Ĭ�ϲ���ʾ*/
		armorLabel = new JLabel("", JLabel.CENTER);
		armorLabel.setForeground(Config.armorColor);
		armorLabel.setFont(Config.armorFont);
		//armorLabel.setOpaque(false);
		midPanel.add(armorLabel, BorderLayout.EAST);
		//����ֵ�����Ե��Զ���Panel
		bottonPanel = new JPanel(new GridLayout(1, 3));
		bottonPanel.setOpaque(false);
		this.add(bottonPanel, BorderLayout.SOUTH);
		/**��ӹ�������ǩ*/
		atkLabel = new JLabel("", JLabel.LEFT);
		atkLabel.setFont(Config.atkFont);
		atkLabel.setForeground(Config.atkColor);
		bottonPanel.add(atkLabel);
		//���������Զ���Panel
//		featurePanel = new JPanel();
//		featurePanel.setOpaque(false);
//		bottonPanel.add(featurePanel);
		/**������Ա�ǩ*/
		featureLabel = new JLabel("", JLabel.CENTER);
		featureLabel.setFont(Config.featureFont);
		bottonPanel.add(featureLabel);
		/**�������ֵ��ǩ*/
		hpLabel = new JLabel("", JLabel.RIGHT);
		hpLabel.setFont(Config.hpFont);
		bottonPanel.add(hpLabel);
		//���ø���ǩ������
		setAttend(attend);
		//����г�����ԣ���ʼ��ʱ��ʾ�߿�
		if(attend.atk > 0 && (attend.features.contains(Feature.Dash) || attend.atkTime > 0))
			borderNow();
		//���ü�����
		Listen.setCardListener(this);
	}
	
	public void setAttend(Attend attend) {
		this.attend = attend;
		//�����������ñ�����ɫ
		this.setBackground(Config.standBackColor[attend.nature.ordinal()]);
		/**�������Ա�ǩ*/
		natureLabel.setText(Config.natureName[attend.nature.ordinal()]);
		natureLabel.setForeground(Config.natureColor[attend.nature.ordinal()]);
		//natureLable.setSize(Config.attendWeight, 1);
		/**�������Ʊ�ǩ*/
		//����������ʱ��С�ֺ�
		if(attend.name.length() < 5) 
			nameLabel.setFont(Config.nameFont);
		else 
			nameLabel.setFont(Config.nameSmallFont);
		nameLabel.setText(attend.name);
		//nameLable.setSize(Config.attendWeight, 1);
		//����ֵ�����Ե��Զ���Panel
		//bottonPanel.setBackground(Config.standBackColor[attend.feature.ordinal()]);
		/**���û��ױ�ǩ*/
		//����л��ײ���ʾ
		if(attend instanceof Player && ((Player) attend).armor > 0) 
			armorLabel.setText("" + ((Player) attend).armor);
		else
			armorLabel.setText("");
		/**���ù�������ǩ*/
		atkLabel.setText("" + attend.atk);
		/**�������Ա�ǩ*/
		//���ֻ��һ����������ʾ������
		if(attend.features.size() <= 1) {
			featureLabel.setText(Config.featureName[attend.features.getFirst().ordinal()]);
			featureLabel.setForeground(Config.featureColor[attend.features.getFirst().ordinal()]);
		}
		//����ж������
		else {
			featureLabel.setText(Config.moreFeatureStr);
			featureLabel.setForeground(Config.moreFeatureColor);
		}
//		//�Ȱ�֮ǰ���е����Ա�ǩɾ��
//		featurePanel.removeAll();
//		//��ѭ�����
//		for(ListIterator<Feature> iter = attend.features.listIterator(); iter.hasNext();) {
//			Feature feature = iter.next();
//			JLabel featureLabel = new JLabel(Config.featureName[feature.ordinal()]);
//			featureLabel.setFont(Config.featureFont);
//			featureLabel.setForeground(Config.featureColor[feature.ordinal()]);
//			featurePanel.add(featureLabel);
//		}
		/**��������ֵ��ǩ*/
		if(attend.hp >= attend.hpMax)//��Ѫ��ɫ
			hpLabel.setForeground(Config.hpMAXColor);
		else//��Ѫ��ɫ
			hpLabel.setForeground(Config.hpColor);
		hpLabel.setText("" + attend.hp);
		
		if(attend.atk > 0 && attend.atkTime > 0)
			borderNow();
	}
	
	//�ҷ�ʱ�ı߿�
	public void borderNow() {
		//���������
		if(this.attend.isFroozen)
			this.setBorder(Config.froozenBorder);
		//����й�������
		else if(this.attend.atkTime > 0)
			this.setBorder(Config.canAtkBorder);
		else
			this.setBorder(null);
	}
	//�з�ʱ�ı߿�
	public void borderOppo() {
		//����г���
		if(this.attend.features.contains(Feature.Taunt))
			this.setBorder(Config.tauntBorder);
		//���������
		else if(this.attend.isFroozen)
			this.setBorder(Config.froozenBorder);
		else
			this.setBorder(null);
	}
	//�رձ߿�
	public void borderOff() {
		this.setBorder(null);
	}
	
//	@Override
//	public void paintComponent(Graphics g1) {
//		super.paintComponent(g1);
//		Graphics2D g = (Graphics2D)g1;
//		//���ÿ���ݿ�
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		/**��������Nature*/
//		//������ɫ
//		g.setColor(Config.natureColor[attend.nature.ordinal()]);
//		//������������Դ����ô�ӡ����
//		String natureName = Config.natureName[attend.nature.ordinal()];
//		//�����ӡX���±�
//		int natureX = Config.attendWeight / 2 - 4 * natureName.length();
//		//����
//		g.drawString(natureName, natureX, Config.attendNatureY);
//		/**��������Feature*/
//		
//	}
	

}
