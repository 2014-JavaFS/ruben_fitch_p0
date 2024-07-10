package com.revature.cra.Course;

public class Course {
    // Fields
    private int courseId;
    private String subject;
    private int courseNumber;
    private String courseName;
    private String professor;
    private String description;
    private short capacity;
    private short numRegistered;

    // Constructors
    /**
     * Default Constructor
     */
    public Course(){}

    /**
     * All args Constructor
     * @param courseId - course's ID
     * @param subject - course's subject
     * @param courseNumber - course's number
     * @param courseName - course's name
     * @param professor - course's professor
     * @param description - course's description
     * @param capacity - course's capacity
     * @param numRegistered - course's current number of registered students
     */
    public Course(int courseId, String subject, int courseNumber, String courseName, String professor, String description, short capacity, short numRegistered) {
        this.courseId = courseId;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.professor = professor;
        this.description = description;
        this.capacity = capacity;
        this.numRegistered = numRegistered;
    }

    // Methods
    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getCapacity() {
        return capacity;
    }

    public void setCapacity(short capacity) {
        this.capacity = capacity;
    }

    public short getNumRegistered() {
        return numRegistered;
    }

    public void setNumRegistered(short numRegistered) {
        this.numRegistered = numRegistered;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", subject='" + subject + '\'' +
                ", courseNumber=" + courseNumber +
                ", courseName='" + courseName + '\'' +
                ", professor='" + professor + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", numRegistered=" + numRegistered +
                '}';
    }

    // Custom Methods
    // TODO: More custom methods
    public boolean isFull(){
        if (numRegistered < capacity){
            return true;
        }
        return false;
    }
}
