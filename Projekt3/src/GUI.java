import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    public static void main(String[] args){
        GUI JG=new GUI();
    }

    Database DB=new Database("usapres","root","789456123");
    GUI(){
        //Frame
        JFrame frame = new JFrame("DATABASE READ WRITER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        //Krijome panelin qe do mbaje komponentet
        JPanel panel = new JPanel();

        //Krijme elementet poshte
        JTextArea searchBox=new JTextArea();
        panel.add(searchBox);




        //Krijojme komponentet
        JLabel kriteri = new JLabel("Kriteri:");

        //ComboBox qe merr te dhenat rreth tabeles
        JComboBox Kritercombo=new JComboBox(DB.getFields("presidents",1));

        //TextField ku do vendosim termat e kerkimit
        JTextField tf1 = new JTextField(20);
        Dimension dim =new Dimension(15,25) ;
        tf1.setPreferredSize(dim);

        //Butoni i kerkimit qe ekzekuton query per kerkimin me termat ne database
        JButton send = new JButton("Kerko");


        //Vendosim komponetet ne Panel
        panel.add(kriteri);
        panel.add(Kritercombo);
        panel.add(tf1);
        panel.add(send);

        // Text Area at the Center
        JTextArea ta = new JTextArea();



        //Krijojme nje menu
        JMenuBar menuBar=new JMenuBar();
        //Vendosim menune ne frame
        frame.setJMenuBar(menuBar);

        //Krijojme disa butona dhe i vendosim eventet
        JButton m1=new JButton("Afisho");
        m1.setToolTipText("Afisho Rekordet");
        m1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String res= DB.getAllData("presidents");
               ta.setText(res);
            }
        });
        JButton m3=new JButton("Shto");
        m3.setToolTipText("Shto Rekord ne DB");
        m3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //popup form
            }
        });
        JButton m4=new JButton("Fshi");
        m4.setToolTipText("Fshij Rekord nga DB");
        m4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //popup form
            }
        });
        //Shtojme elementet ne menu
        menuBar.add(m1);
        menuBar.add(m3);
        menuBar.add(m4);






        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);

        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);


    }
}
