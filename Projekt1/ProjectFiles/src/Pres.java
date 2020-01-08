import sun.usagetracker.UsageTrackerClient;

import java.io.File;
import java.util.Scanner;

public class Pres {

    private static Scanner input= new Scanner(System.in);
    //Liston te gjithe presidentet
    public  static  void ListoTeGjithe(){
        String content=Main.LexoFile();
        System.out.println(content);
    }
    //Liston vetem nje president ne baze te id
    public static void ListoPresident(int id){
        //Merr te dhenat nga skedari
        String content=Main.LexoFile();
        //I ndan te dhenat ne rreshta
        String[] lines = content.split("\n");
        //Rreshtin e ndajm ne copeza sipas vendodhjes se presjeve
        String currLine=lines[id];
        String[] cLine=currLine.split(",");
        //Nqs copeza e pare e rreshtit permban idn printojme rreshtin
        if(cLine[0].contains(Integer.toString(id))){
            System.out.println(currLine);
        }
    }
    //Shtojme nje president te ri ne skedar
    public static void ShtoPresident(int id){
        System.out.print("Emer Mbiemer:");
        String emri=input.nextLine();
        System.out.print("Viti Lindjes:");
        int vitil=input.nextInt();
        System.out.print("Viti Vdekje:");
        int vitiv=input.nextInt();
        System.out.println("Mandati_");
        System.out.print("Nga:");
        String mandatif=input.next();
        System.out.println("Deri:");
        String mandativ=input.next();
        String mandati=mandatif+"_"+mandativ;
        System.out.print("Partia:");
        String partia=input.next();
        String res="\n"+id+","+emri+","+vitil+","+vitiv+","+mandati+","+partia;
        Main.ShtoNeFile(res);
        System.out.println("Presidenti u shtua:\n"+"ID,Emer Mbiemer,Viti Lindjes,Viti Vdekje,Mandati Nga_Deri,Partia"+res+"\n");
    }

    //Gjejme presidentin ne skedar ne baze te emrit
    public static void GjejPresident(String name){
        boolean Found=false;
        //Lexojme skedarin
            String content=Main.LexoFile();
        //E ndajme skedarin ne rreshta
        String[] lines = content.split("\n");
        //Kontrollojme rreshtat me rradhe nqs permbajne emrin e presidentit
        for(int i=1;i< lines.length;i++) {
            String currLine=lines[i];
            //Rreshtin e ndajme ne copeza
            String[] cLine=currLine.split(",");
            //Nqs copeza e dyte permban emrin printojme rreshtin
            if(cLine[1].contains(name)){
                System.out.println(currLine);
                Found=true;
            }
        }
    if(!Found){
        System.out.println("Presidenti nuk u gjet");
    }
    }
    //
    public static void FshijPresidentin(int id){
        //Lexojme skedarin
        String content=Main.LexoFile();
        //E ndajme skedarin ne rreshta
        String[] lines = content.split("\n");
        //Rreshti i I   Koka e skedarit ne rastin tone
        String newContent=lines[0]; //Header
        for(int i=1;i< lines.length;i++) {
            String currLine=lines[i];
            //Rreshtin e ndajme ne copeza
            String[] cLine=currLine.split(",");
            //Nqs copeza e pare permban id shtojme rreshtin
            if(!cLine[0].contains(Integer.toString(id))){
            newContent=newContent+"\n"+lines[i];
            }
            //Shtojme rreshtat ne nje skedar te ri
            Main.MbishkruajFile(newContent);
        }
    }
}
