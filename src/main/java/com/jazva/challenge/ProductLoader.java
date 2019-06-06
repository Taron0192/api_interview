package com.jazva.challenge;

import com.jazva.challenge.entity.Location;
import com.jazva.challenge.entity.Product;
import com.jazva.challenge.service.LocationService;
import com.jazva.challenge.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Loads stored objects from the file system and builds up
 * the appropriate objects to add to the data source.
 *
 */
@Component
public class ProductLoader implements InitializingBean {

    @Value("classpath:data/products.txt")
    private Resource products;

    @Value("classpath:data/locations.txt")
    private Resource locations;

    @Autowired
    DataSource dataSource;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProductService productService;

    /**
     * Load the products into the data source after
     * the application is ready.
     *
     * @throws Exception In case something goes wrong while we load
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(locations.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                locationService.addLocation(new Location(line));
            }
        }

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(products.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {

                final String[] values = line.split(";");
                Product product = new Product();
                product.setName(values[0]);
                product.setPrice(new BigDecimal(values[1]));

                productService.addProduct(product);
                System.out.println(line);

            }
        }
    }

}
