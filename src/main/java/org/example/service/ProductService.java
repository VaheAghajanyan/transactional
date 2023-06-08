package org.example.service;

import org.example.dto.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // This method will be called from a proxy method and if there will be thrown an exception,
    // the transaction will be rollbacked. Propagation = Required is default configuration.
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveProduct() {
        Product product = new Product();

        for (int i = 0; i < 10; i++) {
            if (i == 7) {
                throw new RuntimeException("Some Error Occured.");
            }

            product.setId(i);
            product.setName("Test Product" + i);
            this.productRepository.saveProduct(product);
            System.out.println("Product saved!");
        }
    }

    // This one will not work correct and transaction will not be rollbacked, because
    // we are catching exception by hand, so Transaction Manager isn't catching an exception.
    @Transactional
    public void saveProductWrongWay() {
        Product product = new Product();
        try {
            for (int i = 0; i < 10; i++) {
                if (i == 7) {
                    throw new RuntimeException("Some Error Occured.");
                }

                product.setId(i);
                product.setName("Test Product" + i);
                this.productRepository.saveProduct(product);
                System.out.println("Product saved!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
