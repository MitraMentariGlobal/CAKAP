package co.id.cakap.model;

//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;

import java.util.List;

import co.id.cakap.data.BankData;
import co.id.cakap.data.BankDataDao;
import co.id.cakap.data.DaoSession;
import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.data.FirebaseTokenDataDao;
import co.id.cakap.data.JenisKelaminData;
import co.id.cakap.data.JenisKelaminDataDao;
import co.id.cakap.data.NotificationData;
import co.id.cakap.data.NotificationDataDao;
import co.id.cakap.data.ReligionData;
import co.id.cakap.data.ReligionDataDao;
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
    private JenisKelaminDataDao mJenisKelaminDataDao;
    private ReligionDataDao mReligionDataDao;
    private BankDataDao mBankDataDao;

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataLoginDao = daoSession.getResultDataLoginDao();
        mFirebaseTokenDataDao = daoSession.getFirebaseTokenDataDao();
        mNotificationDataDao = daoSession.getNotificationDataDao();
        mJenisKelaminDataDao = daoSession.getJenisKelaminDataDao();
        mReligionDataDao = daoSession.getReligionDataDao();
        mBankDataDao = daoSession.getBankDataDao();
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

    public void insertJenisKelaminData(JenisKelaminData jenisKelaminData){
        mJenisKelaminDataDao.insertOrReplace(jenisKelaminData);
    }

    public List<JenisKelaminData> getAllJenisKelaminData() {
        return mJenisKelaminDataDao.loadAll();
    }

    public void deleteJenisKelaminData() {
        mJenisKelaminDataDao.deleteAll();
    }

    public void insertReligionData(ReligionData religionData){
        mReligionDataDao.insertOrReplace(religionData);
    }

    public List<ReligionData> getAllReligionData() {
        return mReligionDataDao.loadAll();
    }

    public void deleteReligionData() {
        mReligionDataDao.deleteAll();
    }

    public void insertBankData(BankData bankData){
        mBankDataDao.insertOrReplace(bankData);
    }

    public List<BankData> getAllBankData() {
        return mBankDataDao.loadAll();
    }

    public void deleteBankData() {
        mBankDataDao.deleteAll();
    }
}
