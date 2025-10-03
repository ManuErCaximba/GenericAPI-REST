Esta es la estructura de carpetas que se va a utilizar en este repositorio:

-src
    -main
        -java
            -com.example.project
                -config
                -controller
                -dto
                    -model
                    -request
                    -response
                -enum
                -model
                -repository
                -service
                    -interface
                -util
                MainApplication.java
                ServletInitializer.java
        -resources
    -test

Donde cada carpeta tiene el siguiente propósito:

-config: Contiene clases de configuración para la aplicación, como configuraciones de seguridad, bases de datos, etc.

Ejemplo: CustomerConfig.java
```
package com.example.project.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    // Configuraciones específicas
}
```

-controller: Contiene los controladores REST que manejan las solicitudes HTTP y las respuestas.

Ejemplo: CustomerController.java
```
package com.example.project.controller;

[...]

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    ICustomerService customerService;

    @GetMapping("/login")
    @ResponseBody
    public String getCustomer(@RequestBody Customer customer) {
        return customerService.getCustomer(customer);
    }

    @PostMapping("/addCustomer")
    @ResponseBody
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
}
```

-dto: Contiene objetos de transferencia de datos (Data Transfer Objects) que se utilizan para transferir datos entre capas. Caracteristicas:
    - Solo contienen atributos privados con sus getters y setters.
    - No contienen lógica de negocio.
    - Son normalmente usados para recibir datos de solicitudes o enviar datos en respuestas.

la carpeta dto tiene tres subcarpetas:
    - model: Contiene los DTOs principales que representan las entidades de la aplicación.
    - request: Contiene DTOs específicos para manejar datos de solicitudes entrantes.
    - response: Contiene DTOs específicos para manejar datos de respuestas salientes.

Ejemplo: CustomerDTO.java

package com.example.project.dto;

public class CustomerDTO {
    private Long id;
    private String username;
    private String email;

    // Getters y Setters
}
```

-enum: Contiene enumeraciones que representan un conjunto fijo de constantes.

Ejemplo: CustomerEnum.java
```
package com.example.project.enum;

public enum CustomerEnum {
    REGULAR,
    PREMIUM,
    VIP
}
```

-model: Contiene las clases de modelo que representan las entidades de la base de datos y sus relaciones.

Ejemplo: Customer.java
```
package com.example.project.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    // Getters y Setters
}
```

-repository: Contiene las interfaces de repositorio que extienden JpaRepository o CrudRepository para interactuar con la base de datos.

Ejemplo: CustomerRepository.java
```
package com.example.project.repository;

import com.example.project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Métodos de consulta personalizados (si es necesario)

    List<Customer> findByUsername(String username);
    Customer findById(Long id);
    void deleteById(Long id);
}
```

-service: Contiene las interfaces y clases de servicio que implementan la lógica de negocio de la aplicación.

Ejemplo: CustomerService.java
```
package com.example.project.service;

import com.example.project.model.Customer;
import com.example.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String getCustomer(Customer customer) {
        List<Customer> customers = customerRepository.findByUsername(customer.getUsername());
        if (customers.isEmpty()) {
            return "No existe el usuario";
        } else {
            return "Bienvenido " + customers.get(0).getUsername();
        }
    }

    @Override
    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "Usuario agregado correctamente";
    }
}

-util: Contiene clases utilitarias y de ayuda que proporcionan funcionalidades comunes utilizadas en toda la aplicación.

Ejemplo: StringUtils.java
```
package com.example.project.util;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
```