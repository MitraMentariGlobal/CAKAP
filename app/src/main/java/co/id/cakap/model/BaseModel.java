package co.id.cakap.model;

import co.id.cakap.data.DaoSession;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class BaseModel {
    protected final DaoSession mDaoSession;

    public BaseModel(DaoSession daoSession) {
        mDaoSession = daoSession;
    }
}
