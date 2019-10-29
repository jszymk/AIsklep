package app.controller;

import app.dto.products.OrderProductDTO;
import app.dto.products.ProductDTO;
import app.dto.products.ProductOrderDTO;
import app.dto.products.ProductSearchDTO;
import app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController extends Controller {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity all() throws Exception {
        List<ProductDTO> products = productService.findAll();
        return respond(products, HttpStatus.OK);
    }

    @GetMapping("/image")
    public ResponseEntity image(@RequestParam String path) throws Exception {
        InputStream is = Files.newInputStream(Paths.get("/AIsklep/img/" + path));
        BufferedImage img = ImageIO.read(is);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", bao);

        return respond(bao.toByteArray(), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity search(@RequestBody ProductSearchDTO data) throws Exception {
        List<ProductDTO> products = productService.search(data);
        return respond(products, HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PostMapping("/order")
    public ResponseEntity order(@RequestBody List<OrderProductDTO> data) throws Exception {
        productService.order(data);
        return respond(HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping("/orders")
    public ResponseEntity orders() throws Exception {
        List<ProductOrderDTO> orders = productService.getProductOrders();
        return respond(orders, HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PostMapping("/order/remove")
    public ResponseEntity orderRemove(@RequestBody Long id) throws Exception {
        productService.removeOrder(id);
        return respond(HttpStatus.OK);
    }
}
