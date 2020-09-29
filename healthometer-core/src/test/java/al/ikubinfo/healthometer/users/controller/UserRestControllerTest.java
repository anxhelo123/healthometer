package al.ikubinfo.healthometer.users.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import al.ikubinfo.commons.utils.JsonUtils;
import al.ikubinfo.healthometer.HealthometerApp;
import al.ikubinfo.healthometer.HealthometerTestSupport;
import al.ikubinfo.healthometer.users.dto.*;
import al.ikubinfo.healthometer.users.enums.Status;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(
    classes = HealthometerApp.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserRestControllerTest extends HealthometerTestSupport {

  private static String tokenUser;
  private static String tokenAdmin;
  private static final String baseURL = "/users";
  private static final String getUserURL = "/users" + "/{id}";

  static String getAuth(MockMvc mockMvc, String username, String password) throws Exception {
    AuthDto authDto = AuthDto.builder().username(username).password(password).build();
    MvcResult result =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.toJsonString(authDto))
                    .accept("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn();
    return result.getResponse().getHeader("Authorization");
  }

  @BeforeAll
  static void loginTest(@Autowired MockMvc mockMvc) throws Exception {
    String usernameUser = "user", usernameAdmin = "admin", password = "password";
    tokenUser = getAuth(mockMvc, usernameUser, password);
    tokenAdmin = getAuth(mockMvc, usernameAdmin, password);
  }

  @Test
  void getUserTest() {
    MvcResult result = getRequestWithAuthorization(getUserURL, tokenUser);
    assertEquals(200, result.getResponse().getStatus());
  }

  @Test
  void shouldNotGetUserTest() {
    MvcResult result = getRequestWithAuthorization(getUserURL, tokenAdmin);
    assertEquals(403, result.getResponse().getStatus());
  }

  @Test
  void shouldCreateUserTest() throws Exception {
    String firstname = RandomStringUtils.randomAlphanumeric(10);
    String lastname = RandomStringUtils.randomAlphanumeric(10);
    RoleDto roleDto = new RoleDto();
    roleDto.setId(2L);
    roleDto.setName(RandomStringUtils.randomAlphanumeric(10));
    StatusDto statusDto = new StatusDto();
    statusDto.setName(Status.ACTIVE);
    statusDto.setId(1L);
    UserDto userDto = new UserDto();
    userDto.setEmail(RandomStringUtils.randomAlphanumeric(10));
    userDto.setFirstname(firstname);
    userDto.setLastname(lastname);
    userDto.setUsername(RandomStringUtils.randomAlphanumeric(10));
    userDto.setPassword(RandomStringUtils.randomAlphanumeric(10));
    userDto.setRoleDto(roleDto);
    userDto.setStatusDto(statusDto);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(baseURL)
                .content(JsonUtils.toJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", tokenAdmin))
        .andExpect(status().isOk());
    assertEquals(userDto.getFirstname(), firstname);
    assertEquals(userDto.getLastname(), lastname);
  }

  @Test
  void createUserTesting() {
    String firstname = RandomStringUtils.randomAlphanumeric(10);
    String lastname = RandomStringUtils.randomAlphanumeric(10);
    RoleDto roleDto = new RoleDto();
    roleDto.setId(2L);
    roleDto.setName(RandomStringUtils.randomAlphanumeric(10));
    StatusDto statusDto = new StatusDto();
    statusDto.setName(Status.ACTIVE);
    statusDto.setId(1L);
    UserDto userDto = new UserDto();
    userDto.setEmail(RandomStringUtils.randomAlphanumeric(10));
    userDto.setFirstname(firstname);
    userDto.setLastname(lastname);
    userDto.setUsername(RandomStringUtils.randomAlphanumeric(10));
    userDto.setPassword(RandomStringUtils.randomAlphanumeric(10));
    userDto.setRoleDto(roleDto);
    userDto.setStatusDto(statusDto);
    MvcResult result = createPostWithAuthorization(baseURL, tokenAdmin, userDto);
    assertEquals(200, result.getResponse().getStatus());
  }

  @Test
  void editUserTest() {
    String email = RandomStringUtils.randomAlphanumeric(10);
    String getUserUrl2 = baseURL + "/{id}";
    UserDto userDto = new UserDto();
    userDto.setEmail(email);
    userDto.setPassword("password");
    MvcResult result = putRequestWithAuthorization(getUserUrl2, tokenAdmin, userDto);
    assertEquals(200, result.getResponse().getStatus());
  }

  @Test
  public void deleteUserTest() throws Exception {
    String getUserUrl = baseURL + "/{id}";
    mockMvc
        .perform(
            MockMvcRequestBuilders.delete(getUserUrl, 1)
                .header("Authorization", tokenAdmin)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldDeleteUserTest() {

    MvcResult result = deletePost(tokenAdmin);
    assertEquals(200, result.getResponse().getStatus());
  }

  @Test
  void shouldChangePasswordTest() {
    PasswordDto passwordDto = new PasswordDto();
    passwordDto.setCurrentPassword("password");
    passwordDto.setNewPassword("bjondi");
    String url = baseURL + "/{id}" + "/change-password";
    MvcResult result = putRequestWithAuthorization(url, tokenAdmin, passwordDto);
    assertEquals(200, result.getResponse().getStatus());
  }

  @Test
  void shouldNotChangePassword_Forbidden() {
    PasswordDto passwordDto = new PasswordDto();
    passwordDto.setCurrentPassword("password");
    passwordDto.setNewPassword("bjondi");
    String url = baseURL + "/{id}" + "/change-password";
    MvcResult result = putRequestWithAuthorization(url, tokenUser, passwordDto);
    assertEquals(403, result.getResponse().getStatus());
  }

  @Test
  void shouldChangeRoleTest() {
    UserDto roleDto = new UserDto();
    String url = baseURL + "/{id}" + "/change-role/admin";
    MvcResult result = putRequestWithAuthorization(url, tokenAdmin, roleDto);
    assertEquals(200, result.getResponse().getStatus());
  }
}
