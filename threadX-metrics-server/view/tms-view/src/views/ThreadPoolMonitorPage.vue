<template>
    <div>
        <div></div>
        <div>
            <el-descriptions
                class="margin-top"
                :column="6"
                size="small"
                border
            >
                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池所属的服务"
                        placement="top-start"
                        >
                            所属服务
                        </el-tooltip>
                    </template>
                    <el-tag size="small">测试服务</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池所属的实例"
                        placement="top-start"
                        >
                            所属实例
                        </el-tooltip>
                    </template>
                    <el-tag size="small">测试实例</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池的状态，活跃证明线程池正在执行任务，否则是等待！"
                        placement="top-start"
                        >
                            线程池状态
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">活跃</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="给定的健康阈值，当线程池的错误率（包含拒绝任务）(错误数/总数) > 阈值，则触发告警！"
                        placement="top-start"
                        >
                            健康阈值
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">0.89</el-tag>
                </el-descriptions-item>


                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="线程池所属的组名称，生成规则为：类全限定名#创建线程池的方法:创建该线程池的行数"
                        placement="top-start"
                        >
                            所属组名称
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">com.byit.zio.TeestClass#main:32</el-tag>
                </el-descriptions-item>


                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="线程池所属的组名称，生成规则为：所属组名称-对象内存地址"
                        placement="top-start"
                        >
                            线程池名称
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">com.byit.zio.TeestClass#main:32-126372136736187</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后，可并发核心数量"
                        placement="top-start"
                        >
                            核心线程数
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">32</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后，线程池的最大并发数量"
                        placement="top-start"
                        >
                            最大线程数
                        </el-tooltip>
                    </template>
                    <el-tag size="small">64</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后，正在执行任务的线程数量"
                        placement="top-start"
                        >
                            当前活跃数
                        </el-tooltip>
                    </template>
                    <el-tag size="small">18</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始的线程数量，包含没有执行任务的线程还没有来得及被销毁的非核心线程"
                        placement="top-start"
                        >
                            当前线程数量
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">20</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后曾达到的最大的线程数，历史最大数量"
                        placement="top-start"
                        >
                            历史最大线程数
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">63</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后，拒绝执行任务的数量！"
                        placement="top-start"
                        >
                            任务被拒绝次数
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">0</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后，堆积的、执行中的、已经完成的任务的总和"
                        placement="top-start"
                        >
                            任务总计
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">565789</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后，已经完成的任务总数量"
                        placement="top-start"
                        >
                            任务的完成数量
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">565642</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后，空闲时间"
                        placement="top-start"
                        >
                            空闲时间
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">60000</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后, 当前线程池数据的来源的ip"
                        placement="top-start"
                        >
                            采集机器
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">10.0.120.217</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后, 线程池使用的队列类型"
                        placement="top-start"
                        >
                            队列类型
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">java.util.concurrent.ArrayBlockingQueue</el-tag>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template #label>
                        <el-tooltip
                        effect="dark"
                        content="当前线程池从本次监控开始后, 线程池使用的拒绝策略类型"
                        placement="top-start"
                        >
                            拒绝策略
                        </el-tooltip>
                        
                    </template>
                    <el-tag size="small">java.util.concurrent.ThreadPoolExecutor.AbortPolicy</el-tag>
                </el-descriptions-item>





                

                
            </el-descriptions>
        </div>

        <div></div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
export default defineComponent({
    setup () {
        

        return {}
    }
})
</script>

<style scoped>

</style>