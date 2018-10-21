package com.muscy.api.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="members")
public class MemberDao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="FIRSTNAME")
    private String firstName;
    
    @Column(name="LASTNAME")
    private String lastName;
    
    @Column(name="AGE")
    private int age;
    
    @Override
    public String toString() {
        return String.format(
                "Member[id=%d, firstName='%s', lastName='%s', 'age=%d]",
                id, firstName, lastName, age);
    }
    
}
