package com.muscy.api.member;

import com.muscy.api.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Entity
@Data
@AllArgsConstructor
@Table(name="members")
public class MemberDao extends Auditable<String> {
    
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
    
    public MemberDao(final String firstName, final String lastName, final int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return String.format(
                "[Member[id=%d, firstName='%s', lastName='%s', 'age=%d]",
                id, firstName, lastName, age);
    }
    
}
