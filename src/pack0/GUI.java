package pack0;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	JPanel contentPane;
	
	/** Tables **/
	JTable PT1;
	JTable PT2;
	JTable PT3;
	JTable PT4;
	JTable PT5;

	/** Labels **/
	JLabel CurrentProc;
	JLabel CurrentPage;
	JLabel VictimProcess;
	JLabel VictimPage;
	JLabel P1FR;
	JLabel P2FR;
	JLabel P3FR;
	JLabel P4FR;
	JLabel P5FR;
	JTable frameTable;
	JLabel Empty4;
	
	/** Buttons **/
	Button button;
	Button button_1;
	
	public void run(GUI g) {
		try {
			g.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(12, 2, 0, 0));
		
		JLabel Empty = new JLabel("");
		Empty.setVerticalAlignment(SwingConstants.TOP);
		panel.add(Empty);
		
		JLabel Empty2 = new JLabel("");
		Empty2.setVerticalAlignment(SwingConstants.TOP);
		panel.add(Empty2);
		
		JLabel CurrentProcessLabel = new JLabel("Current Process: ");
		CurrentProcessLabel.setVerticalAlignment(SwingConstants.TOP);
		panel.add(CurrentProcessLabel);
		
		CurrentProc = new JLabel("");
		CurrentProc.setVerticalAlignment(SwingConstants.TOP);
		panel.add(CurrentProc);
		
		JLabel CurrentPageLabel = new JLabel("Current Page: ");
		CurrentPageLabel.setVerticalAlignment(SwingConstants.TOP);
		panel.add(CurrentPageLabel);
		
		CurrentPage = new JLabel("");
		CurrentPage.setVerticalAlignment(SwingConstants.TOP);
		panel.add(CurrentPage);
		
		Empty4 = new JLabel("");
		Empty4.setVerticalAlignment(SwingConstants.TOP);
		panel.add(Empty4);
		
		JLabel Empty5 = new JLabel("");
		Empty5.setVerticalAlignment(SwingConstants.TOP);
		panel.add(Empty5);
		
		JLabel VictimProcessLabel = new JLabel("Victim Process: ");
		VictimProcessLabel.setVerticalAlignment(SwingConstants.TOP);
		panel.add(VictimProcessLabel);
		
		VictimProcess = new JLabel("");
		VictimProcess.setVerticalAlignment(SwingConstants.TOP);
		panel.add(VictimProcess);
		
		JLabel VictimPageLabel = new JLabel("Victim Page:");
		VictimPageLabel.setVerticalAlignment(SwingConstants.TOP);
		panel.add(VictimPageLabel);
		
		VictimPage = new JLabel("");
		VictimPage.setVerticalAlignment(SwingConstants.TOP);
		panel.add(VictimPage);
		
		JLabel Empty6 = new JLabel("");
		Empty6.setVerticalAlignment(SwingConstants.TOP);
		panel.add(Empty6);
		
		JLabel Empty7 = new JLabel("");
		Empty7.setVerticalAlignment(SwingConstants.TOP);
		panel.add(Empty7);
		
		JLabel FR1 = new JLabel("P1 Fault Rate: ");
		FR1.setVerticalAlignment(SwingConstants.TOP);
		panel.add(FR1);
		
		P1FR = new JLabel("");
		P1FR.setVerticalAlignment(SwingConstants.TOP);
		panel.add(P1FR);
		
		JLabel FR2 = new JLabel("P2 Fault Rate: ");
		FR2.setVerticalAlignment(SwingConstants.TOP);
		panel.add(FR2);
		
		P2FR = new JLabel("");
		P2FR.setVerticalAlignment(SwingConstants.TOP);
		panel.add(P2FR);
		
		JLabel FR3 = new JLabel("P3 Fault Rate: ");
		FR3.setVerticalAlignment(SwingConstants.TOP);
		panel.add(FR3);
		
		P3FR = new JLabel("");
		P3FR.setVerticalAlignment(SwingConstants.TOP);
		panel.add(P3FR);
		
		JLabel FR4 = new JLabel("P4 Fault Rate: ");
		FR4.setVerticalAlignment(SwingConstants.TOP);
		panel.add(FR4);
		
		P4FR = new JLabel("");
		P4FR.setVerticalAlignment(SwingConstants.TOP);
		panel.add(P4FR);
		
		JLabel FR5 = new JLabel("P5 Fault Rate: ");
		FR5.setVerticalAlignment(SwingConstants.TOP);
		panel.add(FR5);
		
		P5FR = new JLabel("");
		P5FR.setVerticalAlignment(SwingConstants.TOP);
		panel.add(P5FR);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		PT1 = new JTable();
		PT1.setModel(new DefaultTableModel(
			new Object[][] {
					{new Integer(0), null, null, null},
					{new Integer(1), null, null, null},
					{new Integer(2), null, null, null},
					{new Integer(3), null, null, null},
					{new Integer(4), null, null, null},
					{new Integer(5), null, null, null},
					{new Integer(6), null, null, null},
					{new Integer(7), null, null, null},
					{new Integer(8), null, null, null},
					{new Integer(9), null, null, null},
					{new Integer(10), null, null, null},
					{new Integer(11), null, null, null},
					{new Integer(12), null, null, null},
					{new Integer(13), null, null, null},
					{new Integer(14), null, null, null},
					{new Integer(15), null, null, null},
					{new Integer(16), null, null, null},
					{new Integer(17), null, null, null},
					{new Integer(18), null, null, null},
					{new Integer(19), null, null, null},
					{new Integer(20), null, null, null},
					{new Integer(21), null, null, null},
					{new Integer(22), null, null, null},
					{new Integer(23), null, null, null},
					{new Integer(24), null, null, null},
					{new Integer(25), null, null, null},
					{new Integer(26), null, null, null},
					{new Integer(27), null, null, null},
					{new Integer(28), null, null, null},
					{new Integer(29), null, null, null},
					{new Integer(30), null, null, null},
					{new Integer(31), null, null, null},
					{new Integer(32), null, null, null},
					{new Integer(33), null, null, null},
					{new Integer(34), null, null, null},
					{new Integer(35), null, null, null},
					{new Integer(36), null, null, null},
					{new Integer(37), null, null, null},
					{new Integer(38), null, null, null},
					{new Integer(39), null, null, null},
					{new Integer(40), null, null, null},
					{new Integer(41), null, null, null},
					{new Integer(42), null, null, null},
					{new Integer(43), null, null, null},
					{new Integer(44), null, null, null},
					{new Integer(45), null, null, null},
					{new Integer(46), null, null, null},
					{new Integer(47), null, null, null},
					{new Integer(48), null, null, null},
					{new Integer(49), null, null, null},
					{new Integer(50), null, null, null},
					{new Integer(51), null, null, null},
					{new Integer(52), null, null, null},
					{new Integer(53), null, null, null},
					{new Integer(54), null, null, null},
					{new Integer(55), null, null, null},
					{new Integer(56), null, null, null},
					{new Integer(57), null, null, null},
					{new Integer(58), null, null, null},
					{new Integer(59), null, null, null},
					{new Integer(60), null, null, null},
					{new Integer(61), null, null, null},
					{new Integer(62), null, null, null},
					{new Integer(63), null, null, null},
			},
			new String[] {
				"Page #", "Frame #", "Valid", "Resident"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tabbedPane.addTab("P1", null, new JScrollPane(PT1), null);
		
		PT2 = new JTable();
		PT2.setModel(new DefaultTableModel(
				new Object[][] {
						{new Integer(0), null, null, null},
						{new Integer(1), null, null, null},
						{new Integer(2), null, null, null},
						{new Integer(3), null, null, null},
						{new Integer(4), null, null, null},
						{new Integer(5), null, null, null},
						{new Integer(6), null, null, null},
						{new Integer(7), null, null, null},
						{new Integer(8), null, null, null},
						{new Integer(9), null, null, null},
						{new Integer(10), null, null, null},
						{new Integer(11), null, null, null},
						{new Integer(12), null, null, null},
						{new Integer(13), null, null, null},
						{new Integer(14), null, null, null},
						{new Integer(15), null, null, null},
						{new Integer(16), null, null, null},
						{new Integer(17), null, null, null},
						{new Integer(18), null, null, null},
						{new Integer(19), null, null, null},
						{new Integer(20), null, null, null},
						{new Integer(21), null, null, null},
						{new Integer(22), null, null, null},
						{new Integer(23), null, null, null},
						{new Integer(24), null, null, null},
						{new Integer(25), null, null, null},
						{new Integer(26), null, null, null},
						{new Integer(27), null, null, null},
						{new Integer(28), null, null, null},
						{new Integer(29), null, null, null},
						{new Integer(30), null, null, null},
						{new Integer(31), null, null, null},
						{new Integer(32), null, null, null},
						{new Integer(33), null, null, null},
						{new Integer(34), null, null, null},
						{new Integer(35), null, null, null},
						{new Integer(36), null, null, null},
						{new Integer(37), null, null, null},
						{new Integer(38), null, null, null},
						{new Integer(39), null, null, null},
						{new Integer(40), null, null, null},
						{new Integer(41), null, null, null},
						{new Integer(42), null, null, null},
						{new Integer(43), null, null, null},
						{new Integer(44), null, null, null},
						{new Integer(45), null, null, null},
						{new Integer(46), null, null, null},
						{new Integer(47), null, null, null},
						{new Integer(48), null, null, null},
						{new Integer(49), null, null, null},
						{new Integer(50), null, null, null},
						{new Integer(51), null, null, null},
						{new Integer(52), null, null, null},
						{new Integer(53), null, null, null},
						{new Integer(54), null, null, null},
						{new Integer(55), null, null, null},
						{new Integer(56), null, null, null},
						{new Integer(57), null, null, null},
						{new Integer(58), null, null, null},
						{new Integer(59), null, null, null},
						{new Integer(60), null, null, null},
						{new Integer(61), null, null, null},
						{new Integer(62), null, null, null},
						{new Integer(63), null, null, null},
				},
				new String[] {
					"Page #", "Frame #", "Valid", "Resident"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, Integer.class, Boolean.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		tabbedPane.addTab("P2", null, new JScrollPane(PT2), null);
		
		PT3 = new JTable();
		PT3.setModel(new DefaultTableModel(
				new Object[][] {
						{new Integer(0), null, null, null},
						{new Integer(1), null, null, null},
						{new Integer(2), null, null, null},
						{new Integer(3), null, null, null},
						{new Integer(4), null, null, null},
						{new Integer(5), null, null, null},
						{new Integer(6), null, null, null},
						{new Integer(7), null, null, null},
						{new Integer(8), null, null, null},
						{new Integer(9), null, null, null},
						{new Integer(10), null, null, null},
						{new Integer(11), null, null, null},
						{new Integer(12), null, null, null},
						{new Integer(13), null, null, null},
						{new Integer(14), null, null, null},
						{new Integer(15), null, null, null},
						{new Integer(16), null, null, null},
						{new Integer(17), null, null, null},
						{new Integer(18), null, null, null},
						{new Integer(19), null, null, null},
						{new Integer(20), null, null, null},
						{new Integer(21), null, null, null},
						{new Integer(22), null, null, null},
						{new Integer(23), null, null, null},
						{new Integer(24), null, null, null},
						{new Integer(25), null, null, null},
						{new Integer(26), null, null, null},
						{new Integer(27), null, null, null},
						{new Integer(28), null, null, null},
						{new Integer(29), null, null, null},
						{new Integer(30), null, null, null},
						{new Integer(31), null, null, null},
						{new Integer(32), null, null, null},
						{new Integer(33), null, null, null},
						{new Integer(34), null, null, null},
						{new Integer(35), null, null, null},
						{new Integer(36), null, null, null},
						{new Integer(37), null, null, null},
						{new Integer(38), null, null, null},
						{new Integer(39), null, null, null},
						{new Integer(40), null, null, null},
						{new Integer(41), null, null, null},
						{new Integer(42), null, null, null},
						{new Integer(43), null, null, null},
						{new Integer(44), null, null, null},
						{new Integer(45), null, null, null},
						{new Integer(46), null, null, null},
						{new Integer(47), null, null, null},
						{new Integer(48), null, null, null},
						{new Integer(49), null, null, null},
						{new Integer(50), null, null, null},
						{new Integer(51), null, null, null},
						{new Integer(52), null, null, null},
						{new Integer(53), null, null, null},
						{new Integer(54), null, null, null},
						{new Integer(55), null, null, null},
						{new Integer(56), null, null, null},
						{new Integer(57), null, null, null},
						{new Integer(58), null, null, null},
						{new Integer(59), null, null, null},
						{new Integer(60), null, null, null},
						{new Integer(61), null, null, null},
						{new Integer(62), null, null, null},
						{new Integer(63), null, null, null},
				},
				new String[] {
					"Page #", "Frame #", "Valid", "Resident"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, Integer.class, Boolean.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		tabbedPane.addTab("P3", null, new JScrollPane(PT3), null);
		
		PT4 = new JTable();
		PT4.setModel(new DefaultTableModel(
				new Object[][] {
						{new Integer(0), null, null, null},
						{new Integer(1), null, null, null},
						{new Integer(2), null, null, null},
						{new Integer(3), null, null, null},
						{new Integer(4), null, null, null},
						{new Integer(5), null, null, null},
						{new Integer(6), null, null, null},
						{new Integer(7), null, null, null},
						{new Integer(8), null, null, null},
						{new Integer(9), null, null, null},
						{new Integer(10), null, null, null},
						{new Integer(11), null, null, null},
						{new Integer(12), null, null, null},
						{new Integer(13), null, null, null},
						{new Integer(14), null, null, null},
						{new Integer(15), null, null, null},
						{new Integer(16), null, null, null},
						{new Integer(17), null, null, null},
						{new Integer(18), null, null, null},
						{new Integer(19), null, null, null},
						{new Integer(20), null, null, null},
						{new Integer(21), null, null, null},
						{new Integer(22), null, null, null},
						{new Integer(23), null, null, null},
						{new Integer(24), null, null, null},
						{new Integer(25), null, null, null},
						{new Integer(26), null, null, null},
						{new Integer(27), null, null, null},
						{new Integer(28), null, null, null},
						{new Integer(29), null, null, null},
						{new Integer(30), null, null, null},
						{new Integer(31), null, null, null},
						{new Integer(32), null, null, null},
						{new Integer(33), null, null, null},
						{new Integer(34), null, null, null},
						{new Integer(35), null, null, null},
						{new Integer(36), null, null, null},
						{new Integer(37), null, null, null},
						{new Integer(38), null, null, null},
						{new Integer(39), null, null, null},
						{new Integer(40), null, null, null},
						{new Integer(41), null, null, null},
						{new Integer(42), null, null, null},
						{new Integer(43), null, null, null},
						{new Integer(44), null, null, null},
						{new Integer(45), null, null, null},
						{new Integer(46), null, null, null},
						{new Integer(47), null, null, null},
						{new Integer(48), null, null, null},
						{new Integer(49), null, null, null},
						{new Integer(50), null, null, null},
						{new Integer(51), null, null, null},
						{new Integer(52), null, null, null},
						{new Integer(53), null, null, null},
						{new Integer(54), null, null, null},
						{new Integer(55), null, null, null},
						{new Integer(56), null, null, null},
						{new Integer(57), null, null, null},
						{new Integer(58), null, null, null},
						{new Integer(59), null, null, null},
						{new Integer(60), null, null, null},
						{new Integer(61), null, null, null},
						{new Integer(62), null, null, null},
						{new Integer(63), null, null, null},
				},
				new String[] {
					"Page #", "Frame #", "Valid", "Resident"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, Integer.class, Boolean.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		tabbedPane.addTab("P4", null, new JScrollPane(PT4), null);
		
		PT5 = new JTable();
		PT5.setModel(new DefaultTableModel(
				new Object[][] {
					{new Integer(0), null, null, null},
					{new Integer(1), null, null, null},
					{new Integer(2), null, null, null},
					{new Integer(3), null, null, null},
					{new Integer(4), null, null, null},
					{new Integer(5), null, null, null},
					{new Integer(6), null, null, null},
					{new Integer(7), null, null, null},
					{new Integer(8), null, null, null},
					{new Integer(9), null, null, null},
					{new Integer(10), null, null, null},
					{new Integer(11), null, null, null},
					{new Integer(12), null, null, null},
					{new Integer(13), null, null, null},
					{new Integer(14), null, null, null},
					{new Integer(15), null, null, null},
					{new Integer(16), null, null, null},
					{new Integer(17), null, null, null},
					{new Integer(18), null, null, null},
					{new Integer(19), null, null, null},
					{new Integer(20), null, null, null},
					{new Integer(21), null, null, null},
					{new Integer(22), null, null, null},
					{new Integer(23), null, null, null},
					{new Integer(24), null, null, null},
					{new Integer(25), null, null, null},
					{new Integer(26), null, null, null},
					{new Integer(27), null, null, null},
					{new Integer(28), null, null, null},
					{new Integer(29), null, null, null},
					{new Integer(30), null, null, null},
					{new Integer(31), null, null, null},
					{new Integer(32), null, null, null},
					{new Integer(33), null, null, null},
					{new Integer(34), null, null, null},
					{new Integer(35), null, null, null},
					{new Integer(36), null, null, null},
					{new Integer(37), null, null, null},
					{new Integer(38), null, null, null},
					{new Integer(39), null, null, null},
					{new Integer(40), null, null, null},
					{new Integer(41), null, null, null},
					{new Integer(42), null, null, null},
					{new Integer(43), null, null, null},
					{new Integer(44), null, null, null},
					{new Integer(45), null, null, null},
					{new Integer(46), null, null, null},
					{new Integer(47), null, null, null},
					{new Integer(48), null, null, null},
					{new Integer(49), null, null, null},
					{new Integer(50), null, null, null},
					{new Integer(51), null, null, null},
					{new Integer(52), null, null, null},
					{new Integer(53), null, null, null},
					{new Integer(54), null, null, null},
					{new Integer(55), null, null, null},
					{new Integer(56), null, null, null},
					{new Integer(57), null, null, null},
					{new Integer(58), null, null, null},
					{new Integer(59), null, null, null},
					{new Integer(60), null, null, null},
					{new Integer(61), null, null, null},
					{new Integer(62), null, null, null},
					{new Integer(63), null, null, null},
				},
				new String[] {
					"Page #", "Frame #", "Valid", "Resident"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, Integer.class, Boolean.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		tabbedPane.addTab("P5", null, new JScrollPane(PT5), null);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel Col1Label = new JLabel("Vital Stats");
		panel_1.add(Col1Label, BorderLayout.WEST);
		
		JLabel Col2 = new JLabel("Process Page Tables");
		Col2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(Col2, BorderLayout.CENTER);
		
		JLabel Col3Label = new JLabel("Frame Table");
		panel_1.add(Col3Label, BorderLayout.EAST);
		
		frameTable = new JTable();
		frameTable.setEnabled(false);
		frameTable.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(0), null},
				{new Integer(1), null},
				{new Integer(2), null},
				{new Integer(3), null},
				{new Integer(4), null},
				{new Integer(5), null},
				{new Integer(6), null},
				{new Integer(7), null},
				{new Integer(8), null},
				{new Integer(9), null},
				{new Integer(10), null},
				{new Integer(11), null},
				{new Integer(12), null},
				{new Integer(13), null},
				{new Integer(14), null},
				{new Integer(15), null},
			},
			new String[] {
				"Frame #", "Page"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		frameTable.getColumnModel().getColumn(0).setPreferredWidth(19);
		frameTable.getColumnModel().getColumn(1).setPreferredWidth(106);
		contentPane.add(frameTable, BorderLayout.EAST);
		
		Panel panel_2 = new Panel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		button = new Button("Step");
		panel_2.add(button);
		
		button_1 = new Button("Run to Completion");
		panel_2.add(button_1);
	}
}
