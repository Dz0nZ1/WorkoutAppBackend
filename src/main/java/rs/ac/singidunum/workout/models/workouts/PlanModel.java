package rs.ac.singidunum.workout.models.workouts;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.singidunum.workout.models.auth.UserModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workout_plans")
@Builder
public class PlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "name")
    private String name;

    @Column(name = "identity")
    private Long identity;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel user;


    @ManyToMany
    @JoinTable(
            name = "plan_exercise",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<ExerciseModel> exercises = new HashSet<>();


    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PropertyModel> properties = new ArrayList<>();





}
