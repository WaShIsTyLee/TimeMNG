package github.washistylee.Model.Entitys;

import java.util.List;

public class Child extends Person{

    private String classroom;
    private String observation;
    private List <String> diseases;
    private int age;
    private int id;
    private Minder minder;
    private Teacher teacher;
    private List <Schedule> schedule;

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Child(String name, String surname, String classroom, String observation, List<String> diseases, int age, int id, Minder minder, Teacher teacher, List <Schedule> schedule) {
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



    public Child(String name, String surname, String classroom, String observation, List<String> diseases, int age, int id, Minder minder, Teacher teacher) {
        super(name, surname);
        this.classroom = classroom;
        this.observation = observation;
        this.diseases = diseases;
        this.age = age;
        this.id = id;
        this.minder = minder;
        this.teacher = teacher;
    }

    public Child(String name, String surname, String classroom, String observation, List<String> diseases, int age,
                 Minder minder, Teacher teacher) {
        super(name, surname);
        this.classroom = classroom;
        this.observation = observation;
        this.diseases = diseases;
        this.age = age;
        this.minder = minder;
        this.teacher = teacher;

    }

    public Child(String name, String surname, String email, String password, String classroom, String observation, int age, int id, Minder minder, Teacher teacher, List <Schedule> schedule) {
        super(name, surname, email, password);
        this.classroom = classroom;
        this.observation = observation;
        this.age = age;
        this.id = id;
        this.minder = minder;
        this.teacher = teacher;
        this.schedule = schedule;
    }

    public Child(String name, String surname, String classroom, String observation, int age, Minder minder, Teacher teacher) {
        super(name, surname);
        this.classroom = classroom;
        this.observation = observation;
        this.age = age;
        this.minder = minder;
        this.teacher = teacher;
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

    public List<String> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<String> diseases) {
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
            diseasesAsString += diseases.get(i);
            if (i < diseases.size() - 1) {
                diseasesAsString += ", ";
            }
        }

        return diseasesAsString;
    }
    public static boolean isString(String text) {
        boolean aux = false;
        String regex = "^[a-zA-Z\\s]+$";
        if (text.matches(regex)) {
            aux = true;
        }
        return aux;
    }
    public static boolean isNumber(String text) {
        boolean aux = false;
        String regex = "^(?:100|[1-9]?[0-9])$";
        if (text.matches(regex)) {
            aux = true;
        }
        return aux;
    }


}



