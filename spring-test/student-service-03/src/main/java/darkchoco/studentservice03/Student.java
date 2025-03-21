package darkchoco.studentservice03;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
    private Long id;

    private String name;

    private int grade;
}
