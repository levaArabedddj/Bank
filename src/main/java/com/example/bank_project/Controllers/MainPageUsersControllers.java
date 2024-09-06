package com.example.bank_project.Controllers;


import com.example.bank_project.Entity.Account;
import com.example.bank_project.Entity.Card;
import com.example.bank_project.Entity.Client;
import com.example.bank_project.Entity.Users;
import com.example.bank_project.Repository.CardRepo;
import com.example.bank_project.Repository.UsersRepo;
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
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/mainPageBank")
public class MainPageUsersControllers {




    @Autowired
    private UsersRepo usersRepo;

    @GetMapping
    public String viewPageMain(Model model, Principal principal) {

        if (principal == null) {
            // Если Principal равен null, перенаправляем на страницу входа или отображаем сообщение об ошибке
            return "redirect:/login";
        }
        // Получаем имя пользователя (email) из объекта Principal
        String currentUserName = principal.getName();

        // Находим пользователя по email
        Optional<Users> optionalUser = usersRepo.findByGmail(currentUserName);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();

            if (user.getClient() != null) {
                // Получаем аккаунты клиента
                Set<Account> accounts = user.getClient().getAccounts();

                if (!accounts.isEmpty()) {
                    Account account = accounts.iterator().next();
                    BigDecimal balance = account.getBalance();

                    // Добавляем баланс в модель для отображения на странице
                    model.addAttribute("balance", balance);
                }
            }
        } else {
            model.addAttribute("balance", BigDecimal.ZERO);
        }

        return "mainPageBank";
    }




}
