package com.assignment.datingsuggestionapp.service;

import com.assignment.datingsuggestionapp.entity.User;
import com.assignment.datingsuggestionapp.repository.DatingSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DatingSuggestionServiceImpl implements DatingSuggestionService {

    private final DatingSuggestionRepository datingSuggestionRepository;

    @Autowired
    public DatingSuggestionServiceImpl(DatingSuggestionRepository datingSuggestionRepository) {
        this.datingSuggestionRepository = datingSuggestionRepository;
    }

    public User saveUser(User user) {
        return datingSuggestionRepository.save(user);
    }

    public List<User> findMatches(String username, int count) {
        User targetUser = datingSuggestionRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<User> users = new ArrayList<>(datingSuggestionRepository.findAll());
        users.remove(targetUser);

        return users.stream()
                .sorted((u1, u2) -> compareUsers(u1, u2, targetUser))
                .limit(count)
                .toList();
    }

    private int compareUsers(User u1, User u2, User targetUser) {
        int genderPreference1 = targetUser.getGender().equals(u1.getGender()) ? 1 : 0;
        int genderPreference2 = targetUser.getGender().equals(u2.getGender()) ? 1 : 0;

        if (genderPreference1 != genderPreference2) {
            return genderPreference1 - genderPreference2;
        }

        int ageDifference1 = Math.abs(u1.getAge() - targetUser.getAge());
        int ageDifference2 = Math.abs(u2.getAge() - targetUser.getAge());

        if (ageDifference1 != ageDifference2) {
            return Integer.compare(ageDifference1, ageDifference2);
        }

        long interestMatch1 = u1.getInterests().stream()
                .filter(targetUser.getInterests()::contains)
                .count();
        long interestMatch2 = u2.getInterests().stream()
                .filter(targetUser.getInterests()::contains)
                .count();

        return Long.compare(interestMatch2, interestMatch1);
    }
}