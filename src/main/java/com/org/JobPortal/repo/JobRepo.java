package com.org.JobPortal.repo;

import com.org.JobPortal.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer > {

//    List<JobPost> jobs  = new ArrayList<>(
//            Arrays.asList(
//                        new JobPost(1,"Full Stack Developer" , "Super Expert needed" , 6, new ArrayList<>(Arrays.asList("Java","React","Spring","SpringBoot")))
//                )
//            );
//
//    public List<JobPost> getAllJobs(){
//        return jobs;
//    }
//
//    public void addJob(JobPost job){
//        jobs.add(job);
//        System.out.println(jobs);
//    }
//
//    public JobPost getJob(int postId) {
//        System.out.println(jobs);
//        List<JobPost> job = jobs.stream().filter(jobItem -> jobItem.getPostId()==postId).toList();
//        return job.get(0);
//    }
}
