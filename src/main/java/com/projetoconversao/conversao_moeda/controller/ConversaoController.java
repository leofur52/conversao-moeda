package com.projetoconversao.conversao_moeda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.projetoconversao.conversao_moeda.entidade.Conversao;
import com.projetoconversao.conversao_moeda.repository.ConversaoRepository;
import com.projetoconversao.conversao_moeda.service.ConversaoService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/conversao")
public class ConversaoController {

    @Autowired
    private ConversaoRepository conversaoRepository;
    @Autowired
    private ConversaoService conversaoService;


    @GetMapping
    public String mostrarFormularioConversao(Model model) {
        model.addAttribute("conversao", new Conversao());
        return "conversao";
    }

    @GetMapping("/listagem")
    public String listarConversoes(Model model) {
        List<Conversao> conversoes = conversaoRepository.findAll();
        model.addAttribute("conversoes", conversoes);
        return "listagem";
    }

    // Salva a conversão
    @PostMapping("Conversao")
    public String salvarConversao(@ModelAttribute Conversao dados, Model model) {
        try {
            // Usando o serviço para converter para BRL
            double valorConvertido = conversaoService.converterParaBRL(dados.getValorOrigem(), dados.getMoedaOrigem());

            // Criando um novo objeto de conversão e salvando
            Conversao conversao = new Conversao();
            conversao.setDescricao(dados.getDescricao());
            conversao.setMoedaOrigem(dados.getMoedaOrigem());
            conversao.setValorOrigem(dados.getValorOrigem());
            conversao.setValorConvertido(valorConvertido);

            // Salvando a conversão no banco de dados (você pode usar um repository para isso)
            conversaoService.salvarConversao(conversao);

            model.addAttribute("message", "Conversão realizada com sucesso!");
            return "redirect:/conversao/listagem"; // Redireciona para a página de listagem
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocorreu um erro: " + e.getMessage());
            return "conversao"; // Retorna para a página do formulário com erro
        }
    }

    // Edita uma conversão existente
    @GetMapping("/editar/{id}")
    public String editarConversao(@PathVariable("id") Long id, Model model) {
        Optional<Conversao> conversao = conversaoRepository.findById(id);
        if (conversao.isPresent()) {
            model.addAttribute("conversao", conversao.get());
            return "editar-conversao";
        } else {
            return "redirect:/conversao";
        }
    }

    // Atualiza a conversão
    @PostMapping("/atualizar")
    public String atualizarConversao(@ModelAttribute Conversao conversao) {
        // Verifica se a conversão existe antes de salvar
        if (conversao.getId() != null && conversaoRepository.existsById(conversao.getId())) {
            conversaoRepository.save(conversao);
            return "redirect:/conversao/listagem"; // Redireciona para a página de listagem
        } else {
            return "redirect:/conversao/listagem"; // Redireciona para a página de listagem
        }
    }

    // Deleta uma conversão
    @GetMapping("/deletar/{id}")
    public String deletarConversao(@PathVariable("id") Long id) {
        Optional<Conversao> conversao = conversaoRepository.findById(id);

        // Verifica se a conversão existe antes de tentar deletar
        if (conversao.isPresent()) {
            conversaoRepository.deleteById(id);
        } else {
            // Em caso de erro, você pode adicionar um feedback para o usuário
        }
        return "redirect:/conversao/listagem"; // Redireciona para a página de listagem
    }
}
