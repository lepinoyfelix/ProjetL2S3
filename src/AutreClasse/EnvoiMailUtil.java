package AutreClasse;
import java.lang.management.MonitorInfo;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import sample.InscriptionController;


public class EnvoiMailUtil {
    public static int nombreAleatoireVerificationMail = 100000 + (int)(Math.random() * ((999999 - 100000) + 1));

    public static int getNombreAleatoireVerificationMail() {
        return nombreAleatoireVerificationMail;
    }

    public static void EnvoiMail(String recepient) throws Exception {

        System.out.println("Prepaation pour l'envoi");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String MoncompteEmail  = "projetl2univtours@gmail.com";
        String Mdp = "ProjetL2Univ";

        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(MoncompteEmail,Mdp);
            }
        });
        Message message = prepareMessage(session, MoncompteEmail, recepient);

        Transport.send(message);
        System.out.println("Message envoyer avec succes");

    }

    public static  Message prepareMessage(Session session, String MoncompteEmail, String recepient){

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MoncompteEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Code de validation de votre adresse mail");
            message.setText("Bonjour, veuillez rentre dans la zone fait pour sa le code ci join : "+EnvoiMailUtil.getNombreAleatoireVerificationMail());
            return message;
        } catch (Exception ex) {
            Logger.getLogger(EnvoiMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
