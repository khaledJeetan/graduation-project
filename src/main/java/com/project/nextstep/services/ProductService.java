package com.project.nextstep.services;

import com.project.nextstep.entity.accounts.Supplier;
import com.project.nextstep.entity.construction.Product;
import com.project.nextstep.repositories.ProductRepository;
import com.project.nextstep.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ImageService imageService;

    @Autowired
    public ProductService(ProductRepository productRepository, UserService userService, ImageService imageService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.imageService = imageService;
    }

    public Product getProduct(Long productId){
        return productRepository
                .findById(productId)
                .orElseThrow(()->
                        new IllegalStateException("Product Not Found")
                );
    }

//    public List<byte[]> getProductImages(Long productId) {
//        Product product = getProduct(productId);
//        return imageService.getImages(product.getImages());
//    }

    @Transactional
    public Product addProduct(Product product,Long supplierId) {
       Supplier supplier =  (Supplier) userService.getUser(supplierId);
       product.setSupplier(supplier);
        productRepository.save(product);
        return product;
    }

//    @Transactional
//    public boolean addProductImage(MultipartFile image, Long productId) {
//        Product product = getProduct(productId);
//        String imagePath = imageService.uploadImage(image,product.getSupplier().getEmail(),String.valueOf(product.getId()));
////        product.getImages().add(imagePath);
//        return true;
//    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getSupplierProducts(Long supplierId) {
        return productRepository.findAllBySupplier_Id(supplierId);
    }
}
