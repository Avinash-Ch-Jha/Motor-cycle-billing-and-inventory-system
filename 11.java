import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class sparepartfrm extends JFrame implements ActionListener
{
JButton cmdinventory=new JButton("Inventory");
JButton cmdaccount=new JButton("Account");
JButton cmdcreate=new JButton("CreateEdit");
JButton cmdreport=new JButton("Report");
JButton cmdexit=new JButton("EXIT");
JButton cmdimg=new JButton(new ImageIcon("F:/mbis/yamaha.jpg"));

String menu[]={"Master","Tools","Help"};
  String master[]={"Inventory Module","Accounts Module","Create/Update","Report Module","Exit"};
  String tools[]={"calculator","Editor"};
  String help[]={"About Software","Contact Us"};
sparepartfrm()
{
setLayout(null);
getContentPane().add(cmdimg);
cmdimg.setBounds(50,50,700,400);

setJMenuBar(bar);
for(int i=0;i<menu.length;i++)
  {
    mn[i]=new JMenu(menu[i]);
    bar.add(mn[i]);
  }

for(int i=0;i<master.length;i++)
  {
    mastermn[i]=new JMenuItem(master[i]);
    mn[0].add(mastermn[i]);
	if(i==3)
	{
	  mn[0].addSeparator();
	}
  }

for(int i=0;i<tools.length;i++)
  {
    toolsmn[i]=new JMenuItem(tools[i]);
    mn[1].add(toolsmn[i]);
}

for(int i=0;i<help.length;i++)
  {
    helpmn[i]=new JMenuItem(help[i]);
    mn[2].add(helpmn[i]);
	if(i==0)
	{
	mn[2].addSeparator();	
	}
  }


getContentPane().add(cmdinventory);
cmdinventory.setBounds(50,500,130,60);
getContentPane().add(cmdaccount);
cmdaccount.setBounds(190,500,130,60);
getContentPane().add(cmdcreate);
cmdcreate.setBounds(330,500,130,60);
getContentPane().add(cmdreport);
cmdreport.setBounds(470,500,130,60);
setSize(800,650);
setVisible(true);
setTitle("SPARE PARTS MAINTANCE SYSTEM");
setLocation(100,50);
setResizable(false);
cmdexit.addActionListener(this);
cmdinventory.addActionListener(this);
cmdcreate.addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==cmdexit||e.getSource()==mastermn[4])
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	dispose();
	}

	if(e.getSource()==cmdinventory||e.getSource()==mastermn[0])
	{
	new spareinventoryfrm();
	}
	if(e.getSource()==cmdaccount||e.getSource()==mastermn[1])
	{
	new accountfrm();
	}

	if(e.getSource()==cmdcreate||e.getSource()==mastermn[2])
	{
	new createditfrm();
	}

	if(e.getSource()==cmdreport||e.getSource()==mastermn[3])
	{
	new dailyreportfrm();
	}

	if(e.getSource()==toolsmn[1])
	{
	new notepad();
	}

	if(e.getSource()==toolsmn[0])
	{
	new calculator();
	}

}

public static void main(String ar[])
{
new sparepartfrm();
}
}
