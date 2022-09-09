package pack2;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewSale extends JDialog{
	int billno, qty;
	String cname, item, comp;
	String date;
	public ViewSale() {
		setTitle("Deatails Of Sale Product");
		getContentPane().setLayout(null);
		setSize(600,500);
		Connection con=MyConnection.connect();
		String columns[]={"BillNo", "BillDate","Customer Name", "Item Name","Quantity"};
		String st="select * from sales";
		
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
				billno=rs.getInt("BillNo");
				date=rs.getString("BillDate");
				cname=rs.getString("CustomerName");
				item=rs.getString("ItemName");
				qty=rs.getInt("quantity");
				model.addRow(new Object[]{billno, date,cname,item,qty});
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
		new ViewSale();
	}
}
