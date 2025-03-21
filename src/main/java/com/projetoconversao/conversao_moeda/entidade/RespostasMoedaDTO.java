package com.projetoconversao.conversao_moeda.entidade;

import java.util.Map;

public class RespostasMoedaDTO {
    private Map<String, String> currencies;

    public Map<String, String> getCurrencies() {
        return currencies;
    }
    public void setCurrencies(Map<String, String> currencies) {
        this.currencies = currencies;
    }
}


