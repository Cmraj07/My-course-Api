package com.course.controllers;

import com.course.dto.CourseDto;
import com.course.entity.Course;
import com.course.exception.ResourceNotFoundException;
import com.course.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {


    private CourseService courseService;

    public MyController(CourseService courseService) {
        this.courseService = courseService;
    }

//    @GetMapping("/home")
//    public String home() {
//        return "Welcome to Course Management System";
//    }

    // add courses
        @PostMapping("/addCourse")
        public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    // get course by id
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found: " + courseId));
        return ResponseEntity.ok(course);
    }



    // get all the courses
    @GetMapping("/courses")
    public List<Course> getCourses() {
        List<Course> course  = courseService.getCourses();
        return course;
    }

    // update courses
//    @PutMapping("/updateCourse/{courseId}")
//    public Course updatecourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
//        return courseService.updatecourse(courseId, updatedCourse);
//    }

    @PutMapping("/updateCourse/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
       return courseService.updatedCourse(courseId, courseDto);

    }

    // update courses
    @DeleteMapping("/deleteCourse/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
         courseService.deleteCourse(courseId);
    }

}