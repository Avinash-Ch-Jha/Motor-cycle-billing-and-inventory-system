import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.sql.*;

class  stockfinder extends JFrame implements ActionListener,ItemListener
{

JPanel pl=new JPanel();
Border br=null;

JLabel lbltitle=new JLabel("Stock Finder");
JLabel lblpartno=new JLabel("Parts Number :");
JLabel lblqty=new JLabel("Quantity :");

JComboBox compartno=new JComboBox();
JTextField txtqty=new JTextField();

JButton cmdrefresh=new JButton("Refresh");
JButton cmdexit=new JButton("Exit");

stockfinder()
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

getContentPane().add(lblqty);
lblqty.setBounds(90,130,100,25);
getContentPane().add(txtqty);
txtqty.setBounds(190,130,150,25);

getContentPane().add(cmdrefresh);
cmdrefresh.setBounds(100,200,100,30);
getContentPane().add(cmdexit);
cmdexit.setBounds(220,200,100,30);

setSize(450,300);
setVisible(true);
setTitle("BIKE DETAILS");
setLocation(250,150);
setResizable(false);

cmdrefresh.setMnemonic('R');
cmdexit.setMnemonic('E');

cmdrefresh.addActionListener(this);
cmdexit.addActionListener(this);
compartno.addItemListener(this);
	try
	{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s="Select * from stock";
		ResultSet rs=stat.executeQuery(s);

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

	String s="Select qty from stock where partno='"+s1+"'";
	ResultSet rs=stat.executeQuery(s);
	
	while(rs.next())
	{
	txtqty.setText(rs.getString(1));
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
	if(e.getSource()==cmdrefresh)
	{
	txtqty.setText("");
	}
}

public static void main(String ar[])
{
new stockfinder();
}
}
