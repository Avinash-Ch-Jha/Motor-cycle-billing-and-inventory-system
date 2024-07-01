import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
class motormodule extends JFrame implements ActionListener
{
JButton cmdretail=new JButton("RETAIL INVOICE");
JButton cmdstock=new JButton("STOCK DETAILS");
JButton cmdbikedetail=new JButton("BIKE DETAILS");
JButton cmdpriceentry=new JButton("PRICE ENTRY");
JButton cmdreport=new JButton("REPORT");
JButton cmdexit=new JButton("EXIT");
motormodule()
{
setLayout(null);

cmdretail.setMnemonic('R');
cmdstock.setMnemonic('S');
cmdbikedetail.setMnemonic('B');
cmdpriceentry.setMnemonic('p');
cmdreport.setMnemonic('O');
cmdexit.setMnemonic('E');
setSize(900,650);
setVisible(true);
setTitle("MOTORCYCLE MODULE");
setLocation(50,50);
setResizable(false);
cmdretail.addActionListener(this);
cmdstock.addActionListener(this);
cmdbikedetail.addActionListener(this);
cmdpriceentry.addActionListener(this);
cmdreport.addActionListener(this);
cmdexit.addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==cmdexit)
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	dispose();
	}
	if(e.getSource()==cmdstock)
	{
	new bikestock();
	}
	if(e.getSource()==cmdbikedetail)
	{
	new bikedetails();
	}
	if(e.getSource()==cmdpriceentry)
	{
	 new priceentry();
	}
	if(e.getSource()==cmdreport)
	{
	new report();
	}
	if(e.getSource()==cmdretail)
	{
	new retailinvoice();
	}
}
public static void main(String ar[])
{
new motormodule();
}
}
***************** CODE FOR MOTORCYCLE RETAIL INVOICE ***************
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
class retailinvoice extends JFrame implements ActionListener,ItemListener
{
JPanel pl1=new JPanel();
JPanel pl2=new JPanel();
JPanel pl3=new JPanel();
JPanel pl4=new JPanel();
JLabel lbltitle=new JLabel("RETAIL INVOICE");
JLabel lblinno=new JLabel("Invoice number :");
JLabel lbldate=new JLabel("Invoice date : ");
JLabel lblpmode=new JLabel("Payment mode :");
JLabel lblpadd=new JLabel("Permanent address :");
JLabel lblcadd=new JLabel("Correspondence address :");
JLabel lblpname=new JLabel("Name :");
JLabel lblcname=new JLabel("Name :");
JLabel lblpaddress=new JLabel("Address :");
JLabel lblcaddress=new JLabel("Address :");
JLabel lblpdist=new JLabel("District :");
JLabel lblcdist=new JLabel("District :");
JLabel lblpstate=new JLabel("State :");
JLabel lblcstate=new JLabel("State :");
JLabel lblpphone=new JLabel("Phone/Mob :");
JLabel lblcphone=new JLabel("Phone/Mob :");
JLabel lblmodel=new JLabel("Model  :");
JLabel lblpower=new JLabel("Power :");
JLabel lbleno=new JLabel("Engine number :");
JLabel lblchesis=new JLabel("Chesis No :");
JLabel lblcolor=new JLabel("Color :");
JLabel lblkey=new JLabel("Key No :");
JLabel lblcost=new JLabel("Cost of Vechicle :");
JLabel lblbookno=new JLabel("Service Book No :");
JLabel lblbattery=new JLabel("Battery No :");
JLabel lblvat=new JLabel("VAT @ 12.5% :");
JLabel lblmor=new JLabel("M.O.R. No :");
JLabel lblreg=new JLabel("Register No :");
JLabel lblshowroompr=new JLabel("Ex-Showroom Price :");
JTextField txtamountinword=new JTextField();
JComboBox compmode=new JComboBox();
JComboBox commodel=new JComboBox();
Border br=null;
JButton cmdclear=new JButton("Clear");
JButton cmdsave=new JButton("Save");
JButton cmdprint=new JButton("Print");
JButton cmddel=new JButton("Delete");
JButton cmdexit=new JButton("Exit");
retailinvoice()
{
setLayout(null);
commodel.addItem("cd");
commodel.addItemListener(this);
getContentPane().add(pl1);
pl1.setBounds(200,15,375,40);
pl1.add(lbltitle);
br=BorderFactory.createRaisedBevelBorder();
pl1.setBorder(br);
Font f=new Font("Times new Roman",Font.BOLD,24);
lbltitle.setFont(f);
lbltitle.setForeground(Color.red);
getContentPane().add(lblinno);
lblinno.setBounds(30,75,100,20);
getContentPane().add(txtinno);
txtinno.setBounds(130,75,100,20);
getContentPane().add(lbldate);
lbldate.setBounds(250,75,100,20);
getContentPane().add(txtdate);
txtdate.setBounds(330,75,150,20);
//txtdate.setText("%2$td%2$tB%2$tY","",new Date());
getContentPane().add(lblpmode);
lblpmode.setBounds(510,75,100,20);
getContentPane().add(compmode);
compmode.setBounds(610,75,100,20);
compmode.addItem("cash");
compmode.addItem("credit");
getContentPane().add(lblpadd);
lblpadd.setBounds(50,100,150,20);
getContentPane().add(lblcadd);
lblcadd.setBounds(400,100,175,20);
getContentPane().add(lblpname);
lblpname.setBounds(50,130,100,20);
getContentPane().add(txtpname);
lblpdist.setBounds(50,170,100,20);
getContentPane().add(txtpdist);
txtpdist.setBounds(150,170,100,20);
getContentPane().add(lblcdist);
lblcdist.setBounds(400,170,100,20);
getContentPane().add(txtcdist);
txtcdist.setBounds(500,170,100,20);
getContentPane().add(lblpstate);
lblpstate.setBounds(50,190,100,20);
getContentPane().add(txtpstate);
txtpstate.setBounds(150,190,100,20);
getContentPane().add(lblcstate);
lblcstate.setBounds(400,190,100,20);
getContentPane().add(pl2);
pl2.setBounds(20,250,750,3);
pl2.setBackground(Color.red);
getContentPane().add(lblpower);
pl4.setBackground(Color.red);
//txtadv.addTextListener(this); 
setSize(800,600);
setVisible(true);
setTitle("Retail Invoice");
setLocation(100,30);
setResizable(false);
	try//retrive all model 
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
	try//retrive invoice number at run time
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();
		String x="Select invoice from motorcycleretail";
		ResultSet rs=stat.executeQuery(x);
		while(rs.next())
		{
		String y=rs.getString(1);
		int z=Integer.parseInt(y);z=z+1;
		txtinno.setText(String.valueOf(z));
		}
	}
	catch(Exception e4)
	{
		System.out.println(e4);
	}
}
public void itemStateChanged(ItemEvent e)
{



	int eno=Integer.parseInt(txteno.getText());
	String model=String.valueOf(commodel.getSelectedItem());
	System.out.println(eno);
		System.out.println(model);
	try
	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();
		String x="Select  *from bikedetail where engineno='"+eno+"' and model='"+model+"'";
		ResultSet rs=stat.executeQuery(x);
		while(rs.next())
		{
		txtcolor.setText(String.valueOf(rs.getString(3)));
		txtchesis.setText(String.valueOf(rs.getString(4)));
		txtbattery.setText(String.valueOf(rs.getString(7)));
		}
	}
	catch(Exception e4)
	{
		System.out.println(e4);
	}
	try
	{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		
		String x1="Select  *from bikeprice where model='"+model+"'";
		ResultSet rs=stat.executeQuery(x1);
		while(rs.next())
		{
		txtpower.setText(String.valueOf(rs.getString(4)));
		txtcost.setText(String.valueOf(rs.getString(2)));
				
		}
	}
	catch(Exception e4)
	{
		System.out.println(e4);
	}

	double cost,srp;
	double vat;
	cost=(Integer.parseInt(txtcost.getText()));
	vat=(cost*12.5)/100;
	txtvat.setText(String.valueOf(vat));
	srp=vat+cost;
	txtshowroompr.setText(String.valueOf(srp));
	txttotal.setText(String.valueOf(srp));
}
public void testValueChanged(TextEvent t)
{
}
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==cmdexit)
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	dispose();
	}
	if(e.getSource()==cmdclear)
	{


	
	
	txtinno.setText("");
	txtdate.setText("");	
	txtpname.setText("");
	txtcname.setText("");
	txtpadd.setText("");
	txtcadd.setText("");
	txtpaddress.setText("");
	txtcaddress.setText("");
	txtpdist.setText("");
	try//retrive invoice number at run time
	{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();
		String x="Select invoice from motorcycleretail";
		ResultSet rs=stat.executeQuery(x);

		while(rs.next())
		{
		String y=rs.getString(1);
		int z=Integer.parseInt(y);z=z+1;
		txtinno.setText(String.valueOf(z));
		}
	}
	catch(Exception e4)
	{
		System.out.println(e4);
	}
	}

	if(e.getSource()==cmdsave)
	{
	Double adv,amt,net;
	adv=Double.parseDouble(txtadv.getText());
	amt=Double.parseDouble(txttotal.getText());
	net=amt-adv;
	txtnetpay.setText(String.valueOf(net));
	if(JOptionPane.showConfirmDialog(this,"Do u want to continue !","Confirm",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	{
	String s1=txtinno.getText();
	String s2=txtdate.getText();
	String s3=String.valueOf(compmode.getSelectedItem());
	String s4=txtpname.getText();
	String s5=txtpaddress.getText();
	String s6=txtpdist.getText();
	String s7=txtpstate.getText();
	String s8=txtpphone.getText();
	String s9=txtcname.getText();
	String s10=txtcaddress.getText();
	String s11=txtcdist.getText();
	String s12=txtcstate.getText();
	String s13=txtcphone.getText();

	String s14=String.valueOf(commodel.getSelectedItem());
	String s15=txtchesis.getText();
	String s16=txteno.getText();
	String s17=txtbookno.getText();
	String s18=txtkey.getText();
	String s19=txtcolor.getText();
	String s20=txtbattery.getText();
	String s21=txtmor.getText();
	String s22=txtcost.getText();
	String s23=txtvat.getText();
	String s24=txtshowroompr.getText();
	String s25=txttotal.getText();
	String s26=txtadv.getText();
	String s27=txtnetpay.getText();
	String s28=txtreg.getText();
	String s29=txtpower.getText();
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql1="insert into motorcycleretail values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"','"+s11+"','"+s12+"','"+s13+"','"+s14+"','"+s15+"','"+s16+"','"+s17+"','"+s18+"','"+s19+"','"+s20+"','"+s21+"','"+s22+"','"+s23+"','"+s24+"','"+s25+"','"+s26+"','"+s27+"','"+s28+"','"+s29+"')";
	stat.executeUpdate(sql1);
	JOptionPane.showMessageDialog(this,"Record save successfully");
	}
	catch(Exception e2)
	{
	System.out.println(e2);
	}

	//decrease stock by one----------------------------------

	String s30=String.valueOf(commodel.getSelectedItem());
	String s32=new String();

	try
	{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s31="Select qty from bikedetail where model='"+s30+"'";
		ResultSet rs=stat.executeQuery(s31);

		while(rs.next())
		{
		s32=rs.getString(1);
		}
	}
	catch(Exception e11)
	{
		System.out.println(e11);
	}
	
	int y=Integer.parseInt(s32);
	y=y-1;

		try
		{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();
	
		String sql3="update bikedetail set qty='"+y+"' where model='"+s30+"'";
		stat.executeUpdate(sql3);
		}
		catch(Exception e41)
		{
		System.out.print(e41);
		}
	}


	
	}

	if(e.getSource()==cmddel)
	{
	String ex=JOptionPane.showInputDialog(this,"Enter invoice number :","invoice no");
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql2="delete motorcycleretail where invoice='"+ex+"'";
	stat.executeUpdate(sql2);
	JOptionPane.showMessageDialog(this,"Record deleted successfully");
	}
	catch(Exception e3)
	{
	System.out.println(e3);
	}
	}



}

public static void main(String ar[])
{
new retailinvoice();
}
}
