package github.washistylee.Model.Entitys;

import java.util.ArrayList;

public class Child extends Person{

    private String classroom;
    private String observation;
    private ArrayList <String> diseases;
    private int age;
    private int id;
    private Minder minder;
    private Teacher teacher;
    private Schedule schedule;


    public Child(String name, String surname, String classroom, String observation, ArrayList<String> diseases, int age, int id, Minder minder, Teacher teacher, Schedule schedule) {
        super(name, surname);
        this.classroom = classroom;
        this.observation = observation;
        this.diseases = diseases;
        this.age = age;
        this.id = id;
        this.minder = minder;
        this.teacher = teacher;
        this.schedule=schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Child(){

    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ArrayList<String> getDiseases() {
        return diseases;
    }

    public void setDiseases(ArrayList<String> diseases) {
        this.diseases = diseases;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Minder getMinder() {
        return minder;
    }

    public void setMinder(Minder minder) {
        this.minder = minder;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Child{" +
                "classroom='" + classroom + '\'' +
                ", observation='" + observation + '\'' +
                ", diseases=" + diseases +
                ", age=" + age +
                ", id=" + id +
                ", minder=" + minder +
                ", teacher=" + teacher +
                ", schedule=" + schedule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Child)) return false;
        if (!super.equals(o)) return false;
        Child child = (Child) o;
        return getId() == child.getId();
    }
    public String getDiseasesString() {
        String diseasesAsString = "";
        for (int i = 0; i < diseases.size(); i++) {
            diseasesAsString = diseases.get(i) + diseasesAsString;
            if (i < diseases.size() - 1) {
                diseasesAsString += ", ";
            }
        }

        return diseasesAsString;
    }


}



