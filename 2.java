import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.border.*;
class connectall extends JFrame implements ActionListener
{
JButton cmdcon=new JButton("Connect");
JButton cmdlogin=new JButton("Login");
JButton cmdexit=new JButton("Exit");
JLabel lblcon=new JLabel("To connect Database :");
JLabel lbllog=new JLabel("To Login in Project :");
JLabel lblexit=new JLabel("To close window :");
JPanel pl=new JPanel();
JLabel lbltitle=new JLabel("***Connect All Module***");
Border br=null;
JFrame fm1,fm2;
JLabel lblgname=new JLabel("Enter global database name :");
JLabel lbluname=new JLabel("Enter user name:");
JLabel lblpwd=new JLabel("Enter password :");
JTextField txtgname=new JTextField();
JTextField txtuname=new JTextField();
JTextField txtpwd=new JTextField();
JButton cmdcon1=new JButton("Connect");
JButton cmdexit1=new JButton("Exit");

String gname,uname,pwd;

JLabel l1=new JLabel("MOTORCYCLE BILLING");
JLabel l4=new JLabel("&");
JLabel l5=new JLabel("INVENTORY SYSTEM");
JLabel l2=new JLabel("User Name  :");
JLabel l3=new JLabel("Password :");
JComboBox cb1=new JComboBox();
JPasswordField t1=new JPasswordField();
String s1;
JButton b1=new JButton("Login");
JButton b2=new JButton("Exit");
JButton b3=new JButton("Create user");
JButton img=new JButton(new ImageIcon("f:/swing/Hero-Honda-Set.jpg"));
connectall()
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
getContentPane().add(lblcon);
lblcon.setBounds(50,100,150,25);
getContentPane().add(cmdcon);
cmdcon.setBounds(200,100,100,25);
getContentPane().add(lbllog);
lbllog.setBounds(50,135,150,25);
getContentPane().add(cmdlogin);
cmdlogin.setBounds(200,135,100,25);
getContentPane().add(lblexit);
lblexit.setBounds(50,170,150,25);
getContentPane().add(cmdexit);
cmdexit.setBounds(200,170,100,25);
setSize(450,350);
setVisible(true);
setTitle("CONNECTION MODULE");
setLocation(250,200);
setResizable(false);
cmdcon.setMnemonic('c');
cmdlogin.setMnemonic('l');
cmdexit.setMnemonic('E');
cmdcon.addActionListener(this);
cmdlogin.addActionListener(this);
cmdexit.addActionListener(this);
}
void frmcon()
{
fm1=new JFrame();
fm1.setLayout(null);
fm1.getContentPane().add(lblgname);
lblgname.setBounds(50,50,175,25);
fm1.setSize(450,350);
fm1.setVisible(true);
fm1.setTitle("Database Information");
fm1.setLocation(250,200);
fm1.setResizable(false);
cmdcon1.setMnemonic('C');
cmdexit1.setMnemonic('E');
cmdcon1.addActionListener(this);
cmdexit1.addActionListener(this);
}

void mbis()
{
fm2=new JFrame();
fm2.setLayout(null);
fm2.getContentPane().add(l1);
l1.setBounds(100,10,450,30);
fm2.getContentPane().add(l4);
l4.setBounds(275,40,50,30);
fm2.getContentPane().add(l5);
l5.setBounds(130,70,450,30);
Font f=new Font("Times New Roman",Font.BOLD,30);
l1.setFont(f);
l4.setFont(f);
l5.setFont(f);
l1.setForeground(Color.red);
l4.setForeground(Color.red);
l5.setForeground(Color.red);
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:521:"+gname,uname,pwd);
Statement stat=con.createStatement();
String s="Select * from login";
ResultSet rs=stat.executeQuery(s);
while(rs.next())
{
cb1.addItem(rs.getString(1));
}
}
catch(Exception e1)
{
System.out.println(e1);
}
b1.setMnemonic('l');
b2.setMnemonic('e');
b3.setMnemonic('c');
fm2.setSize(550,400);
fm2.setVisible(true);
fm2.setTitle("LOGIN WINDOW");
fm2.setLocation(300,200);
fm2.setResizable(false);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{

	if(e.getSource()==cmdcon)// database connection  form 
	{
	frmcon();
	}
	if(e.getSource()==cmdlogin)// login form
	{
	mbis();
	dispose();
	}
	if(e.getSource()==cmdexit)//exit from connection module
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	dispose();
	}
	if(e.getSource()==cmdexit1)//exit database form
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	fm1.dispose();
	}
	if(e.getSource()==cmdcon1)// databasconnection 
	{
	gname=txtgname.getText();
	uname=txtuname.getText();
	pwd=txtpwd.getText();
	if(JOptionPane.showConfirmDialog(this,"Have you fill up the field appropriate","Connected",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	fm1.dispose();
	}
	if(e.getSource()==b1)// main menu login code
	{
		s1=String.valueOf(cb1.getSelectedItem());
		String s2=t1.getText();
		String s3=new String();
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:"+gname,uname,pwd);			Statement stat=con.createStatement();
		            String s="Select pwd from login where uname='"+s1+"'";
			ResultSet rs=stat.executeQuery(s);

			while(rs.next())
			{
			 s3=rs.getString(1);
			}
		}
		catch(Exception e2)
		{
			System.out.println(e2);
		}
		if(s2.equals(s3))
		{
		 new mainfrm();
		fm2.dispose();
		}
		else
		{
		JOptionPane.showMessageDialog(this,"login failed");
		}	 
	}
	if(e.getSource()==b2)//exit from login
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	fm2.dispose();
	}
	if(e.getSource()==b3)//open create user form
	{
	new createusr();
	}
}
public static void main(String ar[])
{
new connectall();
}
}
