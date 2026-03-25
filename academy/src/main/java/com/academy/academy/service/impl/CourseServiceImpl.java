package com.academy.academy.service.impl;

import com.academy.academy.dto.CourseRequest;
import com.academy.academy.dto.CourseResponse;
import com.academy.academy.dto.UpdateCourseRequest;
import com.academy.academy.entity.Course;
import com.academy.academy.exception.ResourceNotFoundException;
import com.academy.academy.repository.CourseRepository;
import com.academy.academy.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());
        course.setStatus(request.getStatus());

        return toResponse(repository.save(course));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> list() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getById(Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course " + id + " not found"));
        return toResponse(course);
    }

    @Override
    public CourseResponse update(Long id, UpdateCourseRequest request) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course " + id + " not found"));

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

        Course saved = repository.save(course);

        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Course " + id + " not found");
        }
        repository.deleteById(id);
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