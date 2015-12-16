package BeatHeaven_Classes;
import java.util.Date;
import java.util.Properties;
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//@Stateless
//@LocalBean
public class EmailSessionBean {

    /* private int port = 465;
     private String host = "smtp.gmail.com";
     private String from = "famtrecruit@gmail.com";
     private boolean auth = true;
     private String username = "famtrecruit@gmail.com";
     private String password = "ORfamt10";
     private Protocol protocol = Protocol.SMTPS;
     private boolean debug = true;*/
    public void sendEmail(String to, String subject, String body) {
        int port = 465;
        String host = "smtp.gmail.com";
        String from = "famtrecruit@gmail.com";
        boolean auth = true;
        final String username = "famtrecruit@gmail.com";
        final String password = "ORfamt10";        
        Protocol protocol = Protocol.SMTPS;
        boolean debug = true;
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        switch (protocol) {
            case SMTPS:
                props.put("mail.smtp.ssl.enable", true);
                break;
            case TLS:
                props.put("mail.smtp.starttls.enable", true);
                break;
        }

        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }

        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);
            Multipart multipart = new MimeMultipart("alternative");

            MimeBodyPart textPart = new MimeBodyPart();
            String textContent = body;
            textPart.setText(textContent);

            MimeBodyPart htmlPart = new MimeBodyPart();
           // String htmlContent = "<html><h1>Online Recruitment Testing Mail...plz do not reply The code is:" + body + "</h1> </html>";
            String htmlContent=body;
            htmlPart.setContent(htmlContent, "text/html");

            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}