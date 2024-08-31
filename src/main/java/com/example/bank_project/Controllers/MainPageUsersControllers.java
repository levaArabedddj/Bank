package com.example.bank_project.Controllers;

import com.example.bank_project.DAO.AccountForm;
import com.example.bank_project.DAO.ContactForm;
import com.example.bank_project.Entity.Account;
import com.example.bank_project.Entity.Client;
import com.example.bank_project.Service.AccountService;
import com.example.bank_project.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/mainPageBank")
public class MainPageUsersControllers {


    @Autowired
    private ClientService client;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String viewPageMain(){
        return "mainPageBank";
    }

    @GetMapping("/register/account")
    public String showAccountRegistrationForm(Model model, Principal principal) {
        // Получаем клиента по Principal
        Client client = getClientFromPrincipal(principal);

        if (client == null) {
            return "error"; // Если клиент не найден, возвращаем ошибку
        }

        // Передаем клиента в модель, чтобы использовать его в форме регистрации аккаунта
        model.addAttribute("client", client);
        model.addAttribute("accountForm", new AccountForm());

        return "accountRegistration"; // Возвращаем страницу регистрации аккаунта
    }

    @PostMapping("/register/account")
    public String registerAccount(@ModelAttribute AccountForm accountForm, Principal principal) {
        // Получаем клиента по Principal
        Client client = getClientFromPrincipal(principal);

        if (client == null) {
            return "error"; // Если клиент не найден, возвращаем ошибку
        }

        // Создаем и сохраняем новый аккаунт, связанный с клиентом
        Account account = new Account();
        account.setAccountNumber(accountForm.getAccountNumber());
        account.setAccountType(accountForm.getAccountType());
        account.setBalance(BigDecimal.ZERO); // Инициализируем начальный баланс
        account.setCurrency(accountForm.getCurrency());
        account.setCreatedDate(LocalDate.now().toString());
        account.setClient(client); // Связываем аккаунт с клиентом

        accountService.saveAccount(account);

        return "redirect:/mainPageBank"; // После успешной регистрации аккаунта, перенаправляем на главную страницу банка
    }
    private Client getClientFromPrincipal(Principal principal) {
        return client.getClientByEmail(principal.getName());
    }


}
