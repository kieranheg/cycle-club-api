package com.muscy.api.member;

import java.util.List;

import static java.util.Arrays.asList;

final public class MemberTestUtilities {
    
    public static List<MemberDao> buildMultiMembers() {
        return asList(
                buildMemberDao(1L, "fName2", "lName2", 31),
                buildMemberDao(2L, "fName2", "lName2", 32));
    }
    
    public static MemberDao buildMemberDao(final Long id, final String firstName, final String lastName, final int age) {
        return MemberDao.builder().id(id).firstName(firstName).lastName(lastName).age(age).build();
    }
}
