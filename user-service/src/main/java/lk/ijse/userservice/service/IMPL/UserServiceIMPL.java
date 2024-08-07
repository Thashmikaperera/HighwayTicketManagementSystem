package lk.ijse.userservice.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.userservice.conversion.ConversionData;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.repository.UserServiceDAO;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    @Autowired
    private ConversionData conversionData;

    @Autowired
    private UserServiceDAO userServiceDAO;

    @Override
    public void saveUser(UserDTO userDTO) {
        userServiceDAO.save(conversionData.mapTo(userDTO, User.class));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (!userServiceDAO.existsById(userDTO.getUserId())){
            return;
        }
        userServiceDAO.save(conversionData.mapTo(userDTO, User.class));
    }

    @Override
    public UserDTO getUser(String userId) {
        if (!userServiceDAO.existsById(userId)){
            return null;
        }
        return conversionData.mapTo(userServiceDAO.findById(userId).get(), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return conversionData.mapTo(userServiceDAO.findAll(), UserDTO.class);
    }

    /*@Override
    public void deleteUser(String userId) {
        userServiceDAO.deleteById(userId);
    }*/

    @Override
    public boolean isUserExists(String userId) {
        return userServiceDAO.existsById(userId);
    }

    @Override
    public String login(String username, String password) {
        User user = userServiceDAO.findUserByEmail(username);
        if(user!= null && user.getPassword().equals(password)){
            return "User logged in successfully!";
        }
        return "User user name and password invalid!";
    }

    @Override
    public String generateUserId() {
        User user = userServiceDAO.findFirstByOrderByUserIdDesc();
        if(user == null){
            return "U001";
        }
        int id = Integer.parseInt(user.getUserId().substring(1)) + 1;
        return String.format("U%03d", id);
    }
}
