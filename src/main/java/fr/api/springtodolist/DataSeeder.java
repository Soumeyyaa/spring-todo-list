package fr.api.springtodolist;


import fr.api.springtodolist.models.PriorityLevel;
import fr.api.springtodolist.models.Task;
import fr.api.springtodolist.models.TaskStatus;
import fr.api.springtodolist.models.User;
import fr.api.springtodolist.repositories.TaskRepository;
import fr.api.springtodolist.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                System.out.println("Ajout des utilisateurs...");

                User user1 = new User();
                user1.setUsername("soumeyaa");
                user1.setEmail("soumeyaa@example.com");
                user1.setPassword("password123");

                User user2 = new User();
                user2.setUsername("sara");
                user2.setEmail("sara@example.com");
                user2.setPassword("password456");

                userRepository.saveAll(List.of(user1, user2));
            }

            User user = userRepository.findByUsername("soumeyaa").orElseThrow();

            if (taskRepository.count() == 0) {
                System.out.println("Ajout des tâches...");

                Task task1 = new Task();
                task1.setUser(user);
                task1.setTitle("Apprendre Spring Boot");
                task1.setDescription("Regarder des tutoriels et coder une API REST.");
                task1.setPriorityLevel(PriorityLevel.HIGH);
                task1.setStatus(TaskStatus.TODO);
                task1.setCreatedAt(LocalDateTime.now());

                Task task2 = new Task();
                task2.setUser(user);
                task2.setTitle("Faire du sport");
                task2.setDescription("Aller courir 30 minutes.");
                task2.setPriorityLevel(PriorityLevel.MEDIUM);
                task2.setStatus(TaskStatus.IN_PROGRESS);
                task2.setCreatedAt(LocalDateTime.now());

                taskRepository.saveAll(List.of(task1, task2));
            }

            System.out.println("Données de test ajoutées avec succès !");
        };
    }
}
