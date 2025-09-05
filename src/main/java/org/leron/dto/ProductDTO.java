package org.leron.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDTO
{
    private Long id;

    @NotBlank(message = "The title cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "The price can't be empty")
    @DecimalMin(value = "0.01", message = "The price must be greater than 0")
    private Double price;

    @NotNull(message = "The stock quantity cannot be empty")
    @Min(value = 0, message = "Stock quantities cannot be negative")
    private Integer stockQuantity;



    private MultipartFile imageFile;
    public MultipartFile getImageFile() {
        return imageFile;
    }

    @Getter
    private String imageUrl;
    public String getImageUrl()
    {
        return imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
