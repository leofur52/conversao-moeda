package com.projetoconversao.conversao_moeda.entidade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseAPI {
    private String base;
    @JsonProperty("result")
    private Result result;
    private String updated;
    private int ms;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }
    public double getBRL() {
        if (result != null) {
            return result.getBRL();
        } else {
            throw new RuntimeException("Resultado não encontrado.");
        }
    }

    public static class Result {
        @JsonProperty("BRL")
        private double BRL;
        public double getBRL() {
            return BRL;
        }

        public void setBRL(double BRL) {
            this.BRL = BRL;
        }
    }
}


