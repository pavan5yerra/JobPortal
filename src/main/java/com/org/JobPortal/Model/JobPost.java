package com.org.JobPortal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JobPost {
    @Id
    private int postId;
    private String postProfile;
    private String postDesc;
    private int reqExperience;
    private List<String> postTechStack;

//    public JobPost(){}
//
//
//    public JobPost(int postId, String postProfile, String postDesc, int reqExperience, List<String> postTechStack) {
//        this.postId = postId;
//        this.postProfile = postProfile;
//        this.postDesc = postDesc;
//        this.reqExperience = reqExperience;
//        this.postTechStack = postTechStack;
//    }

    @Override
    public String toString() {
        return "JobPost{" +
                "postId=" + postId +
                ", postProfile='" + postProfile + '\'' +
                ", postDesc='" + postDesc + '\'' +
                ", reqExperience=" + reqExperience +
                ", postTechStack=" + postTechStack +
                '}';
    }

}
