package app.service;

import app.dto.products.ProductCategoryDTO;
import app.repository.ProductCategoryRepository;
import app.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategoryDTO> findAll() {
        return productCategoryRepository.findAll().stream()
                .map(pc -> DtoUtils.convert(pc, ProductCategoryDTO.class))
                .collect(Collectors.toList());
    }
}
