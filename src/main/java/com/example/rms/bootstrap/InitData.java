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
import jakarta.transaction.Transactional;
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

        Menu menu1 = Menu.builder()
                .name("Cake")
                .description("cake")
                .price("12.4$")
                .category("Dessert")
                .build(); 
        Menu menu2 = Menu.builder()
                .name("Water")
                .description("water")
                .price("0.5$")
                .category("Drink")
                .build();            
        menuRepository.saveAll(List.of(menu1, menu2));
        Set<Menu> menuItems = new HashSet<>();
        menuItems.add(menu1);
        menuItems.add(menu2);
        Table table = Table.builder()
                .seatNum(2)
                .available(false)
                .build();
        tableRepository.save(table);
        Customer customer = Customer.builder()
                    .customer("Lena")
                    .table(table)
                    .build();    
        customerRepository.save(customer);
        Order order = Order.builder()
                .status("new")
                .customer(customer)
                .table(table)
                .menuItems(menuItems)
                .build();    
        orderRepository.save(order);
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
                    .seatNum(random.nextInt(6) + 1)
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
            Customer customer = Customer.builder()
                    .customer("Customer" + i)
                    .table(randomTable)
                    .build();    
            customerRepository.save(customer);
        }
    }

    @Transactional
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
    
        List<Object[]> menuItems = menuRepository.findAllMenuAttributes();
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
                Object[] menuData = menuItems.get(random.nextInt(menuItems.size()));
                Menu randomMenuItem = Menu.builder()
                        .id((Long) menuData[0])
                        .name((String) menuData[1])
                        .description((String) menuData[2])
                        .price((String) menuData[3])
                        .category((String) menuData[4])
                        .build();
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
