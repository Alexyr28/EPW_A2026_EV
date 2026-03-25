package com.academy.academy.service;

import com.academy.academy.dto.CourseRequest;
import com.academy.academy.dto.CourseResponse;
import com.academy.academy.dto.UpdateCourseRequest;

import java.util.List;

public interface CourseService {
    CourseResponse create(CourseRequest request);

    List<CourseResponse> list();

    CourseResponse getById(Long id);

    CourseResponse update(Long id, UpdateCourseRequest request);

    void delete(Long id);
}