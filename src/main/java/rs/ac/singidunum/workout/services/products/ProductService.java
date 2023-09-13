package rs.ac.singidunum.workout.services.products;

import rs.ac.singidunum.workout.models.ProductModel;

import java.util.List;

public interface ProductService {

    List<ProductModel> getAllProducts();

    ProductModel getProduct(Long productId);

    ProductModel createProduct(ProductModel productModel);

    ProductModel updateProduct(ProductModel productModel, Long id);

}
