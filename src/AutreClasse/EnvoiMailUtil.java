package AutreClasse;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;


public class EnvoiMailUtil {
    public static int nombreAleatoireVerificationMail = 100000 + (int) (Math.random() * ((999999 - 100000) + 1));

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

        String MoncompteEmail = "projetunivl2infotours@gmail.com";
        String Mdp = "projetunivl2infotours123123";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MoncompteEmail, Mdp);
            }
        });
        Message message = prepareMessage(session, MoncompteEmail, recepient);

        Transport.send(message);
        System.out.println("Message envoyer avec succes");

    }

    public static Message prepareMessage(Session session, String MoncompteEmail, String recepient) {


        try {
            Random rand = new Random();
            String str = "";
            for (int i = 0; i < 500; i++) {
                char c = (char) (rand.nextInt(26) + 97);
                str += c;
            }

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(MoncompteEmail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
                message.setSubject("Code de validation de votre adresse mail");
                String MessageAenvoyer = "Bonjour, veuillez rentre dans la zone fait pour sa le code ci join : <strong>" + EnvoiMailUtil.getNombreAleatoireVerificationMail() + "</strong> <b style='color:white;'>" + str + "</b>";
                message.setContent(MessageAenvoyer, "text/html; charset=utf-8");
                return message;
            } catch (Exception ex) {
                Logger.getLogger(EnvoiMailUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

    }


