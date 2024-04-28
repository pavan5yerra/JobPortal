package com.org.JobPortal.controller;

import com.org.JobPortal.Model.JobPost;
import com.org.JobPortal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="https://localhost:3000")
@RestController
public class JobController {

    @Autowired
    JobService service;

    @RequestMapping({"/","home"})
    public String home(){
        return "Hello this home page";
    }


//    @RequestMapping(method = RequestMethod.POST , value = "/handleform")
    @PostMapping("handleform")
    public String handleForm(JobPost jobPost){
        return "Form Submission is Success"+ jobPost.toString();
    }

    @GetMapping(path="jobposts", produces = {"application/json"})
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @GetMapping("job/{postId}")
    public JobPost getJob(@PathVariable int postId){
        System.out.println("postid"+postId);
        return service.getJob(postId);
    }

    @PostMapping(path="addjob" , consumes = {"application/json"})
    public JobPost  addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
        return  service.getJob(jobPost.getPostId());
    }

    @PutMapping(path="updatejob" , consumes = {"application/json"})
    public String updateJob(@RequestBody JobPost jobPost){
         service.addJob(jobPost);
        return "updated Successfully";
    }

    @DeleteMapping("deletejob/{postId}")
    public String deleteJob(@PathVariable int postId){
        service.deleteJob(postId);
        return "deleted Successfully";
    }

    @PostMapping("loadjobs")
    public String loadData() {
        service.loadData();
        return "Data Loaded Success fully";
    }
}
