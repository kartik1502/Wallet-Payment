package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.repository.UserRepository;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    
    
    
    @Test
    void testFindByUserId() {
    	
    	User user = new User();
		user.setUserId(1);
		user.setFirstName("divya");
		user.setLastName("shree");
		user.setEmailId("divya@gmail.com");
        when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByUserId(1);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
	
	
}
