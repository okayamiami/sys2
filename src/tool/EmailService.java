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


public class EmailService {

	public boolean sendEmail(String recipientEmail, String subject, String messageBody) {

        // 送信元のメールアドレスとパスワード
        String fromEmail = "your-email@example.com";
        String password = "your-app-password";  // アプリパスワード

        // メールサービスのドメインを確認して適切なSMTPサーバーを選択
        String smtpServer = getSmtpServerFromEmail(fromEmail);
        String smtpPort = "587"; // 通常のSMTPポート（STARTTLS）

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
            message.setSubject(subject);
            message.setText(messageBody);

            // メール送信
            Transport.send(message);
            System.out.println("メール送信成功");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // メールアドレスからSMTPサーバーを選択するロジック
    private static String getSmtpServerFromEmail(String email) {
        if (email.endsWith("@gmail.com")) {
            return "smtp.gmail.com"; // Gmailの場合
        } else if (email.endsWith("@outlook.com")) {
            return "smtp-mail.outlook.com"; // Outlookの場合
        } else if (email.endsWith("@yahoo.com")) {
            return "smtp.mail.yahoo.com"; // Yahooの場合
        } else {
            return "smtp.example.com"; // デフォルトのSMTPサーバー
        }
    }
}
