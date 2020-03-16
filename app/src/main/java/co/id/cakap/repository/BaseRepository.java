package co.id.cakap.repository;

import co.id.cakap.network.NetworkService;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class BaseRepository {
    protected NetworkService networkService;

    public BaseRepository(NetworkService networkService) {
        this.networkService = networkService;
    }
}
