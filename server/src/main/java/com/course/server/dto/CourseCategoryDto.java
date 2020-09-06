package com.course.server.dto;


import com.course.server.domain.Category;

import java.util.List;

public class CourseCategoryDto {

    /**
     * id
     */
    private String id;

    /**
     * 课程|course.id
     */
    private String courseId;




    private String categoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "CourseCategoryDto{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }

    /**
     * 分类|course.id
     */

}