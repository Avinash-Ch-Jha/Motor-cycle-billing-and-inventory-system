import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.sql.*;
class createusr extends JFrame implements ActionListener
{
JTextField t1=new JTextField();
JLabel l1=new JLabel("Admin Passward :");
JLabel l2=new JLabel("New User Name :");
JLabel l3=new JLabel("Passward  :");
JPasswordField pt1=new JPasswordField();
JPasswordField pt2=new JPasswordField();
JButton cmdsave=new JButton("Save");
JButton cmdexit=new JButton("Exit");
createusr()
{
setLayout(null);
getContentPane().add(l1);
l1.setBounds(50,20,200,25);
getContentPane().add(l2);
l2.setBounds(50,50,200,25);
getContentPane().add(l3);
l3.setBounds(50,80,200,25);
getContentPane().add(pt1);
pt1.setBounds(250,20,100,25);
getContentPane().add(t1);
t1.setBounds(250,50,100,25);
getContentPane().add(pt2);
pt2.setBounds(250,80,100,25);
getContentPane().add(cmdsave);
cmdsave.setBounds(100,130,75,25);
getContentPane().add(cmdexit);
cmdexit.setBounds(200,130,75,25);
setSize(400,250);
setTitle("Create new user");
setVisible(true);
setLocation(500,300);
cmdsave.addActionListener(this);
cmdexit.addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==cmdsave)
	{
	String s1=pt1.getText();
	if(s1.equals("kumar"))
	{	
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	String s2=t1.getText();
	String s3=pt2.getText();
	String sql="insert into login values('"+s2+"','"+s3+"')";
	stat.executeUpdate(sql);
	JOptionPane.showMessageDialog(this,"User created");
	}
	catch(Exception e1)
	{
	System.out.println(e1);
	}
	}
	else
	JOptionPane.showMessageDialog(this,"Invalid admin passward");
	}

	if(e.getSource()==cmdexit)
	{
	dispose();
	}
}
public static void main(String ar[])
{
new createusr();
}
}

