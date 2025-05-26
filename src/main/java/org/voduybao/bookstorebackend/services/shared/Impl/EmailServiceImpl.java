package org.voduybao.bookstorebackend.services.shared.Impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.voduybao.bookstorebackend.services.shared.EmailService;

@Component
@Slf4j(topic = "EMAIL")
public class EmailServiceImpl implements EmailService {

    @Setter(onMethod_ = @Autowired)
    private JavaMailSender mailSender;
    @Override
    public void sendVerifyOtp(String email, String otp) {
        // Tạo nội dung email HTML
        String htmlContent = String.format("""
                <html>
                <body>
                    <p>Cảm ơn bạn đã sử dụng dịch vụ của <b>%s</b>.</p>
                
                    <p>Mã xác thực (OTP) của bạn là: <b>%s</b></p>
                
                    <p>Lưu ý: Mã này chỉ có hiệu lực trong <b>%d phút</b> và chỉ sử dụng một lần.</p>
                
                    <p>Nếu bạn không thực hiện yêu cầu này, vui lòng liên hệ với chúng tôi qua <b>%s</b> để được hỗ trợ ngay lập tức.</p>
                
                    <p>Trân trọng,</p>
                   <p>Đội ngũ <a href="https://www.google.com/">BookStore.vn</a></p>
                </body>
                </html>
                """, "BookStore.vn", otp, 5, "19008198");

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Your OTP Code");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendNewPassword(String email, String newPassword) {
        // Tạo nội dung email HTML
        String htmlContent = String.format("""
                <html>
                <body>
                    <p>Cảm ơn bạn đã sử dụng dịch vụ của <b>%s</b>.</p>
                
                    <p>Mật khẩu mới của bạn là: <b>%s</b></p>
                
                    <p>Lưu ý: Thay đổi mật khẩu sau khi đăng nhập vào ứng dụng.</p>
                
                    <p>Nếu bạn không thực hiện yêu cầu này, vui lòng liên hệ với chúng tôi qua <b>%s</b> để được hỗ trợ ngay lập tức.</p>
                
                    <p>Trân trọng,</p>
                    <p>Đội ngũ <a href="https://www.google.com/">BookStore.vn</a></p>
                </body>
                </html>
                """, "BookStore.vn", newPassword,  "19008198");

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Your password");
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
