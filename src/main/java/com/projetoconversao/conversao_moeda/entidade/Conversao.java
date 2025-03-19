package com.projetoconversao.conversao_moeda.entidade;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Conversao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String moedaOrigem;
    private double valorOrigem;
    private double valorConvertido;

    @Temporal(TemporalType.DATE)
    private Date dataCotacao;

    public Conversao() {}
    public Conversao(String descricao, String moedaOrigem, double valorOrigem, double valorConvertido, Date dataCotacao) {
        this.descricao = descricao;
        this.moedaOrigem = moedaOrigem;
        this.valorOrigem = valorOrigem;
        this.valorConvertido = valorConvertido;
        this.dataCotacao = dataCotacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getMoedaOrigem() { return moedaOrigem; }
    public void setMoedaOrigem(String moedaOrigem) { this.moedaOrigem = moedaOrigem; }

    public double getValorOrigem() { return valorOrigem; }
    public void setValorOrigem(double valorOrigem) { this.valorOrigem = valorOrigem; }

    public double getValorConvertido() { return valorConvertido; }
    public void setValorConvertido(double valorConvertido) { this.valorConvertido = valorConvertido; }

    public Date getDataCotacao() { return dataCotacao; }
    public void setDataCotacao(Date dataCotacao) { this.dataCotacao = dataCotacao; }
}
