
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;

class estimatefrm extends JFrame implements ActionListener,ItemListener
{

JPanel p1=new JPanel();
JPanel pl1=new JPanel();
JPanel pl2=new JPanel();


int i=0,sum=0,a=0;
Border br=null;
JTable tab;
String head[];
Object data[][];

JLabel lbltitle=new JLabel("ESTIMATE");
JLabel estno=new JLabel("Estimate No.");
JLabel estdate=new JLabel("Estimate Date");
JLabel appvat=new JLabel("Applicable VAT");
JLabel estto=new JLabel("Estimate To");
JLabel e1=new JLabel();
JLabel e2=new JLabel();

JLabel lblpartno=new JLabel("Part No:");
JLabel lblpartnm=new JLabel("Part Name :");
JLabel lblrate=new JLabel("Rate :");
JLabel lblqty=new JLabel("QTY :");
JLabel lblvat=new JLabel("VAT :");
JLabel lblamt=new JLabel("Amount :");
JLabel lbltotal=new JLabel("Total purchase value :");

JTextField txtno=new JTextField();
JComboBox comvat=new JComboBox();
JTextField txtidate=new JTextField();
JTextField txtto1=new JTextField();
JTextField txtto2=new JTextField();
JTextField txtto3=new JTextField();

JTextField txtrate=new JTextField();
JTextField txtpartnm=new JTextField();
JTextField txtqty=new JTextField();
JTextField txtvat=new JTextField();
JTextField txtamt=new JTextField();
JTextField txttotal=new JTextField();

JComboBox compartno=new JComboBox();

JButton cmdadd=new JButton("ADD");
JButton cmddelete=new JButton("Delete");
JButton cmdcal=new JButton("calculate");
JButton cmdsave=new JButton("Save");
JButton cmdprint=new JButton("Print");
JButton cmdexit=new JButton("Exit");


estimatefrm()
{
setLayout(null);

getContentPane().add(pl1);
pl1.setBounds(200,15,375,40);
pl1.add(lbltitle);
br=BorderFactory.createRaisedBevelBorder();
pl1.setBorder(br);
Font f=new Font("Times new Roman",Font.BOLD,24);
lbltitle.setFont(f);
lbltitle.setForeground(Color.red);

getContentPane().add(p1);
p1.setBounds(50,80,660,80);
p1.setLayout(new GridLayout(3,4,10,8));
p1.add(estno);p1.add(txtno);p1.add(estto);p1.add(txtto1);
p1.add(estdate);p1.add(txtidate);p1.add(e1);p1.add(txtto2);
p1.add(appvat);p1.add(comvat);p1.add(e2);p1.add(txtto3);

comvat.addItem("12");
comvat.addItem("4");
getContentPane().add(pl2);
pl2.setBounds(50,190,660,3);
pl2.setBackground(Color.red);

getContentPane().add(lblpartno);
lblpartno.setBounds(50,210,100,20);
getContentPane().add(compartno);
compartno.setBounds(50,235,140,20);

getContentPane().add(lblpartnm);
lblpartnm.setBounds(200,210,100,20);
getContentPane().add(txtpartnm);
txtpartnm.setBounds(200,235,140,20);

getContentPane().add(lblrate);
lblrate.setBounds(350,210,80,20);
getContentPane().add(txtrate);
txtrate.setBounds(350,235,90,20);

getContentPane().add(lblqty);
lblqty.setBounds(450,210,80,20);
getContentPane().add(txtqty);
txtqty.setBounds(450,235,80,20);

getContentPane().add(lblvat);
lblvat.setBounds(540,210,80,20);
getContentPane().add(txtvat);
txtvat.setBounds(540,235,80,20);

getContentPane().add(lblamt);
lblamt.setBounds(630,210,80,20);
getContentPane().add(txtamt);
txtamt.setBounds(630,235,80,20);

getContentPane().add(cmdadd);
cmdadd.setBounds(390,290,100,20);
getContentPane().add(cmddelete);
cmddelete.setBounds(500,290,100,20);
getContentPane().add(cmdcal);
cmdcal.setBounds(610,290,100,20);

tablebody();

getContentPane().add(lbltotal);
lbltotal.setBounds(420,450,130,20);
getContentPane().add(txttotal);
txttotal.setBounds(560,450,150,20);

getContentPane().add(cmdsave);
cmdsave.setBounds(390,490,100,20);
getContentPane().add(cmdprint);
cmdprint.setBounds(500,490,100,20);
getContentPane().add(cmdexit);
cmdexit.setBounds(610,490,100,20);

setSize(800,600);
setVisible(true);
setTitle("Estimate Details");
setLocation(100,30);
setResizable(false);

cmdexit.addActionListener(this);
cmdsave.addActionListener(this);
cmdcal.addActionListener(this);
cmddelete.addActionListener(this);
cmdprint.addActionListener(this);
cmdadd.addActionListener(this);
compartno.addItemListener(this);

	try
	{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s="Select * from partmaster";
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

void tablebody()
{
head=new String[6];
data=new Object[25][6];
head[0]="PART NO";
head[1]="PART NAME";
head[2]="RATE";
head[3]="QTY";
head[4]="VAT";
head[5]="Amount";
tab=new JTable(data,head);
tab.setRowHeight(20);

JScrollPane pane=new JScrollPane(tab);
tab.setFont(new Font("Arial",Font.BOLD,18));
getContentPane().add(pane);
pane.setBounds(50,330,660,100);

}

public void itemStateChanged(ItemEvent e)
{
	String s2=String.valueOf(comvat.getSelectedItem());
	
	a=Integer.parseInt(s2);
	

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
	txtrate.setText(rs.getString(3));
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

	if(e.getSource()==cmdadd)
	{
	tab.setValueAt(String.valueOf(compartno.getSelectedItem()),i,0);
	tab.setValueAt(String.valueOf(txtpartnm.getText()),i,1);
	tab.setValueAt(txtrate.getText(),i,2);
	tab.setValueAt(txtqty.getText(),i,3);
	tab.setValueAt(txtvat.getText(),i,4);
	tab.setValueAt(txtamt.getText(),i,5);
	int d=Integer.parseInt(txtamt.getText());
	sum=sum+d;
	txttotal.setText(String.valueOf(sum));
	i++;
		
		if(JOptionPane.showConfirmDialog(this,"Do you want to add more item !","Add more Item",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		{
		
		txtpartnm.setText("");
		txtrate.setText("");
		txtqty.setText("");
		txtamt.setText("");
		txtvat.setText("");
		}
		else
		{
		compartno.setEnabled(false);
		txtpartnm.setEnabled(false);
		txtrate.setEnabled(false);
		txtqty.setEnabled(false);
		txtamt.setEnabled(false);
		txtvat.setEnabled(false);
		cmdadd.setEnabled(false);
		cmdsave.setEnabled(true);
		cmdprint.setEnabled(true);
		}
	}

	if(e.getSource()==cmdsave)
	{
	String s1=txtno.getText();
	String s2=txtidate.getText();
	String s3=String.valueOf(comvat.getSelectedItem());
	String s4=txtto1.getText();
	String s5=txtto2.getText();
	String s6=txtto3.getText();
	String s7=txttotal.getText();

	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql="insert into estimatemaster values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"')";
	stat.executeUpdate(sql);
	JOptionPane.showMessageDialog(this,"Record save successfully");
	}
	catch(Exception e2)
	{
	System.out.println(e2);
	}
	}


	if(e.getSource()==cmddelete)
	{i=i-1;
	tab.setValueAt("",i,0);
	tab.setValueAt("",i,1);
	tab.setValueAt("",i,2);
	tab.setValueAt("",i,3);
	tab.setValueAt("",i,4);
	tab.setValueAt("",i,5);
	}

	if(e.getSource()==cmdcal)
	{
	int x=Integer.parseInt(txtrate.getText())*Integer.parseInt(txtqty.getText());
	txtamt.setText(String.valueOf(x));
	int t=(x*a)/100;
	txtvat.setText(String.valueOf(t));
	}
}


public static void main(String ar[])
{
new estimatefrm();
}
}
