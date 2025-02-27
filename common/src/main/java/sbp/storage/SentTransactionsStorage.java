package sbp.storage;

import sbp.dto.TransactionDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SentTransactionsStorage {
    List<TransactionDto> getSentTransactions(long intervalKey);
    boolean isSentTransactionsEmpty();
    Set<Long> getSentTransactionIntervalKeys();
    void putSentTransaction(long intervalKey, TransactionDto transaction);
    void cleanupInterval(Long intervalKey);
}
