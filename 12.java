import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class spareinventoryfrm extends JFrame implements ActionListener
{

String menu[]={"Purchase","Sales/Sales Return","Tools","Help"};
String purchase[]={"Purchase","Purchase Return"};
String Sales[]={"Estimate","Retail Invoice","Tax Invoice","Sales Return Retail Invoice"};
String tools[]={"calculator","Editor"};
String help[]={"about software","contact us"};
  JMenu mn[]=new JMenu[4];
  JMenuItem purchasemn[]=new JMenuItem[2];
  JMenuItem Salesmn[]=new JMenuItem[4];
  JMenuItem toolsmn[]=new JMenuItem[2];
  JMenuItem helpmn[]=new JMenuItem[2];
  JMenuBar bar=new JMenuBar();

spareinventoryfrm()
{

  setJMenuBar(bar);
  for(int i=0;i<menu.length;i++)
  {
    mn[i]=new JMenu(menu[i]);
    bar.add(mn[i]);
  }

	for(int i=0;i<purchase.length;i++)
  	{
	purchasemn[i]=new JMenuItem(purchase[i]);
	mn[0].add(purchasemn[i]);
	}

	for(int i=0;i<Sales.length;i++)
  	{
	Salesmn[i]=new JMenuItem(Sales[i]);
	mn[1].add(Salesmn[i]);
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
setLocation(100,100);
setSize(800,500);
setVisible(true);
setResizable(false);

purchasemn[0].addActionListener(this);
purchasemn[1].addActionListener(this);
Salesmn[0].addActionListener(this);
Salesmn[1].addActionListener(this);
Salesmn[2].addActionListener(this);
Salesmn[3].addActionListener(this);
toolsmn[0].addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==purchasemn[0])
	{
	new purchasefrm();
	}

	if(e.getSource()==purchasemn[1])
	{
	new purchasereturnfrm();
	}

	if(e.getSource()==Salesmn[0])
	{
	new estimatefrm();
	}

	if(e.getSource()==Salesmn[1])
	{
	new spareinvoicefrm();
	}

	if(e.getSource()==Salesmn[2])
	{
	new taxinvoicefrm();
	}
	
	if(e.getSource()==Salesmn[3])
	{
	new sparereturntaxinvoicefrm();
	}
	if(e.getSource()==helpmn[0])
	{
	}
	if(e.getSource()==helpmn[1])
	{
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
new spareinventoryfrm();
}

}
