package com.example.bookmyshow.Models;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_info")
@Data //@Data annotation is used in-place of @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    @NonNull //@NonNull attribute is used to make particular attribute non nullable. (equivalent to nullable =false)
    private String mobNo;
    private String address;

    @Column(unique = true,nullable = false)
    private String email;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> ticketEntityList = new ArrayList<>();

}
