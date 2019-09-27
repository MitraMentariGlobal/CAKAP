package co.id.cakap.model;

//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;

import java.util.List;

import co.id.cakap.data.DaoSession;
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

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataDao = daoSession.getResultDataDao();
        mResultDataLoginDao = daoSession.getResultDataLoginDao();
    }

//    @NonNull
    public void insertData(ResultData resultData){
        mResultDataDao.insertOrReplace(resultData);
    }

//    @Nullable
    public List<ResultData> getAllData(){
        return mResultDataDao.loadAll();
    }

    public void deleteDataList() {
        mResultDataDao.deleteAll();
    }

    public void insertResultDataLogin(ResultDataLogin resultDataLogin){
        mResultDataLoginDao.insertOrReplace(resultDataLogin);
    }

    public List<ResultDataLogin> getAllResultDataLogin(){
        try {
            return mResultDataLoginDao.loadAll();
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteResultDataLogin() {
        try {
            mResultDataLoginDao.deleteAll();
        } catch (Exception e) {

        }
    }
}
