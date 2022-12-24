package jardineria.jardineriapf.servicios;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmailServicio {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String remitente;

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;


    @Async
    public void enviarMailSimple(String destinatario, String asunto, String cuerpo) {
        SimpleMailMessage correo = new SimpleMailMessage(); 

        correo.setFrom(remitente);
        correo.setTo(destinatario); 
        correo.setSubject(asunto); 
        correo.setText(cuerpo);

        sender.send(correo);
    }

    @Async
    public void enviarMailConAdjunto(String destinatario, String asunto, String cuerpo, String[] adjuntos) {
        MimeMessage correo = sender.createMimeMessage();
        
        try {
            MimeMessageHelper helper = new MimeMessageHelper(correo, true);
            
            helper.setFrom(remitente);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpo);

            for (String a : adjuntos) {
                File f = ResourceUtils.getFile(a); //clase propia de springbook - convierte archivos de la carpeta de recursos a un archivo
                //System.out.println(f.getPath());
                helper.addAttachment(f.getName(), f);
            }

            sender.send(correo);
        } catch ( /* cuando no se peude crear el correo */ MessagingException e) {
            e.printStackTrace();
        } catch ( /* cuando no se encuentra el atchivo */ FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void enviarMailHtml(String destinatario, String asunto, String plantilla,
        Map<String, Object> valores) throws MessagingException {

        MimeMessage correo = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(correo, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context(); // esto es de timeleaf - son las variables de contexto
        context.setVariables(valores);
        helper.setFrom(remitente);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        String html = templateEngine.process(plantilla, context);
        helper.setText(html, true);
        emailSender.send(correo);
    }
}
