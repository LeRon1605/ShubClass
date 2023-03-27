import nodemailer from 'nodemailer';

class MailService {
    async sendMail(toAddress, subject, content) {
        const config = {
            host: 'smtp.gmail.com',
            port: 465,
            secure: true,
            auth: {
                user: process.env.MAIL_USER,
                pass: process.env.MAIL_PASSWORD
            },
            tls: {
                rejectUnauthorized: false,
                minVersion: "TLSv1.2"
            }
        };

        const message = {
            from: process.env.MAIL_DISPLAY_NAME,
            to: toAddress,
            subject: subject,
            html: content
        };

        const transporter = nodemailer.createTransport(config);
        transporter.sendMail(message, (error) => {
            if (error) {
                console.log(`[Mail error]: ${error}`);
            }
        });
    }
}

export default new MailService();