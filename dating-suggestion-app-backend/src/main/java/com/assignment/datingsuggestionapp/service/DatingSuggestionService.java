package com.assignment.datingsuggestionapp.service;

import com.assignment.datingsuggestionapp.entity.User;

import java.util.List;

public interface DatingSuggestionService {
    User saveUser(User user) ;

    List<User> findMatches(String username, int count);
}
