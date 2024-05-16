package org.example.Service;

import lombok.Setter;
import org.example.Repository.ContactRepository;
import org.example.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Component
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

//    public void addContact(ContactDTO contactDTO) {
//        ContactDTO byPhone = contactRepository.getByPhone(contactDTO.getPhone());
//        if (byPhone != null) {
//            System.out.println("bu contact avvaldan bor");
//            return;
//        }
//        boolean result = contactRepository.addContact(contactDTO);
//        if (result) {
//            System.out.println("dto.Contact added");
//        } else {
//            System.out.println("Something wend wrong. Mazgi");
//        }
//    }
    public void addContact(ContactDTO dto) {
        ContactDTO exists = contactRepository.getByPhone(dto.getPhone());
        if (exists != null) {
            System.out.println("Phone already exists");
            return;
        }
        //save
        boolean result = contactRepository.addContact(dto);
        if (result) {
            System.out.println("dto.Contact added");
        } else {
            System.out.println("Something wend wrong. Mazgi");
        }
    }
    public void contactList(){
        List<ContactDTO> contactDTOList= contactRepository.contactList();
        for (ContactDTO contactDTO:contactDTOList){
            System.out.println(contactDTO.toString());
        }
    }
    public void deleteContact(String phone){
        ContactDTO contactDTO=contactRepository.getByPhone(phone);
        if (contactDTO==null){
            System.out.println("dto.Contact not exists. Mazgi");
            return;
        }
        int effectedRows = contactRepository.deleteContact(phone);
        if (effectedRows==1){
            System.out.println("dto.Contact successfully deleted.");
        }
    }
    public void searchContact(String query){
       List<ContactDTO> contactDTOList=contactRepository.contactSearch(query);
       for (ContactDTO contactDTO:contactDTOList){
           System.out.println(contactDTO.toString());
       }
    }
}


