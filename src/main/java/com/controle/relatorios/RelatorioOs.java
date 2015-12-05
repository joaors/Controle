/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.relatorios;

import com.controle.dto.EmissaoOrdemServico;
import com.controle.jsf.util.JsfUtil;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author joao
 */
@Named
@RequestScoped
public class RelatorioOs {
    
    private Integer inicio;    
    private Integer fim;

    
    public void gerarRelatorio() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("WEB-INF/relatorios/Blank_A4.jasper");
        JRDataSource jrds = new JRBeanCollectionDataSource(listaOps());  
        gerarRelatorioWeb(jrds, null, arquivo);        
    }
 
    private void gerarRelatorioWeb(JRDataSource jrds, Map<String, Object> parametros, String arquivo) {
        ServletOutputStream servletOutputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
 
        try {
            servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, jrds);
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, "Não foi possível emitir Ordens de serviço.");
        }
    }
 
    private List<EmissaoOrdemServico> listaOps() {
        List<EmissaoOrdemServico> p = new ArrayList<>();
        for (int i = inicio; i <= fim; i++) {
            p.add(new EmissaoOrdemServico("Filial João", i));
        }
        return p;
    }
    
    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFim() {
        return fim;
    }

    public void setFim(Integer fim) {
        this.fim = fim;
    }
    
    
}
