package org.leron.controller;

import jakarta.validation.Valid;

import org.leron.dto.ProductDTO;
import org.leron.model.Product;
import org.leron.service.ProductService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/products")
public class ProductController
{
    private final ProductService productService;
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    public ProductController(ProductService productService)
    {

        this.productService = productService;

    }

    @GetMapping
    public String listProducts(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model)
    {
        Page<Product> productPage = productService.getPaginatedProducts(page, size);
        model.addAttribute("productPage", productPage);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "products/list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model)
    {
        model.addAttribute("productDTO", new ProductDTO());
        return "products/add-edit";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("productDTO") ProductDTO productDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors())
        {
            return "products/add-edit";
        }

        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product, "imageFile");

        if (productDTO.getImageFile() != null && !productDTO.getImageFile().isEmpty())
        {
            try
            {
                String fileName = UUID.randomUUID().toString() + "_" + productDTO.getImageFile().getOriginalFilename();
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath))
                {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(productDTO.getImageFile().getInputStream(), filePath);
                product.setImageUrl("/" + UPLOAD_DIR + fileName);
            }
            catch (IOException e)
            {
                bindingResult.rejectValue("imageFile", "error.productDTO", "Failed to upload the file.");
                return "products/add-edit";
            }
        }

        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product has been successfully added!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditProductForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes)
    {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isEmpty())
        {
            redirectAttributes.addFlashAttribute("errorMessage", "Item not found.");
            return "redirect:/products";
        }

        Product product = productOptional.get();
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        model.addAttribute("productDTO", productDTO);
        return "products/add-edit";
    }

    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id,
                              @Valid @ModelAttribute("productDTO") ProductDTO productDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors())
        {
            return "products/add-edit";
        }

        Optional<Product> existingProductOptional = productService.getProductById(id);
        if (existingProductOptional.isEmpty())
        {
            redirectAttributes.addFlashAttribute("errorMessage", "The item to edit was not found.");
            return "redirect:/products";
        }

        Product existingProduct = existingProductOptional.get();

        BeanUtils.copyProperties(productDTO, existingProduct, "imageFile", "id");

        if (productDTO.getImageFile() != null && !productDTO.getImageFile().isEmpty())
        {
            try
            {
                if (existingProduct.getImageUrl() != null && !existingProduct.getImageUrl().isEmpty())
                {
                    Path oldFilePath = Paths.get(UPLOAD_DIR + existingProduct.getImageUrl().substring(UPLOAD_DIR.length() + 1));
                    if (Files.exists(oldFilePath))
                    {
                        Files.delete(oldFilePath);
                    }
                }

                String fileName = UUID.randomUUID().toString() + "_" + productDTO.getImageFile().getOriginalFilename();
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath))
                {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(productDTO.getImageFile().getInputStream(), filePath);
                existingProduct.setImageUrl("/" + UPLOAD_DIR + fileName);
            }
            catch (IOException e)
            {
                bindingResult.rejectValue("imageFile", "error.productDTO", "Failed to upload a new file.");
                return "products/add-edit";
            }
        }
        else if (productDTO.getImageUrl() == null || productDTO.getImageUrl().isEmpty())
        {
            if (existingProduct.getImageUrl() != null && !existingProduct.getImageUrl().isEmpty())
            {
                try
                {
                    Path oldFilePath = Paths.get(UPLOAD_DIR + existingProduct.getImageUrl().substring(UPLOAD_DIR.length() + 1));
                    if (Files.exists(oldFilePath))
                    {
                        Files.delete(oldFilePath);
                    }
                }
                catch (IOException e)
                {
                    System.err.println("Error when deleting an old file: " + e.getMessage());
                }
            }
            existingProduct.setImageUrl(null);
        }


        productService.saveProduct(existingProduct);
        redirectAttributes.addFlashAttribute("message", "The product has been successfully updated!");
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes)
    {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent())
        {
            Product product = productOptional.get();
            if (product.getImageUrl() != null && !product.getImageUrl().isEmpty())
            {
                try
                {
                    Path filePath = Paths.get(UPLOAD_DIR + product.getImageUrl().substring(UPLOAD_DIR.length() + 1));
                    if (Files.exists(filePath))
                    {
                        Files.delete(filePath);
                    }
                }
                catch (IOException e)
                {
                    System.err.println("Error when deleting a file: " + e.getMessage());
                }
            }
            productService.deleteProductById(id);
            redirectAttributes.addFlashAttribute("message", "The item has been successfully deleted!");
        }
        else
        {
            redirectAttributes.addFlashAttribute("errorMessage", "Item not found.");
        }
        return "redirect:/products";
    }
}
