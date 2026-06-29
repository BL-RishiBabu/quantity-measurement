package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
    private static QuantityMeasurementCacheRepository instance;
    private final List<QuantityMeasurementEntity> memoryCache;
    private static final String FILE_PATH = "measurement_history.dat";

    private QuantityMeasurementCacheRepository() {
        this.memoryCache = new ArrayList<>();
        loadFromDisk();
    }

    public static synchronized QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public synchronized void save(QuantityMeasurementEntity entity) {
        memoryCache.add(entity);
        saveToDisk(entity);
    }

    @Override
    public synchronized List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(memoryCache);
    }

    private void saveToDisk(QuantityMeasurementEntity entity) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(memoryCache);
        } catch (IOException ignored) {}
    }

    @SuppressWarnings("unchecked")
    private void loadFromDisk() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<QuantityMeasurementEntity> loaded = (List<QuantityMeasurementEntity>) ois.readObject();
            this.memoryCache.addAll(loaded);
        } catch (Exception ignored) {}
    }
}