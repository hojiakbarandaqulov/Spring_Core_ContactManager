package org.example.Controller;

import lombok.Setter;
import org.example.Service.ContactService;
import org.example.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
@Setter
@Component
public class ContactController {
    @Autowired
    private ContactService contactService;
    private Scanner scanner;

    public ContactController() {
        scanner=new Scanner(System.in);
    }

    public void start(){
        boolean b=true;
        while (b){
            menu();
            switch (action()){
                case 1:
                    addContact();
                    break;
                case 2:
                    contactList();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    contactSearch();
                    break;
                case 0:
                    System.out.println("program finished!!!");
                    b=false;
                    break;
                default:
                    b=false;
                    break;
            }
        }
    }

    public void contactSearch() {
        System.out.println("Enter query");
        String query=scanner.next();
        contactService.searchContact(query);
    }

    public void deleteContact() {
        System.out.print("Enter phone: ");
        String phone=scanner.next();
        contactService.deleteContact(phone);
    }

    public void contactList() {
        contactService.contactList();
    }

    public void addContact() {
        System.out.print("Enter name: ");
        String name=scanner.next();
        System.out.print("Enter surname: ");
        String surname=scanner.next();
        System.out.print("Enter phone: ");
        String phone=scanner.next();

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName(name);
        contactDTO.setSurname(surname);
        contactDTO.setPhone(phone);
        contactService.addContact(contactDTO);
    }

    public void menu(){
        System.out.println("*** Menu ***");
        System.out.println("1. Add contact");
        System.out.println("2. Contact List");
        System.out.println("3. Delete dto.Contact");
        System.out.println("4. Search dto.Contact");
        System.out.println("0. Exit");
    }
    public int action(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter action: ");
        int action=scanner.nextInt();
        return action;
    }

}
