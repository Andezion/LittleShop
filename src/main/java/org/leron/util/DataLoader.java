package org.leron.util;

import org.leron.model.Product;
import org.leron.model.Role;
import org.leron.model.User;
import org.leron.repository.ProductRepository;
import org.leron.repository.RoleRepository;
import org.leron.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner
{
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(ProductRepository productRepository, UserRepository userRepository,
                      RoleRepository roleRepository, PasswordEncoder passwordEncoder)
    {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception
    {
        Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() ->
        {
            Role role = new Role();
            role.setName("ADMIN");
            return roleRepository.save(role);
        });

        Role userRole = roleRepository.findByName("USER").orElseGet(() ->
        {
            Role role = new Role();
            role.setName("USER");
            return roleRepository.save(role);
        });

        if (userRepository.findByUsername("admin").isEmpty())
        {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);
            userRepository.save(admin);
            System.out.println("Admin user created.");
        }

        if (userRepository.findByUsername("user").isEmpty())
        {
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user"));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            user.setRoles(userRoles);
            userRepository.save(user);
            System.out.println("Regular user created.");
        }


        if (productRepository.count() == 0)
        {
            //productRepository.deleteAll();
            Product product1 = new Product();
            product1.setName("X1 smartphone");
            product1.setDescription("A powerful smartphone with OLED display and triple camera.");
            product1.setPrice(799.99);
            product1.setStockQuantity(50);
            product1.setImageUrl("/uploads/phone.jpg");

            Product product2 = new Product();
            product2.setName("Наушники AirBeats Pro");
            product2.setDescription("AirBeats Pro headphones");
            product2.setPrice(199.50);
            product2.setStockQuantity(120);
            product2.setImageUrl("/uploads/headphones.jpg");

            Product product3 = new Product();
            product3.setName("FitGear 2 smart watch");
            product3.setDescription("Fitness tracker with heart rate monitoring and GPS.");
            product3.setPrice(120.00);
            product3.setStockQuantity(80);
            product3.setImageUrl("/uploads/watch.jpg");

            Product product4 = new Product();
            product4.setName("Gaming K200 Keyboard");
            product4.setDescription("Mechanical gaming keyboard with RGB backlighting.");
            product4.setPrice(85.00);
            product4.setStockQuantity(60);
            product4.setImageUrl("/uploads/keyboard.jpg");

            Product product5 = new Product();
            product5.setName("Wireless Pro Mouse");
            product5.setDescription("Wireless mouse for work and play with high precision.");
            product5.setPrice(45.99);
            product5.setStockQuantity(90);
            product5.setImageUrl("/uploads/mouse.jpg");

//            Product product6 = new Product();
//            product6.setName("4K Ultra HD Monitor");
//            product6.setDescription("27-inch 4K UHD monitor with ultra-wide color gamut.");
//            product6.setPrice(349.99);
//            product6.setStockQuantity(40);
//            product6.setImageUrl("/uploads/photo.jpg");
//
//            Product product7 = new Product();
//            product7.setName("Portable Bluetooth Speaker");
//            product7.setDescription("Waterproof Bluetooth speaker with deep bass and 12h battery.");
//            product7.setPrice(59.90);
//            product7.setStockQuantity(100);
//            product7.setImageUrl("/uploads/photo.jpg");
//
//            Product product8 = new Product();
//            product8.setName("Smart Home Hub");
//            product8.setDescription("Control all your smart devices from one central hub.");
//            product8.setPrice(99.99);
//            product8.setStockQuantity(70);
//            product8.setImageUrl("/uploads/photo.jpg");
//
//            Product product9 = new Product();
//            product9.setName("VR Headset VisionX");
//            product9.setDescription("Immersive VR headset for gaming and virtual experiences.");
//            product9.setPrice(299.00);
//            product9.setStockQuantity(35);
//            product9.setImageUrl("/uploads/photo.jpg");
//
//            Product product10 = new Product();
//            product10.setName("External SSD 1TB");
//            product10.setDescription("High-speed portable SSD with USB-C support.");
//            product10.setPrice(129.99);
//            product10.setStockQuantity(85);
//            product10.setImageUrl("/uploads/photo.jpg");
//
//            Product product11 = new Product();
//            product11.setName("Digital Drawing Tablet");
//            product11.setDescription("Graphics tablet with pressure-sensitive stylus.");
//            product11.setPrice(249.00);
//            product11.setStockQuantity(45);
//            product11.setImageUrl("/uploads/photo.jpg");
//
//            Product product12 = new Product();
//            product12.setName("Noise Cancelling Headphones");
//            product12.setDescription("Over-ear headphones with active noise cancellation.");
//            product12.setPrice(189.99);
//            product12.setStockQuantity(60);
//            product12.setImageUrl("/uploads/photo.jpg");
//
//            Product product13 = new Product();
//            product13.setName("Mini Drone with Camera");
//            product13.setDescription("Compact drone with 1080p camera and GPS return home.");
//            product13.setPrice(159.49);
//            product13.setStockQuantity(30);
//            product13.setImageUrl("/uploads/photo.jpg");

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
//            productRepository.save(product6);
//            productRepository.save(product7);
//            productRepository.save(product8);
//            productRepository.save(product9);
//            productRepository.save(product10);
//            productRepository.save(product11);
//            productRepository.save(product12);
//            productRepository.save(product13);

            System.out.println("Test products loaded.");
        }
    }
}