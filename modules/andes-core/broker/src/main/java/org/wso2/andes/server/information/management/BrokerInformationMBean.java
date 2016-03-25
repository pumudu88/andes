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

package org.wso2.andes.server.information.management;

import org.wso2.andes.kernel.AndesContext;
import org.wso2.andes.kernel.AndesException;
import org.wso2.andes.kernel.AndesRecoveryTask;
import org.wso2.andes.kernel.slot.SlotDeliveryWorkerManager;
import org.wso2.andes.management.common.mbeans.BrokerInformation;
import org.wso2.andes.management.common.mbeans.annotations.MBeanConstructor;
import org.wso2.andes.management.common.mbeans.annotations.MBeanOperationParameter;
import org.wso2.andes.server.ClusterResourceHolder;
import org.wso2.andes.server.management.AMQManagedObject;

import java.io.File;
import java.io.IOException;
import javax.management.JMException;

public class BrokerInformationMBean extends AMQManagedObject
        implements BrokerInformation {


    @MBeanConstructor("Creates an MBean exposing an Cluster Manager")
    public BrokerInformationMBean() throws JMException {
        super(BrokerInformation.class, BrokerInformation.TYPE);
    }

    @Override
    public String getObjectInstanceName() {
        return BrokerInformation.TYPE;
    }

    @Override
    public void dumpMessageStatusInfo(
            @MBeanOperationParameter(name = "filePath", description = "path to dump " +
                                                                      "message status info")
                                                                       String filePath) {
        try {
            File fileToWriteMessageStatus = new File(filePath);
            if (fileToWriteMessageStatus.exists()) {
                fileToWriteMessageStatus.delete();
            }
            fileToWriteMessageStatus.getParentFile().mkdirs();
            fileToWriteMessageStatus.createNewFile();


            //Get slot delivery workers
            SlotDeliveryWorkerManager.getInstance().dumpAllSlotInformationToFile(fileToWriteMessageStatus);

        } catch (AndesException e) {
            throw new RuntimeException("Internal error while dumping message status", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a file to dump message status", e);
        }

    }

    @Override
    public void dumpSubscriptionStoreInfo(@MBeanOperationParameter(name = "filePath",
            description = "path to create file") String filePath) {
        try {
            File fileToWriteSubscriptionStoreInfo = new File(filePath);
            if (fileToWriteSubscriptionStoreInfo.exists()) {
                fileToWriteSubscriptionStoreInfo.delete();
            }
            fileToWriteSubscriptionStoreInfo.getParentFile().mkdirs();
            fileToWriteSubscriptionStoreInfo.createNewFile();

            AndesContext.getInstance().getSubscriptionStore().dumpSubscriptionStoreInfo(filePath);

        } catch (AndesException e) {
            throw new RuntimeException("Internal error while dumping subscription store info", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a file to dump subscription store info", e);
        }
    }

    @Override
    public void executeAndesRecoveryTask() {
        AndesRecoveryTask andesRecoveryTask = ClusterResourceHolder.getInstance().getAndesRecoveryTask();
        andesRecoveryTask.executeNow();
    }
}
