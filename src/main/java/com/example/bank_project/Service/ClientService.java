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
        System.out.println("Saving document for client ID: " + client.getId());

        Document document = new Document();
        document.setDocumentType(form.getDocumentType());
        document.setDocumentNumber(form.getDocumentNumber());
        document.setClient(client);

        System.out.println("Document will be saved for client ID: " + client.getId());
        documentRepository.save(document);
    }



    // Метод для проверки, существует ли документ у клиента
    public boolean documentExists(Client client) {
        return documentRepository.existsByClient(client);
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
    // Метод для проверки, существует ли адрес у клиента
    public boolean addressExists(Client client) {
        return addressRepository.existsByClient(client);
    }



    @Transactional
    public void saveContact(ContactForm form, Client client) {
        Contact contact = new Contact();
        contact.setPhone(form.getPhone());
        contact.setEmail(form.getEmail());
        contact.setClient(client);
        contactRepository.save(contact);
    }
    // Метод для проверки, существуют ли контактные данные у клиента
    public boolean contactExists(Client client) {
        return contactRepository.existsByClient(client);
    }

    public Client getClientByEmail(String email) {
        Optional<Users> optionalUser = usersRepo.findByGmail(email);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            Client client = user.getClient();
            if (client != null) {
                System.out.println("Client found for user: " + email + " with client ID: " + client.getId());
            } else {
                System.out.println("No client found for user: " + email);
            }
            return client;
        } else {
            System.out.println("User not found for email: " + email);
        }
        return null;
    }


}


