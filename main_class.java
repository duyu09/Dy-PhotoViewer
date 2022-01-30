import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class main_class
{
    public static void main(String argv[])
    {
        Container con=new Container();
        //con.setSize(form1.getSize());
        con.setLocation(0,0);
        JButton command1=new JButton("View");
        command1.setSize(70,30);
        command1.setLocation(10,10);
        JTextField text1=new JTextField();
        text1.setFont(new Font("微软雅黑",Font.PLAIN,20));
        String pa="";
        if(argv.length==1)  {pa="";}
        else
        {
            try{pa=argv[1];} catch (Exception e) {}
        }
        BackgroundPanel image1=new BackgroundPanel(new ImageIcon(pa).getImage());
        con.add(command1);
        con.add(text1);
        con.add(image1);
        winmain form1=new winmain();
        text1.setLocation(90,10);
        text1.setSize(form1.getWidth()-110,30);
        text1.setVisible(true);
        image1.setSize(form1.getWidth()-35,form1.getHeight()-100);
        ComponentListener listen1=new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                text1.setSize(form1.getWidth()-110,30);
                image1.setSize(form1.getWidth()-35,form1.getHeight()-100);
                //form1.validate();
            }
        };
        form1.addComponentListener(listen1);
        form1.add(con);
        form1.validate();
        ActionListener listen2=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                image1.setVisible(false);
                String path=text1.getText();
                File file=new File(path);
                if(!file.exists())
                {
                    JOptionPane.showMessageDialog(null, "File don't exist.", "Waring - Duyu Photo Viewer", JOptionPane.ERROR_MESSAGE);
                }
                Image temp=new ImageIcon(path).getImage();
                image1.image=temp;
                //form1.validate();
                image1.setVisible(true);
            }
        };
        command1.addActionListener(listen2);
    }
}
class BackgroundPanel extends JPanel
{
       Image image = null;
       BackgroundPanel(Image image)
       {
           this.image = image;
           setLocation(10,50);
       }
       protected void paintComponent(Graphics g)
       {
           g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
       }
}

class winmain extends JFrame
{
    winmain()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(750,600);
        setLocationRelativeTo(getOwner());
        setIconImage(new ImageIcon(".\\Duyu_Logo.jpg").getImage());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(750,600);
        setTitle("Duyu Photo Viewer");
    }
}