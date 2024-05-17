package todolist.todolist.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Task {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    private String name;
    private String description;

    @CreatedDate
    private LocalDateTime createdDate;

    private LocalDateTime conclusionDate;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
