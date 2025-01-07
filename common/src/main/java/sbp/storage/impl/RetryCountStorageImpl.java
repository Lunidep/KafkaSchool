package sbp.storage.impl;

import lombok.RequiredArgsConstructor;
import sbp.storage.RetryCountStorage;
import sbp.storage.Storage;

@RequiredArgsConstructor
public class RetryCountStorageImpl implements RetryCountStorage {
    private final Storage storage;

    @Override
    public int getRetryCount(String transactionId) {
        Integer retryCount = storage.getRetryCountMap().get(transactionId);
        if (retryCount == null) {
            retryCount = 0;
        }
        return retryCount;
    }

    @Override
    public void putRetryCount(String transactionId, int retryCount) {
        storage.getRetryCountMap().put(transactionId, retryCount);
    }

}