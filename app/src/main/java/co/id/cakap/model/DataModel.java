package co.id.cakap.model;

//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;

import java.util.List;

import co.id.cakap.data.DaoSession;
import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.data.FirebaseTokenDataDao;
import co.id.cakap.data.ResultData;
import co.id.cakap.data.ResultDataDao;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.data.ResultDataLoginDao;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class DataModel extends BaseModel {
    private ResultDataDao mResultDataDao;
    private ResultDataLoginDao mResultDataLoginDao;
    private FirebaseTokenDataDao mFirebaseTokenDataDao;

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataDao = daoSession.getResultDataDao();
        mResultDataLoginDao = daoSession.getResultDataLoginDao();
        mFirebaseTokenDataDao = daoSession.getFirebaseTokenDataDao();
    }

//    @NonNull
    public void insertData(ResultData resultData){
        mResultDataDao.insertOrReplace(resultData);
    }

//    @Nullable
    public List<ResultData> getAllData() {
        return mResultDataDao.loadAll();
    }

    public void deleteDataList() {
        mResultDataDao.deleteAll();
    }

    public void insertResultDataLogin(ResultDataLogin resultDataLogin){
        mResultDataLoginDao.insertOrReplace(resultDataLogin);
    }

    public List<ResultDataLogin> getAllResultDataLogin() {
        return mResultDataLoginDao.loadAll();
    }

    public void deleteResultDataLogin() {
        mResultDataLoginDao.deleteAll();
    }

    public void insertFirebaseTokenData(FirebaseTokenData firebaseTokenData){
        mFirebaseTokenDataDao.insertOrReplace(firebaseTokenData);
    }

    public List<FirebaseTokenData> getAllFirebaseTokenData() {
        return mFirebaseTokenDataDao.loadAll();
    }

    public void deleteFirebaseTokenData() {
        mFirebaseTokenDataDao.deleteAll();
    }
}
