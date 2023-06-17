package br.com.m3Tech.solucoesFromtis.certificadora.utils;

import org.apache.commons.lang3.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ControllerUtils {

    public static void downloadAsAttachment(final String nome, final InputStream inputStream) throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + arquivoComExtensao(nome, "text/xml"));
        OutputStream responseOutputStream = response.getOutputStream();
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = inputStream.read(bytesBuffer)) > 0) {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }
        responseOutputStream.flush();

        inputStream.close();
        responseOutputStream.close();
        facesContext.responseComplete();
    }

    private static String arquivoComExtensao(String fileName, String mimeType) {
        fileName = fileName.replaceAll(" ", "_");
        String fileNameExtensao = StringUtils.substringAfter(fileName, ".");
        if (!StringUtils.isEmpty(fileNameExtensao)) {
            return fileName;
        }
        String extensao = StringUtils.substringAfter(mimeType, "/");
        return fileName.concat(".").concat(extensao);
    }

    public static void addMessageInfo(final String msg) {
        final FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }
}
