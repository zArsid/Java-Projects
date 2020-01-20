import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.xml.crypto.Data;
import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection con=null;
    private Statement stmt=null;

    //Creates a connection with the provided info
    Database(String DBNAME,String USER,String PASS){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBNAME,USER,PASS);
            stmt=con.createStatement();
        }catch (Exception e){ e.printStackTrace(); }
    }





    public String getAllData(String table){
        String query ="select * from "+table;
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
        }catch (Exception e){e.printStackTrace();}
        return results;
    }

    public String[] getFields(String table,int index) {
        String query = "describe " + table;
        List<String> fields = new ArrayList<>();
        String[] rfields = new String[0];
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                fields.add(rs.getString(index));
            }

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

    public void Search(String table,String field,String value){
        String query="select * from "+table+" where "+field+" = "+value;
    //statement.executeQuery(query);
    }


    public void QueryDB(String query) {

        try{
            stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()) { }
        }catch(Exception e){ e.printStackTrace(); }

    }
    public void CloseCon(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
