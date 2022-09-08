package com.example.practiceapp06;

public class Course {

    private String _CourseName = null;
    private int _ImgPath = 0;
    public Course(String courseName,int imgPath){
        this._CourseName = courseName;
        this._ImgPath = imgPath;

    }

    public void set_ImgPath(int _ImgPath) {
        this._ImgPath = _ImgPath;
    }

    public void set_CourseName(String _CourseName) {
        this._CourseName = _CourseName;
    }

    public String get_CourseName() {
        return _CourseName;
    }

    public int get_ImgPath() {
        return _ImgPath;
    }
}
