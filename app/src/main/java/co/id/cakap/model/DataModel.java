package co.id.cakap.model;

//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;

import java.util.List;

import co.id.cakap.data.BankData;
import co.id.cakap.data.BankDataDao;
import co.id.cakap.data.BankInfoData;
import co.id.cakap.data.BankInfoDataDao;
import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.CashbillSuccessDataDao;
import co.id.cakap.data.DaoSession;
import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.data.FirebaseTokenDataDao;
import co.id.cakap.data.InvoiceToMbSuccessData;
import co.id.cakap.data.InvoiceToMbSuccessDataDao;
import co.id.cakap.data.JenisKelaminData;
import co.id.cakap.data.JenisKelaminDataDao;
import co.id.cakap.data.KotaData;
import co.id.cakap.data.KotaDataDao;
import co.id.cakap.data.NotificationData;
import co.id.cakap.data.NotificationDataDao;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.OperationUserStatusDataDao;
import co.id.cakap.data.ProvinsiData;
import co.id.cakap.data.ProvinsiDataDao;
import co.id.cakap.data.ReligionData;
import co.id.cakap.data.ReligionDataDao;
import co.id.cakap.data.ReqInvoiceToBcSuccessData;
import co.id.cakap.data.ReqInvoiceToBcSuccessDataDao;
import co.id.cakap.data.ReqInvoiceToCompanySuccessData;
import co.id.cakap.data.ReqInvoiceToCompanySuccessDataDao;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.data.ResultDataLoginDao;
import co.id.cakap.data.SubmitCashbillData;
import co.id.cakap.data.SubmitCashbillDataDao;

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
    private BankInfoDataDao mBankInfoDataDao;
    private ProvinsiDataDao mProvinsiDataDao;
    private KotaDataDao mKotaDataDao;
    private SubmitCashbillDataDao mSubmitCashbillDataDao;
    private CashbillSuccessDataDao mCashbillSuccessDataDao;
    private InvoiceToMbSuccessDataDao mInvoiceToMbSuccessDataDao;
    private ReqInvoiceToCompanySuccessDataDao mReqInvoiceToCompanySuccessDataDao;
    private ReqInvoiceToBcSuccessDataDao mReqInvoiceToBcSuccessDataDao;

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataLoginDao = daoSession.getResultDataLoginDao();
        mFirebaseTokenDataDao = daoSession.getFirebaseTokenDataDao();
        mNotificationDataDao = daoSession.getNotificationDataDao();
        mJenisKelaminDataDao = daoSession.getJenisKelaminDataDao();
        mReligionDataDao = daoSession.getReligionDataDao();
        mBankDataDao = daoSession.getBankDataDao();
        mBankInfoDataDao = daoSession.getBankInfoDataDao();
        mProvinsiDataDao = daoSession.getProvinsiDataDao();
        mKotaDataDao = daoSession.getKotaDataDao();
        mSubmitCashbillDataDao = daoSession.getSubmitCashbillDataDao();
        mCashbillSuccessDataDao = daoSession.getCashbillSuccessDataDao();
        mInvoiceToMbSuccessDataDao = daoSession.getInvoiceToMbSuccessDataDao();
        mReqInvoiceToCompanySuccessDataDao = daoSession.getReqInvoiceToCompanySuccessDataDao();
        mReqInvoiceToBcSuccessDataDao = daoSession.getReqInvoiceToBcSuccessDataDao();
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

    public void updateNotificationData(NotificationData notificationData){
        mNotificationDataDao.update(notificationData);
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

    public void insertBankInfoData(BankInfoData bankInfoData){
        mBankInfoDataDao.insertOrReplace(bankInfoData);
    }

    public List<BankInfoData> getAllBankInfoData() {
        return mBankInfoDataDao.loadAll();
    }

    public void deleteBankInfoData() {
        mBankInfoDataDao.deleteAll();
    }

    public void insertProvinsiData(ProvinsiData provinsiData){
        mProvinsiDataDao.insertOrReplace(provinsiData);
    }

    public List<ProvinsiData> getAllProvinsiData() {
        return mProvinsiDataDao.loadAll();
    }

    public void deleteProvinsiData() {
        mProvinsiDataDao.deleteAll();
    }

    public void insertKotaData(KotaData kotaData){
        mKotaDataDao.insertOrReplace(kotaData);
    }

    public List<KotaData> getAllKotaData() {
        return mKotaDataDao.loadAll();
    }

    public void deleteKotaData() {
        mKotaDataDao.deleteAll();
    }

    public void insertCashbillSuccessData(SubmitCashbillData submitCashbillData){
        mSubmitCashbillDataDao.insertOrReplace(submitCashbillData);
    }

    public List<SubmitCashbillData> getAllCashbillSuccessData() {
        return mSubmitCashbillDataDao.loadAll();
    }

    public void deleteCashbillSuccessData() {
        mSubmitCashbillDataDao.deleteAll();
    }

    public void insertCashbillSuccessDetailData(CashbillSuccessData cashbillSuccessData){
        mCashbillSuccessDataDao.insertOrReplace(cashbillSuccessData);
    }

    public List<CashbillSuccessData> getAllCashbillSuccessDetailData() {
        return mCashbillSuccessDataDao.loadAll();
    }

    public void deleteCashbillSuccessDetailData() {
        mCashbillSuccessDataDao.deleteAll();
    }

    public void insertInvoiceToMbSuccessDetailData(InvoiceToMbSuccessData invoiceToMbSuccessData){
        mInvoiceToMbSuccessDataDao.insertOrReplace(invoiceToMbSuccessData);
    }

    public List<InvoiceToMbSuccessData> getAllInvoiceToMbSuccessDetailData() {
        return mInvoiceToMbSuccessDataDao.loadAll();
    }

    public void deleteInvoiceToMbSuccessDetailData() {
        mInvoiceToMbSuccessDataDao.deleteAll();
    }

    public void insertReqInvoiceToCompanySuccessData(ReqInvoiceToCompanySuccessData reqInvoiceToCompanySuccessData){
        mReqInvoiceToCompanySuccessDataDao.insertOrReplace(reqInvoiceToCompanySuccessData);
    }

    public List<ReqInvoiceToCompanySuccessData> getAllReqInvoiceToCompanySuccessData() {
        return mReqInvoiceToCompanySuccessDataDao.loadAll();
    }

    public void deleteReqInvoiceToCompanySuccessData() {
        mReqInvoiceToCompanySuccessDataDao.deleteAll();
    }

    public void insertReqInvoiceToBcSuccessData(ReqInvoiceToBcSuccessData reqInvoiceToBcSuccessData){
        mReqInvoiceToBcSuccessDataDao.insertOrReplace(reqInvoiceToBcSuccessData);
    }

    public List<ReqInvoiceToBcSuccessData> getAllReqInvoiceToBcSuccessData() {
        return mReqInvoiceToBcSuccessDataDao.loadAll();
    }

    public void deleteReqInvoiceToBcSuccessData() {
        mReqInvoiceToBcSuccessDataDao.deleteAll();
    }
}
