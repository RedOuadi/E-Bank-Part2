package bank.service;

import bank.dto.UserDTO;
import bank.model.User;
import bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User CreateUser(UserDTO userDTO) {
        User user = new User();
        user.setNom(userDTO.getNom());
        user.setEmail(userDTO.getEmail());
        user.setMotDePasse(userDTO.getMotDePasse());
        return userRepository.save(user);
    }


    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }
    //

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

//    @Override
//    public User updateUser(int userId, User user) {
//        Optional<User> existingUserOpt = userRepository.findById(userId);
//        if (existingUserOpt.isPresent()) {
//            User existingUser = existingUserOpt.get();
//            existingUser.setNom(user.getNom());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setMotDePasse(user.getMotDePasse());
//            return userRepository.save(existingUser);
//        }
//        return null; // Handle this appropriately
//    }
}
