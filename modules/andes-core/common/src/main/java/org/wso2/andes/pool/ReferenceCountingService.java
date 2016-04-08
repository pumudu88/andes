package org.wso2.andes.pool;

import java.util.concurrent.ExecutorService;

/**
 * Manages the reference counts
 */
public interface ReferenceCountingService {
    public ExecutorService getPool();
}
