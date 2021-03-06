
package com.canal.data;

import com.canal.data.CanalBean;
import com.canal.entity.TbCommodityInfo;

import java.util.List;


/**
 * Created by ThinkPad on 2021/3/2.
 */

public class TbCommodityInfoData extends CanalBean<TbCommodityInfo> {

    //数据
    private List<TbCommodityInfo> data;

    public List<TbCommodityInfo> getData() {
        return data;
    }

    public void setData(List<TbCommodityInfo> data) {
        this.data = data;
    }
}

