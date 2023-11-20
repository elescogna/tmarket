package use_case.contact;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactInteractor implements ContactInputBoundary {
    final ContactOutputBoundary contactPresenter;

    public ContactInteractor(ContactOutputBoundary contactPresenter) {
        this.contactPresenter = contactPresenter;
    }

    public ContactOutputBoundary getContactPresenter() {
        return contactPresenter;
    }

    @Override
    public void execute(ContactInputData contactInputData) {
        String senderEmail = System.getenv("MAIL_SENDER_ADDRESS");
        String senderPassword = System.getenv("MAIL_SENDER_PASSWORD");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(contactInputData.getDestinationAddress()));
            message.setSubject(contactInputData.getSubject());
            message.setText(contactInputData.getBody());

            Transport.send(message);

            this.contactPresenter.prepareSuccessView();
        } catch (MessagingException e) {
            this.contactPresenter.prepareFailView("Failed to send message.");
        }
    }
}
