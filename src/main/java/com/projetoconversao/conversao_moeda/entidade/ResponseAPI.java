package com.projetoconversao.conversao_moeda.entidade;

public class ResponseAPI {
    private double resultado;
    private String data;

    public ResponseAPI( Double resultado, String data) {
        this.resultado = resultado;
        this.data = data;
    }

    public double getResultado() { return resultado; }
    public void setResultado(double resultado) { this.resultado = resultado; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}

