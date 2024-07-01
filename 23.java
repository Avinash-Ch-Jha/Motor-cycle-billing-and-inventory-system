import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class aboutsoftware extends JFrame implements ActionListener
{

JTabbedPane tp=new JTabbedPane(JTabbedPane.TOP);
JLabel l1=new JLabel(".................................  MotorCycle Module  ..................................");
JLabel l11=new JLabel("                                                                         ");
JLabel l2=new JLabel("1. This module (software) maintain the retail invoice                     ");
JLabel l3=new JLabel("  i.e.save customer data and also update bike stock.                      ");
JLabel l4=new JLabel("                                                                          ");
JLabel l5=new JLabel("2. Store bike details like quantity ,name ,unique id ,etc .               ");
JLabel l6=new JLabel("                                                                          ");
JLabel l7=new JLabel("3. Store bike price corresponding to the bike and                         ");
JLabel l8=new JLabel("                     many more.                                           ");
JLabel l9=new JLabel("                                                                          ");
JLabel l14=new JLabel("                                                                                      ");
JLabel l15=new JLabel("1. This module maintain the spare related data.                           ");
JLabel l16=new JLabel("                                                                          ");
JLabel l17=new JLabel("2.Spare which are purchase from market can store                          ");
JLabel l18=new JLabel("  as well as update as desire data can only save.                         ");
JLabel l19=new JLabel("                                                                          ");
JLabel l20=new JLabel("3. Tax related information can also be generted.				 ");
JLabel l21=new JLabel("										 ");
JLabel l22=new JLabel("4. Generate Daily sheet for retail invoice , tax invoice etc.		 ");
JLabel l25=new JLabel("...................................  Master Help  ....................................");
JLabel l26=new JLabel("											    ");
JLabel l27=new JLabel("                                                       1. calculator module works general mathematical calculation .             ");          
JLabel l28=new JLabel("					  					 ");
JLabel l31=new JLabel("                                            3. Login window just take username and passward to enter in 		 ");
JLabel l32=new JLabel("               software .						 ");
JLabel l33=new JLabel("									        ");

JLabel l34=new JLabel("                   ..................................  H/w & S/W Requirement  .....................................");
JLabel l35=new JLabel("											    ");
JLabel l36=new JLabel("		               Pentium IV, 512MB RAM, 60GB HDD,                            							");
JLabel l37=new JLabel("		                                                 Color Monitor			");
JLabel l38=new JLabel("	                                                         Java, Oracle,Window Xp					");

aboutsoftware()
{

getContentPane().add(tp,"Center");
tp.addTab("Motorcycle",p1);
tp.addTab("Spare parts",p2);
tp.addTab("Another help",p3);
tp.addTab("System Requirement",p4);
p1.add(l1);
p1.add(l11);
p1.add(l2);
p1.add(l3);
p1.add(l4);
p1.add(l5);
p1.add(l6);
p1.add(l7);
p1.add(l8);
p1.add(l9);
p1.add(l10);
p1.add(l12);
setSize(500,400);
setVisible(true);
setTitle("About Software");
setLocation(250,200);
setResizable(false);
}
public static void main(String ar[])
{
new aboutsoftware();
}
