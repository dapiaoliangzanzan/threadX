package com.threadx.metrics.server.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * *************************************************<br/>
 * 线程池详情的包装对象<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/7/1 20:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "线程池详情的包装对象", value = "线程池详情的包装对象")
public class ThreadPoolDetailPackVo implements Serializable {
    private static final long serialVersionUID = 2245804401641602304L;

    /**
     * 线程池的具体数据
     */
    @ApiModelProperty(name = "threadPoolDetailsVo", value = "线程池的具体数据")
    private ThreadPoolDetailsVo threadPoolDetailsVo;

    /**
     * 任务的分页数据
     */
    @ApiModelProperty(name = "threadTaskPage", value = "任务的分页数据")
    private ThreadxPage<ThreadTaskVo> threadTaskPage;
}
