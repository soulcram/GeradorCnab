package br.com.m3Tech.solucoesFromtis.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m3Tech.solucoesFromtis.dto.FundoDto;
import br.com.m3Tech.solucoesFromtis.model.Base;
import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.repositories.ConfGlobalRepository;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfGlobalServiceImpl implements IConfGlobalService {

    private final ConfGlobalRepository confGlobalRepository;

    @Override
    public ConfGlobal getConfGlobal() {

        try {
            List<ConfGlobal> confGlobais = this.confGlobalRepository.findAll();

            String pathPadrao = "C:\\GeradorCnab\\" + LocalDate.now().toString().replaceAll("-", "") + "\\";

            ConfGlobal confGlobal;

            if (confGlobais == null || confGlobais.isEmpty()) {
                confGlobal = new ConfGlobal(1, pathPadrao, "blitzer", "FromtisSoluções");
            } else {
                confGlobal = confGlobais.get(0);
            }

            if (StringUtils.emptyOrNull(confGlobal.getPath())) {
                confGlobal.setPath(pathPadrao);
            }

            return confGlobal;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPathRepositorio(Base base) {

        try {

            String sqlQuery = "SELECT TOP 1 DS_PATH_REPOSITORIO FROM TB_GLOBAL_CONFIG";

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
            PreparedStatement ps = con.prepareStatement(sqlQuery);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                return rs.getString("DS_PATH_REPOSITORIO");
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }
    
    @Override
    public String getPathBancoDeDados(Base base) {

        try {

            String sqlQuery = "SELECT TOP 1 DS_PATH_BANCO_DADOS FROM TB_GLOBAL_CONFIG";

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+base.getUrl(), base.getUsuario(), base.getSenha());
			
            PreparedStatement ps = con.prepareStatement(sqlQuery);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                return rs.getString("DS_PATH_BANCO_DADOS");
            }
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }

    @Override
    public String getPathSalvarArquivo(Base base, Boolean importacaoAutomatica, Boolean versaoMercado,
                                       FundoDto fundo) {
        if (importacaoAutomatica) {

            String pathRepositorio = getPathRepositorio(base);

            if (versaoMercado != null && versaoMercado) {
                return pathRepositorio + File.separator + "ENCONTRADOR_ARQUIVO";

            } else {

                return pathRepositorio + File.separator + fundo.getCodigoIsin() + File.separator + "REMESSA"
                        + File.separator + fundo.getDataFundo().format(DateTimeFormatter.ofPattern("ddMMyyyy"));

            }

        } else {
            return getConfGlobal().getPath();
        }
    }

    @Override
    public void salvar(ConfGlobal configuracaoGlobal) {
        this.confGlobalRepository.save(configuracaoGlobal);
    }

    @Override
    public List<ConfGlobal> findAll() {
        return this.confGlobalRepository.findAll();
    }

    @Override
    public String getPathSalvarArquivoCobranca(Base base, Boolean importacaoAutomatica, FundoDto fundo) {
        if (importacaoAutomatica) {

            String pathRepositorio = getPathRepositorio(base);


            return pathRepositorio + File.separator + fundo.getCodigoIsin() + File.separator + "COBRANCA"
                    + File.separator + fundo.getDataFundo().format(DateTimeFormatter.ofPattern("ddMMyyyy"));


        } else {
            return getConfGlobal().getPath();
        }
    }

}
