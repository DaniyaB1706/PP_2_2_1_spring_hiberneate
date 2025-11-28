package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        Car car1 = new Car("Toyota", 10);
        Car car2 = new Car("BMW", 20);
        Car car3 = new Car("Audi", 30);
        Car car4 = new Car("Lada", 40);


        User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
        User user4 = new User("User4", "Lastname4", "user4@mail.ru", car4);


        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        for (User user : userService.listUsers()) {
            System.out.println(user);
        }


        System.out.println("=== Поиск по машине ===");
        User foundUser = userService.findUserByCar("BMW", 20);
        System.out.println(foundUser);

        context.close();
    }
}