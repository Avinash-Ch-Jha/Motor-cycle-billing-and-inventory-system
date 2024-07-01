import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.color.*;
class mbis  extends JFrame
{
JProgressBar bar=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
JLabel l1=new JLabel("MOTORCYCLE BILLING & INVENTORY SYSTEM");
JButton img=new JButton(new ImageIcon("F:/mbis/showroom.jpg"));
JLabel l5=new JLabel("Developed by :");
JLabel l6=new JLabel("VISHAL  KUMAR");
JLabel l7=new JLabel("REG NO  - 05010100399");
JLabel l8=new JLabel("Email :vishalhlp4u@gmail.com");
JLabel l9=new JLabel("TIIT");
JLabel l10=new JLabel("KATIHAR");
JLabel l11=new JLabel("Hardware Requirement :");
JLabel l12=new JLabel("PIV processor,512 MB RAM,");
JLabel l13=new JLabel("60 GB HDD,Color MOnitor");
JLabel l14=new JLabel("Software Requirement :");
JLabel l15=new JLabel("O.S.Window xp ");
JLabel l16=new JLabel("JAVA,Oracle 9i");
 mbis()
{
setLayout(null);
getContentPane().add(l1);
l1.setBounds(50,10,500,30);
Font f=new Font("Times New Roman",Font.BOLD,20);
l1.setFont(f);
getContentPane().add(img);
img.setBounds(20,60,530,330);
getContentPane().add(bar);
bar.setBounds(20,400,530,20);
getContentPane().add(l5);
l5.setBounds(350,430,200,20);
getContentPane().add(l11);
l11.setBounds(50,430,200,20);
getContentPane().add(l6);
l6.setBounds(350,450,200,20);
getContentPane().add(l12);
l12.setBounds(50,450,200,20);
getContentPane().add(l7);
l7.setBounds(350,470,300,20);
getContentPane().add(l13);
setSize(600,600);
setVisible(true);
setLocation(150,100);
setResizable(false);
bar.setStringPainted(true);
 for(int i=1;i<=100;i++)
 {
   bar.setValue(i);
	try
	{
  	Thread.sleep(50);
	}
	catch(Exception e)
	{
	}
	if(i==100)
	{
	dispose();
	new connectall();
	}
 }
}
public static void main(String ar[])
{
new mbis();
}
}