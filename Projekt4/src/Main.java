import javax.swing.JFrame;

public class Main {
    public static void main(String args[]) {
        Gui gui = new Gui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(300, 300);
        gui.setVisible(true);
    }

}