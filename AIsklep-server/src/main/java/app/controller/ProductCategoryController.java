package app.controller;

import app.dto.products.ProductCategoryDTO;
import app.dto.products.ProductDTO;
import app.service.ProductCategoryService;
import app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productCategory")
public class ProductCategoryController extends Controller {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/all")
    public ResponseEntity all() throws Exception {
        List<ProductCategoryDTO> categories = productCategoryService.findAll();
        return respond(categories, HttpStatus.OK);
    }
}
