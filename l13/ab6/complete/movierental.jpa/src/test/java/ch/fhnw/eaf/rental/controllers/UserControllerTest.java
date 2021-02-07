package ch.fhnw.eaf.rental.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.services.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userServiceMock;

    @Test
    public void getAllUsers_ShouldReturnOK() throws Exception {
        User u1 = new User("MyLastName1", "MyFirstName1");
        User u2 = new User("MyLastName2", "MyFirstName2");

        when(userServiceMock.getAllUsers()).thenReturn(Arrays.asList(u1, u2));

        mockMvc.perform(get("/users").header("Accept", "application/json"))
                // .andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].lastName", is("MyLastName1")))
                .andExpect(jsonPath("$[0].firstName", is("MyFirstName1")))
                .andExpect(jsonPath("$[1].lastName", is("MyLastName2")))
                .andExpect(jsonPath("$[1].firstName", is("MyFirstName2")));

        Mockito.verify(userServiceMock, times(1)).getAllUsers();
    }

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
        User u1 = new User("MyLastName1", "MyFirstName1");
        u1.setId(1L);

        when(userServiceMock.save(u1)).thenReturn(u1);

        String json = toJSON(u1);

        // use XML instead of JSON as wrong content type
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_XML).content(json))
                .andExpect(status().is4xxClientError());
        Mockito.verify(userServiceMock, times(0)).save(u1);
    }

    @Test
    public void create_NewUser_WithWrongSerialisation_ShouldReturnClientError() throws Exception {
        User u1 = new User("MyLastName1", "MyFirstName1");
        u1.setId(1L);

        when(userServiceMock.save(u1)).thenReturn(u1);

        // serialize payload into a string to test wrong serialisation
        String json = u1.toString();

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is4xxClientError());
        Mockito.verify(userServiceMock, times(0)).save(u1);
    }

    @Test
    public void create_NewUserWithEmptyLastname_ShouldReturnNOK() throws Exception {
        // Use empty lastname to force validation error
        User u1 = new User("", "MyFirstName1");
        u1.setId(1L);

        when(userServiceMock.save(u1)).thenReturn(u1);

        String json = toJSON(u1);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isPreconditionFailed());
        Mockito.verify(userServiceMock, times(0)).save(u1);
    }

    @Test
    public void create_NewUserWithoutCallToService_ShouldReturnServerError() throws Exception {
        User u1 = new User("MyLastName1", "MyFirstName1");
        u1.setId(1L);

        // return null to simulate a database error and force a server error
        when(userServiceMock.save(u1)).thenReturn(null);

        String json = toJSON(u1);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isInternalServerError());
        Mockito.verify(userServiceMock, times(1)).save(u1);
    }

    @Test
    public void update_User_ShouldReturnOK() throws Exception {
        User u1 = new User("MyLastName1", "MyFirstName1");
        u1.setId(1L);

        // Override User.equals() to be able to execute these mock calls!
        when(userServiceMock.getUserById(1L)).thenReturn(u1);
        when(userServiceMock.save(u1)).thenReturn(u1);

        String json = toJSON(u1);

        mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
        Mockito.verify(userServiceMock, times(1)).getUserById(1L);
        Mockito.verify(userServiceMock, times(1)).save(u1);
    }

    @Test
    public void delete_User_ShouldReturnOK() throws Exception {
        User u1 = new User("MyLastName1", "MyFirstName1");
        u1.setId(1L);

        // Override User.equals() to be able to execute these mock calls!
        when(userServiceMock.getUserById(1L)).thenReturn(u1);

        mockMvc.perform(delete("/users/1")).andExpect(status().isOk());
        Mockito.verify(userServiceMock, times(1)).getUserById(1L);
    }

    private static String toJSON(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

}
