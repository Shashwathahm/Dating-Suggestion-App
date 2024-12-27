package com.assignment.datingsuggestionapp;

import com.assignment.datingsuggestionapp.entity.User;
import com.assignment.datingsuggestionapp.repository.DatingSuggestionRepository;
import com.assignment.datingsuggestionapp.service.DatingSuggestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatingSuggestionServiceImplTest {

    @Mock
    private DatingSuggestionRepository datingSuggestionRepository;

    @InjectMocks
    private DatingSuggestionServiceImpl datingSuggestionService;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User("user1", "female", 25, Arrays.asList("Cricket", "Chess"));
        user2 = new User("user2", "male", 27, Arrays.asList("Cricket", "Football", "Movies"));
        user3 = new User("user3", "male", 26, Arrays.asList("Movies", "Tennis", "Football", "Cricket"));
    }

    @Test
    void testSaveUser() {
        when(datingSuggestionRepository.save(user1)).thenReturn(user1);

        User savedUser = datingSuggestionService.saveUser(user1);

        assertNotNull(savedUser);
        assertEquals("user1", savedUser.getUsername());
        verify(datingSuggestionRepository, times(1)).save(user1);
    }

    @Test
    void testFindMatches() {
        when(datingSuggestionRepository.findById("user1")).thenReturn(Optional.of(user1));
        when(datingSuggestionRepository.findAll()).thenReturn(Arrays.asList(user1, user2, user3));

        List<User> matches = datingSuggestionService.findMatches("user1", 2);

        assertEquals(2, matches.size());
        assertEquals("user3", matches.get(0).getUsername());
        assertEquals("user2", matches.get(1).getUsername());

        verify(datingSuggestionRepository, times(1)).findById("user1");
        verify(datingSuggestionRepository, times(1)).findAll();
    }

    @Test
    void testFindMatchesUserNotFound() {
        when(datingSuggestionRepository.findById("unknown_user")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            datingSuggestionService.findMatches("unknown_user", 2);
        });

        assertEquals("User not found", exception.getMessage());
        verify(datingSuggestionRepository, times(1)).findById("unknown_user");
    }
}