package org.example.Repository;

import org.example.Connection.DatabaseConnection;
import org.example.dto.ContactDTO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
@Component
public class ContactRepository {
    public ContactDTO getByPhone(String phone) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact where phone=?");
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
                return contactDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean addContact(ContactDTO contactDTO) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into contact(name,surname,phone) values (?,?,?)");

            preparedStatement.setString(1, contactDTO.getName());
            preparedStatement.setString(2, contactDTO.getSurname());
            preparedStatement.setString(3, contactDTO.getPhone());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<ContactDTO> contactList() {
        Connection connection = DatabaseConnection.getConnection();
        List<ContactDTO> contactDTOList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
                contactDTOList.add(contactDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contactDTOList;
    }
    public int deleteContact(String phone){
        Connection connection=DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from contact where phone=?");
            preparedStatement.setString(1,phone);
            int effectedRows = preparedStatement.executeUpdate();
            return effectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    public List<ContactDTO> contactSearch(String query){
        Connection connection=DatabaseConnection.getConnection();
        List<ContactDTO>contactDTOList=new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact where lower(name) like  ? or lower(surname) like ? or lower(phone)like ?");
            String param="%"+query+"%";
            param=param.toLowerCase();
            preparedStatement.setString(1,param);
            preparedStatement.setString(2,param);
            preparedStatement.setString(3,param);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ContactDTO contactDTO=new ContactDTO();
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
                contactDTOList.add(contactDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contactDTOList;
    }
}
