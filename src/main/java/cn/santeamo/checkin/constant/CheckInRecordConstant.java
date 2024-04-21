package cn.santeamo.checkin.constant;

public interface CheckInRecordConstant {

    /**
     * 打卡状态
     */
    interface WorkInState {
        /**
         * 0：未打卡
         * 1：正常
         * 2：迟到
         */
        Integer NOT_CHECK_IN = 0;
        Integer NORMAL = 1;
        Integer LATE = 2;
    }

    /**
     * 打卡状态
     */
    interface WorkOutState {
        /**
         * 0：未打卡
         * 1：正常
         * 2：早退
         */
        Integer NOT_CHECK_IN = 0;
        Integer NORMAL = 1;
        Integer LEAVE_EARLY = 2;
    }
}
