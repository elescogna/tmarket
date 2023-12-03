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
        prop.put("mail.smtp.port", "465"); // prev: 587
        prop.put("mail.smtp.ssl.enable", "true"); // TLS // prev: "mail.smtp.starttls.enable"
        prop.put("mail.smtp.auth", "true"); // check

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // need the authentication part

        String recipientEmail = "";
        try {
            System.out.println("i am reaching before the error");
            System.out.println(contactInputData.getItemToSell().getOwnerId()); // print the id: works
            System.out.println();
            recipientEmail =
                studentDataAccessObject
                .getStudentById(contactInputData.getItemToSell().getOwnerId())
                .getUoftEmail();
            System.out.println(recipientEmail);
            System.out.println("i am not reaching after the error");
        } catch (IOException e) {
            this.contactPresenter.prepareFailView("Cannot access Atlas database.");
        }

        try {
            System.out.println("I reached the block that sends emails");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject(contactInputData.getSubject());
            message.setText(contactInputData.getBody());

            System.out.println("I reached the line that should send the email");
            Transport.send(message);

            this.contactPresenter.prepareSuccessView();
        } catch (MessagingException e) {
            this.contactPresenter.prepareFailView("Failed to send message.");
        }
    }
}
