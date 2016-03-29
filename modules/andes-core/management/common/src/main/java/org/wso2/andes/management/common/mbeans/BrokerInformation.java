/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.andes.management.common.mbeans;

import org.wso2.andes.management.common.mbeans.annotations.MBeanAttribute;
import org.wso2.andes.management.common.mbeans.annotations.MBeanOperationParameter;

/**
 * Interface for the JMX MBeans for getting details of the broker for debugging purposes.
 */
public interface BrokerInformation {
    String TYPE = "BrokerInformation";

    /**
     * Writes the message statuses residing in
     * {@link org.wso2.andes.kernel.slot.SlotDeliveryWorkerManager#slotDeliveryWorkerMap} to a file.
     *
     * @param filePath Location of the file path.
     */
    @MBeanAttribute(name = "Dump Message Status Info", description = "Dumping all status of messages processed")
    void dumpMessageStatusInfo(@MBeanOperationParameter(name = "filePath",
            description = "path to create file") String filePath);

    /**
     * Writes the subscription details in the {@link org.wso2.andes.subscription.SubscriptionStore} to a file.
     * @param filePath Location of the file path.
     */
    @MBeanAttribute(name = "Dump Subscription Store Info", description = "Dumping all status of subscriptions in " +
                                                                         "Subscription Store")
    void dumpSubscriptionStoreInfo(@MBeanOperationParameter(name = "filePath",
            description = "path to create file") String filePath);

    /**
     * Runs AndesRecoveryTask.
     */
    @MBeanAttribute(name = "Run Andes Recovery Task", description = "Runs Andes Recovery Task to sync DB with " +
                                                                    "Subscription" +
                                                                    " Store")
    void executeAndesRecoveryTask();
}
