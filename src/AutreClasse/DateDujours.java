package AutreClasse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class DateDujours {

    public static void main(String[] args) {

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //obtenir la date courante
        Date date = new Date();
        String  Date = format.format(date);
        System.out.println(Date);
        String Separateur = "/";
        String MaChaine = Date;
        StringTokenizer ST = new StringTokenizer(MaChaine, Separateur);
        int A= Integer.parseInt(ST.nextToken());
        int B = Integer.parseInt(ST.nextToken());
        int C = Integer.parseInt(ST.nextToken());

        System.out.println(A);
        System.out.println(B);
        System.out.println(C);

        String DateSaise = "05/01/2021";
        String Separateur2 = "/";
        StringTokenizer ST1 = new StringTokenizer(DateSaise, Separateur2);
        int D = Integer.parseInt(ST1.nextToken());
        int E = Integer.parseInt(ST1.nextToken());
        int F = Integer.parseInt(ST1.nextToken());
        System.out.println(D);
        System.out.println(E);
        System.out.println(F);

        if(F>C){
            System.out.println(DateSaise+" est plus grand que "+Date);
        }else if(F<C){
            System.out.println(DateSaise+" est plus petit que "+Date);
        }else if (F == C){
            if(E>B){
                System.out.println(DateSaise+" est plus grand que "+Date);
            }else if(E<B) {
                System.out.println(DateSaise + " est plus petit que " + Date);
            } else if (E == B) {
                if(D>A){
                    System.out.println(DateSaise+" est plus grand que "+Date);
                }else if(D<A) {
                    System.out.println(DateSaise + " est plus petit que " + Date);
                } else if (D == A) {
                    System.out.println("la meme date ");
                }
            }
        }
    }
}
