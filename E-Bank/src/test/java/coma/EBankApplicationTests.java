package coma;

import bank.dto.UserDTO;
import bank.model.User;
import bank.service.UserService;
import bank.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class  EBankApplicationTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateUser() {
		// Arrange
		UserDTO userDTO = new UserDTO();
		userDTO.setNom("John Doe");
		userDTO.setEmail("john.doe@example.com");
		userDTO.setMotDePasse("password123");

		User user = new User();
		user.setNom(userDTO.getNom());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getMotDePasse());

		when(userRepository.save(any(User.class))).thenReturn(user);


		User createdUser = userService.CreateUser(userDTO);


		assertEquals("John Doe", createdUser.getNom());
		assertEquals("john.doe@example.com", createdUser.getEmail());
		assertEquals("password123", createdUser.getPassword());

		verify(userRepository, times(1)).save(any(User.class));
	}
}
