package com.projetoconversao.conversao_moeda.service;

import com.projetoconversao.conversao_moeda.entidade.Conversao;
import com.projetoconversao.conversao_moeda.entidade.ResponseAPI;
import com.projetoconversao.conversao_moeda.entidade.RespostasMoedaDTO;
import com.projetoconversao.conversao_moeda.repository.ConversaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class ConversaoService {

    @Autowired
    private ConversaoRepository conversaoRepository;

    @Value("${fastforex.api.key}")
    private String apiKey;
    private final String URL = "https://api.fastforex.io/fetch-one?from={coin}&to=BRL&api_key={apiKey}";

    public ResponseAPI buscarCotacao(String moedaOrigem) {

        String url = URL.replace("{coin}", moedaOrigem).replace("{apiKey}", apiKey);
        RestTemplate restTemplate = new RestTemplate();

        ResponseAPI apiResponse = restTemplate.getForObject(url, ResponseAPI.class);
        if (apiResponse != null && apiResponse.getResult() != null) {
            return apiResponse;
        } else {
            throw new RuntimeException("API não retornou uma resposta válida.");
        }
    }
    public Double converterParaBRL(double valor, String moedaOrigem) {

        ResponseAPI apiResponse = buscarCotacao(moedaOrigem);
        Double taxaDeConversao = apiResponse.getBRL();
        if (taxaDeConversao != null) {
            return valor * taxaDeConversao;
        } else {
            throw new RuntimeException("Taxa de conversão não encontrada para BRL.");
        }
    }
    public Map<String, String> buscarMoedas() {
        String url = "https://api.fastforex.io/currencies?api_key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        RespostasMoedaDTO response = restTemplate.getForObject(url, RespostasMoedaDTO.class);
        if (response != null && response.getCurrencies() != null) {
            return response.getCurrencies();
        } else {
            throw new RuntimeException("Falha ao buscar as moedas na API.");
        }
    }


    public void NovaConversao(Conversao dados) {
        double valorConvertido = converterParaBRL(dados.getValorOrigem(), dados.getMoedaOrigem());

        Conversao conversao = new Conversao();
        conversao.setDescricao(dados.getDescricao());
        conversao.setMoedaOrigem(dados.getMoedaOrigem());
        conversao.setValorOrigem(dados.getValorOrigem());
        conversao.setValorConvertido(valorConvertido);
        conversao.setDataCotacao(new Date());

        conversaoRepository.save(conversao);
    }
    public void atualizarConversao(Long id, Conversao dados) throws Exception {
        Optional<Conversao> conversaoOptional = conversaoRepository.findById(id);
        if (conversaoOptional.isPresent()) {
            Conversao conversao = conversaoOptional.get();

            double valorConvertido = converterParaBRL(dados.getValorOrigem(), dados.getMoedaOrigem());

            conversao.setDescricao(dados.getDescricao());
            conversao.setMoedaOrigem(dados.getMoedaOrigem());
            conversao.setValorOrigem(dados.getValorOrigem());
            conversao.setValorConvertido(valorConvertido);
            conversao.setDataCotacao(new Date());

            conversaoRepository.save(conversao);
        } else {
            throw new Exception("Conversão não encontrada!");
        }
    }
    public void deletarConversao(Long id) throws Exception {
        Optional<Conversao> conversao = conversaoRepository.findById(id);
        if (conversao.isPresent()) {
            conversaoRepository.deleteById(id);
        } else {
            throw new Exception("Conversão não encontrada para exclusão");
        }
    }
}
