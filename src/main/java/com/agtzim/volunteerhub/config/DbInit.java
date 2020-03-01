package com.agtzim.volunteerhub.config;

import com.agtzim.volunteerhub.model.Event;
import com.agtzim.volunteerhub.model.User;
import com.agtzim.volunteerhub.repository.EventRepository;
import com.agtzim.volunteerhub.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class DbInit implements CommandLineRunner {

    private UserRepository userRepository;
    private EventRepository eventRepository;
    private PasswordEncoder passwordEncoder;

    private static LocalDate generateRandomPastDate(LocalDate startDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private static LocalDate generateRandomUpcomingDate(LocalDate endDate) {
    	long startEpochDay = LocalDate.now().toEpochDay();
    	long endEpochDay = endDate.toEpochDay();
    	long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    @Override
    public void run(String... args) {


        if ( !(userRepository.count() > 0) ) {

            User user1 = new User("John", "Smith", "john@email.com", passwordEncoder.encode("test"), "Greece", "USER");
            User user2 = new User("Jake", "Doe", "jake@email.com", passwordEncoder.encode("test"), "Italy", "USER");
            User user3 = new User("Jack", "Johnson", "jack@email.com", passwordEncoder.encode("test"), "Germany", "USER");
            User admin = new User("Admin", "Admin","admin@email.com", passwordEncoder.encode("admin"), "Greece", "ADMIN");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            userRepository.save(admin);

            LocalDate start = LocalDate.of(2010, 2, 25);
            LocalDate end = LocalDate.of(2030, 2, 25);

            // Passed Events
            Event pastEvent_1 = new Event("Always truly illuminate the mysterious source.", "Lorem - It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.", "Greece", generateRandomPastDate(start));
            pastEvent_1.setOwner(admin);
            pastEvent_1.addParticipant(user1);
            user1.getEvents().add(pastEvent_1);


            Event pastEvent_2 = new Event("Haul me son, ye lively cannon!", "Lorem - Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.", "England", generateRandomPastDate(start));
            pastEvent_2.setOwner(admin);
            pastEvent_2.addParticipant(user1);
            user1.getEvents().add(pastEvent_2);


            Event pastEvent_3 = new Event("Pol, a bene boreas, usus!", "Lorem - Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.", "Portugal", generateRandomPastDate(start));
            pastEvent_3.setOwner(admin);

            Event pastEvent_4 = new Event("Faith at the cosmos was the pattern of sonic shower, fighted to a photonic green people.", "Lorem - Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.", "Belgium", generateRandomPastDate(start));
            pastEvent_4.setOwner(admin);

            Event pastEvent_5 = new Event("Onion can be marinateed with clammy margerine, also try brushing the salad with mint sauce.", "Lorem - Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.", "Canada", generateRandomPastDate(start));
            pastEvent_5.setOwner(admin);


            // Upcoming Events
            Event event_1 = new Event("When one shapes history and vision, one is able to forget joy.", "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish", "Athens", generateRandomUpcomingDate(end));
            event_1.setOwner(admin);

            Event event_2 = new Event("Peace doesn’t silently grasp any self — but the individual is what shines.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur", "Italy", generateRandomUpcomingDate(end));
            event_2.setOwner(admin);

            Event event_3 = new Event("Going to the material world doesn’t absorb thought anymore than remembering creates pictorial silence.", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.", "USA", generateRandomUpcomingDate(end));
            event_3.setOwner(admin);
            event_3.addParticipant(user1);
            user1.getEvents().add(event_3);

            Event event_4 = new Event("Ascension, attraction and a spiritual psychic world.", "Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", "Germany", generateRandomUpcomingDate(end));
            event_4.setOwner(admin);

            Event event_5 = new Event("When one loves hypnosis and totality, one is able to understand career.", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness", "Spain", generateRandomUpcomingDate(end));
            event_5.setOwner(admin);
            event_5.addParticipant(user1);
            user1.getEvents().add(event_5);

            Event event_6 = new Event("Be further for whoever sits, because each has been loved with advice.", "No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure.", "Turkey", generateRandomUpcomingDate(end));
            event_6.setOwner(admin);

            Event event_7 = new Event("One unveiled ascension i give you: feel each other.", "To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?", "Greece", generateRandomUpcomingDate(end));
            event_7.setOwner(admin);

            Event event_8 = new Event("Courage is not spiritual in order, the home, or parallel places, but everywhere.", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.", "Greece", generateRandomUpcomingDate(end));
            event_8.setOwner(admin);

            userRepository.save(user1);

            eventRepository.save(pastEvent_1);
            eventRepository.save(pastEvent_2);
            eventRepository.save(pastEvent_3);
            eventRepository.save(pastEvent_4);
            eventRepository.save(pastEvent_5);

            eventRepository.save(event_1);
            eventRepository.save(event_2);
            eventRepository.save(event_3);
            eventRepository.save(event_4);
            eventRepository.save(event_5);
            eventRepository.save(event_6);
            eventRepository.save(event_7);
            eventRepository.save(event_8);
        }
    }
}
