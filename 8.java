import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.sql.*;
class bikestock extends JFrame implements ActionListener
{
JPanel pl=new JPanel();
JLabel lbltitle=new JLabel("BIKE STOCK INFORMATION");
JLabel lblbmd=new JLabel("Enter bike model :");
JLabel lblnum=new JLabel("Quantity :");
JButton cmdrefresh=new JButton("Refresh");
JButton cmdsearch=new JButton("Search");
JButton cmdexit=new JButton("Exit");
JComboBox combmd=new JComboBox();
JTextField txtnum=new JTextField();
Border br=null;
bikestock()
{
setLayout(null);
getContentPane().add(pl);
pl.setBounds(30,25,375,40);
pl.add(lbltitle);
br=BorderFactory.createRaisedBevelBorder();
pl.setBorder(br);
Font f=new Font("Times new Roman",Font.BOLD,24);
lbltitle.setFont(f);
lbltitle.setForeground(Color.red);
getContentPane().add(lblbmd);
lblbmd.setBounds(100,130,120,25);
getContentPane().add(lblnum);
lblnum.setBounds(100,160,100,25);
getContentPane().add(combmd);
combmd.setBounds(220,130,100,25);
getContentPane().add(txtnum);
txtnum.setBounds(220,160,100,25);
getContentPane().add(cmdrefresh);
cmdrefresh.setBounds(90,225,85,25);
getContentPane().add(cmdsearch);
cmdsearch.setBounds(180,225,75,25);
getContentPane().add(cmdexit);
cmdexit.setBounds(260,225,75,25);
setSize(450,350);
setVisible(true);
setTitle("BIKE STOCK");
setLocation(250,200);
setResizable(false);
cmdexit.setMnemonic('E');
cmdsearch.setMnemonic('s');
cmdrefresh.setMnemonic('s');
cmdexit.addActionListener(this);
cmdsearch.addActionListener(this);
cmdrefresh.addActionListener(this);
try
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s="Select * from bikedetail";
		ResultSet rs=stat.executeQuery(s);

		while(rs.next())
		{
		combmd.addItem(rs.getString(1));
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}

}

public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==cmdexit)
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	dispose();
	}
	
	if(e.getSource()==cmdsearch)
	{
	String s1=String.valueOf(combmd.getSelectedItem());
	try
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s2="Select * from bikedetail where model='"+s1+"'";
		ResultSet rs=stat.executeQuery(s2);

		while(rs.next())
		{
		txtnum.setText(rs.getString(8));
		}
	}
	catch(Exception e1)
	{
		System.out.println(e1);
	}
	}
	if(e.getSource()==cmdrefresh)
	{
	txtnum.setText("");
	}
}
public static void main(String ar[])
{
new bikestock();
}
}
