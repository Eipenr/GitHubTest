package com.qianfeng.smartdevices.event;



/**
 * Created by jackiechan on 2021/10/15 10:04
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */

public class PowerCommandEvent extends BaseCommandEvent{


    public PowerCommandEvent(String uuid, String command) {
        super(uuid, command);
    }
}
