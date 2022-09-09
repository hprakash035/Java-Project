package pack2;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewStock extends JDialog{
	int qty;
	String item, comp;
	public ViewStock() {
		setTitle("Details Of Product");
		getContentPane().setLayout(null);
		setSize(600,500);
		Connection con=MyConnection.connect();
		String columns[]={ "Item Name", "Company Name","Quantity"};
		String st="select * from stock";
		
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
				item=rs.getString("ItemName");
				comp=rs.getString("CompanyName").toUpperCase();
				qty=rs.getInt("quantity");
				model.addRow(new Object[]{item,comp,qty});
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		scroll.setBounds(20,10,500,400);
		getContentPane().add(scroll);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ViewStock();
	}
}
