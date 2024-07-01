import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class createditfrm extends JFrame implements ActionListener
{

String menu[]={"Create","Edit","Tools","Help"};
String create[]={"New Spare Parts in part master","Add new Parts in stock"};
String edit[]={"Stock Finder","Add new Rate"};
String tools[]={"calculator","Editor"};
String help[]={"Under development"};

JMenu mn[]=new JMenu[4];
JMenuItem createmn[]=new JMenuItem[2];
JMenuItem editmn[]=new JMenuItem[2];
JMenuItem toolsmn[]=new JMenuItem[2];
JMenuItem helpmn[]=new JMenuItem[1];
JMenuBar bar=new JMenuBar();

createditfrm()
{

setJMenuBar(bar);
  for(int i=0;i<menu.length;i++)
  {
    mn[i]=new JMenu(menu[i]);
    bar.add(mn[i]);
  }
	for(int i=0;i<create.length;i++)
  	{
	createmn[i]=new JMenuItem(create[i]);
	mn[0].add(createmn[i]);
	}
	
	for(int i=0;i<edit.length;i++)
  	{
	editmn[i]=new JMenuItem(edit[i]);
	mn[1].add(editmn[i]);
	}

	for(int i=0;i<tools.length;i++)
  	{
	toolsmn[i]=new JMenuItem(tools[i]);
	mn[2].add(toolsmn[i]);
	}

	for(int i=0;i<help.length;i++)
  	{
	helpmn[i]=new JMenuItem(help[i]);
	mn[3].add(helpmn[i]);
	}

setTitle("SPARE PARTS MAINTENENCE SYSTEM");	
setLocation(200,100);
setSize(600,350);
setVisible(true);
setResizable(false);
createmn[0].addActionListener(this);
createmn[1].addActionListener(this);
editmn[0].addActionListener(this);
editmn[1].addActionListener(this);
toolsmn[0].addActionListener(this);
toolsmn[1].addActionListener(this);
helpmn[0].addActionListener(this);

}

public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==createmn[0])
	{
	new partmaster();
	}
	if(e.getSource()==createmn[1])
	{
	new stock();
	}
	if(e.getSource()==editmn[0])
	{
	new stockfinder();
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
new createditfrm();
}

}
