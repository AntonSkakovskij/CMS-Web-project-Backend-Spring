package com.truba.web_server.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT_TBL")
public class Student {

    @Id
    @GeneratedValue
    @Min(value = 0, message = "ID must be greater than or equal to 0")
    @Max(value = Integer.MAX_VALUE, message = "ID must be less than or equal to Integer.MAX_VALUE")
    private int id;

    @Pattern(regexp = "PZ-21|PZ-22|PZ-23|PZ-24|PZ-25|PZ-26|PZ-27", message = "Invalid group name")
    private String groupName;

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = "^[A-ZА-ЯІЇЄҐ][a-zа-яіїєґ`']*", message = "First name must start with an uppercase letter")
    @Pattern(regexp = "[A-Za-zА-Яа-яІіЇїЄєҐґ`']+", message = "First name contains invalid symbols")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Pattern(regexp = "^[A-ZА-ЯІЇЄҐ][a-zа-яіїєґ`']*", message = "Last name must start with an uppercase letter")
    @Pattern(regexp = "[A-Za-zА-Яа-яІіЇїЄєҐґ`']+", message = "Last name contains invalid symbols")
    private String lastName;


    @Pattern(regexp = "Male|Female", message = "Invalid gender")
    private String gender;

    @NotNull(message = "Birthday cannot be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in the format YYYY-MM-DD")
    private String birthday;

    @AssertTrue(message = "Must be 18 or older")
    public boolean isOver18() {
        if (birthday == null) {
            return false;
        }

        LocalDate birthDate = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);

        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);

        return birthDate.isBefore(eighteenYearsAgo) || birthDate.equals(eighteenYearsAgo);
    }

    @Override
    public String toString(){
        return "{"+"id = " + id    + "\n"
                +"group = " + groupName    + "\n"
                +"first name = " + firstName + "\n"
                +"last name = " + lastName + "\n"
                +"gender = " + gender + "\n"
                +"birthday = " + birthday + "\n"
                +"}";
    }
}
