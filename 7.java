import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.sql.*;
class bikedetails extends JFrame implements ActionListener
{
JPanel pl=new JPanel();
JLabel lbltitle=new JLabel("BIKE DETAILS");
JLabel lblmc=new JLabel("Model :");
JLabel lblcolor=new JLabel("Color :");
JLabel lbleno=new JLabel("Engine No :");
JLabel lblcno=new JLabel("Chasies No :");
JLabel lblkey=new JLabel("Key No :");
JLabel lblbno=new JLabel("Bill No :");
JLabel lblbattery=new JLabel("Battery No :");
JLabel lblqty=new JLabel("Quantity :");
JTextField txt1=new JTextField();
JTextField txt2=new JTextField();
JTextField txt3=new JTextField();
JTextField txt4=new JTextField();
JTextField txt5=new JTextField();
JTextField txt6=new JTextField();
JTextField txt7=new JTextField();
JTextField txt8=new JTextField();
JButton cmdrefresh=new JButton("Refresh");
JButton cmdsave=new JButton("SAVE");
JButton cmdexit=new JButton("Exit");
Border br=null;
bikedetails()
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
getContentPane().add(lblmc);
lblmc.setBounds(90,90,100,25);
getContentPane().add(txt1);
txt1.setBounds(190,90,150,25);
getContentPane().add(lbleno);
lbleno.setBounds(90,125,100,25);
getContentPane().add(txt2);
txt2.setBounds(190,125,150,25);
getContentPane().add(lblcolor);
lblcolor.setBounds(90,160,100,25);
getContentPane().add(txt3);
txt3.setBounds(190,160,150,25);

getContentPane().add(lblcno);
lblcno.setBounds(90,195,100,25);
getContentPane().add(txt4);
txt4.setBounds(190,195,150,25);
getContentPane().add(lblkey);
lblkey.setBounds(90,230,100,25);
getContentPane().add(txt5);
txt5.setBounds(190,230,150,25);
getContentPane().add(lblbno);
lblbno.setBounds(90,265,100,25);
getContentPane().add(txt6);
txt6.setBounds(190,265,150,25);
getContentPane().add(lblbattery);
lblbattery.setBounds(90,300,100,25);
getContentPane().add(txt7);
txt7.setBounds(190,300,150,25);
getContentPane().add(lblqty);
lblqty.setBounds(90,335,100,25);
getContentPane().add(txt8);
txt8.setBounds(190,335,150,25);
getContentPane().add(cmdrefresh);
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
	String s1=txt1.getText();
	String s2=txt2.getText();
	String s3=txt3.getText();
	String s4=txt4.getText();
	String s5=txt5.getText();
	String s6=txt6.getText();
	String s7=txt7.getText();
	String s8=txt8.getText();

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
	txt1.setText("");
	txt2.setText("");
	txt3.setText("");
	txt4.setText("");
	txt5.setText("");
	txt6.setText("");
	txt7.setText("");
	txt8.setText("");
	}
}
public static void main(String ar[])
{
new bikedetails();
}
}
