package pack2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Login extends JDialog implements ActionListener
{
	private JTextField tn;
	private JPasswordField tp;
	private JButton SUBMIT;
	private JButton submit;
	private JButton cancel;

	public Login() 
	{
		setTitle("LOGIN");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setLayout(null);
		
		JLabel un = new JLabel("UserName -");
		un.setBackground(Color.LIGHT_GRAY);
		un.setForeground(Color.WHITE);
		un.setFont(new Font("Times New Roman", Font.BOLD, 20));
		un.setBounds(70, 60, 105, 26);
		getContentPane().add(un);
		
		JLabel pw = new JLabel("Password-");
		pw.setForeground(Color.WHITE);
		pw.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pw.setBounds(70, 114, 105, 26);
		getContentPane().add(pw);
		
		tn = new JTextField();
		tn.setBounds(200, 60, 254, 26);
		getContentPane().add(tn);
		tn.setColumns(10);
		
		tp = new JPasswordField();
		tp.setBounds(200, 117, 254, 26);
		getContentPane().add(tp);
		
		submit = new JButton("SUBMIT");
		submit.setOpaque(false);
		submit.setBackground(Color.BLACK);
		submit.setFont(new Font("Tahoma", Font.BOLD, 15));
		submit.setForeground(Color.RED);
		submit.setBounds(87, 189, 144, 25);
		getContentPane().add(submit);
		
		cancel = new JButton("CANCEL");
		cancel.setOpaque(false);
		cancel.setForeground(Color.RED);
		cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cancel.setBackground(Color.BLACK);
		cancel.setBounds(256, 189, 155, 25);
		submit.addActionListener(this);
		cancel.addActionListener(this);
		getContentPane().add(cancel);
		setSize(500,300);
		setModal(true);
		getRootPane().setDefaultButton(submit);
		setLocationRelativeTo(null);
		ImageIcon p= new ImageIcon(getClass().getResource("pic/10.jpg"));
		JLabel lab=new JLabel(p);
		lab.setBounds(0,-51,484,301);
		getContentPane().add(lab);
		setVisible(true);
		
	}

	public static void main(String[] args)
	{
		new Login();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob=e.getSource();
		if(ob==cancel)
		{
			dispose();
		}
		else if(ob==submit )
		{
			String user =tn.getText();
			String pass=new String(tp.getPassword());
			String st="select * from login where userName=? and Password=?";
			Connection con = MyConnection.connect();
			try
			{
				PreparedStatement ps= con.prepareStatement(st);
				ps.setString(1, user);
				ps.setString(2, pass);
				ResultSet rs= ps.executeQuery();
				if(rs.next())
				{
					new Home();
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "invalid");
					tn.setText("");
					tp.setText("");
					tp.requestFocusInWindow();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}	
	}
}
