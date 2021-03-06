
package com.canal.data;

import com.canal.data.CanalBean;
import com.canal.entity.User;

import java.util.List;


/**
 * Created by ThinkPad on 2021/3/2.
 */

public class UserData extends CanalBean<User> {
    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}

