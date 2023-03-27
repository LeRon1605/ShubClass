import nodemailer from 'nodemailer';

class MailService {
    async sensMail(toAddress, subject, content) {
        const config = {
            host: 'smtp.gmail.com',
            port: 587,
            secure: true,
            auth: {
                user: process.env.MAIL_USER,
                password: process.env.MAIL_PASSWORD
            },
            tls: {
                rejectUnauthorized: false
            }
        };

        const message = {
            from: process.env.MAIL_DISPLAY_NAME,
            to: toAddress,
            subject: subject,
            html: 'Hello World from Shub class'
        };

        const transporter = nodemailer.createTransport(config);
        transporter.sendMail(message, (error) => {
            if (error) {
                console.log(error);
            }
        });
    }
}

export default new MailService();