package br.com.locadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.locadora.model.Cliente;
import br.com.locadora.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

  
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", service.listarTodos());
        return "cliente-list";
    }

 
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

   
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente) {
        service.salvar(cliente);
        return "redirect:/clientes";
    }

 
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", service.buscarPorId(id));
        return "cliente-form";
    }

   
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/clientes";
    }
}
