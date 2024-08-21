package com.example.bank_project.Service;


import com.example.bank_project.DAO.AddressForm;
import com.example.bank_project.DAO.ContactForm;
import com.example.bank_project.DAO.DocumentForm;
import com.example.bank_project.Entity.*;
import com.example.bank_project.Repository.AddressRepo;
import com.example.bank_project.Repository.ContactRepo;
import com.example.bank_project.Repository.DocumentRepo;
import com.example.bank_project.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private DocumentRepo documentRepository;

    @Autowired
    private AddressRepo addressRepository;

    @Autowired
    private ContactRepo contactRepository;

    @Autowired
    private UsersRepo usersRepo;

    @Transactional
    public void saveDocument(DocumentForm form, Client client) {
        Document document = new Document();
        document.setDocumentType(form.getDocumentType());
        document.setDocumentNumber(form.getDocumentNumber());
        document.setClient(client);
        documentRepository.save(document);
    }

    @Transactional
    public void saveAddress(AddressForm form, Client client) {
        Address address = new Address();
        address.setStreet(form.getStreet());
        address.setCity(form.getCity());
        address.setPostalCode(form.getPostalCode());
        address.setClient(client);
        addressRepository.save(address);
    }

    @Transactional
    public void saveContact(ContactForm form, Client client) {
        Contact contact = new Contact();
        contact.setPhone(form.getPhone());
        contact.setEmail(form.getEmail());
        contact.setClient(client);
        contactRepository.save(contact);
    }

    public Client getClientByEmail(String email) {
        Optional<Users> optionalUser = usersRepo.findByGmail(email);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            return user.getClient(); // Возвращает клиента, связанного с найденным пользователем
        }
        return null; // Или выбросите исключение, если пользователь не найден
    }

    }


