import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.sql.*;
class report extends JFrame implements ActionListener
{
JPanel pl=new JPanel();
JLabel lbltitle=new JLabel("REPORT GENERATION");
JLabel lbldate=new JLabel("Select date :");
JTextField txt1=new JTextField();
JButton cmdds=new JButton("Daily Sheet");
JButton cmdexit=new JButton("Exit");
Border br=null;
report()
{
setLayout(null);
getContentPane().add(pl);
pl.setBounds(130,25,300,35);
pl.add(lbltitle);
br=BorderFactory.createRaisedBevelBorder();
pl.setBorder(br);
Font f=new Font("Times new Roman",Font.BOLD,24);
lbltitle.setFont(f);
lbltitle.setForeground(Color.red);

getContentPane().add(lbldate);
lbldate.setBounds(160,120,80,25);
getContentPane().add(txt1);
txt1.setBounds(260,120,150,25);

getContentPane().add(cmdds);
cmdds.setBounds(180,200,100,25);
getContentPane().add(cmdexit);
cmdexit.setBounds(310,200,75,25);

setSize(600,300);
setVisible(true);
setTitle("Report generation");
setLocation(200,125);
setResizable(false);

cmdds.setMnemonic('D');
cmdexit.setMnemonic('E');

cmdds.addActionListener(this);
cmdexit.addActionListener(this);

}

public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==cmdexit)
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	dispose();
	}

	if(e.getSource()==cmdds)
	{
	

	String str1=txt1.getText();
	
		try
		{
	
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:pratap","scott","tiger");
		Statement stat=con.createStatement();

		String s1="Select * from motorcycleretail where cdate='"+str1+"'";
		ResultSet rs=stat.executeQuery(s1);

		int col=rs.getMetaData().getColumnCount();
		Object head[]=new  Object[col];
		for(int i=0;i<col;i++)
		{
				head[i]=rs.getMetaData().getColumnLabel(i+1);
		}
		int row=0;
		rs=stat.executeQuery(s1);
		while(rs.next())row++;
		Object data[][]=new Object[row][col];
		rs=stat.executeQuery(s1);
		for(int i=0;rs.next();i++)
		{
			for(int j=0;j<col;j++)
			{
				data[i][j]=rs.getString(j+1);
			}
		}
		JTable tab=new JTable(data,head);
		JScrollPane pane=new JScrollPane(tab);
		JDialog d=new JDialog(this,"");
		d.getContentPane().add(pane,"Center");
		d.setSize(500,400);
		d.setLocation(250,200);
		d.setVisible(true);
		}catch(Exception ex){System.out.println(ex);;}

	}

}
public static void main(String ar[])
{
new report();
}
}
