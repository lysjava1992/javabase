package com.learn.controller;
import com.learn.core.QuartzManager;
import com.learn.entity.SysQuartz;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RequestMapping("quartz" )
@RestController
public class QuartzController {
    /**
     * 获取所有
     * @return
     */
    @GetMapping
    public List<SysQuartz>  quartzList(){
         List<SysQuartz> quartzList=QuartzManager.getAllJobs();
        return quartzList;
    }

    /**
     *  添加
     * @return
     */
    @PostMapping()
    public String quartzSave(String cron,String id){
        Random random=new Random();
        int classId=random.nextInt(3)+1;
        QuartzManager.addJob("Job_"+id,"Job_Group_"+id
                , "Trigger_"+id,"Trigger_Group_"+id
                ,"com.learn.job.Job"+classId,cron);
        return "OK";
    }


    /**
     * 立即执行
     * @return
     */
    @GetMapping("job/{group}/{name}")
    public boolean execute(@PathVariable("group")String group,@PathVariable("name")String name){
        return   QuartzManager.runJob(name, group);
    }

    /**
     * 暂停
     * @param group
     * @param name
     * @return
     * @throws SchedulerException
     */
    @GetMapping("pause/{group}/{name}")
    public boolean pause(@PathVariable("group")String group,@PathVariable("name")String name) throws SchedulerException {
        return QuartzManager.pauseJob(name,group);
    }

    /**
     * 恢复
     * @param group
     * @param name
     * @return
     * @throws SchedulerException
     */
    @GetMapping("resume/{group}/{name}")
    public boolean resume(@PathVariable("group")String group,@PathVariable("name")String name) throws SchedulerException {
        return QuartzManager.resumeJob(name,group);
    }

    @GetMapping("clear")
    public boolean resume() throws Exception {
        return QuartzManager.clear();
    }
    @DeleteMapping("delete/{group}/{name}")
    public boolean delete(@PathVariable("group")String group,@PathVariable("name")String name) throws Exception {
        return QuartzManager.deleteJob(name,group);
    }
}
