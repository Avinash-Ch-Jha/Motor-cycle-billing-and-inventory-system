import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
class notepad extends JFrame implements ActionListener,KeyListener
{

JPanel p=new JPanel();
JTextPane tp=new JTextPane();
JMenu file=new JMenu("File");
JMenu edit=new JMenu("Edit");
JMenu format=new JMenu("Format");
JMenu help=new JMenu("Help");

JMenuItem newmi=new JMenuItem("New");
JMenuItem openmi=new JMenuItem("Open");
JMenuItem savemi=new JMenuItem("Save");
JMenuItem colormi=new JMenuItem("set Color");
JMenuItem exitmi=new JMenuItem("Exit");

JMenuItem datemi=new JMenuItem("Date/Time");

JMenuItem fontcolormi=new JMenuItem("Font Color...");
JMenuItem backcolormi=new JMenuItem("Background");
JMenuItem fontsizemi=new JMenuItem("Font Size...");	

JMenuItem helpmi=new JMenuItem("About Editor");
    
JMenuBar bar=new JMenuBar();
JFileChooser fc=new JFileChooser();
JColorChooser cc=new JColorChooser();
String br="";
String fileName="";

notepad()
{
    
    setJMenuBar(bar);
    bar.add(file);
    bar.add(edit);
    bar.add(format);
    bar.add(help);				
    bar.add(backcolormi);
    file.add(newmi);
    file.add(openmi);
    file.add(savemi);
    file.addSeparator();
    file.add(colormi);
    file.addSeparator();
    file.add(exitmi);
    edit.add(datemi);
    format.add(fontcolormi);
    format.add(fontsizemi);
    format.add(backcolormi);
    help.add(helpmi);
    

    getContentPane().add(new JScrollPane(tp),"Center");
    Toolkit tk=Toolkit.getDefaultToolkit();
    Dimension d=tk.getScreenSize();
    setSize(d.width,d.height);
    setVisible(true);
    setTitle("Test Pad");
    backcolormi.addActionListener(this);
    fontsizemi.addActionListener(this);
    openmi.addActionListener(this);
    savemi.addActionListener(this);
    colormi.addActionListener(this);
    newmi.addActionListener(this);
    exitmi.addActionListener(this);
    tp.addKeyListener(this);

}
public void actionPerformed(ActionEvent e)
{
    if(e.getSource()==newmi)tp.setText("");
    if(e.getSource()==backcolormi)
    {
    tp.setBackground(Color.blue);

    }
    
    if(e.getSource()==openmi)
    {
        try
        {
        fc.showOpenDialog(this);
        FileReader f=new FileReader(fc.getSelectedFile());
        StringBuffer sb=new StringBuffer();
        int ch=0;
        while((ch=f.read())!=-1)
        {
            sb.append(String.valueOf((char)ch));
        }
        f.close();
        tp.setText(String.valueOf(sb));
        }catch(Exception e2){}

    }
    if(e.getSource()==savemi)
    {
            if(fileName=="")dataSave();
            else
            ChangeableDataSave();
}
    if(e.getSource()==colormi)
    {
        
        JDialog d=JColorChooser.createDialog(this,"our Color",true,cc,new notepad(),new notepad());
        
        dispose();
        d.setVisible(true);
        tp.setForeground(cc.getColor());

    }
    if(e.getSource()==exitmi)
    {
        if(br=="")
            { 
                dispose();
            }
        else
        {
                int type=JOptionPane.showConfirmDialog(this,"Do you want to save the changes data?","Confirmation",JOptionPane.OK_CANCEL_OPTION);
                if(type==JOptionPane.OK_OPTION)
                {
                        if(fileName=="")
                        {
                            dataSave();System.exit(1);
                        }
                        else
                        {
                                ChangeableDataSave();System.exit(1);
                        }

                }
                else
                {
                //new collegemain();
                dispose();
                }
        }
}

}
public void keyTyped(KeyEvent k1)
{
    char ch=k1.getKeyChar();
    //br.append(String.valueOf(ch));
    br=br+(String.valueOf((char)ch));

}
public void keyPressed(KeyEvent k2){	}
public void keyReleased(KeyEvent k3){	}
public void dataSave()
{
    try{
                fc.showSaveDialog(this);
                String text=tp.getText();
                File fname=fc.getSelectedFile();
                FileWriter f=new FileWriter(fname);
                for(int i=0;i<text.length();i++)
                {
                    char ch=text.charAt(i);
                    f.write(ch);
                }
                setTitle(String.valueOf(fname));
                fileName=String.valueOf(fname);
                br="";
                f.close();
                }catch(Exception e1){}

}
public void ChangeableDataSave()
    {
        try{
                    String text=tp.getText();
                    FileWriter f=new FileWriter(fileName);
                    for(int i=0;i<text.length();i++)
                    {
                        char ch=text.charAt(i);
                        f.write(ch);
                    }
                    br="";
                    //setTitle(String.valueOf(fname));

                    f.close();

                    }catch(Exception e1){}

}


public static void main(String as[])
{
    new notepad();
}
}
