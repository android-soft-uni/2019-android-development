package com.inveitix.templateandroidapp.data.local.database.dao;

import android.database.Cursor;
import android.text.TextUtils;

import com.inveitix.templateandroidapp.data.local.database.DatabaseHelper;
import com.inveitix.templateandroidapp.data.local.database.RecordManager;
import com.inveitix.templateandroidapp.data.local.database.records.KeyValueRecord;
import com.inveitix.templateandroidapp.domain.repositories.UserSettingsRepository;

import java.util.concurrent.TimeUnit;

import static com.inveitix.templateandroidapp.data.local.database.DatabaseHelper.ARGUMENT_MATCHER;
import static com.inveitix.templateandroidapp.data.local.database.DatabaseHelper.LIKE;

public class UserSettingsDao extends RecordManager<KeyValueRecord> implements UserSettingsRepository {

    private static final String TABLE_NAME = "USER_SETTINGS";

    final static String STRING_VALUE_TRUE = "1";
    final static String STRING_VALUE_FALSE = "0";

    public static final String COLUMN_KEY = "KEY";
    public static final String COLUMN_VALUE = "ADDRESS";

    public final static String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " ("
            + META_COLUMNS_QUERY_SUBSET
            + COLUMN_KEY + " TEXT, "
            + COLUMN_VALUE + " TEXT"
            + " );";
    private final static long DEFAULT_REMINDER_OFFSET_TIME = TimeUnit.HOURS.toMillis(7); // 7:00 AM


    private final static String KEY_NICKNAME = "user.settings.nickname";
    private final static String KEY_PIN = "user.settings.pin";
    private final static String KEY_FINGERPRINT_ENABLED = "user.settings.fingerprint_enabled";
    private final static String KEY_FINISHED_SETUP = "user.settings.finished_setup";
    private static final String KEY_LAST_INTERACTION_MILLIS = "last_interaction_millis";
    private static final String KEY_IS_APP_LOCKED = "is_app_locked";
    private static final String KEY_HAS_OPENED_DAILY_QUESTIONNAIRE = "has_daily_questionnaire_been_opened";
    private static final String KEY_HAS_OPENED_LOG_EVENT_RATINGS = "has_log_event_ratings_been_opened";
    private static final String KEY_HAS_OPENED_LOG_EVENT_FACTORS = "has_log_event_factors_been_opened";
    private static final String KEY_HAS_OPENED_LOG_EVENT_SYMPTOMS = "has_log_event_symptoms_been_opened";
    private static final String KEY_DAILY_QUISTIONNAIRE_REMINDER = "user.settings.questionnaire.reminder";
    private static final String KEY_SPIROMETER_TEST_REMINDER = "user.settings.spirometer.test.reminder";
    private static final String KEY_SHOULD_SHOW_BREEZOMETER_CONNECTION_ERROR = "user.settings.breezometer.connection_error";
    private static final String KEY_SHOULD_SHOW_BREEZOMETER_LOCATION_UNAVAILABLE_ERROR = "user.settings.breezometer.location_unavailable";
    private static final String KEY_USER_PEF_BASELINE = "user_baseline";
    private static final String KEY_USER_FEV_BASELINE = "user_fev_baseline";
    private static final String KEY_USER_ACCEPTED_EULA_PRIVACY_POLICY_DATE = "user_accepted_eula_privacy_policy_date";
    private static final String KEY_FINISHED_ONBOARDING = "user.settings.finished_onboarding";
    private static final String KEY_LAST_SYNC_TIME = "user.settings.last_sync_time";
    private static final String KEY_LAST_SYNC_TIME_BIO_PATCH = "user.settings.last_sync_time.bio_patch.";
    private static final String KEY_USER_DEVICE_ID = "user.android_device.id";

    public UserSettingsDao(DatabaseHelper databaseHelper) {
        super(databaseHelper);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public KeyValueRecord fromCursor(Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }
        return new KeyValueRecord().fromCursor(cursor);
    }

    @Override
    public void savePin(String pin) {
        insertOrUpdate(KEY_PIN, pin);
    }

    @Override
    public void saveNickname(String nickname) {
        insertOrUpdate(KEY_NICKNAME, nickname);
    }

    @Override
    public String getPin() {
        KeyValueRecord keyValue = findByKey(KEY_PIN);
        return keyValue != null ? keyValue.getValue() : null;
    }

    @Override
    public String getNickname() {
        KeyValueRecord keyValue = findByKey(KEY_NICKNAME);
        return keyValue != null ? keyValue.getValue() : null;
    }

    @Override
    public boolean isFingerprintSettingEnabled() {
        KeyValueRecord keyValue = findByKey(KEY_FINGERPRINT_ENABLED);
        return keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false;
    }

    @Override
    public void enableFingerprintSetting() {
        insertOrUpdate(KEY_FINGERPRINT_ENABLED, STRING_VALUE_TRUE);
    }

    @Override
    public void disableFingerprintSetting() {
        insertOrUpdate(KEY_FINGERPRINT_ENABLED, STRING_VALUE_FALSE);
    }

    @Override
    public boolean hasPin() {
        return getPin() != null;
    }

    @Override
    public void clearPin() {
        delete(KEY_PIN);
    }

    @Override
    public void setFinishedInitialSetup() {
        insertOrUpdate(KEY_FINISHED_SETUP, STRING_VALUE_TRUE);
    }

    @Override
    public boolean hasFinishedInitialSetup() {
        KeyValueRecord keyValue = findByKey(KEY_FINISHED_SETUP);
        return (keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false);
    }

    @Override
    public void clearFinishedInitialSetup() {
        delete(KEY_FINISHED_SETUP);
    }

    @Override
    public void setFinishedOnboarding() {
        insertOrUpdate(KEY_FINISHED_ONBOARDING, STRING_VALUE_TRUE);
    }

    @Override
    public boolean hasFinishedOnboarding() {
        KeyValueRecord keyValue = findByKey(KEY_FINISHED_ONBOARDING);
        return (keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false);
    }

    @Override
    public boolean hasOpenedDailyQuestionnaireBefore() {
        KeyValueRecord keyValue = findByKey(KEY_HAS_OPENED_DAILY_QUESTIONNAIRE);
        return keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false;
    }

    @Override
    public boolean hasOpenedLogEventRatingsBefore() {
        KeyValueRecord keyValue = findByKey(KEY_HAS_OPENED_LOG_EVENT_RATINGS);
        return keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false;
    }

    @Override
    public boolean hasOpenedLogEventFactorsBefore() {
        KeyValueRecord keyValue = findByKey(KEY_HAS_OPENED_LOG_EVENT_FACTORS);
        return keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false;
    }

    @Override
    public boolean hasOpenedLogEventSymptomsBefore() {
        KeyValueRecord keyValue = findByKey(KEY_HAS_OPENED_LOG_EVENT_SYMPTOMS);
        return keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false;
    }

    @Override
    public double getUserPefBaseline() {
        KeyValueRecord keyValue = findByKey(KEY_USER_PEF_BASELINE);
        return keyValue != null ? Double.parseDouble(keyValue.getValue()) : -1;
    }

    @Override
    public void markLogEventSymptomsOpened() {
        insertOrUpdate(KEY_HAS_OPENED_LOG_EVENT_SYMPTOMS, STRING_VALUE_TRUE);
    }

    @Override
    public void saveDailyQuestionnaireReminder(int hourOfDay, int minute) {
        insertOrUpdate(KEY_DAILY_QUISTIONNAIRE_REMINDER, String.valueOf(TimeUnit.HOURS.toMillis(hourOfDay) + TimeUnit.MINUTES.toMillis(minute)));
    }

    @Override
    public void saveSpirometerTestReminder(int hourOfDay, int minute) {
        insertOrUpdate(KEY_SPIROMETER_TEST_REMINDER, String.valueOf(TimeUnit.HOURS.toMillis(hourOfDay) + TimeUnit.MINUTES.toMillis(minute)));
    }

    @Override
    public long getDailyQuestionnaireReminder() {
        KeyValueRecord keyValue = findByKey(KEY_DAILY_QUISTIONNAIRE_REMINDER);
        return keyValue != null ? Long.valueOf(keyValue.getValue()) : DEFAULT_REMINDER_OFFSET_TIME;
    }

    @Override
    public long getSpirometerTestReminder() {
        KeyValueRecord keyValue = findByKey(KEY_SPIROMETER_TEST_REMINDER);
        return keyValue != null ? Long.valueOf(keyValue.getValue()) : DEFAULT_REMINDER_OFFSET_TIME;
    }

    @Override
    public long getFinishedInitialSetupTime() {
        KeyValueRecord keyValue = findByKey(KEY_FINISHED_SETUP);
        return keyValue != null ? keyValue.getEditedOn() : System.currentTimeMillis();
    }

    @Override
    public void setShowBreezometerConnectionError(boolean enable) {
        insertOrUpdate(KEY_SHOULD_SHOW_BREEZOMETER_CONNECTION_ERROR, enable ? STRING_VALUE_TRUE : STRING_VALUE_FALSE);
    }

    @Override
    public void setShowBreezometerLocationError(boolean enable) {
        insertOrUpdate(KEY_SHOULD_SHOW_BREEZOMETER_LOCATION_UNAVAILABLE_ERROR, enable ? STRING_VALUE_TRUE : STRING_VALUE_FALSE);
    }

    @Override
    public boolean shouldShowBreezometerLocationError() {
        KeyValueRecord keyValue = findByKey(KEY_SHOULD_SHOW_BREEZOMETER_LOCATION_UNAVAILABLE_ERROR);
        return keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false;
    }

    @Override
    public boolean shouldShowBreezometerConnectionError() {
        KeyValueRecord keyValue = findByKey(KEY_SHOULD_SHOW_BREEZOMETER_CONNECTION_ERROR);
        return keyValue != null ? STRING_VALUE_TRUE.equalsIgnoreCase(keyValue.getValue()) : false;
    }

    @Override
    public void saveUserPefBaseline(double baseline) {
        insertOrUpdate(KEY_USER_PEF_BASELINE, String.valueOf(baseline));
    }

    @Override
    public double getUserFevBaseline() {
        KeyValueRecord keyValue = findByKey(KEY_USER_FEV_BASELINE);
        return keyValue != null ? Double.parseDouble(keyValue.getValue()) : -1;
    }

    @Override
    public void saveUserFevBaseline(double baseline) {
        insertOrUpdate(KEY_USER_FEV_BASELINE, String.valueOf(baseline));
    }

    @Override
    public void markLogEventFactorsOpened() {
        insertOrUpdate(KEY_HAS_OPENED_LOG_EVENT_FACTORS, STRING_VALUE_TRUE);
    }

    @Override
    public void markDailyQuestionnaireOpened() {
        insertOrUpdate(KEY_HAS_OPENED_DAILY_QUESTIONNAIRE, STRING_VALUE_TRUE);
    }

    @Override
    public void markLogEventRatingOpened() {
        insertOrUpdate(KEY_HAS_OPENED_LOG_EVENT_RATINGS, STRING_VALUE_TRUE);
    }

    @Override
    public void saveUserAcceptedEulaAndPrivacyPolicyMillis() {
        insertOrUpdate(KEY_USER_ACCEPTED_EULA_PRIVACY_POLICY_DATE, String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public Long getUserAcceptedEulaAndPrivacyPolicyMillis() {
        KeyValueRecord keyValue = findByKey(KEY_USER_ACCEPTED_EULA_PRIVACY_POLICY_DATE);
        return keyValue != null ? Long.valueOf(keyValue.getValue()) : null;
    }

    @Override
    public void saveLastSyncTimeInMillis() {
        insertOrUpdate(KEY_LAST_SYNC_TIME, String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public long getLastSyncTimeInMillis() {
        KeyValueRecord keyValue = findByKey(KEY_LAST_SYNC_TIME);
        if (keyValue != null)
            return Long.valueOf(keyValue.getValue());
        else {
            Long userAcceptedEulaAndPrivacyPolicyMillis = getUserAcceptedEulaAndPrivacyPolicyMillis();
            return userAcceptedEulaAndPrivacyPolicyMillis == null ? 0 : userAcceptedEulaAndPrivacyPolicyMillis;
        }
    }

    @Override
    public String getAndroidDeviceDmdpId() {
        KeyValueRecord keyValue = findByKey(KEY_USER_DEVICE_ID);
        return keyValue != null ? keyValue.getValue() : null;
    }

    @Override
    public void saveAndroidDeviceAndroidId(String id) {
        insertOrUpdate(KEY_USER_DEVICE_ID, id);
    }


    //Low-level DV access

    public void insertOrUpdate(String key, String value) {
        KeyValueRecord setting = findByKey(key);
        if (setting != null && !TextUtils.isEmpty(setting.getValue())) {
            update(setting, value);
        } else {
            insert(key, value);
        }
    }

    private void insert(String key, String value) {
        KeyValueRecord keyValueModel = new KeyValueRecord();
        keyValueModel.setValue(value);
        keyValueModel.setKey(key);
        super.insert(keyValueModel);
    }

    private KeyValueRecord findByKey(String type) {
        Cursor cursor = query(null, COLUMN_KEY + ARGUMENT_MATCHER, new String[]{type}, null, null, null, null);
        KeyValueRecord record = fromCursor(cursor);
        if (cursor != null) {
            cursor.close();
        }
        return record;
    }

    public void update(KeyValueRecord keyValueModel, String newValue) {
        if (keyValueModel != null) {
            keyValueModel.setValue(newValue);
            super.update(keyValueModel);
        } else {
            // TODO or just log
            throw new IllegalArgumentException("Cannot update a device record when it does not exist for type: ");
        }
    }

    public void delete(String key) {
        super.delete(COLUMN_KEY + LIKE + "'%" + key + "%'", null);
    }

}
