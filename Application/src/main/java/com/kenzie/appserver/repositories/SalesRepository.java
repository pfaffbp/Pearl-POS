/*
package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.SalesRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;



<<<<<<< HEAD
public class SalesRepository {
}
=======
    */
/**
     * @param entity must not be {@literal null}.
     * @param <S>
     * @return
     *//*

    @Override
    public <S extends Sales> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Sales> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Sales> findById(String s) {
        for (Sales record : records) {
            if (record.getId().equals(s)) {
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return findById(s).isPresent();
    }

    @Override
    public Iterable<Sales> findAll() {
        return records;
    }

    @Override
    public Iterable<Sales> findAllById(Iterable<String> strings) {
        List<Sales> foundRecords = new ArrayList<>();
        for (String id : strings) {
            findById(id).ifPresent(foundRecords::add);
        }
        return foundRecords;
    }

    @Override
    public long count() {
        return records.size();
    }

    @Override
    public void deleteById(String s) {
        records.removeIf(record -> record.getId().equals(s));
    }

    @Override
    public void delete(Sales entity) {
        records.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        for (String id : strings) {
            deleteById(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Sales> entities) {
        for (Sales entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        records.clear();
    }

    public void save(SalesRecord salesRecord) {


    }
}
*/
>>>>>>> d6bc43a (preping to merge)
