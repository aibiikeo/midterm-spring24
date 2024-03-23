package com.example.rms.bootstrap;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.rms.entities.Menu;
import com.example.rms.entities.Order;
import com.example.rms.entities.Table;
import com.example.rms.entities.User;
import com.example.rms.repositories.MenuRepository;
import com.example.rms.repositories.OrderRepository;
import com.example.rms.repositories.TableRepository;
import com.example.rms.repositories.UserRepository;

@Component
public class InitData implements CommandLineRunner{
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final UserRepository userRepository;

    public InitData(MenuRepository menuRepository, OrderRepository orderRepository, TableRepository tableRepository, UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        generateMenuItems(25);
        generateOrders(6);
        generateTables(10);
        User user1 = User.builder()
                .userType("customer")
                .build();    
        User user2 = User.builder()
                .userType("customer")
                .build();    
        User user3 = User.builder()
                .userType("admin")
                .build();    
        userRepository.saveAll(List.of(user1, user2, user3));
    }
    
    Random random = new Random();
    public void generateMenuItems(int numberOfMenuItems) {
        String[] categories = {"Appetizer", "Main Course", "Dessert", "Beverage"};
        
        for (int i = 1; i <= numberOfMenuItems; i++) {
            DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
            Menu menu = Menu.builder()
                    .name("Menu " + i)
                    .description("Description for menu " + i)
                    .price(String.valueOf(Double.parseDouble(df.format(random.nextDouble() * 100))) + "$")
                    .category(categories[random.nextInt(categories.length)])
                    .build();            
            menuRepository.save(menu);
        }
    }

    public void generateOrders(int numberOfOrders) {  
        String[] statuses = {"new", "preparing", "ready", "delivered", "cancelled"};
        for (int i = 1; i <= numberOfOrders; i++) {
            Order order = Order.builder()
                    .status(statuses[random.nextInt(statuses.length)])
                    .build();    
            orderRepository.save(order);
        }
    }

    public void generateTables(int numberOfTables) { 
        for (int i = 1; i <= numberOfTables; i++) {
            Table table = Table.builder()
                    .seatNum("Table " + i)
                    .available(random.nextBoolean())
                    .build();
            tableRepository.save(table);
        }
    }

    public void generateUsers(int numberOfUsers) { 
        String[] userTypes = {"admin", "waiter", "chef", "customer"};
        for (int i = 1; i <= numberOfUsers; i++) {
            User user = User.builder()
                    .userType(userTypes[random.nextInt(userTypes.length)])
                    .build();    
            userRepository.save(user);
        }
    }

}
