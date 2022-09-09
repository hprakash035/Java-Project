package pack2;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewPurchase extends JDialog{
	int invoice, qty;
	String dealer, item, comp;
	String date;
	public ViewPurchase() {
		setLayout(null);
		setSize(600,500);
		Connection con=MyConnection.connect();
		String columns[]={"Invoice No.", "P Date","Dealer Name", "Item Name", "Company","Quantity"};
		String st="select * from purchase";
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		JTable table=new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		try{
			PreparedStatement ps=con.prepareStatement(st);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				invoice=rs.getInt("invoice");
				date=rs.getString("pdate");
				dealer=rs.getString("dealer");
				item=rs.getString("itemname");
				comp=rs.getString("companyname");
				qty=rs.getInt("quantity");
				model.addRow(new Object[]{invoice, date, dealer,item,comp,qty});
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		scroll.setBounds(20,10,500,400);
		add(scroll);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ViewPurchase();
	}
}
