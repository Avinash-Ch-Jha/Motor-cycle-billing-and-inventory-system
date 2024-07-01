import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;
class purchasereturnfrm extends JFrame implements ActionListener,ItemListener
{
JPanel pl1=new JPanel();
JPanel pl2=new JPanel();

int i=0,sum=0,f=1,j=0,x=0;
Border br=null;
JTable tab;
String head[];
Object data[][];

JLabel lbltitle=new JLabel("PURCHASE RETURN");
JLabel lblpino=new JLabel("Purchase Invoice NO:");
JLabel lblidate=new JLabel("Invoice date :");
JLabel lblpnum=new JLabel("Packing No :");
JLabel lblgno=new JLabel("GR NO :");
JLabel lblgdate=new JLabel("GR Date :");
JLabel lblpfrom=new JLabel("Purchase from :");
JLabel lblcase=new JLabel("No of case :");
JLabel lbltname=new JLabel("Transport name :");
JLabel lblwt=new JLabel("Weight :");
JLabel lblpartno=new JLabel("Part No:");
JLabel lblpartnm=new JLabel("Part Name :");
JLabel lblrate=new JLabel("Rate :");
JLabel lblqty=new JLabel("QTY :");
JLabel lblamt=new JLabel("Amount :");
JLabel lbltotal=new JLabel("Total purchase value :");

JTextField txtpino=new JTextField();
JTextField txtidate=new JTextField();
JTextField txtpnum=new JTextField();
JTextField txtgno=new JTextField();
JTextField txtgdate=new JTextField();
JTextField txtpfrom1=new JTextField();
JTextField txtpfrom2=new JTextField();
JTextField txtpfrom3=new JTextField();
JTextField txtcase=new JTextField();
JTextField txttname=new JTextField();
JTextField txtwt=new JTextField();
JTextField txtrate=new JTextField();
JTextField txtpartnm=new JTextField();
JTextField txtqty=new JTextField();
JTextField txtamt=new JTextField();
JTextField txttotal=new JTextField();

JComboBox compartno=new JComboBox();

JButton cmdadd=new JButton("ADD");
JButton cmddelete=new JButton("Delete");
JButton cmdcal=new JButton("calculate");
JButton cmdsave=new JButton("Save");
JButton cmdprint=new JButton("Print");
JButton cmdexit=new JButton("Exit");

purchasereturnfrm()
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

getContentPane().add(lblpino);
lblpino.setBounds(50,90,130,20);
getContentPane().add(txtpino);
txtpino.setBounds(180,90,150,20);
getContentPane().add(lblpfrom);
lblpfrom.setBounds(360,90,100,20);
getContentPane().add(txtpfrom1);
txtpfrom1.setBounds(460,90,250,20);

getContentPane().add(lblidate);
lblidate.setBounds(50,115,100,20);
getContentPane().add(txtidate);
txtidate.setBounds(180,115,150,20);
getContentPane().add(txtpfrom2);
txtpfrom2.setBounds(460,115,250,20);

getContentPane().add(lblpnum);
lblpnum.setBounds(50,140,100,20);
getContentPane().add(txtpnum);
txtpnum.setBounds(180,140,100,20);
getContentPane().add(txtpfrom3);
txtpfrom3.setBounds(460,140,250,20);

getContentPane().add(lblgno);
lblgno.setBounds(50,165,100,20);
getContentPane().add(txtgno);
txtgno.setBounds(180,165,100,20);
getContentPane().add(lblcase);
lblcase.setBounds(360,165,100,20);
getContentPane().add(txtcase);
txtcase.setBounds(460,165,100,20);
getContentPane().add(lblwt);
lblwt.setBounds(570,165,60,20);
getContentPane().add(txtwt);
txtwt.setBounds(630,165,80,20);

getContentPane().add(lblgdate);
lblgdate.setBounds(50,190,100,20);
getContentPane().add(txtgdate);
txtgdate.setBounds(180,190,100,20);
getContentPane().add(lbltname);
lbltname.setBounds(360,190,100,20);
getContentPane().add(txttname);
txttname.setBounds(460,190,250,20);

getContentPane().add(pl2);
pl2.setBounds(50,220,660,3);
pl2.setBackground(Color.red);

getContentPane().add(lblpartno);
lblpartno.setBounds(50,230,100,20);
getContentPane().add(compartno);
compartno.setBounds(50,255,150,20);

getContentPane().add(lblpartnm);
lblpartnm.setBounds(210,230,100,20);
getContentPane().add(txtpartnm);
txtpartnm.setBounds(210,255,150,20);

getContentPane().add(lblrate);
lblrate.setBounds(370,230,120,20);
getContentPane().add(txtrate);
txtrate.setBounds(370,255,120,20);

getContentPane().add(lblqty);
lblqty.setBounds(500,230,80,20);
getContentPane().add(txtqty);
txtqty.setBounds(500,255,100,20);

getContentPane().add(lblamt);
lblamt.setBounds(610,230,80,20);
getContentPane().add(txtamt);
txtamt.setBounds(610,255,100,20);

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
setTitle("Purchase Return Details");
setLocation(100,30);
setResizable(false);

cmdsave.setEnabled(false);
cmdprint.setEnabled(false);

cmdexit.addActionListener(this);
cmdadd.addActionListener(this);
cmddelete.addActionListener(this);
cmdsave.addActionListener(this);
cmdcal.addActionListener(this);
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
head=new String[5];
data=new Object[25][5];
head[0]="PART NO";
head[1]="PART NAME";
head[2]="RATE";
head[3]="QTY";
head[4]="Amount";
tab=new JTable(data,head);
tab.setRowHeight(20);

JScrollPane pane=new JScrollPane(tab);
tab.setFont(new Font("Arial",Font.BOLD,18));
getContentPane().add(pane);
pane.setBounds(50,330,660,100);

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
	tab.setValueAt(txtamt.getText(),i,4);

	int d=Integer.parseInt(txtamt.getText());
	sum=sum+d;
	txttotal.setText(String.valueOf(sum));
	i++;
		
		if(JOptionPane.showConfirmDialog(this,"Do you want to add more item !","Add more Item",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		{
		f=f+1;
		txtpartnm.setText("");
		txtrate.setText("");
		txtqty.setText("");
		txtamt.setText("");
		}
		else
		{
		compartno.setEnabled(false);
		txtpartnm.setEnabled(false);
		txtrate.setEnabled(false);
		txtqty.setEnabled(false);
		txtamt.setEnabled(false);
		cmdadd.setEnabled(false);
		cmdsave.setEnabled(true);
		cmdprint.setEnabled(true);
		}
	
	}
	
	if(e.getSource()==cmdsave)
	{
	String s1=txtpino.getText();
	String s2=txtidate.getText();
	String s3=txtpnum.getText();
	String s4=txtgno.getText();
	String s5=txtgdate.getText();
	String s6=txtpfrom1.getText();
	String s7=txtpfrom2.getText();
	String s8=txtpfrom3.getText();
	String s9=txtcase.getText();
	String s10=txtwt.getText();
	String s11=txttname.getText();
	String s12=txttotal.getText();
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
	Statement stat=con.createStatement();
	
	String sql="insert into purchasereturn values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"','"+s11+"','"+s12+"')";
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
		y=z-y;

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


	}
	
	}

	if(e.getSource()==cmddelete)
	{i=i-1;
	tab.setValueAt("",i,0);
	tab.setValueAt("",i,1);
	tab.setValueAt("",i,2);
	tab.setValueAt("",i,3);
	tab.setValueAt("",i,4);
	}
	
	if(e.getSource()==cmdcal)
	{
	int x=Integer.parseInt(txtrate.getText())*Integer.parseInt(txtqty.getText());
	txtamt.setText(String.valueOf(x));
	}


}


public static void main(String ar[])
{
new purchasereturnfrm();
}
} 
