import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    int Port=3306;
    String DBNAME="";
    String User="root";
    String Pass="";
    String table="";

    Database DB=new Database(Port,DBNAME,User,Pass);
    public static void main(String[] args){
        GUI JG=new GUI();
    }


    GUI(){
        //Frame
        JFrame frame = new JFrame("DATABASE READ WRITER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        //Krijome panelin qe do mbaje komponentet
        JPanel panel = new JPanel();



        //*================================Paneli_Poshte================================*//
        JLabel klabel = new JLabel("Kriteri:");
        panel.add(klabel);           //Shtojme ne panel

        //ComboBox qe merr te dhenat rreth tabeles
        JComboBox Kritercombo=new JComboBox(DB.getFields(table,1)); //Popullojme combobox me te dhena nga database
        panel.add(Kritercombo);     //Shtojme ne panel

        //TextField ku do vendosim termat e kerkimit
        JTextField Termat = new JTextField(20);
        Dimension dim =new Dimension(15,25) ;
        Termat.setPreferredSize(dim);   //I japim dimensione per paraqitje
        panel.add(Termat);       //Shtojme ne panel

        //Butoni i kerkimit qe ekzekuton query per kerkimin me termat ne database
        JButton searchB = new JButton("Kerko");
        panel.add(searchB);         //Shtojme ne panel

        //Butoni i fshirjes qe ekzekuton query per fshirjen me termat ne database
        JButton deleteB = new JButton("Fshi");
        panel.add(deleteB);      //Shtojme ne panel
        deleteB.setVisible(false);
        //Vendosim nje border panelit
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        //*==========================================================================*//


        //Text Area ne qender
        JTextArea TQender = new JTextArea();
        frame.getContentPane().add(BorderLayout.CENTER, TQender); //E pozicionojme ne qender


        //*============================Menuja===============================*//
        //Pjesa e siperme (Menuja)
        JMenuBar menuBar=new JMenuBar(); //Krijojme nje menu
        frame.setJMenuBar(menuBar); //Vendosim menune ne frame

        //Krijojme disa butona
        JButton m1=new JButton("Afisho");
        m1.setToolTipText("Afisho Rekordet");

        JButton m2=new JButton("Shto");
        m2.setToolTipText("Shto Rekord ne DB");

        JButton m3=new JButton("Fshi");
        m3.setToolTipText("Fshij Rekord nga DB");

        //Shtojme butonat ne menu
        menuBar.add(m1);
        menuBar.add(m2);
        menuBar.add(m3);
        //*=================================================================*//




        //Eventet
        m1.addActionListener(new ActionListener() {
            //Pret per nje veprim mbi butonin
            public void actionPerformed(ActionEvent e) {
                //Kur veprimi kryhet ekzekutohet nje query qe merr te dhenat ne db
                String res= DB.getAllData(table);
                TQender.setText(res);
            }
        });
        m2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DB.Add(table);
            }
        });
        m3.addActionListener(new ActionListener() {  //Ndryshon gjendjen e butonit Kerko /Fshi
            public void actionPerformed(ActionEvent e){
                //Vendos butonin per fshirjen e rekordeve dhe fshe butonin e kerkimit
                if(m3.getText()=="Fshi"){
                    deleteB.setVisible(true);
                    searchB.setVisible(false);
                    m3.setText("Kerko");
                }
                //Vendos butonin per kerkimin e rekordeve dhe fshe butonin e fshirjes
                else {
                    deleteB.setVisible(false);
                    searchB.setVisible(true);
                    m3.setText("Fshi");
                }
            }
        });

        searchB.addActionListener(new ActionListener() {
            //Pret per nje veprim mbi butonin
            public void actionPerformed(ActionEvent e) {
                //Ekzekuton query per kerkimit e rekordit me termat
                String res=DB.Search(table,Kritercombo.getSelectedItem().toString(),Termat.getText().toString());
                TQender.setText(res);
            }
        });
        deleteB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Ekzekuton query per fshirjen e rekordit
                DB.Delete(table,Kritercombo.getSelectedItem().toString(),Termat.getText());
                String res= DB.getAllData(table);
                TQender.setText("Tabela e re:\n"+res);
            }
        });


        frame.setVisible(true);
    }
}
