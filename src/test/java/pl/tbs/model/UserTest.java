package pl.tbs.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tbs.exception.WrongRoleException;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    User user;

    //given
    @Before
    public void createUser() {
        user = new User();
    }

    @Test
    public void shouldSetCorrectRole() throws WrongRoleException {
        //when
        String testRole = "admin";
        user.setRole(testRole);
        //then
        assertEquals(testRole, user.getRole());
    }

    @Test
    public void shouldThrowWrongRoleException() throws WrongRoleException {
        //when
        String testRole = "WrongRole";
        //then
        assertThrows(WrongRoleException.class, () -> user.setRole(testRole));
    }
}
