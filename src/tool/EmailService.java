package tool;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.FacilityDao;

public class EmailService {
    public boolean sendEmail(String facility_id, String recipientEmail, String subject, String messageBody) throws Exception {
        FacilityDao fDao = new FacilityDao();
        String fromEmail = fDao.getFacilityInfo(facility_id).getFacility_mail();
        String password = fDao.getFacilityInfo(facility_id).getFacility_app_password();
        String smtpServer = getSmtpServerFromEmail(fromEmail);
        String smtpPort = "587";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpServer);
        properties.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject, "UTF-8");
            message.setContent(messageBody, "text/plain; charset=UTF-8");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            System.err.println("メール送信エラー: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private static String getSmtpServerFromEmail(String email) throws IllegalArgumentException {
        if (email.endsWith("@gmail.com")) {
            return "smtp.gmail.com";
        } else {
            throw new IllegalArgumentException("未対応のメールドメインです: " + email);
        }
    }
}
