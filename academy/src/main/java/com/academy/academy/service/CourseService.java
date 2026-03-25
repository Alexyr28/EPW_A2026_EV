package com.academy.academy.service;

import com.academy.academy.dto.CourseRequest;
import com.academy.academy.dto.CourseResponse;
import com.academy.academy.dto.UpdateCourseRequest;
import com.academy.academy.entity.Course;
import com.academy.academy.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponse createCourse(CourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());
        course.setStatus(request.getStatus());

        Course saved = courseRepository.save(course);
        return toResponse(saved);
    }

    public CourseResponse updateCourse(Long id, UpdateCourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        course.setName(request.getName());

        if (request.getDescription() != null)
            course.setDescription(request.getDescription());
        if (request.getPrice() != null)
            course.setPrice(request.getPrice());
        if (request.getDuration() != null)
            course.setDuration(request.getDuration());
        if (request.getLevel() != null)
            course.setLevel(request.getLevel());
        if (request.getStatus() != null)
            course.setStatus(request.getStatus());

        Course updated = courseRepository.save(course);
        return toResponse(updated);
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CourseResponse toResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setName(course.getName());
        response.setDescription(course.getDescription());
        response.setPrice(course.getPrice());
        response.setDuration(course.getDuration());
        response.setLevel(course.getLevel());
        response.setStatus(course.getStatus());
        response.setCreatedAt(course.getCreatedAt());
        response.setUpdatedAt(course.getUpdatedAt());
        return response;
    }
}