/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 *
 */
package org.wso2.andes.management.common.mbeans;

import org.wso2.andes.management.common.mbeans.annotations.MBeanAttribute;
import org.wso2.andes.management.common.mbeans.annotations.MBeanOperationParameter;

public interface BrokerInformation {
    static final String TYPE = "BrokerInformation";

    @MBeanAttribute(name="Dump Message Status Info" ,description = "Dumping all status of messages processed")
    void dumpMessageStatusInfo(@MBeanOperationParameter(name = "filePath" ,
                                              description = "path to create file") String filePath);

    @MBeanAttribute(name="Dump Subscription Store Info" ,description = "Dumping all status of subscriptions in " +
                                                                       "Subscription Store")
    void dumpSubscriptionStoreInfo(@MBeanOperationParameter(name = "filePath" ,
            description = "path to create file") String filePath);

    @MBeanAttribute(name="Run Andes Recovery Task" ,description = "Runs Andes Recovery Task to sync DB with Subscription" +
                                                                  " Store")
    void executeAndesRecoveryTask();
}
