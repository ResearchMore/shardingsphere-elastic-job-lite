/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.job.lite.integrate.std.dataflow;

import com.dangdang.ddframe.job.lite.api.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.integrate.AbstractBaseStdJobAutoInitTest;
import com.dangdang.ddframe.job.lite.integrate.WaitingUtils;
import com.dangdang.ddframe.job.lite.integrate.fixture.dataflow.StreamingDataflowElasticJob;
import com.dangdang.ddframe.job.lite.util.JobConfigurationUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public final class StreamingDataflowElasticJobForNotMonitorTest extends AbstractBaseStdJobAutoInitTest {
    
    public StreamingDataflowElasticJobForNotMonitorTest() {
        super(StreamingDataflowElasticJob.class);
    }
    
    @Before
    @After
    public void reset() {
        StreamingDataflowElasticJob.reset();
    }
    
    @Override
    protected void setLiteJobConfig(final LiteJobConfiguration liteJobConfig) {
        JobConfigurationUtil.setFieldValue(liteJobConfig, "monitorExecution", false);
        JobConfigurationUtil.setFieldValue(liteJobConfig.getTypeConfig(), "streamingProcess", true);
    }
    
    @Test
    public void assertJobInit() {
        while (!StreamingDataflowElasticJob.isCompleted()) {
            WaitingUtils.waitingShortTime();
        }
        assertFalse(getRegCenter().isExisted("/" + getJobName() + "/execution"));
    }
}
