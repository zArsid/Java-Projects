import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection con=null;
    private Statement stmt=null;

    //Krijon lidhje me databazen duke perdorur parametrat e dhene
    Database(int PORT,String DBNAME,String USER,String PASS){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:"+PORT+"/"+DBNAME,USER,PASS);
            stmt=con.createStatement();
        }catch (Exception e){ e.printStackTrace(); }
    }



    //Ekzekuton nje query qe merr te gjithe te dhenat nga tabela
    public String getAllData(String table){
        String query ="SELECT * FROM "+table;
        System.out.println(query);
        String results="";
        try {
            ResultSet rs=stmt.executeQuery(query);
            //Merr emrat e kolonave nga tabela
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                results+=rs.getMetaData().getColumnName(i)+"\t";
            }
            results+="\n";

            while (rs.next()) {
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    results+=rs.getObject(i)+"\t";
                }
                results+="\n";
            }
        }catch (Exception e){e.printStackTrace();}
        return results;
    }

    //Merr results nga kolonat e describe table qe nevojiten per data validation and search
    public String[] getFields(String table,int index) {
        String query = "DESCRIBE " + table;
        System.out.println(query);
        List<String> fields = new ArrayList<>();
        String[] rfields = new String[0];
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                fields.add(rs.getString(index));
            }

            //Konverton listen e fields ne array qe i nevojitet Combobox
            rfields = new String[fields.size()];
            for (int i = 0; i < fields.size(); i++) {
                rfields[i] = fields.get(i);
            }
            fields.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rfields;
    }
    //Kerkon ne baze te kriterit
    public String Search(String table,String field,String value){
        String query="SELECT * FROM "+table+" WHERE "+field+" LIKE '%"+value+"%'";
        System.out.println(query);
        String results="";
        try {
            ResultSet rs=stmt.executeQuery(query);

            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                results+=rs.getMetaData().getColumnName(i)+"\t";
            }
            results+="\n";

            while (rs.next()) {
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    results+=rs.getObject(i)+"\t";
                }
                results+="\n";
            }

        } catch (Exception e) { e.printStackTrace(); }
    return results;
    }
    public void Delete(String table,String field,String value){
        String query="DELETE FROM "+table+" WHERE "+field+" LIKE '"+value+"'";
        System.out.println(query);
        try { stmt.executeUpdate(query); } catch (Exception e) { e.printStackTrace(); }
    }
    public void Add(String table){
        String[] fields=getFields(table,1);
        String[] types=getFields(table,2);


        JFrame frame = new JFrame("Add new Record");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel=new JPanel();
        frame.add(panel);
        List<JTextField> tfields=new ArrayList<JTextField>();
        for(int i=0;i<fields.length;i++){
            panel.add(new JLabel(fields[i]));
            JTextField tf=new JTextField(15);
            tfields.add(tf); //Ruaj referencen e textfield
            panel.add(tf);
        }


        panel.setLayout(new GridLayout(8,2));

        JButton ABtn=new JButton("Shto");
        panel.add(ABtn);


        ABtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query="";
                query+="INSERT INTO "+table+ " VALUES(";
                for(int i=0;i<tfields.size();i++){ //TODO Validate data
                    System.out.println(tfields.get(i).getText());
                    query+="'"+tfields.get(i).getText()+"'";
                    if(i<tfields.size()-1){ query+=","; }
                }
                query+=");";
                try {
                    System.out.println(query);
                    stmt.executeUpdate(query);
                }catch (Exception err){
                    err.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Vendosi te dhenat ne formatin e duhur!");
                }

            }
        });

        frame.setVisible(true);

    }

}
