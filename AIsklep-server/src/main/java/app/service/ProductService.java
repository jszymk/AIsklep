package app.service;

import app.auth.UserSessionInfo;
import app.dto.products.*;
import app.model.Product;
import app.model.ProductOrder;
import app.model.ProductOrderProduct;
import app.model.User;
import app.repository.ProductOrderRepository;
import app.repository.ProductRepository;
import app.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private UserSessionInfo userSessionInfo;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(p -> DtoUtils.convert(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> search(ProductSearchDTO data) {
        return productRepository.findByCriteria(data.getCategoryId(), data.getSearchPhrase()).stream()
                .map(p -> DtoUtils.convert(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public void order(List<OrderProductDTO> data) {
        User user = new User();
        user.setId(userSessionInfo.getCurrentUser().getId());
        ProductOrder productOrder = new ProductOrder();
        productOrder.setUser(user);
        productOrder.setProducts(data.stream().map(op -> {
            ProductOrderProduct p = new ProductOrderProduct();
            Product pr = new Product();
            pr.setId(op.getProductId());
            p.setProduct(pr);
            p.setProductOrder(productOrder);
            p.setQuantity(op.getQuantity());
            return p;
        }).collect(Collectors.toList()));
        productOrderRepository.save(productOrder);
    }

    public List<ProductOrderDTO> getProductOrders() {
        List<ProductOrder> productOrders = productOrderRepository.findByUserId(userSessionInfo.getCurrentUser().getId());
        return productOrders.stream().map(this::toDto).collect(Collectors.toList());
    }

    private ProductOrderDTO toDto(ProductOrder po) {
        ProductOrderDTO dto = new ProductOrderDTO();
        dto.setId(po.getId());
        dto.setProducts(po.getProducts().stream().map(this::toDto).collect(Collectors.toList()));
        return dto;
    }

    private ProductOrderProductDTO toDto(ProductOrderProduct p) {
        ProductOrderProductDTO dto = new ProductOrderProductDTO();
        dto.setProduct(DtoUtils.convert(p.getProduct(), ProductDTO.class));
        dto.setQuantity(p.getQuantity());
        return dto;
    }

    public void removeOrder(Long id) {
        ProductOrder productOrder = productOrderRepository.findOne(id);
        if(!productOrder.getUser().getId().equals(userSessionInfo.getCurrentUser().getId())) {
            throw new BadCredentialsException("Wrong user");
        }
        productOrderRepository.delete(productOrder);
    }
}
