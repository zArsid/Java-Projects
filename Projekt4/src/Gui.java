import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gui extends JFrame
{
    private  JButton Fillo;
    private  JPanel Panel;
    private  JLabel Output;
    float num=5; //Numri default

    Gui(){
        super("THREADED GUI");

        Panel = new JPanel();
        Output = new JLabel("Vendos numrin");
        JTextField tf=new JTextField();
        tf.setHorizontalAlignment(SwingConstants.CENTER);



        Fillo = new JButton("Fillo");
        Fillo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               try {
                   Integer.parseInt(tf.getText());
                   tf.setVisible(false);
                   num= Float.parseFloat(tf.getText());
                   add(Panel, BorderLayout.CENTER);
                   Function Func = new Function();
                   Col col = new Col();
               }catch (NumberFormatException e){
                   JOptionPane.showMessageDialog(rootPane,"Vendosni nje numer!");
               }

            }});


        Output.setHorizontalAlignment(JLabel.CENTER);
        add(Output, BorderLayout.NORTH);
        add(tf);
        add(Fillo, BorderLayout.SOUTH);

    }



     class Function extends Thread {
        Function(){
            start();
        }
        public void run(){
            try {
                System.out.println("Calculating");

                for(int i = 1; i < 100; i++) {
                    Output.setText(i+"*2+"+num+"*"+i+"/2 = "+ (i*2+num*i/2));
                    Thread.sleep(4000);
                }

            }catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
            System.out.println("Thread ending");
        }
    }

     class Col extends Thread {
        Col(){
            start();
        }
        public void run() {
            try {
                while(true) {
                    //Gjeneron nje ngjyre random dhe e vendos backgroundit
                    Panel.setBackground(new Color((int)(Math.random() * 0x1000000)));
                    Panel.repaint();
                    Thread.sleep(8000);
                }

            }catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
            System.out.println("Thread ending");
        }}

}