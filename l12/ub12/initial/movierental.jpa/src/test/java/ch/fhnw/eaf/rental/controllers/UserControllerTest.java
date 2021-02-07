package ch.fhnw.eaf.rental.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.services.UserService;

@Ignore
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userServiceMock;

    @Test
    public void create_NewUser_ShouldReturnOK() throws Exception {
        User u1 = new User("MyLastName1", "MyFirstName1");
        u1.setId(1L);

        // Override User.equals() to be able to execute these mock calls!
        when(userServiceMock.save(u1)).thenReturn(u1);

        String json = toJSON(u1);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.lastName", is("MyLastName1")))
                .andExpect(jsonPath("$.firstName", is("MyFirstName1")));
        Mockito.verify(userServiceMock, times(1)).save(u1);
    }


    @Test
    public void create_NewUser_WithWrongContentType_ShouldReturnClientError() throws Exception {
        fail();
    }

    @Test
    public void create_NewUser_WithWrongSerialisation_ShouldReturnClientError() throws Exception {
        fail();
    }

    @Test
    public void create_NewUserWithEmptyLastname_ShouldReturnNOK() throws Exception {
        fail();
    }

    @Test
    public void create_NewUserWithoutCallToService_ShouldReturnServerError() throws Exception {
        fail();
    }

    @Test
    public void update_User_ShouldReturnOK() throws Exception {
        fail();
    }

    @Test
    public void delete_User_ShouldReturnOK() throws Exception {
        fail();
    }

    private static String toJSON(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

}
