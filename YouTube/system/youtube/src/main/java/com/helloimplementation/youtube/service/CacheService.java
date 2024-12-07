package com.helloimplementation.youtube.service;

import com.helloimplementation.youtube.models.Result;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.transactions.TransactionException;

import javax.cache.Cache;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class CacheService<T> {
    private final IgniteCache<UUID, T> cache;

    public CacheService(IgniteCache<UUID, T> cache) {
        this.cache = cache;
    }

    public Result<T> save(UUID id, T data) {
        try {
            cache.put(id, data);
            return Result.success(data);
        } catch (Exception e) {
            return Result.failure(e.getLocalizedMessage());
        }
    }

    public Result<List<T>> getAll() {
        try (QueryCursor<Cache.Entry<Long, T>> cursor = cache.query(new ScanQuery<Long, T>())) {
            return Result.success(cursor.getAll().stream().map(Cache.Entry::getValue).toList());
        } catch (TransactionException e) {
            return Result.failure(e.getLocalizedMessage());
        }
    }

    public Result<T> getItem(UUID id) {
        try {
            return Result.success(cache.get(id));
        } catch (TransactionException e) {
            return Result.failure(e.getLocalizedMessage());
        }
    }

    public Result<T> patchEntry(UUID id, Consumer<T> patcher) {
        Result<T> getItemResult = getItem(id);
        if (getItemResult.isFailure()) {
            return Result.failure(String.format("Could not find entry with id=%s, with original msg=%s", id, getItemResult.getErrMsg()));
        }
        T entry = getItemResult.getData();
        patcher.accept(entry);
        Result<T> savedEntryResult = save(id, entry);
        if (savedEntryResult.isFailure()) {
            return Result.failure(String.format("Could save new entry, with original msg=%s", savedEntryResult.getErrMsg()));
        }
        return Result.success(savedEntryResult.getData());
    }
}
