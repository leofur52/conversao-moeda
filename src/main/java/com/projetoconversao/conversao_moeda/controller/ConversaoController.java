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
    @PostMapping("/salvar")
    public String salvarConversao(@ModelAttribute Conversao dados, Model model) {
        try {
            conversaoService.NovaConversao(dados);
            model.addAttribute("message", "Conversão realizada com sucesso!");
            return "redirect:/conversao/listagem";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocorreu um erro: " + e.getMessage());
            return "conversao";
        }
    }
    @GetMapping("/editar/{id}")
    public String editarConversao(@PathVariable("id") Long id, Model model) {
        Optional<Conversao> conversaoOptional = conversaoRepository.findById(id);
        if (conversaoOptional.isPresent()) {
            model.addAttribute("conversao", conversaoOptional.get());
            return "editar-conversao";
        } else {
            return "redirect:/conversao/listagem";
        }
    }
    @PostMapping("/editar/{id}")
    public String atualizarConversao(@PathVariable("id") Long id, @ModelAttribute Conversao dados, Model model) {
        try {
            conversaoService.atualizarConversao(id, dados);
            model.addAttribute("message", "Conversão atualizada com sucesso!");
            return "redirect:/conversao/listagem";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao atualizar: " + e.getMessage());
            return "editar-conversao";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarConversao(@PathVariable("id") Long id) {
        try {
            conversaoService.deletarConversao(id);
            return "redirect:/conversao/listagem";
        } catch (Exception e) {
            return "redirect:/conversao/listagem";
        }
    }
}
