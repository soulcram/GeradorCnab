package br.com.m3Tech.solucoesFromtis.service.impl;

import java.time.LocalDate;
import java.util.List;

import br.com.m3Tech.solucoesFromtis.model.ConfGlobal;
import br.com.m3Tech.solucoesFromtis.service.IConfGlobalService;
import br.com.m3Tech.solucoesFromtis.util.StringUtils;

public class ConfGlobalServiceImpl implements IConfGlobalService{

	@Override
	public ConfGlobal getConfGlobal() {
		
		try {
			List<ConfGlobal> confGlobais;

			confGlobais = new ConfGlobal().findAll();

			String pathPadrao = "C:\\GeradorCnab\\" + LocalDate.now().toString().replaceAll("-", "") + "\\";

			ConfGlobal confGlobal;

			if (confGlobais == null || confGlobais.isEmpty()) {
				confGlobal = new ConfGlobal(1, pathPadrao);
			} else {
				confGlobal = confGlobais.get(0);
			}

			if (StringUtils.EmptyOrNull(confGlobal.getPath())) {
				confGlobal.setPath(pathPadrao);
			}

			return confGlobal;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


}
