package pack2;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

public class Home extends JFrame implements ActionListener
{
	private JMenuItem cp;
	private JMenuItem vstock;
	private JMenuItem vpurchase;
	private JMenuItem vsale;
	private JMenuItem pur;
	private JMenuItem sale;
	private Object cancel;

	public Home() 
	{
		setTitle("Home");
		setSize(1000,500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu action = new JMenu("Transactions");
		action.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(action);
		
		pur = new JMenuItem("Purchase");
		pur.setForeground(new Color(0, 0, 0));
		pur.setFont(new Font("SimSun", Font.BOLD, 16));
		action.add(pur);
		
		sale = new JMenuItem("Sale");
		sale.setFont(new Font("SimSun", Font.BOLD, 16));
		action.add(sale);
		
		JMenu report = new JMenu("Report");
		report.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(report);
		
		vsale = new JMenuItem("View Sale");
		vsale.setFont(new Font("SimSun", Font.BOLD, 16));
		report.add(vsale);
		
		vpurchase = new JMenuItem("View Purchase");
		vpurchase.setFont(new Font("SimSun", Font.BOLD, 16));
		report.add(vpurchase);
		
		vstock = new JMenuItem("View Stock");
		vstock.setFont(new Font("SimSun", Font.BOLD, 16));
		report.add(vstock);
		vpurchase.addActionListener(this);
		vsale.addActionListener(this);
		vstock.addActionListener(this);
		
		JMenu sdfs = new JMenu("Exit");
		sdfs.setForeground(Color.RED);
		sdfs.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(sdfs);
		
		cp = new JMenuItem("Close");
		cp.setFont(new Font("SimSun", Font.BOLD, 16));
		sdfs.add(cp);
		getContentPane().setLayout(null);
		
	//	setModal();
		
		/*ImageIcon ii=new ImageIcon(getClass().getResource("images/com.png"));
		JLabel lab=new JLabel(ii);
		lab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lab.setBounds(0, 0, 1370, 999);
		getContentPane().add(lab);*/
		getContentPane().setLayout(null);
		pur.addActionListener(this);
		sale.addActionListener(this);
		cp.addActionListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Home();

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object ob=e.getSource();
		if(ob==cancel)
				dispose();
		else if(ob==pur)
				new Purchase();
		else if (ob==sale)
				new Sales();
		else if(ob==vpurchase){
				new ViewPurchase();
		}
		else if(ob==vsale){
				new ViewSale();
		}
		else if(ob==vstock){
			 	new ViewStock();
		}
		else if (ob==cp)
		{
			int yn=JOptionPane.showConfirmDialog(null, "Sure to exit ?", "CONFIRMATION !", JOptionPane.YES_NO_OPTION);
			if(yn==0)
				System.exit(0);
		}
		
	}
}
