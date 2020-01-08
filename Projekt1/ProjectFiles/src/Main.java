/*
Java 2, Informatike, Viti 3

madeby[0]= Leart Gogaj
madeby[1]= Arsid Meta

Projekti 1
Ky program lexon dhe modifikon skedarin UsaPresident.txt

*/
import java.io.*;
import java.util.Scanner;

public class Main {

    static File ff=new File("UsaPresident.txt");

    public static void main(String[] args) {

        Scanner input=new Scanner(System.in);
        while(true) {
            System.out.println("=========================\n" +
                    "1)Listo te gjithe\n" +
                    "2)Shto President\n" +
                    "3)Listo President (id)\n" +
                    "4)Gjej President (emer)\n" +
                    "5)Fshij President (id)\n" +
                    "6)Mbyll\n" +
                    "=========================");
            System.out.println(">>>");
            int zgjedhja=input.nextInt();
            switch (zgjedhja){
                case 1:
                    Pres.ListoTeGjithe();
                    break;
                case 2:
                    System.out.print("Jep nje ID:");
                    int id=input.nextInt();
                    Pres.ShtoPresident(id);
                    break;
                case 3:
                    System.out.print("ID e presidentit:");
                    int pid=input.nextInt();
                    Pres.ListoPresident(pid);
                    break;
                case 4:
                    System.out.print("Emri i presidentit:");
                    String emri=input.next();
                    Pres.GjejPresident(emri);
                    break;
                case 5:
                    System.out.print("ID e presidentit:");
                    int pidf=input.nextInt();
                    Pres.FshijPresidentin(pidf);
                    break;
                case 6:
                    System.out.println("U mbyll");
                    System.exit(0);
                    default:
                        System.out.println("Numer i gabuar");
            }
        }

    }

    //Lexon Skedarin dhe kthen te dhenat e skedarit
    public static String LexoFile(){
        String content="";
        try {
            FileReader fr = new FileReader(ff);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null)
            content=content+line+"\n";

        }catch (FileNotFoundException e){System.out.println("File mungon");}
        catch (IOException e){System.out.println("Nuk mund te lexojme filin");}
        return content;
    }
    //Shton te dhena ne skedar
    public static void ShtoNeFile(String text){
        try{
            FileWriter fw=new FileWriter(ff,true);
            fw.write(text);
            fw.close();
        }catch (IOException e){System.out.println("Nuk mund te shtojme ne file");}
    }
    public static void MbishkruajFile(String text){
        try{
            FileOutputStream os=new FileOutputStream(ff);
            byte[] stringBytes=text.getBytes();
            os.write(stringBytes);
            os.close();
        }catch(IOException e){System.out.println("Failed to write to file");}
    }



}

