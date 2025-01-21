package com.example.usermanagement;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private AutoCloseable closeable;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Initializing test suite...");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Test suite execution complete.");
    }

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        System.out.println("Setting up before each test...");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
        System.out.println("Cleaning up after each test...");
    }

    @Test
    void testCreateUser_ValidInput() {
        User user = new User("John Doe", "john@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser("John Doe", "john@example.com");

        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        assertEquals("john@example.com", createdUser.getEmail());
    }

    @Test
    void testGetUserById_ExistingUser() {
        User user = new User("Alice", "alice@example.com");
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserById(1L);

        assertNotNull(retrievedUser);
        assertEquals("Alice", retrievedUser.getName());
        assertEquals("alice@example.com", retrievedUser.getEmail());
    }

    @Test
    void testGetUserById_NonExistentUser() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.getUserById(999L));

        assertEquals("User not found", exception.getMessage());
    }

    @Disabled("Disabled until feature X is implemented")
    @Test
    void testDisabledFeature() {
        fail("This test should not run!");
    }
}
