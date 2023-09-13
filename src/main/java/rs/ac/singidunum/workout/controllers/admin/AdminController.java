package rs.ac.singidunum.workout.controllers.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.workout.models.ProductModel;
import rs.ac.singidunum.workout.models.auth.UserModel;
import rs.ac.singidunum.workout.services.products.ProductService;
import rs.ac.singidunum.workout.services.users.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('Admin')")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    private final ProductService productService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductModel productModel, BindingResult result) {

        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            //TODO implement error handle here:
        }

        return new ResponseEntity<>(productService.createProduct(productModel), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin:update')")

    public ResponseEntity<?> updateProduct(@Valid @PathVariable(value = "id") Long Id, @RequestBody ProductModel productModel){
        return new ResponseEntity<>(productService.updateProduct(productModel, Id), HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete() {
        return "delete admin method";
    }
}
