package com.org.JobPortal.service;

import com.org.JobPortal.Model.JobPost;
import com.org.JobPortal.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepo repo;


    public void loadData() {
        List<JobPost> jobs  = new ArrayList<>(
            List.of(
                    new JobPost(1,"Full Stack Developer" , "Super Expert needed" , 6, new ArrayList<>(Arrays.asList("Java","React","Spring","SpringBoot"))),
                    new JobPost(2,"Full Stack Developer" , "Super Expert needed" , 6, new ArrayList<>(Arrays.asList("Java","React","Spring","SpringBoot"))),
                    new JobPost(3,"Full Stack Developer" , "Super Expert needed" , 6, new ArrayList<>(Arrays.asList("Java","React","Spring","SpringBoot")))
            ));
        repo.saveAll(jobs);
    }

    public void addJob(JobPost jobpost){
        repo.save(jobpost);
    }

    public List<JobPost> getAllJobs(){
//        System.out.println(repo.findAll());
        return repo.findAll();
    }

    public JobPost getJob(int postId) {
        return repo.findById(postId).orElse(new JobPost());
    }

    public void updateJob(JobPost jobpost){
         repo.save(jobpost);
    }

    public void deleteJob(int postId){
         repo.deleteById(postId);
    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword , keyword);
    }
}
