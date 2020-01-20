import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Buttons {
    public static void main(String[] args) {
        new JFrame("Different ways of creating JButtons") {{
            getContentPane().setLayout(new FlowLayout());


            JButton b1 = new JButton("Click");
            class MyActionListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Tickles!");
                }
            }
            b1.addActionListener(new MyActionListener());
            add(b1);


            JButton b2 = new JButton("Click");
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Tickles!");
                }
            });
            add(b2);


            add(new JButton("Click") {{
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Tickles!");
                    }
                });
            }});


            add(new JButton("Click") {{
                addActionListener(e -> System.out.println("Tickles!"));
            }});


            pack();
            setVisible(true);
        }};
    }
}