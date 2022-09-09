package pack2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;                        // 1
import java.sql.ResultSet;
import java.sql.SQLException;                             

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;

public class Purchase extends JDialog implements ActionListener
{
	private JTextField in;
	private JTextField da;
	private JTextField de;
	private JTextField itn;
	private JTextField cn;
	private JTextField q;
	private JTextField r;
	private JButton done;
	private JButton cancel;
	//private JTextField textField;
	//private int itn;
	private int invoice;
	private String datee;
	private JTextField tftotm;

	public Purchase() 
	{
		getContentPane().setLayout(null);
		
		
		
		JLabel invoice = new JLabel("Invoice");
		invoice.setAlignmentX(Component.CENTER_ALIGNMENT);
		invoice.setFont(new Font("Times New Roman", Font.BOLD, 18));
		invoice.setBounds(65, 74, 71, 14);
		getContentPane().add(invoice);
		
		in = new JTextField();
		in.setFocusTraversalKeysEnabled(false);
		in.setFocusable(false);
		in.setHorizontalAlignment(SwingConstants.CENTER);
		in.setEditable(false);
		in.setBounds(146, 73, 86, 20);
		getContentPane().add(in);
		in.setColumns(10);
		
		JLabel date = new JLabel("Date");
		date.setFont(new Font("Times New Roman", Font.BOLD, 18));
		date.setBounds(405, 76, 46, 14);
		getContentPane().add(date);
		
		
		datee= new java.sql.Date(new java.util.Date().getTime()).toString();
		da = new JTextField(datee);
		da.setFocusTraversalKeysEnabled(false);
		da.setFocusable(false);
		da.setAlignmentX(Component.RIGHT_ALIGNMENT);
		da.setHorizontalAlignment(SwingConstants.CENTER);
		da.setEditable(false);
		da.setBounds(461, 73, 86, 20);
		getContentPane().add(da);
		da.setColumns(10);
		
		de = new JTextField();
		de.setBounds(250, 235, 226, 20);
		getContentPane().add(de);
		de.setColumns(10);
		
		JLabel itemname = new JLabel("Item Name");
		itemname.setFont(new Font("Times New Roman", Font.BOLD, 15));
		itemname.setBounds(83, 136, 86, 14);
		getContentPane().add(itemname);
		
		JLabel company = new JLabel("Company Name");
		company.setFont(new Font("Times New Roman", Font.BOLD, 15));
		company.setBounds(83, 183, 121, 14);
		getContentPane().add(company);
		
		JLabel quantity = new JLabel("Quantity");
		quantity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		quantity.setBounds(83, 292, 86, 14);
		getContentPane().add(quantity);
		
		JLabel rate = new JLabel("Rate");
		rate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rate.setBounds(83, 348, 86, 14);
		getContentPane().add(rate);
		
		itn = new JTextField();
		itn.setBounds(250, 130, 226, 20);
		getContentPane().add(itn);
		itn.setColumns(10);
		
		cn = new JTextField();
		cn.setColumns(10);
		cn.setBounds(250, 181, 226, 20);
		getContentPane().add(cn);
		
		q = new JTextField();
		q.setColumns(10);
		q.setBounds(250, 290, 106, 20);
		getContentPane().add(q);
		
		r = new JTextField();
		r.setColumns(10);
		r.setBounds(250, 346, 106, 20);
		getContentPane().add(r);
		
		tftotm = new JTextField();
		tftotm.setColumns(10);
		tftotm.setBounds(250, 401, 226, 23);
		getContentPane().add(tftotm);
		
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
					tftotm.setText(String.valueOf(s*ss));
				}
			}
		});
		
		JLabel dealer = new JLabel("Dealer Name");
		dealer.setFont(new Font("Times New Roman", Font.BOLD, 15));
		dealer.setBounds(83, 237, 103, 14);
		getContentPane().add(dealer);
		
		
		
		
		JLabel totm = new JLabel("Total Amount -");
		totm.setFont(new Font("Times New Roman", Font.BOLD, 15));
		totm.setBounds(65, 397, 121, 28);
		getContentPane().add(totm);
		
		
		
		done = new JButton("Done");
		done.setFont(new Font("Tahoma", Font.BOLD, 11));
		done.setBounds(179, 465, 89, 23);
		getContentPane().add(done);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		cancel.setBounds(376, 465, 89, 23);
		
		done.addActionListener(this);                             // 3
		cancel.addActionListener(this);                           // 4
		
		getContentPane().add(cancel);                             // 5
		

		
		setSize(600, 600);      
		
		setModal(true);
		getInvoice();
		getRootPane().setDefaultButton(done);
		
		
		setVisible(true);                                        // 6
	}

	public static void main(String[] args) 
	{
		
		new Purchase();
	
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object ob= e.getSource();                                 // 6
		if (ob==cancel)
		{
			dispose();
		}
		else if(ob==done)
		{       int inv;
		String date;
		String deal;
		String name;
		String comp;
		int quantity;
		Double rate;
		try {
			inv = Integer.parseInt(in.getText());
					date = da.getText();
					deal = de.getText();
					name = itn.getText().toUpperCase();
					comp = cn.getText().toUpperCase();;
					String dl=de.getText().toUpperCase();;
					quantity = Integer.parseInt(q.getText());
					
					rate = Double.parseDouble(r.getText());
					
					if(deal==null || name == null ||comp == null || dl == null || quantity < 0 || rate < 0)
					{
						JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.ERROR_MESSAGE);
					}
					
					String st="insert into purchase(Invoice, PDate, Dealer, ItemName, Companyname, Quantity, Rate) "
							+ "values(?, ?, ?, ?, ?, ?, ?)";
				try
				{
					Connection con= MyConnection.connect();
					PreparedStatement ps= con.prepareStatement(st);
					ps.setInt(1, inv );
					ps.setString(2, date );
					ps.setString(3, deal );
					ps.setString(4, name);
					ps.setString(5,comp);
					ps.setInt(6, quantity);
					ps.setDouble(7, rate);
					
					ps.executeUpdate();
					updateStock();
					JOptionPane.showMessageDialog(null, "Item Added");
					dispose();
					
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
					
					
					
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	void updateStock(){
		//String cu =cn.getText();
		int it=Integer.parseInt(in.getText());
		String cm=cn.getText();
		int qu=Integer.parseInt(q.getText());
		String t="select * from stock where itemname=? and companyname=?";
		try
		{
			Connection con= MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(t);
			ps.setString(1, itn.getText().toUpperCase());
			ps.setString(2, cm);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				String st="update stock set quantity = quantity+? where itemname=? and companyname=?";
				ps=con.prepareStatement(st);
				ps.setInt(1, qu);
				ps.setString(2, itn.getText().toUpperCase());
				ps.setString(3, cm);
				ps.executeUpdate();
			}
			else
			{
				String st="insert into stock(itemname, companyname, quantity) values(?, ?, ?)";
				ps=con.prepareStatement(st);
				ps.setString(1, itn.getText().toUpperCase());
				ps.setString(2, cm);
				ps.setInt(3,  qu);
				ps.executeUpdate();
				
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	
	}
	void getInvoice()
	{
		String s="select max(Invoice) from purchase";
		Connection con= MyConnection.connect();
		try
		{
			PreparedStatement ps= con.prepareStatement(s);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				invoice=rs.getInt(1);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		++invoice;
		in.setText(String.valueOf(invoice));
	}
}
