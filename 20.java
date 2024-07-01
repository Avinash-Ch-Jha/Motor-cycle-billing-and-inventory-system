import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.sql.*;

class  stock extends JFrame implements ActionListener,ItemListener
{

JPanel pl=new JPanel();
Border br=null;

JLabel lbltitle=new JLabel("Stock Details");
JLabel lblpartno=new JLabel("Parts Number :");
JLabel lblpartnm=new JLabel("Parts Name :");
JLabel lblmrp=new JLabel("MRP :");
JLabel lblqty=new JLabel("Quantity :");

JComboBox compartno=new JComboBox();
JTextField txtpartnm=new JTextField();
JTextField txtmrp=new JTextField();
JTextField txtqty=new JTextField();

JButton cmdrefresh=new JButton("Refresh");
JButton cmdsave=new JButton("SAVE");
JButton cmdexit=new JButton("Exit");
JButton cmdsearch=new JButton("Search");
JButton cmdupdate=new JButton("Update");
JButton cmddel=new JButton("Delete");

stock()
{
setLayout(null);
getContentPane().add(pl);
pl.setBounds(90,25,250,35);
pl.add(lbltitle);
br=BorderFactory.createRaisedBevelBorder();
pl.setBorder(br);
Font f=new Font("Times new Roman",Font.BOLD,24);
lbltitle.setFont(f);
lbltitle.setForeground(Color.red);

getContentPane().add(lblpartno);
lblpartno.setBounds(90,90,100,25);
getContentPane().add(compartno);
compartno.setBounds(190,90,150,25);

getContentPane().add(lblpartnm);
lblpartnm.setBounds(90,125,100,25);
getContentPane().add(txtpartnm);
txtpartnm.setBounds(190,125,150,25);

getContentPane().add(lblmrp);
lblmrp.setBounds(90,160,100,25);
getContentPane().add(txtmrp);
txtmrp.setBounds(190,160,150,25);

getContentPane().add(lblqty);
lblqty.setBounds(90,195,100,25);
getContentPane().add(txtqty);
txtqty.setBounds(190,195,150,25);


getContentPane().add(cmdrefresh);
cmdrefresh.setBounds(90,240,90,20);
getContentPane().add(cmdsave);
cmdsave.setBounds(190,240,75,20);
getContentPane().add(cmdsearch);
cmdsearch.setBounds(275,240,75,20);
getContentPane().add(cmdupdate);
cmdupdate.setBounds(90,270,90,20);
getContentPane().add(cmddel);
cmddel.setBounds(190,270,75,20);
getContentPane().add(cmdexit);
cmdexit.setBounds(275,270,75,20);


setSize(450,400);
setVisible(true);
setTitle("BIKE DETAILS");
setLocation(250,150);
setResizable(false);

cmdrefresh.setMnemonic('R');
cmdsave.setMnemonic('S');
cmdexit.setMnemonic('E');

cmdrefresh.addActionListener(this);
cmdsave.addActionListener(this);
cmdexit.addActionListener(this);
cmdupdate.addActionListener(this);
cmddel.addActionListener(this);
cmdsearch.addActionListener(this);
compartno.addItemListener(this);
	try
	{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s10="Select * from partmaster";
		ResultSet rs=stat.executeQuery(s10);

		while(rs.next())
		{
		compartno.addItem(rs.getString(1));
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}



}
public void itemStateChanged(ItemEvent e)
{
String s1=String.valueOf(compartno.getSelectedItem());
	
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();

	String s="Select * from partmaster where partno='"+s1+"'";
	ResultSet rs=stat.executeQuery(s);
	
	while(rs.next())
	{
	txtpartnm.setText(rs.getString(2));
	txtmrp.setText(rs.getString(3));
	}
	}
	catch(Exception e1)
	{
	System.out.println(e1);
	}
}

public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==cmdexit)
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	dispose();
	}

	if(e.getSource()==cmdsave)
	{
	String s1=String.valueOf(compartno.getSelectedItem());
	String s2=txtpartnm.getText();
	String s3=txtmrp.getText();
	String s4=txtqty.getText();
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql="insert into stock values('"+s1+"','"+s2+"','"+s3+"','"+s4+"')";
	stat.executeUpdate(sql);
	JOptionPane.showMessageDialog(this,"Record save successfully");
	}
	catch(Exception e1)
	{
	System.out.println(e1);
	}
	
	}

	if(e.getSource()==cmdrefresh)
	{
	txtpartnm.setText("");
	txtmrp.setText("");
	txtqty.setText("");
	}

	if(e.getSource()==cmddel)
	{
	String s=JOptionPane.showInputDialog(this,"Enter Parts number :","Part no  ");
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql2="delete stock where partno='"+s+"'";
	stat.executeUpdate(sql2);
	JOptionPane.showMessageDialog(this,"Record deleted successfully");
	}
	catch(Exception e2)
	{
	System.out.println(e2);
	}
	}

	if(e.getSource()==cmdsearch)
	{
	String s5=String.valueOf(compartno.getSelectedItem());
	try
	{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s6="Select * from stock where partno='"+s5+"'";
		ResultSet rs=stat.executeQuery(s6);

		while(rs.next())
		{
		txtpartnm.setText(rs.getString(2));
		txtmrp.setText(rs.getString(3));
		txtqty.setText(rs.getString(4));
		}
	}
	catch(Exception e3)
	{
		System.out.println(e3);
	}
	
	}

	if(e.getSource()==cmdupdate)
	{
	String st1=String.valueOf(compartno.getSelectedItem());
	String st2=JOptionPane.showInputDialog(this,"Enter quantity","New quantity");	
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql3="update stock set qty='"+st2+"' where partno='"+st1+"'";
	stat.executeUpdate(sql3);
	JOptionPane.showMessageDialog(this,"Update successfully");
	
	}
	catch(Exception e4)
	{
	System.out.print(e4);
	}

	}

}

public static void main(String ar[])
{
new stock();
}
}
