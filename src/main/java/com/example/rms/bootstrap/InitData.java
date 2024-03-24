package com.example.rms.bootstrap;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.rms.entities.Menu;
import com.example.rms.entities.Order;
import com.example.rms.entities.Table;
import com.example.rms.entities.Customer;
import com.example.rms.repositories.MenuRepository;
import com.example.rms.repositories.OrderRepository;
import com.example.rms.repositories.TableRepository;
import com.example.rms.repositories.CustomerRepository;

@Component
public class InitData implements CommandLineRunner{
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final CustomerRepository customerRepository;

    public InitData(MenuRepository menuRepository, OrderRepository orderRepository, TableRepository tableRepository, CustomerRepository customerRepository) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        generateMenuItems(25);
        generateTables(10);
        generateCustomers(8);
        generateOrders(6);
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

    public void generateTables(int numberOfTables) { 
        for (int i = 1; i <= numberOfTables; i++) {
            Table table = Table.builder()
                    .seatNum("Table " + i)
                    .build();
            tableRepository.save(table);
        }
    }

    public void generateCustomers(int numberOfCustomers) { 
        List<Table> tables = (List<Table>) tableRepository.findAll();
        if (tables.isEmpty()) {
            System.err.println("No tables found in the database");
            return;
        }
        Collections.shuffle(tables);
        int tableIndex = 0;        
        for (int i = 1; i <= numberOfCustomers; i++) {
            if (tableIndex >= tables.size()) {
                System.err.println("No available tables found in the database");
                return;
            }
            Table randomTable = tables.get(tableIndex++);
            randomTable.setAvailable(false);
            tableRepository.save(randomTable);
            Customer user = Customer.builder()
                    .customer("Customer" + i)
                    .table(randomTable)
                    .build();    
            customerRepository.save(user);
        }
    }

    public void generateOrders(int numberOfOrders) {  
        String[] statuses = {"new", "preparing", "ready", "delivered", "cancelled"};
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        if (customers.isEmpty()) {
            System.err.println("No customers found in the database");
            return;
        }
        Collections.shuffle(customers);
    
        List<Table> tables = (List<Table>) tableRepository.findByAvailableFalse();
        if (tables.isEmpty()) {
            System.err.println("No tables found in the database");
            return;
        }
        Collections.shuffle(tables);
    
        List<Menu> menuItems = (List<Menu>) menuRepository.findAll();
        if (menuItems.isEmpty()) {
            System.err.println("No menu items found in the database");
            return;
        }
        Collections.shuffle(menuItems);

        for (int i = 1; i <= numberOfOrders; i++) {
            Customer randomCustomer = customers.get(random.nextInt(customers.size()));
            Table randomTable = tables.get(random.nextInt(tables.size()));
            Set<Menu> randomMenuItems = new HashSet<>();
            int numberOfMenuItems = random.nextInt(3);
            for (int j = 0; j < numberOfMenuItems; j++) {
                Menu randomMenuItem = menuItems.get(random.nextInt(menuItems.size()));
                randomMenuItems.add(randomMenuItem);
            }
            Order order = Order.builder()
                    .status(statuses[random.nextInt(statuses.length)])
                    .customer(randomCustomer)
                    .table(randomTable)
                    .menuItems(randomMenuItems)
                    .build();    
            orderRepository.save(order);
        }
    }

}
