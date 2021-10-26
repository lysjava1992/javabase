package com.learn.chapter14.quota.host;


import com.learn.chapter14.quota.inmemory.IntervalRegistryCleaner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author james mu
 * @date 2019/8/12 上午9:49
 */
@Component
public class HostIntervalRegistryCleaner extends IntervalRegistryCleaner {

    public HostIntervalRegistryCleaner(HostRequestIntervalRegistry intervalCleaner,
                                       @Value("${quota.host.cleanPeriodMs}") long cleanPeriodMs) {
        super(intervalCleaner, cleanPeriodMs);
    }
}
