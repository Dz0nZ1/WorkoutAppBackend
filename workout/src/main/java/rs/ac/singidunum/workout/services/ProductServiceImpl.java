package rs.ac.singidunum.workout.services;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.models.ProductModel;
import rs.ac.singidunum.workout.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductModel getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public ProductModel createProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel updateProduct(ProductModel productModel, Long id) {
        ProductModel productToUpdate = productRepository.findById(id).orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setName(productModel.getName());
            productToUpdate.setPrice(productModel.getPrice());
            productToUpdate.setQuantity(productModel.getQuantity());
            return productRepository.save(productToUpdate);
        }
        return null;
    }
}
