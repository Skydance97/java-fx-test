package by.silverscreen.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Human implements Serializable {

    private static final long serialVersionUID = 1L;

    private SimpleStringProperty name;
    private SimpleIntegerProperty age;
    private SimpleObjectProperty<LocalDate> birthday;

    public Human() {
    }

    public Human(SimpleStringProperty name, SimpleIntegerProperty age, SimpleObjectProperty<LocalDate> birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public Human(String name, int age, LocalDate birthday) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.birthday = new SimpleObjectProperty<>(birthday);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) &&
                Objects.equals(age, human.age) &&
                Objects.equals(birthday, human.birthday);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, birthday);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name=" + name +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
