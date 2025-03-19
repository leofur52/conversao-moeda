package com.projetoconversao.conversao_moeda.service;

import com.projetoconversao.conversao_moeda.entidade.Conversao;
import com.projetoconversao.conversao_moeda.entidade.CotacaoResponse;
import com.projetoconversao.conversao_moeda.entidade.ResponseAPI;
import com.projetoconversao.conversao_moeda.repository.ConversaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConversaoService {

    @Value("${fastforex.api.key}")
    private String apiKey;

    private final String URL = "https://api.fastforex.io/fetch-one?from={fromCurrency}&to=BRL&api_key={apiKey}";

    @Autowired
    private ConversaoRepository conversaoRepository;

    public Double converterParaBRL(double valor, String moedaOrigem) {

        CotacaoResponse cotacaoResponse = buscarCotacao(moedaOrigem);
        Double taxaDeConversao = cotacaoResponse.getResult();
        if (taxaDeConversao != null) {
            return valor * taxaDeConversao;
        } else {
            throw new RuntimeException("Taxa de conversão não encontrada para BRL.");
        }
    }

    public CotacaoResponse buscarCotacao(String moedaOrigem) {

        String url = URL.replace("{fromCurrency}", moedaOrigem).replace("{apiKey}", apiKey);
        RestTemplate restTemplate = new RestTemplate();
        ResponseAPI apiResponse = restTemplate.getForObject(url, ResponseAPI.class);
        if (apiResponse != null && apiResponse.getResultado() > 0) {
            return new CotacaoResponse(apiResponse.getResultado(), apiResponse.getData());
        } else {
            throw new RuntimeException("API não retornou uma resposta válida.");
        }

    }
    public void salvarConversao(Conversao conversao) {
        // Salva a conversão no banco de dados usando o repositório
        conversaoRepository.save(conversao);
    }
}
