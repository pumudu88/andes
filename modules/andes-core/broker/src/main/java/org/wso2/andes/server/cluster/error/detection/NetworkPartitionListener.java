package org.wso2.andes.server.cluster.error.detection;

/**
 * Defines contractual obligations for an entity interesting in knowing about
 * network partitions during runtime.
 */
public interface NetworkPartitionListener {

    /**
     * Invoked when a current cluster size becomes less than expected minimum
     * 
     * @param currentNodeCount
     *            current size of the cluster
     */
    public void minimumNodeCountNotFulfilled(int currentNodeCount);

    /**
     * Invoked when a current cluster size become equal to expected minimum
     * 
     * @param currentNodeCount
     *            current size of the cluster
     */
    public void minimumNodeCountFulfilled(int currentNodeCount);

}
