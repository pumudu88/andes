/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.andes.kernel.subscription;

import org.wso2.andes.kernel.AndesContent;
import org.wso2.andes.kernel.AndesException;
import org.wso2.andes.kernel.AndesMessageMetadata;
import org.wso2.andes.kernel.ProtocolMessage;

import java.util.UUID;

/**
 * Contract defining subscription related to outbound operations.
 */
public interface OutboundSubscription {

    /**
     * Forcefully disconnects protocol subscriber from server. This is called when a server admin wants to disconnect a
     * subscriber using management console.
     *
     * @throws AndesException
     */
    void forcefullyDisconnect() throws AndesException;

    /**
     * Check if message is accepted by 'selector' set to the subscription.
     *
     * @param messageMetadata message to be checked
     * @return true if message is selected, false otherwise
     * @throws AndesException on an error
     */
    boolean isMessageAcceptedBySelector(AndesMessageMetadata messageMetadata)
            throws AndesException;

    /**
     * Deliver the message and content to the subscriber
     *
     * @param messageMetadata metadata of the message
     * @param content         content of the message
     * @return delivery is success. If delivery rule evaluations are failed delivery will not be a success
     * @throws org.wso2.andes.kernel.AndesException
     */
    boolean sendMessageToSubscriber(ProtocolMessage messageMetadata, AndesContent content) throws
            AndesException;

    /**
     * Check if subscription is active. If the underlying channel can accept
     * messages it is considered as live
     *
     * @return true if subscriber is active
     */
    boolean isOutboundConnectionLive();

    /**
     * Get ID of the subscription channel
     *
     * @return unique id
     */
    UUID getChannelID();

    /**
     * Get time of subscription is made to the broker
     *
     * @return time as number of milliseconds elapsed from 1/1/1970
     */
    long getSubscribeTime();

}
