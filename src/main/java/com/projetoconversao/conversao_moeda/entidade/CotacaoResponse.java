package com.projetoconversao.conversao_moeda.entidade;

import java.util.Map;

public class CotacaoResponse {
    private  Double result; // Mapeia as moedas com suas taxas de conversão
    private String updated; // Data de atualização

    // Construtor
    public CotacaoResponse(double result, String updated) {
        this.result = result;
        this.updated = updated;
    }

    // Getters e Setters
    public Double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}

