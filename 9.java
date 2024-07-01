import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.sql.*;
class priceentry extends JFrame implements ActionListener
{
JPanel pl=new JPanel();
JLabel lbltitle=new JLabel("PRICE ENTRY");
JLabel lblmodel=new JLabel("Model :");
JLabel lblprice=new JLabel("Price :");
JLabel lblpiw=new JLabel("Weight :");
JLabel lblcode=new JLabel("Code :");
JLabel lblhorse=new JLabel("Hourse power :");
JLabel lblcubic=new JLabel("Cubic :");
JLabel lblmfg=new JLabel("Date of Mfg. :");
JLabel lblbt=new JLabel("Body type :");
JComboBox commodel=new JComboBox();
JTextField txtprice=new JTextField();
JTextField txtweight=new JTextField();
JTextField txtcode=new JTextField();
JTextField txthorse=new JTextField();
JTextField txtcubic=new JTextField();
JTextField txtmfg=new JTextField();
JTextField txtbt=new JTextField();

JButton cmdrefresh=new JButton("Refresh");
JButton cmdadd=new JButton("Add");
JButton cmdupdate=new JButton("Update");
JButton cmdexit=new JButton("Exit");
Border br=null;
priceentry()
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
getContentPane().add(lblmodel);
lblmodel.setBounds(90,90,100,25);
getContentPane().add(commodel);
commodel.setBounds(190,90,100,25);
getContentPane().add(lblprice);
lblprice.setBounds(90,125,100,25);
getContentPane().add(txtprice);
txtprice.setBounds(190,125,100,25);
getContentPane().add(lblpiw);
lblpiw.setBounds(90,160,100,25);
getContentPane().add(txtweight);
txtweight.setBounds(190,160,100,25);
getContentPane().add(lblcode);
lblcode.setBounds(90,195,100,25);
getContentPane().add(txtcode);
txtcode.setBounds(190,195,100,25);
getContentPane().add(lblhorse);
lblhorse.setBounds(90,230,100,25);
getContentPane().add(txthorse);
txthorse.setBounds(190,230,100,25);
getContentPane().add(lblcubic);
	try
	{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s="Select * from bikedetail";
		ResultSet rs=stat.executeQuery(s);

		while(rs.next())
		{
		commodel.addItem(rs.getString(1));
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

	if(e.getSource()==cmdadd)
	{
	String s1=String.valueOf(commodel.getSelectedItem());
	String s2=txtprice.getText();
	String s3=txtweight.getText();
	String s4=txtcode.getText();
	String s5=txthorse.getText();
	String s6=txtcubic.getText();
	String s7=txtmfg.getText();
	String s8=txtbt.getText();

	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql="insert into bikedetail values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";
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
	txtprice.setText("");
	txtweight.setText("");
	txtcode.setText("");
	txthorse.setText("");
	txtcubic.setText("");
	txtmfg.setText("");
	txtbt.setText("");
	}

	if(e.getSource()==cmdupdate)
	{
	String st1=String.valueOf(commodel.getSelectedItem());
	String st2=JOptionPane.showInputDialog(this,"Enter Price","New price");	



	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql="update bikeprice set price='"+st2+"' where model='"+st1+"'";
	stat.executeUpdate(sql);
	JOptionPane.showMessageDialog(this,"Update successfully");
	
	}
	catch(Exception e1)
	{
	System.out.print(e1);
	}

	}
}
public static void main(String ar[])
{
new priceentry();
