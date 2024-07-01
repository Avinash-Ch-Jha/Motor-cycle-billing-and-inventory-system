import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
class mainfrm extends JFrame implements ActionListener
{
JButton cmdmotor=new JButton("MOTORCYCLE");
JButton cmdspare=new JButton("SPARE PARTS");
JButton cmdhelp=new JButton("HELP");
JButton cmdexit=new JButton("EXIT");
JButton cmdimg=new JButton(new ImageIcon("F:/mbis/showroom.jpg"));
mainfrm()
{
setLayout(null);
getContentPane().add(cmdimg);
cmdimg.setBounds(50,50,700,400);
getContentPane().add(cmdmotor);
cmdmotor.setBounds(100,500,150,75);
getContentPane().add(cmdspare);
cmdspare.setBounds(260,500,150,75);
getContentPane().add(cmdhelp);
cmdhelp.setBounds(420,500,150,75);
getContentPane().add(cmdexit);
cmdexit.setBounds(580,500,150,75);
cmdmotor.setMnemonic('M');
cmdspare.setMnemonic('S');
cmdhelp.setMnemonic('H');
cmdexit.setMnemonic('E');
setSize(800,650);
setVisible(true);
setTitle("MOTORCYCLE BILLING & INVENTORY SYSTEM");
setLocation(100,50);
setResizable(false);
cmdmotor.addActionListener(this);
cmdexit.addActionListener(this);
cmdspare.addActionListener(this);
cmdhelp.addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==cmdmotor)
	{
	new motormodule();
	}

	if(e.getSource()==cmdexit)
	{
	if(JOptionPane.showConfirmDialog(this,"Are you really want to exit !","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
	dispose();
	}
	if(e.getSource()==cmdspare)
	{
	new sparepartfrm();
	}
	if(e.getSource()==cmdhelp)
	{
	new aboutsoftware();
	}
}
public static void main(String ar[])
{
new mainfrm();
}
}
