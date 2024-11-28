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
	//recipientEmail 受取先 subject 件名 messageBody メール内容
	public boolean sendEmail(String facility_id, String recipientEmail, String subject, String messageBody) throws Exception {

        // 送信元のメールアドレスとパスワード
		FacilityDao fDao = new FacilityDao();
        String fromEmail = fDao.getFacilityInfo(facility_id).getFacility_mail();
        String password = fDao.getFacilityInfo(facility_id).getFacility_app_password();

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
            message.setSubject(subject,"UTF-8");
            message.setText(messageBody,"UTF-8");
            System.out.println("メッセージ準備成功");

            // メール送信
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // メールアドレスからSMTPサーバーを選択するロジック
    private static String getSmtpServerFromEmail(String email) {
        if (email.endsWith("@gmail.com")) {
            return "smtp.gmail.com"; // Gmailの場合の場合
        }  else {
            return "えらー"; // 対応してない
        }
    }

}
