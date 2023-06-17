//package br.com.m3Tech.solucoesFromtis.pagamentos.sinacor;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//
//import br.com.m3Tech.solucoesFromtis.certificadora.service.GuardadorPagamentosAzumi;
//import br.com.m3Tech.solucoesFromtis.dto.OperacaoAzumi;
//import br.com.m3Tech.solucoesFromtis.dto.TransferenciaResponse;
//import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
//
//public class PagamentoAzumiControllerTest {
//
//    private final TokenService tokenService = Mockito.mock(TokenService.class);
//    private final IConfGlobalService confGlobalService = Mockito.mock(IConfGlobalService.class);
//    private final GuardadorPagamentosAzumi guardadorPagamentoAzumi = Mockito.mock(GuardadorPagamentosAzumi.class);
//
//    private final PagamentoAzumiController pagamentoAzumiController =
//            new PagamentoAzumiController(tokenService, confGlobalService, guardadorPagamentoAzumi);
//
//    @Test
//    void testGetReceberPagamento() {
//        String token = "api_token";
//        String body = "{\"id\": 1}";
//
//        OperacaoAzumi operacao = new OperacaoAzumi();
//        operacao.setId(1);
//
//        when(guardadorPagamentoAzumi.guardaOperacao(any(OperacaoAzumi.class))).thenReturn(true);
//
//        ResponseEntity<TransferenciaResponse> response = pagamentoAzumiController.getReceberPagamento(token, body);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
//        assertEquals(1, response.getBody().getId());
//    }
//}
//
//
//
