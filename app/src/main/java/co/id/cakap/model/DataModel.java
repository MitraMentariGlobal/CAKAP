package co.id.cakap.model;

//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;

import java.util.List;

import co.id.cakap.data.DaoSession;
import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.data.FirebaseTokenDataDao;
import co.id.cakap.data.NotificationData;
import co.id.cakap.data.NotificationDataDao;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.data.ResultDataLoginDao;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class DataModel extends BaseModel {
    private ResultDataLoginDao mResultDataLoginDao;
    private FirebaseTokenDataDao mFirebaseTokenDataDao;
    private NotificationDataDao mNotificationDataDao;

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataLoginDao = daoSession.getResultDataLoginDao();
        mFirebaseTokenDataDao = daoSession.getFirebaseTokenDataDao();
        mNotificationDataDao = daoSession.getNotificationDataDao();
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

    public void insertNotificationData(NotificationData notificationData){
        mNotificationDataDao.insertOrReplace(notificationData);
    }

    public List<NotificationData> getAllNotificationData() {
        return mNotificationDataDao.loadAll();
    }

    public void deleteNotificationData() {
        mNotificationDataDao.deleteAll();
    }
}
