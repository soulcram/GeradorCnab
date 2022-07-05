//package br.com.m3Tech.solucoesFromtis.certificadora.view;
//
//import java.io.ByteArrayInputStream;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.SessionScoped;
//
//import com.google.common.collect.Lists;
//
//import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorRequisicoesCertificadora;
//import br.com.m3Tech.solucoesFromtis.certificadora.utils.ControllerUtils;
//import br.com.m3Tech.solucoesFromtis.certificadora.utils.JaxbUtils;
//import br.com.m3Tech.solucoesFromtis.certificadora.webservices.wrapper.RequisicaoCertificadoraDigitalWrapper;
//import lombok.Getter;
//import lombok.Setter;
//
//@ManagedBean
//@SessionScoped
//@Getter
//@Setter
//public class certificadoraController {
//    private static final String OUT = "requisicao";
//
//    @ManagedProperty("#{guardadorRequisicao}")
//    private GuardadorRequisicoesCertificadora guardadorRequisicao;
//
//    private List<RequisicaoCertificadoraDigitalWrapper> requisicoes;
//
//    public String init() {
//        atualizaRequisicoes();
//        return OUT;
//    }
//
//    public void removerTudo() {
//        this.guardadorRequisicao.removerTudo();
//        atualizaRequisicoes();
//    }
//
//    public void atualizaRequisicoes() {
//        requisicoes = Lists.newArrayList(guardadorRequisicao.pegaRequisicoes());
//    }
//
//    public void removeAquisicao(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
//        guardadorRequisicao.removeRequisica(requisicaoWrapper);
//        atualizaRequisicoes();
//    }
//
//    public void download(RequisicaoCertificadoraDigitalWrapper requisicaoWrapper) {
//        final String xml = JaxbUtils.jaxbObjectToXML(requisicaoWrapper.getRequisicao());
//        try {
//            ControllerUtils.downloadAsAttachment(
//                    LocalDateTime.now().toString("yyyyMMDDHHmm")
//                    + "_" + requisicaoWrapper.getRequisicao().getId()
//                    + ".xml", new ByteArrayInputStream(xml.getBytes())
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//            ControllerUtils.addMessageInfo("Ocorreu um erro inesperado.");
//        }
//    }
//
//}
