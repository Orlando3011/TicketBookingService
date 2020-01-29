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

    //given
    @Before
    public void createUser() {
        user = new User();
    }

    @Test
    public void shouldReturnUserWithCredentials() {
        //when
        String testName = "name";
        user.setUsername(testName);
        //then
        assertEquals(testName, userService.retrieveUserCredentials(user).getUsername());
    }

    @Test
    public void shouldRemoveUnnecessaryData() {
        //when
        String testAddress = "testAddress";
        user.setAddress(testAddress);
        //then
        assertNull(userService.retrieveUserCredentials(user).getAddress());
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
        user.setUsername("testName");
        user.setPassword("wrongPass");
        when(userRepository.findByUsername(anyString())).thenReturn(new User("testName", "testPass"));
        //then
        assertNull(userService.getUserCredentials(user));
    }

    @Test
    public void shouldReturnCorrectCredentials() {
        //when
        user.setUsername("testName");
        user.setPassword("testPass");
        when(userRepository.findByUsername(anyString())).thenReturn(new User("testName", "testPass"));
        //then
        assertEquals(user.getUsername(), userService.getUserCredentials(user).getUsername());
    }

}