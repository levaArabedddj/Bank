package com.example.bank_project.Controllers;

import com.example.bank_project.DAO.AddressForm;
import com.example.bank_project.DAO.ContactForm;
import com.example.bank_project.DAO.DocumentForm;
import com.example.bank_project.DAO.RegistrationForm;
import com.example.bank_project.Entity.Client;
import com.example.bank_project.Service.ClientService;
import com.example.bank_project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService; // Сервис для работы с пользователями

    @Autowired
    private ClientService clientService;


    @GetMapping("/address")
    public String addressRegistration(){
        return "address";
    }

    @GetMapping("/contact")
    public String contactRegistration(){
        return "contact";
    }
    @GetMapping("/document")
    public String documentRegistration(){
        return "document";
    }





    @PostMapping
    public String registerUser(@ModelAttribute RegistrationForm form) {
        userService.createUser(form.getEmail(), form.getPassword(), form.getFirstName(), form.getLastName());
        return "login"; // После регистрации перенаправить на страницу входа
    }


    @PostMapping("/saveDocument")
    public String addDocument(@ModelAttribute DocumentForm form, Principal principal) {
        if (principal == null) {
            System.out.println("Principal is null");
            return "redirect:/login";
        }
        Client client = getClientFromPrincipal(principal);
        System.out.println("Attempting to register document for user: " + principal.getName());

        if (client == null) {
            System.out.println("Client not found for user: " + principal.getName());
            return "error";
        }

        if (clientService.documentExists(client)) {

            System.out.println("Document already exists for user: " + principal.getName());
            return "redirect:/register/address"; // Если документ уже существует, перенаправляем на следующую страницу
        }
        clientService.saveDocument(form, client);
        return "redirect:/register/address";
    }

    @PostMapping("/saveAddress")
    public String registerAddress(@ModelAttribute AddressForm form, Principal principal) {
        Client client = getClientFromPrincipal(principal);
        if (client == null) {
            return "error";
        }
        clientService.saveAddress(form, client);
        return "redirect:/register/contact";
    }

    @PostMapping("/saveContact")
    public String registerContact(@ModelAttribute ContactForm form, Principal principal) {
        Client client = getClientFromPrincipal(principal);
        if (client == null) {
            return "error";
        }
        clientService.saveContact(form, client);
        return "redirect:/mainPageBank";
    }

    // Метод для получения текущего клиента
    private Client getClientFromPrincipal(Principal principal) {
        return clientService.getClientByEmail(principal.getName());
    }


}

