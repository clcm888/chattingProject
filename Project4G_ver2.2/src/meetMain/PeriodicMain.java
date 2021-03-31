package meetMain;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import mainFrameDB.MainDBVO;
import memberDB.MemberDBVO;
import noticeboardDB.NoticeboardDBDAO;
import noticeboardDB.NoticeboardDBVO;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PeriodicMain extends JPanel {
	
	MemberDBVO login;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	int page;
	
	protected static final String String = null;
	private MemberDBVO mvo;
	private NoticeboardDBDAO ndao;
	private PeriodicMainAdd pda;
	private NoticeboardDBVO nvo;
	private MainDBVO main;
	
	ArrayList<NoticeboardDBVO> noticeArray;
	JButton btnAddBoard;
	JPanel lowerPanel;
	
	private JPanel contentPane, mainPanel;
	private JScrollPane scrollPane;
	private JPanel[] arrPanel;
	private GridBagConstraints[] gridBagConstraints;
	private JLabel nameLabel, titleLabel, dateLabel, nameTxtLabel, titleTxtLabel, dateTxtlabel;
	
	private Color bgcolor = new Color(92, 198, 181) ; //배경색
	
	@SuppressWarnings("unchecked")
	public PeriodicMain(MemberDBVO login,DataInputStream dis,DataOutputStream dos,ObjectInputStream ois,int page) throws SQLException {
		this.login=login;
		this.dis=dis;
		this.dos=dos;
		this.ois=ois;
		this.page=page;
		noticeArray = new ArrayList<NoticeboardDBVO>();
		
		try {
			
			if(page==2) {
				dos.writeInt(3);
				dos.writeUTF("id");
				dos.writeUTF(1+"noticeboardSelect()");
			}else if(page==3) {
				dos.writeInt(3);
				dos.writeUTF("id");
				dos.writeUTF(2+"noticeboardSelect()");
			}else {
				dos.writeInt(3);
				dos.writeUTF("id");
				dos.writeUTF(3+"noticeboardSelect()");
			}
			if(ois!=null) {
				noticeArray=(ArrayList<NoticeboardDBVO>) ois.readObject();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setContentPane();
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		scrollPane.setBounds(10, 10, 512, 550);
		lowerPanel.add(scrollPane);
		
		btnAddBoard = new JButton("\uCD94\uAC00");
		btnAddBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new PeriodicMainAdd(login,dis, dos, ois, page, noticeArray).setVisible(true);
			}
		});
		btnAddBoard.setBounds(408, 577, 114, 39);
		btnAddBoard.setBackground(bgcolor);
		btnAddBoard.setForeground(Color.white);
		btnAddBoard.setBorder(BorderFactory.createLineBorder(Color.white));
		lowerPanel.add(btnAddBoard);
		
		mainPanel = new JPanel();
		scrollPane.setViewportView(mainPanel);
		GridBagLayout mainGBL = new GridBagLayout();
		mainGBL.columnWidths = new int[] {468, 0};
		mainGBL.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		mainGBL.rowHeights = new int[noticeArray.size()+1]/*{92, 92, 92, 92, 92, 92, 0}*/;
		mainGBL.rowWeights = new double[noticeArray.size()+1]/*{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE}*/;
		for(int j = 0 ; j < noticeArray.size() ; j++) {
			mainGBL.rowHeights[j] = 92;
			mainGBL.rowHeights[noticeArray.size()] = 0;
			mainGBL.rowWeights[j] = 0.0;
			mainGBL.rowWeights[noticeArray.size()] = Double.MIN_VALUE;
		}
		mainPanel.setLayout(mainGBL);
		mainPanel.setBackground(bgcolor);
		mainPanel.setBorder(new LineBorder(Color.white));
		
		for(int i = 0 ; i < noticeArray.size() ; i++) {
			arrPanel = new JPanel[noticeArray.size()];
			gridBagConstraints = new GridBagConstraints[noticeArray.size()];

			arrPanel[i] = new JPanel();
			arrPanel[i].setBackground(Color.WHITE);
			arrPanel[i].setBorder(BorderFactory.createLineBorder(Color.white, 1));
			
			NoticeboardDBVO vo = noticeArray.get(i);
			//for문 내에서만 vo.setType로 값 변경
			vo.setType(i);
			
			
			arrPanel[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int j = vo.getType();
					try {
						new PeriodicMainDetailView(login, dis, dos, ois, noticeArray,  j,  page).setVisible(true);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			gridBagConstraints[i] = new GridBagConstraints();
			gridBagConstraints[i].fill = GridBagConstraints.BOTH;
			gridBagConstraints[i].insets = new Insets(10, 7, 0, 7);
			gridBagConstraints[i].gridx = 0;
			gridBagConstraints[i].gridy = (noticeArray.size()-1) - i;
			arrPanel[i].setLayout(null);
			arrPanel[i].setBackground(bgcolor);
			mainPanel.add(arrPanel[i], gridBagConstraints[i]);
			
			nameLabel = new JLabel("이름");
			nameLabel.setBounds(14, 12, 62, 18);
			nameLabel.setForeground(Color.white);
			nameLabel.setFont(new Font("새굴림", Font.BOLD, 13));
			arrPanel[i].add(nameLabel);
			
			titleLabel = new JLabel("제목");
			titleLabel.setBounds(14, 45, 62, 18);
			titleLabel.setForeground(Color.white);
			titleLabel.setFont(new Font("새굴림", Font.BOLD, 13));
			arrPanel[i].add(titleLabel);
			
			dateLabel = new JLabel("날짜");
			dateLabel.setBounds(250, 12, 62, 18);
			dateLabel.setForeground(Color.white);
			dateLabel.setFont(new Font("새굴림", Font.BOLD, 13));
			arrPanel[i].add(dateLabel);
			
			nameTxtLabel = new JLabel(noticeArray.get(i).getWriter());
			nameTxtLabel.setBounds(50, 12, 180, 18);
			nameTxtLabel.setForeground(Color.white);
			nameTxtLabel.setBackground(bgcolor);
			nameTxtLabel.setFont(new Font("새굴림", Font.PLAIN, 13));
			arrPanel[i].add(nameTxtLabel);

			titleTxtLabel = new JLabel(noticeArray.get(i).getTitle());
			titleTxtLabel.setBounds(50, 45, 416, 18);
			titleTxtLabel.setForeground(Color.white);
			titleTxtLabel.setFont(new Font("새굴림", Font.PLAIN, 13));
			arrPanel[i].add(titleTxtLabel);
			
			dateTxtlabel = new JLabel(noticeArray.get(i).getTime());
			dateTxtlabel.setBounds(286, 12, 180, 18);
			dateTxtlabel.setForeground(Color.white);
			dateTxtlabel.setFont(new Font("새굴림", Font.PLAIN, 13));
			arrPanel[i].add(dateTxtlabel);

		}
	}
	public JPanel getPeriodicMain() {
		
		return lowerPanel;

	}
	public void setContentPane() {
		//하단 JPanel
		lowerPanel = new JPanel();
		lowerPanel.setBounds(0, 121, 534, 640);
		lowerPanel.setLayout(null);
		lowerPanel.setBackground(bgcolor);
	}
	

}

