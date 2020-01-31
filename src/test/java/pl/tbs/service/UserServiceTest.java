package pl.tbs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tbs.model.User;
import pl.tbs.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    UserService userService;
    User user;

    String testName = "testName";
    String wrongPass = "wrongPass";
    String testPass = "testPass";
    String testAddress = "testAddress";

    //given
    @Before
    public void createUser() {
        user = new User();
    }

    @Test
    public void shouldRemoveUnnecessaryData() {
        //when
        user.setUsername(testName);
        user.setPassword(testPass);
        user.setAddress(testAddress);
        when(userRepository.findByUsername(anyString())).thenReturn(new User("testName", "testPass", "address"));
        //then
        assertNull(userService.getUserCredentials(user).getAddress());
    }

    @Test
    public void shouldReturnNullWhenUsernameIsMissing() {
        //when
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        //then
        assertNull(userService.getUserCredentials(user));
    }

    @Test
    public void shouldReturnNullWhenPasswordIsWrong() {
        //when
        user.setUsername(testName);
        user.setPassword(wrongPass);
        when(userRepository.findByUsername(anyString())).thenReturn(new User("testName", "testPass"));
        //then
        assertNull(userService.getUserCredentials(user));
    }

    @Test
    public void shouldReturnCorrectCredentials() {
        //when
        user.setUsername(testName);
        user.setPassword(testPass);
        when(userRepository.findByUsername(anyString())).thenReturn(new User("testName", "testPass"));
        //then
        assertEquals(user.getUsername(), userService.getUserCredentials(user).getUsername());
    }

}