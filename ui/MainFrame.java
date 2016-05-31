package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.*;

import rmi.RemoteHelper;
import runner.Execute;


public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JLabel resultLabel;
	private JLabel input1Label;
	private JLabel input2Label;
	private String Run_Result;
	private Font font = new java.awt.Font("", java.awt.Font.PLAIN, 20);

	public MainFrame() {
		// 创建窗体
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu loginMenu = new JMenu("Account");
		JMenu historyMenu = new JMenu("History");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(loginMenu);
		menuBar.add(historyMenu);
		
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		fileMenu.add(runMenuItem);
		
		JMenuItem undoMenuItem = new JMenuItem("Undo");
		editMenu.add(undoMenuItem);
		JMenuItem redoMenuItem = new JMenuItem("Redo");
		editMenu.add(redoMenuItem);
		
		JMenuItem loginMenuItem = new JMenuItem("Log in");
		loginMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("Log out");
		loginMenu.add(logoutMenuItem);
		
		frame.setJMenuBar(menuBar);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		
		frame.setLayout(null);
		
		//代码区域
		input1Label = new JLabel();
		input1Label.setText("TextContent：");
		input1Label.setBackground(Color.blue);
		input1Label.setBounds(20, 15, 700, 25);
		input1Label.setFont(font);
		frame.add(input1Label);
		
		textArea1 = new JTextArea();
		JScrollPane Scroll_1 = new JScrollPane(textArea1);
		Scroll_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textArea1.setBackground(Color.LIGHT_GRAY);
		textArea1.setBounds(20, 50, 685, 480);
		textArea1.setLineWrap(true);
		textArea1.setFont(font);
		frame.add(textArea1);
		
		//用户输入
		
		input2Label = new JLabel();
		input2Label.setText("Input:");
		input2Label.setBackground(Color.blue);
		input2Label.setBounds(735, 15, 965, 25);
		input2Label.setFont(font);
		frame.add(input2Label);
		
		textArea2 = new JTextArea();
		JScrollPane Scroll_2 = new JScrollPane(textArea2);
		Scroll_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textArea2.setBackground(Color.LIGHT_GRAY);
		textArea2.setBounds(735, 50, 250, 480);
		textArea2.setLineWrap(true);
		frame.add(textArea2);

		// 显示结果
		resultLabel = new JLabel();
		resultLabel.setText("result");
		resultLabel.setBackground(Color.white);
		resultLabel.setFont(font);
		resultLabel.setBounds(20, 550, 965, 100);
		frame.add(resultLabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		frame.setLocation(400, 100);
		frame.setVisible(true);
	}
	
	//获得运行结果并输出
	void setRunResult(String result){
		Run_Result=result;
	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				textArea1.setText("Open");
			} else if (cmd.equals("Save")) {
				textArea1.setText("Save");
			} else if (cmd.equals("Run")) {
				String code = textArea1.getText();
				String input = textArea2.getText();
				/*Execute exe = new Execute();*/
				resultLabel.setText(Run_Result);
			}
		}
	}
	//代码的保存、读取、执行等功能都是在服务器上完成的
	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textArea1.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
}
