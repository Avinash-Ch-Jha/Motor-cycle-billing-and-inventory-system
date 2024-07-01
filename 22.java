import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;

class sparereturntaxinvoicefrm extends JFrame implements ActionListener,ItemListener
{

JPanel pl1=new JPanel();
JPanel pl2=new JPanel();
JPanel p1=new JPanel();
JPanel p2=new JPanel();

int i=0,sum=0,a=0,x=0,k1,k2=0,k3=0,k4=0,k5=0,k6=0;
Border br=null;
JTable tab;
String head[];
Object data[][];

JLabel lbltitle=new JLabel("SALES RETURN RATAIL INVOICE");
JLabel lblino=new JLabel("Invoice No :");
JLabel lblidate=new JLabel("Invoice Date :");
JLabel lblappvat=new JLabel("Applicable VAT");
JLabel lblpaymode=new JLabel("Payment mode :");
JLabel lblinvto=new JLabel("Invoice To :");
JLabel lblinvno=new JLabel("TIN No :");

Label lblvalamt=new JLabel("Total valuable Amount :");
JLabel lbltvat=new JLabel ("Total VAT :");
JLabel lblnet=new JLabel("Net Payable Amount :");
JLabel e1=new JLabel();
JLabel e2=new JLabel();


JTextField txtino=new JTextField();
JTextField txtidate=new JTextField();

JTextField txtito1=new JTextField();
JTextField txtito2=new JTextField();
JTextField txtito3=new JTextField();
JTextField txtinvno=new JTextField();

JTextField txtpartnm=new JTextField();
JTextField txtrate=new JTextField();
JTextField txtqty=new JTextField();
JTextField txtvat=new JTextField();
JTextField txtamt=new JTextField();
getContentPane().add(cmdadd);
cmdadd.setBounds(420,260,100,20);
getContentPane().add(cmddelete);
cmddelete.setBounds(530,260,100,20);
getContentPane().add(cmdcal);
cmdcal.setBounds(640,260,100,20);

tablebody();

getContentPane().add(p2);
p2.setBounds(400,390,340,130);
p2.setLayout(new GridLayout(5,2,10,8));
p2.add(lbltotal);p2.add(txttotal);
p2.add(lbldis);p2.add(txtdis);
p2.add(lblvalamt);p2.add(txtvalamt);
p2.add(lbltvat);p2.add(txttvat);
p2.add(lblnet);p2.add(txtnet);

getContentPane().add(cmdsave);
cmdsave.setBounds(420,530,100,20);
getContentPane().add(cmdprint);
cmdprint.setBounds(530,530,100,20);
getContentPane().add(cmdexit);
cmdexit.setBounds(640,530,100,20);
setSize(800,600);
setVisible(true);
setTitle("TAX INVOICE");
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
pane.setBounds(50,290,690,100);

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

	String s="Select * from stock where partno='"+s1+"'";
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
	k1=(sum*10)/100;
	
	k3=sum-k1;
	
	k4=k3+k2;

	txttotal.setText(String.valueOf(sum));
	txtdis.setText(String.valueOf(k1));
	txtvalamt.setText(String.valueOf(k3));
	txtnet.setText(String.valueOf(k4));

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
		}
	}
	if(e.getSource()==cmdsave)
	{
	String s1=txtino.getText();
	String s2=txtidate.getText();
	String s3=String.valueOf(compmode.getSelectedItem());
	String s4=String.valueOf(comvat.getSelectedItem());
	String s5=txtito1.getText();
	String s6=txtito2.getText();
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	String sql="insert into invoicereturnmaster values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"','"+s11+"','"+s12+"')";
	stat.executeUpdate(sql);
	JOptionPane.showMessageDialog(this,"Record save successfully");
	}
	catch(Exception e2)
	{
	System.out.println(e2);
	}
	//Update stock
	for(x=0;x<i;x++)
	{
	String str1=String.valueOf(tab.getValueAt(x,0));
	String str2=String.valueOf(tab.getValueAt(x,3));
	String s17=new String();
		try
		{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String st6="Select qty from stock where partno='"+str1+"'";
		ResultSet rs=stat.executeQuery(st6);

		while(rs.next())
		{
		s17=rs.getString(1);
		}
		}
		catch(Exception e3)
		{
		System.out.println(e3);
		}
	
	
	
		int y=Integer.parseInt(str2);
		int z=Integer.parseInt(s17);
		y=z+y;
		try
		{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();
	
		String sql13="update stock set qty='"+y+"' where partno='"+str1+"'";
		stat.executeUpdate(sql13);
		}
		catch(Exception e14)
		{
		System.out.print(e14);
		}
	}}
	if(e.getSource()==cmddelete)
	{i=i-1;
	tab.setValueAt("",i,0);
	tab.setValueAt("",i,1);
	tab.setValueAt("",i,2);
	}
	if(e.getSource()==cmdcal)
	{
	int k=Integer.parseInt(txtrate.getText())*Integer.parseInt(txtqty.getText());
	txtamt.setText(String.valueOf(k));
	int t=(k*a)/100;
	txtvat.setText(String.valueOf(t));
	k2=k2+t;
	txttvat.setText(String.valueOf(k2));
	}

}


public static void main(String ar[])
{
new sparereturntaxinvoicefrm();
}
