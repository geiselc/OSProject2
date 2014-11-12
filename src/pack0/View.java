package pack0;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class View extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JPanel panel_2;
	private JLabel CurrProcLabel;
	private JLabel FaultRatesLabel;
	private JLabel cProc;
	private JLabel FR1;
	private JLabel FR2;
	private JLabel FR3;
	private JLabel FR4;
	private JLabel FR5;
	private JLabel victim;
	private JLabel VictimLabel;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"0", null, null, null},
				{"1", null, null, null},
				{"2", null, null, null},
				{"3", null, null, null},
				{"4", null, null, null},
				{"5", null, null, null},
				{"6", null, null, null},
				{"7", null, null, null},
				{"8", null, null, null},
				{"9", null, null, null},
				{"10", null, null, null},
				{"11", null, null, null},
				{"12", null, null, null},
				{"13", null, null, null},
				{"14", null, null, null},
				{"15", null, null, null},
				{"16", null, null, null},
				{"17", null, null, null},
				{"18", null, null, null},
				{"19", null, null, null},
				{"20", null, null, null},
				{"21", null, null, null},
				{"22", null, null, null},
				{"23", null, null, null},
				{"24", null, null, null},
				{"25", null, null, null},
				{"26", null, null, null},
				{"27", null, null, null},
				{"28", null, null, null},
				{"29", null, null, null},
				{"30", null, null, null},
				{"31", null, null, null},
				{"32", null, null, null},
				{"33", null, null, null},
				{"34", null, null, null},
				{"35", null, null, null},
				{"36", null, null, null},
				{"37", null, null, null},
				{"38", null, null, null},
				{"39", null, null, null},
				{"40", null, null, null},
				{"41", null, null, null},
				{"42", null, null, null},
				{"43", null, null, null},
				{"44", null, null, null},
				{"45", null, null, null},
				{"46", null, null, null},
				{"47", null, null, null},
				{"48", null, null, null},
				{"49", null, null, null},
				{"50", null, null, null},
				{"51", null, null, null},
				{"52", null, null, null},
				{"53", null, null, null},
				{"54", null, null, null},
				{"55", null, null, null},
				{"56", null, null, null},
				{"57", null, null, null},
				{"58", null, null, null},
				{"59", null, null, null},
				{"60", null, null, null},
				{"61", null, null, null},
				{"62", null, null, null},
				{"63", null, null, null},
			},
			new String[] {
				"Page #", "Frame #", "Valid", "Resident"
			}
		));
		tabbedPane.addTab("P1", null, table_1, null);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
				new Object[][] {
					{"0", null, null, null},
					{"1", null, null, null},
					{"2", null, null, null},
					{"3", null, null, null},
					{"4", null, null, null},
					{"5", null, null, null},
					{"6", null, null, null},
					{"7", null, null, null},
					{"8", null, null, null},
					{"9", null, null, null},
					{"10", null, null, null},
					{"11", null, null, null},
					{"12", null, null, null},
					{"13", null, null, null},
					{"14", null, null, null},
					{"15", null, null, null},
					{"16", null, null, null},
					{"17", null, null, null},
					{"18", null, null, null},
					{"19", null, null, null},
					{"20", null, null, null},
					{"21", null, null, null},
					{"22", null, null, null},
					{"23", null, null, null},
					{"24", null, null, null},
					{"25", null, null, null},
					{"26", null, null, null},
					{"27", null, null, null},
					{"28", null, null, null},
					{"29", null, null, null},
					{"30", null, null, null},
					{"31", null, null, null},
					{"32", null, null, null},
					{"33", null, null, null},
					{"34", null, null, null},
					{"35", null, null, null},
					{"36", null, null, null},
					{"37", null, null, null},
					{"38", null, null, null},
					{"39", null, null, null},
					{"40", null, null, null},
					{"41", null, null, null},
					{"42", null, null, null},
					{"43", null, null, null},
					{"44", null, null, null},
					{"45", null, null, null},
					{"46", null, null, null},
					{"47", null, null, null},
					{"48", null, null, null},
					{"49", null, null, null},
					{"50", null, null, null},
					{"51", null, null, null},
					{"52", null, null, null},
					{"53", null, null, null},
					{"54", null, null, null},
					{"55", null, null, null},
					{"56", null, null, null},
					{"57", null, null, null},
					{"58", null, null, null},
					{"59", null, null, null},
					{"60", null, null, null},
					{"61", null, null, null},
					{"62", null, null, null},
					{"63", null, null, null},
				},
				new String[] {
					"Page #", "Frame #", "Valid", "Resident"
				}
			));
		tabbedPane.addTab("P2", null, table_2, null);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
				new Object[][] {
					{"0", null, null, null},
					{"1", null, null, null},
					{"2", null, null, null},
					{"3", null, null, null},
					{"4", null, null, null},
					{"5", null, null, null},
					{"6", null, null, null},
					{"7", null, null, null},
					{"8", null, null, null},
					{"9", null, null, null},
					{"10", null, null, null},
					{"11", null, null, null},
					{"12", null, null, null},
					{"13", null, null, null},
					{"14", null, null, null},
					{"15", null, null, null},
					{"16", null, null, null},
					{"17", null, null, null},
					{"18", null, null, null},
					{"19", null, null, null},
					{"20", null, null, null},
					{"21", null, null, null},
					{"22", null, null, null},
					{"23", null, null, null},
					{"24", null, null, null},
					{"25", null, null, null},
					{"26", null, null, null},
					{"27", null, null, null},
					{"28", null, null, null},
					{"29", null, null, null},
					{"30", null, null, null},
					{"31", null, null, null},
					{"32", null, null, null},
					{"33", null, null, null},
					{"34", null, null, null},
					{"35", null, null, null},
					{"36", null, null, null},
					{"37", null, null, null},
					{"38", null, null, null},
					{"39", null, null, null},
					{"40", null, null, null},
					{"41", null, null, null},
					{"42", null, null, null},
					{"43", null, null, null},
					{"44", null, null, null},
					{"45", null, null, null},
					{"46", null, null, null},
					{"47", null, null, null},
					{"48", null, null, null},
					{"49", null, null, null},
					{"50", null, null, null},
					{"51", null, null, null},
					{"52", null, null, null},
					{"53", null, null, null},
					{"54", null, null, null},
					{"55", null, null, null},
					{"56", null, null, null},
					{"57", null, null, null},
					{"58", null, null, null},
					{"59", null, null, null},
					{"60", null, null, null},
					{"61", null, null, null},
					{"62", null, null, null},
					{"63", null, null, null},
				},
				new String[] {
					"Page #", "Frame #", "Valid", "Resident"
				}
			));
		tabbedPane.addTab("P3", null, table_3, null);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
				new Object[][] {
					{"0", null, null, null},
					{"1", null, null, null},
					{"2", null, null, null},
					{"3", null, null, null},
					{"4", null, null, null},
					{"5", null, null, null},
					{"6", null, null, null},
					{"7", null, null, null},
					{"8", null, null, null},
					{"9", null, null, null},
					{"10", null, null, null},
					{"11", null, null, null},
					{"12", null, null, null},
					{"13", null, null, null},
					{"14", null, null, null},
					{"15", null, null, null},
					{"16", null, null, null},
					{"17", null, null, null},
					{"18", null, null, null},
					{"19", null, null, null},
					{"20", null, null, null},
					{"21", null, null, null},
					{"22", null, null, null},
					{"23", null, null, null},
					{"24", null, null, null},
					{"25", null, null, null},
					{"26", null, null, null},
					{"27", null, null, null},
					{"28", null, null, null},
					{"29", null, null, null},
					{"30", null, null, null},
					{"31", null, null, null},
					{"32", null, null, null},
					{"33", null, null, null},
					{"34", null, null, null},
					{"35", null, null, null},
					{"36", null, null, null},
					{"37", null, null, null},
					{"38", null, null, null},
					{"39", null, null, null},
					{"40", null, null, null},
					{"41", null, null, null},
					{"42", null, null, null},
					{"43", null, null, null},
					{"44", null, null, null},
					{"45", null, null, null},
					{"46", null, null, null},
					{"47", null, null, null},
					{"48", null, null, null},
					{"49", null, null, null},
					{"50", null, null, null},
					{"51", null, null, null},
					{"52", null, null, null},
					{"53", null, null, null},
					{"54", null, null, null},
					{"55", null, null, null},
					{"56", null, null, null},
					{"57", null, null, null},
					{"58", null, null, null},
					{"59", null, null, null},
					{"60", null, null, null},
					{"61", null, null, null},
					{"62", null, null, null},
					{"63", null, null, null},
				},
				new String[] {
					"Page #", "Frame #", "Valid", "Resident"
				}
			));
		tabbedPane.addTab("P4", null, table_4, null);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
				new Object[][] {
					{"0", null, null, null},
					{"1", null, null, null},
					{"2", null, null, null},
					{"3", null, null, null},
					{"4", null, null, null},
					{"5", null, null, null},
					{"6", null, null, null},
					{"7", null, null, null},
					{"8", null, null, null},
					{"9", null, null, null},
					{"10", null, null, null},
					{"11", null, null, null},
					{"12", null, null, null},
					{"13", null, null, null},
					{"14", null, null, null},
					{"15", null, null, null},
					{"16", null, null, null},
					{"17", null, null, null},
					{"18", null, null, null},
					{"19", null, null, null},
					{"20", null, null, null},
					{"21", null, null, null},
					{"22", null, null, null},
					{"23", null, null, null},
					{"24", null, null, null},
					{"25", null, null, null},
					{"26", null, null, null},
					{"27", null, null, null},
					{"28", null, null, null},
					{"29", null, null, null},
					{"30", null, null, null},
					{"31", null, null, null},
					{"32", null, null, null},
					{"33", null, null, null},
					{"34", null, null, null},
					{"35", null, null, null},
					{"36", null, null, null},
					{"37", null, null, null},
					{"38", null, null, null},
					{"39", null, null, null},
					{"40", null, null, null},
					{"41", null, null, null},
					{"42", null, null, null},
					{"43", null, null, null},
					{"44", null, null, null},
					{"45", null, null, null},
					{"46", null, null, null},
					{"47", null, null, null},
					{"48", null, null, null},
					{"49", null, null, null},
					{"50", null, null, null},
					{"51", null, null, null},
					{"52", null, null, null},
					{"53", null, null, null},
					{"54", null, null, null},
					{"55", null, null, null},
					{"56", null, null, null},
					{"57", null, null, null},
					{"58", null, null, null},
					{"59", null, null, null},
					{"60", null, null, null},
					{"61", null, null, null},
					{"62", null, null, null},
					{"63", null, null, null},
				},
				new String[] {
					"Page #", "Frame #", "Valid", "Resident"
				}
			));
		tabbedPane.addTab("P5", null, table_5, null);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Frame #", "Page"
			}
		));
		contentPane.add(table, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		Button button = new Button("Step");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(button);
		
		Button button_1 = new Button("Run");
		panel_1.add(button_1);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(10, 3, 0, 0));
		
		CurrProcLabel = new JLabel("Curr Process");
		CurrProcLabel.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(CurrProcLabel);
		
		cProc = new JLabel("Display CurrProc");
		cProc.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(cProc);
		
		VictimLabel = new JLabel("Victim");
		VictimLabel.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(VictimLabel);
		
		victim = new JLabel("Display Victim");
		victim.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(victim);
		
		FaultRatesLabel = new JLabel("Fault Rate");
		FaultRatesLabel.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(FaultRatesLabel);
		
		FR1 = new JLabel("FR1");
		FR1.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(FR1);
		
		FR2 = new JLabel("FR2");
		FR2.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(FR2);
		
		FR3 = new JLabel("FR3");
		FR3.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(FR3);
		
		FR4 = new JLabel("FR4");
		FR4.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(FR4);
		
		FR5 = new JLabel("FR5");
		FR5.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(FR5);
	}

}
