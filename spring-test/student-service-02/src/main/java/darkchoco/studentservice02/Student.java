package darkchoco.studentservice02;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "sts_student")  // 이렇게 안 해주면 hibenate는 무조건 'Student'로만 찾는다. 없으면 에러 발생하고.
@Table(name = "sts_student")
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private boolean active;

    private int grade;
}
