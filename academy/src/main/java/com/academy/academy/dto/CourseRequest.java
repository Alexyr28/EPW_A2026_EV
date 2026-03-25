package com.academy.academy.dto;

import com.academy.academy.entity.CourseLevel;
import com.academy.academy.entity.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CourseRequest {

    @NotBlank(message = "name is required")
    @Size(max = 150, message = "name must be <= 150 chars")
    private String name;

    @Size(max = 2000, message = "description must be <= 2000 chars")
    private String description;

    @NotNull(message = "price is required")
    @Positive(message = "price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "duration is required")
    @Positive(message = "duration must be greater than 0")
    private Integer duration;

    private CourseLevel level = CourseLevel.BEGINNER;

    private CourseStatus status = CourseStatus.ACTIVE;

    // Getters y Setters
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public CourseLevel getLevel() {
        return level;
    }

    public void setLevel(CourseLevel level) {
        this.level = level;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}
