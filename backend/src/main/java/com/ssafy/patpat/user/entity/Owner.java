package com.ssafy.patpat.user.entity;

import com.ssafy.patpat.shelter.entity.Shelter;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long ownerId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "owner")
    private Shelter shelter;

}
