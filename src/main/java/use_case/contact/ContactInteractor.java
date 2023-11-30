package use_case.contact;

import java.io.IOException;
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
    final ContactDataAccessInterface studentDataAccessObject;

    public ContactInteractor(ContactOutputBoundary contactPresenter,
            ContactDataAccessInterface studentDataAccessObject) {
        this.contactPresenter = contactPresenter;
        this.studentDataAccessObject = studentDataAccessObject;
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

        String recipientEmail = "";
        try {
            recipientEmail =
                studentDataAccessObject
                .getStudentById(contactInputData.getItemToSell().getOwnerId())
                .getUoftEmail();
        } catch (IOException e) {
            this.contactPresenter.prepareFailView("Cannot access Atlas database.");
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject(contactInputData.getSubject());
            message.setText(contactInputData.getBody());

            Transport.send(message);

            this.contactPresenter.prepareSuccessView();
        } catch (MessagingException e) {
            this.contactPresenter.prepareFailView("Failed to send message.");
        }
    }
}
