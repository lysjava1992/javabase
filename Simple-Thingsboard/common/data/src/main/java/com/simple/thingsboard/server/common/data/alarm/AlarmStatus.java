package com.simple.thingsboard.server.common.data.alarm;


public enum AlarmStatus {
    /**
     * 活跃、未确认的报警
     */
    ACTIVE_UNACK,
    /**
     * 活跃、已确认的报警
     */
    ACTIVE_ACK,
    /**
     * 未确认、已清除的报警
     */
    CLEARED_UNACK,
    /**
     * 确认、已清除报警
     */
    CLEARED_ACK;
}
