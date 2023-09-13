package rs.ac.singidunum.workout.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.workout.models.ProductModel;
import rs.ac.singidunum.workout.services.products.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    @CrossOrigin("*")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    @CrossOrigin("*")
    public ResponseEntity<ProductModel> getProduct(@PathVariable("id") Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping("/create")
    @CrossOrigin("*")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductModel productModel, BindingResult result) {

        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            //TODO implement error handle here:
        }

        return new ResponseEntity<>(productService.createProduct(productModel), HttpStatus.CREATED);
    }

}
