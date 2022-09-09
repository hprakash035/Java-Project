package pack2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.sql.*;
import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Sales extends JDialog implements ActionListener
{
	private JTextField bn;
	java.sql.Date date;
	private JTextField bd;
	private JTextField cn;
	private JTextField q;
	private JTextField r;
	private JTextField tam;
	private JButton cancel;
	private JButton ms;
	private JComboBox in;
	int billno;
	private JComboBox itemtype;
	private int i =0,j=0;
	private int maxqty = 0;
	int a[] = new int[500];
	String b[] = new String [500];
	String s2="";
	public Sales()
	{
		getContentPane().setLayout(null);
		
		JLabel lblBillNo = new JLabel("Bill No. -");
		lblBillNo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBillNo.setBounds(87, 45, 91, 28);
		getContentPane().add(lblBillNo);
		
		JLabel lblBillDate = new JLabel("Bill Date -");
		lblBillDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBillDate.setBounds(428, 40, 91, 28);
		getContentPane().add(lblBillDate);
		
		bn = new JTextField();
		bn.setBounds(168, 50, 137, 20);
		getContentPane().add(bn);
		bn.setColumns(10);
		date=new java.sql.Date(new java.util.Date().getTime());
		bd = new JTextField(date.toString());
		bd.setBounds(529, 50, 137, 20);
		getContentPane().add(bd);
		bd.setColumns(10);
		
		JLabel lblCustomerName = new JLabel("Customer Name -");
		lblCustomerName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCustomerName.setBounds(22, 121, 121, 28);
		getContentPane().add(lblCustomerName);
		
		cn = new JTextField();
		cn.setBounds(178, 121, 216, 23);
		getContentPane().add(cn);
		cn.setColumns(10);
		
		JLabel lblItemname = new JLabel("ItemName -");
		lblItemname.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblItemname.setBounds(22, 160, 121, 28);
		getContentPane().add(lblItemname);
		
		JLabel lblQuantity = new JLabel("Quantity -");
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQuantity.setBounds(22, 199, 121, 28);
		getContentPane().add(lblQuantity);
		
		JLabel lblRate = new JLabel("Rate -");
		lblRate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRate.setBounds(32, 238, 121, 28);
		getContentPane().add(lblRate);
		
		JLabel lblToatalAmount = new JLabel("Toatal Amount -");
		lblToatalAmount.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblToatalAmount.setBounds(22, 277, 121, 28);
		getContentPane().add(lblToatalAmount);
		
		q = new JTextField();
		q.setColumns(10);
		q.setBounds(178, 203, 216, 23);
		getContentPane().add(q);
		
		r = new JTextField();
		r.setColumns(10);
		r.setBounds(178, 242, 216, 23);
		getContentPane().add(r);
		
		tam = new JTextField();
		tam.setColumns(10);
		tam.setBounds(178, 281, 216, 23);
		getContentPane().add(tam);
		
		r.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(r.getText()==null || r.getText().equals("") || q.getText()==null || q.getText().equals(""))
				{
				}
				else {
					double s=0;
					double ss=0;
					try {
						s = Double.parseDouble(r.getText());
						ss = Double.parseDouble(q.getText());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Invalid Input");
					}
					tam.setText(String.valueOf(s*ss));
				}
			}
			
		});
		
		itemtype = new JComboBox();
		itemtype.setEnabled(false);
		getContentPane().add(itemtype);
		itemtype.setBounds(309, 165, 121, 20);
		
		
		
		in = new JComboBox();
		in.setModel(new DefaultComboBoxModel(new String[] {"Selelct Product"}));
		try{
			Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement("select distinct Itemname from stock order by itemname");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				in.addItem(rs.getString("itemname"));
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
//		in.setModel(new DefaultComboBoxModel(new String[] {"Sheela", "Surf", "Rin", "Sugar", "Tea"}));
		in.setBounds(178, 165, 121, 20);
		getContentPane().add(in);
		
		
		in.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				itemtype.removeAllItems();
				String s = in.getSelectedItem().toString();
				try {
					Connection con = MyConnection.connect();
					PreparedStatement ps=con.prepareStatement("select distinct CompanyName,quantity from stock where ItemName=?");
					ps.setString(1, s);
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
						itemtype.setEnabled(true);
						if(true){
							itemtype.addItem(rs.getString("CompanyName"));
							a[i]=rs.getInt("quantity");
							b[i++]= rs.getString("CompanyName");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		
		itemtype.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				try {
					String s =itemtype.getSelectedItem().toString();
					for(int i = 0;i<500;i++)
					{
						if(b[i].equals(s)){
							q.setText(String.valueOf(a[i]));
							maxqty = a[i];
						}
						else if(b[i]==null)
						{}
					}
					
				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
				}
				
			}
		});
		
		
		ms = new JButton("Make Sale");
		ms.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ms.setBounds(87, 353, 112, 23);
		getContentPane().add(ms);
		
		cancel = new JButton("CANCEL");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cancel.setBounds(254, 353, 112, 23);
		getContentPane().add(cancel);
		setSize(800,600);
		setModal(true);
		ms.addActionListener(this);
		cancel.addActionListener(this);
		getRootPane().setDefaultButton(ms);
		
		
		getBillNo();
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new Sales();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob=e.getSource();
		if(ob==cancel)
		{
		
			dispose();
		}
		else if(ob==ms)
		{
			int b;
			String cu;
			String it;
			int qu;
			double rt;
			double tm;
			
			
			
			
			try {
				b = Integer.parseInt(bn.getText());
				cu = cn.getText();
				it = in.getSelectedItem().toString();
				qu = Integer.parseInt(q.getText());
				rt = Double.parseDouble(r.getText().toString());
				tm = Double.parseDouble(tam.getText().toString());
				
				String st="insert into sales(billno, customername, itemname, quantity,"
						+ " rate, totalamount,billdate) values(?,?, ?, ?, ?, ?, ?)";
				
				if(cu.equals("") || it == null || qu<1 || rt==0 || tm == 0)
				{
					JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
						if(qu>maxqty)
						{
							JOptionPane.showMessageDialog(null, "Invalid Quantity!", "Warning", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							try
							{
								Connection con= MyConnection.connect();
								PreparedStatement ps=con.prepareStatement(st);
								ps.setInt(1, b);
								ps.setString(2,  cu);
								ps.setString(3, it);
								ps.setInt(4,  qu);
								ps.setDouble(5, rt);
								ps.setDouble(6, tm);
								ps.setDate(7, date);
								ps.executeUpdate();
								updateStock();
								JOptionPane.showMessageDialog(null, "Information added");
								dispose();
							}
							catch(SQLException se)
							{
								se.printStackTrace();
							}
						}
						
					}
			}catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.ERROR_MESSAGE);
					
						}
				}
		
	}
	void updateStock(){
		int b=Integer.parseInt(bn.getText());
		String cu =cn.getText();
		String it=in.getSelectedItem().toString();
		int qu=Integer.parseInt(q.getText());
		double rt=Double.parseDouble(r.getText());
		double tm=Double.parseDouble(tam.getText());
		String t="select * from stock where itemname=?";
		try
		{
			Connection con= MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(t);
			ps.setString(1, it);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				String st="update stock set quantity = quantity-? where itemname=? and companyname=?";
				ps=con.prepareStatement(st);
				ps.setInt(1, qu);
				ps.setString(2, it);
				try {
					s2= itemtype.getSelectedItem().toString();
					ps.setString(3,s2);
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
				}
				ps.executeUpdate();
			}
			else
			{
				String st="insert into stock(itemname, quantity) values(?, ?)";
				ps=con.prepareStatement(st);
				ps.setString(1, it);
				ps.setInt(2,  qu);
				ps.executeUpdate();
				
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	
	}
	void getBillNo()
	{
		String s="select max(BillNo) from sales";
		Connection con= MyConnection.connect();
		try
		{
			PreparedStatement ps= con.prepareStatement(s);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				billno=rs.getInt(1);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		++billno;
		bn.setText(String.valueOf(billno));
	}
}
