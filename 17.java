import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class calculator implements ActionListener
{
	
	String menu[]={"Edit","View","Help"};
	String edit[]={"Copy","Paste"};
	String view[]={"Standard","Scientific","Digit grouping"};
	String help[]={"Help topic","about Calculator"};
   	
	String btn="789/456*123+0.-=";

   	JTextField t1=new JTextField(15);
	JButton on=new JButton("On");
	JButton clr=new JButton("CLR");
	JButton ext=new JButton("Exit");
  	 JPanel p1=new JPanel();
   	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
   	double Preval=0,Curval=0;
	
	JMenu mn[]=new JMenu[3];
	JMenuItem mnedit[]=new JMenuItem[2];
	JMenuItem mnview[]=new JMenuItem[3];
	JMenuItem mnhelp[]=new JMenuItem[2];
	
	JMenuBar bar=new JMenuBar();
   	
	JButton b[]=new JButton[16];
	int onFlag=0;
	String op=null;
	int opFlag=0;
	int test=0;
	int dotFlag=0;
	JDialog dg=null;

   calculator()
   {	
	
	dg=new JDialog(new JFrame(),"Calculator");
	dg.setLocation(350,250);
	//dg.getContentPane().add(p3,"North");
	
	p3.add(bar);
	/*setJMenuBar(bar);*/
	for(int i=0;i<menu.length;i++)
	{
		mn[i]=new JMenu(menu[i]);
		bar.add(mn[i]);
	}

	for(int i=0;i<edit.length;i++)
	{
		mnedit[i]=new JMenuItem(edit[i]);
		mn[0].add(mnedit[i]);
		if(i==0)
		{
			mn[0].addSeparator();
		}
	}

	for(int i=0;i<view.length;i++)
	{
		mnview[i]=new JMenuItem(view[i]);
		mn[1].add(mnview[i]);
	}
	for(int i=0;i<help.length;i++)
	{
		mnhelp[i]=new JMenuItem(help[i]);
		mn[2].add(mnhelp[i]);
	}

	/*dg=new JDialog(new JFrame(),"Calculator");*/
      	p1.add(t1);
	/*t1.setFont(new Font("",Font.BOLD,15));
	t1.setForeground(Color.blue);*/
      	t1.setHorizontalAlignment(JTextField.RIGHT);
	
      p1.add(on);p1.add(clr);p1.add(ext);
      p2.setLayout(new GridLayout(4,4,0,0));
      dg.getContentPane().add(p1,"Center");
      dg.getContentPane().add(p2,"South");
	for(int i=0;i<btn.length();i++)
	{
	b[i]=new JButton(String.valueOf(btn.charAt(i)));
	p2.add(b[i]);
	b[i].addActionListener(this);
	}

	on.addActionListener(this);
	clr.addActionListener(this);
	ext.addActionListener(this);
	dg.setSize(200,250);
        dg.setVisible(true);
       dg.setResizable(false);
   }

 public void actionPerformed(ActionEvent e)
 {
	try{
	if(e.getActionCommand().equals("="))
	{
		test=1;
		if(op.equals("+"))
		{

				Preval=Curval+Preval;
				Curval=Preval;
				t1.setText(String.valueOf(Preval));
				test=1;
				dotFlag=0;
		}
		if(op.equals("-"))
		{

				Preval=Preval-Curval;
				Curval=Preval;
				t1.setText(String.valueOf(Preval));
				test=1;
				dotFlag=0;
		}

		if(op.equals("/"))
		{

				Preval=Preval/Curval;
				Curval=Preval;
				t1.setText(String.valueOf(Preval));
				test=1;
				dotFlag=0;
		}
		if(op.equals("*"))
		{

				Preval=Curval*Preval;
				Curval=Preval;
				t1.setText(String.valueOf(Preval));
				test=1;
		}

	}
	if(e.getSource()==on)
	{
		Preval=0;
		Curval=0;
		opFlag=0;
		dotFlag=0;
		test=0;
		if(onFlag==0)
		{
			t1.setText("0");
			onFlag=1;

		}
		else
		{
			t1.setText("");
			onFlag=0;
		}
	}
	
	if(e.getSource()==ext)
	{
			
			dg.dispose();
	}
	if(e.getSource()==clr)
	{

		t1.setText("0");
		if(onFlag==1)onFlag=1;
	}
	if(e.getActionCommand().equals("+"))
	{
		if(onFlag==1)
		{
			if(opFlag==1&&test==0)
			{
				Preval=Curval+Preval;
				Curval=Preval;
				t1.setText(String.valueOf(Preval));
				test=1;
			}
			else
			{
				Preval=Curval;
				t1.setText("0");
				opFlag=1;
				test=0;
			}
			op="+";
		}

	}
	if(e.getActionCommand().equals("*"))
	{
		if(onFlag==1)
		{
			if(opFlag==1&&test==0)
			{
				Preval=Curval*Preval;
				Curval=Preval;
				t1.setText(String.valueOf(Preval));
				test=1;
			}
			else
			{
				Preval=Curval;
				t1.setText("0");
				opFlag=1;
				test=0;
			}
			op="*";
		}

	}

	if(e.getActionCommand().equals("-"))
	{
		if(onFlag==1)
		{
			if(opFlag==1&&test==0)
			{
				Preval=Preval-Curval;
				Curval=Preval;
				t1.setText(String.valueOf(Preval));
				test=1;
			}
			else
			{
				Preval=Curval;
				t1.setText("0");
				opFlag=1;
				test=0;
			}
			op="-";
		}

	}

	if(e.getActionCommand().equals("/"))
	{
		if(onFlag==1)
		{
			if(opFlag==1&&test==0)
			{
				Preval=Preval/Curval;
				Curval=Preval;
				t1.setText(String.valueOf(Preval));
				test=1;
			}
			else
			{
				Preval=Curval;
				t1.setText("0");
				opFlag=1;
				test=0;
			}
			op="/";
		}

	}
	if((Integer.parseInt(String.valueOf(e.getActionCommand().charAt(0)))>=0&&Integer.parseInt(String.valueOf(e.getActionCommand().charAt(0)))<=9))
	{

		if(onFlag==1)
		{
			if(t1.getText().equals("0"))
			{
				t1.setText(String.valueOf(e.getActionCommand().charAt(0)));
			}
			else
			{
			if(test==1)
			{
			t1.setText("");
			test=0;
			}
			t1.setText(t1.getText()+e.getActionCommand().charAt(0));

			}
			Curval=new Double(t1.getText()).doubleValue();


		}

	}

	}catch(Exception e1){}
  }

public static void main (String as[])
{

  new calculator();
}


}
