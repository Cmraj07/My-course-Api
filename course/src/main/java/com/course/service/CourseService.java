package com.course.service;

import com.course.dto.CourseDto;
import com.course.entity.Course;
import com.course.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course) {
    return courseRepository.save(course);
    }


    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }


    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

//    public Course updatecourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
//        Course existingCourse = courseRepository.findById(courseId).get();
//
//        existingCourse.setTitle(updatedCourse.getTitle());
//        existingCourse.setDescription(updatedCourse.getDescription());
//        return courseRepository.save(existingCourse);
//    }

    public Course updatedCourse(Long courseId, CourseDto courseDto){
        Optional<Course> opCourse = courseRepository.findById(courseId);
        Course course = opCourse.get();

        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
       return courseRepository.save(course);
    }

    public String deleteCourse(Long courseId) {
         courseRepository.deleteById(courseId);
         return "Deleted";
    }
}
